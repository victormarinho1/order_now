package com.order_now.demo.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/restaurants")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/{restaurant_id}/categories")
    private ResponseEntity<List<CategoryDTO>> findAll(@PathVariable Long restaurant_id){
        List<Category> list = this.categoryService.findByRestaurant(restaurant_id);
        List<CategoryDTO> listDto = new ArrayList<>();
        for(Category c :list){
            listDto.add(c.toDto());
        }
       return ResponseEntity.ok(listDto);
    }
}
