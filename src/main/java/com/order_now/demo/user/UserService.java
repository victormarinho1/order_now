package com.order_now.demo.user;

import com.order_now.demo.core.Role;
import com.order_now.demo.core.authentication.AuthenticationService;
import com.order_now.demo.core.authentication.RegisterDTO;
import com.order_now.demo.core.exception.user.UserNotFoundException;
import com.order_now.demo.email_verificator.EmailVerificatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private EmailVerificatorService emailVerificatorService;

    @Autowired
    private AuthenticationService authenticationService;

    public List<UserDTO> findAll(){
        List<User> clientsList = userRepository.findAll();
        List<UserDTO> clientsDTOList = new ArrayList();
        for (User user : clientsList) {
            clientsDTOList.add(convertToDTO(user));
        }
        return clientsDTOList;
    }

    public UserDTO findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return convertToDTO(user);
    }

    public UserDTO findByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return convertToDTO(user);
    }

    public UserDTO create(User user) {
        userValidator.checkEmailExists(user.getEmail());
        userValidator.checkEmailValidity(user.getEmail());
        this.emailVerificatorService.create(user.getEmail());
        user.setRole(Role.BOSS);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return convertToDTO(userRepository.save(user));
    }

    public User create(RegisterDTO registerDTO) {
        User user = new User(registerDTO.email(),registerDTO.name(),registerDTO.password(),Role.BOSS);
        this.emailVerificatorService.create(user.getEmail());
        return this.userRepository.save(user);
    }

    public UserDTO update(Long id, User user){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){

            userValidator.checkEmailExists(user.getEmail());
            userValidator.checkEmailValidity(user.getEmail());
            updateData(user, user);
            return convertToDTO(userRepository.save(user));
        }
        throw new UserNotFoundException();
    }

    public User updatePassword(Long id, String password, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(id);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                return userRepository.save(user);
            } else {
                throw new RuntimeException("Senha atual não confere.");
            }
        } else {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }

    public UserDTO getCurrentUser(){
        User user =  this.authenticationService.getCurrentUser();
        return convertToDTO(user);
    }

    public void delete(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }

    public UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole().toString(), user.getEnabled());
    }

    public void updateData(User user, User newUser){
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
    }


}