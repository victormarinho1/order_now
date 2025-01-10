package com.order_now.demo.category;

import com.order_now.demo.core.exception.category.CategoryNameAlreadyTakenException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryValidator {

    @Autowired
    private CategoryRepository categoryRepository;

    public void isValid(Category category){
        isNameAlreadyTaken(category.getName(),category.getRestaurant().getId());
    }

    public void isNameAlreadyTaken(String name, Long restaurant_id){
        Optional<Category> category = this.categoryRepository.findByRestaurantIdAndName(restaurant_id, name);
        if(category.isPresent()){
            throw new CategoryNameAlreadyTakenException();
        }
    }
}
