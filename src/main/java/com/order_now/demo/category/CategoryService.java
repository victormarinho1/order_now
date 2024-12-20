package com.order_now.demo.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void create(Category category){
        this.categoryRepository.save(category);
    }

    public List<Category> findByRestaurant(Long id){
        return this.categoryRepository.findByRestaurantId(id);
    }

    public void change_status(Long id){
        Optional<Category> category = this.categoryRepository.findById(id);
        category.get().setEnabled(!category.get().getEnabled());
        this.categoryRepository.save(category.get());
    }

    public void update(Long id, CategoryDTO dto){
        Category category = this.findBy
    }
}
