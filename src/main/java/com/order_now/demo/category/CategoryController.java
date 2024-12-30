package com.order_now.demo.category;

import com.order_now.demo.restaurant.Restaurant;
import com.order_now.demo.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/restaurants")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/{restaurant_id}/categories")
    private ResponseEntity<List<CategoryListDTO>> findAll(@PathVariable Long restaurant_id){
        List<Category> list = this.categoryService.findByRestaurant(restaurant_id);
        List<CategoryListDTO> listDto = new ArrayList<>();
        for(Category c :list){
            listDto.add(c.toListDto());
        }
       return ResponseEntity.ok(listDto);
    }

    @PostMapping("/{restaurant_id}/categories")
    private ResponseEntity create(@PathVariable Long restaurant_id, @RequestBody CategoryDTO categoryDTO){
        Restaurant restaurant = this.restaurantService.findById(restaurant_id);
        int nextIndex = this.categoryService.findByRestaurant(restaurant_id).size() + 1;
        Category category = new Category(restaurant,categoryDTO, nextIndex);
        this.categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/categories/change_status/{category_id}")
    private ResponseEntity change_status(@PathVariable Long category_id){
        this.categoryService.change_status(category_id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/categories/{category_id}")
    private ResponseEntity update(@PathVariable Long category_id, @RequestBody CategoryDTO categoryDTO){
        this.categoryService.update(category_id,categoryDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/categories/{category_id}")
    private ResponseEntity delete(@PathVariable Long category_id){
        this.categoryService.change_status(category_id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
