package com.order_now.demo.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void create(CategoryDTO categoryDTO){
        Category category = new Category(categoryDTO.name());
        this.categoryRepository.save(category);
    }

    public List<Category> findByRestaurant(Long id){
        return this.categoryRepository.findByRestaurantId(id);
    }
}
