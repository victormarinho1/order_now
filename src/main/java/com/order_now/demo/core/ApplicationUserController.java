package com.order_now.demo.core;


import com.order_now.demo.core.authentication.EmailResponseDTO;
import com.order_now.demo.core.authentication.LoginDTO;
import com.order_now.demo.core.authentication.LoginResponseDTO;
import com.order_now.demo.core.authentication.RegisterDTO;
import com.order_now.demo.core.config.TokenService;
import com.order_now.demo.core.listener.EmailSentEventDTO;
import com.order_now.demo.user.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.order_now.demo.core.config.RabbitMqConfig.EMAIL_SENT_QUEUE;
import static com.order_now.demo.core.config.RabbitMqConfig.EMAIL_SENT_REGISTER_QUEUE;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class ApplicationUserController{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ApplicationUserService applicationUserService;

    @Autowired
    private final RabbitTemplate rabbitTemplate;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(),loginDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        rabbitTemplate.convertAndSend(EMAIL_SENT_REGISTER_QUEUE,registerDTO);
        EmailResponseDTO response = new EmailResponseDTO(
                "Verify email sent.",
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestBody @Valid EmailSentEventDTO email){
        rabbitTemplate.convertAndSend(EMAIL_SENT_QUEUE, email);
        EmailResponseDTO response = new EmailResponseDTO(
                "Password reset email sent.",
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                LocalDateTime.now()
        );
        return  ResponseEntity.ok(response);
    }
}