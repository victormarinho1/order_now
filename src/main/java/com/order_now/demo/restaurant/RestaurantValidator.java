package com.order_now.demo.restaurant;

import com.order_now.demo.core.exception.EmailAlreadyTakenException;
import com.order_now.demo.core.exception.PhoneAlreadyTakenException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantValidator {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void isValid(Restaurant restaurant){
        isEmailAlreadyTaken(restaurant.getEmail());
        isPhoneAlreadyTaken(restaurant.getPhone());
    }

    public void isEmailAlreadyTaken(String email){
        if(restaurantRepository.existsByEmail(email)){
            throw new EmailAlreadyTakenException();
        }
    }

    public void isPhoneAlreadyTaken(String phone){
        if(restaurantRepository.existsByPhone(phone)){
            throw new PhoneAlreadyTakenException();
        }
    }


}
