package com.order_now.demo.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    private ResponseEntity<List<RestaurantDTO>> findAll(){
        List<Restaurant> list = this.restaurantService.findAll();
        List<RestaurantDTO> listDto = new ArrayList<>();
        for(Restaurant r : list){
            listDto.add(r.toDto());
        }
        return ResponseEntity.ok(listDto);
    }

    @PostMapping
    private ResponseEntity<Restaurant> create(@RequestBody RestaurantDTO restaurantDTO){
        this.restaurantService.create(restaurantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    private ResponseEntity<Restaurant> update(@PathVariable Long id, @RequestBody RestaurantDTO restaurantDTO){
        this.restaurantService.update(id, restaurantDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id){
        this.restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

