package com.order_now.demo.menu;

import com.order_now.demo.category.Category;
import com.order_now.demo.restaurant.Restaurant;
import com.order_now.demo.restaurant.RestaurantService;
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
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;



    @GetMapping("/{restaurant_id}/menu")
    public ResponseEntity<MenuDTO> findAll(@PathVariable Long restaurant_id){

        List<Category>  listDto = this.menuService.showMenu(restaurant_id);
        List<MenuItemDTO> menuDTOList = new ArrayList<>();
        Restaurant restaurant = this.restaurantService.findById(restaurant_id);
        for(Category c : listDto){
            menuDTOList.add(new MenuItemDTO(c));
        }
        MenuDTO menu = new MenuDTO(restaurant.getName(),menuDTOList);
        return ResponseEntity.ok(menu);
    }
}
