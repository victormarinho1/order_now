package com.order_now.demo.category;

import com.order_now.demo.core.exception.category.CategoryNameAlreadyTakenException;
import com.order_now.demo.core.exception.category.CategoryNotFoundException;
import com.order_now.demo.restaurant.RestaurantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryValidator categoryValidator;

    public void create(Category category){
        this.categoryValidator.isValid(category);
        this.categoryRepository.save(category);
    }

    public List<Category> findByRestaurant(Long id){
        return this.categoryRepository.findByRestaurantId(id);
    }

    public Category change_status(Long id){
        Optional<Category> category = this.categoryRepository.findById(id);
        if (category.isPresent()){
        category.get().setEnabled(!category.get().getEnabled());
        return this.categoryRepository.save(category.get());
        }
        throw new CategoryNotFoundException();
    }

    public Category update(Long id, CategoryDTO dto){
        Optional<Category> category = this.categoryRepository.findById(id);
        if(category.isPresent()){
         return this.categoryRepository.save(updateData(dto,category.get()));
        }
        throw new CategoryNotFoundException();
    }

    public Category updateData(CategoryDTO dto, Category category){
        category.setName(dto.name());
        category.setIndex(dto.index());
        return category;
    }
}
