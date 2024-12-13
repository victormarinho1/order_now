package com.order_now.demo.menu;

import com.order_now.demo.category.Category;
import com.order_now.demo.category.CategoryService;
import com.order_now.demo.product.Product;
import com.order_now.demo.product.ProductService;
import com.order_now.demo.restaurant.Restaurant;
import com.order_now.demo.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    public List<MenuDTO> showMenu(Long id){
        Restaurant restaurant = this.restaurantService.findById(id);
        List<Category> categories = this.categoryService.findByRestaurant(restaurant.getId());
        List<MenuDTO> listDto = new ArrayList<>();
        for(Category c: categories){
           List<Product> products = this.productService.findByCategory(c.getId());
           MenuDTO dto =  new MenuDTO(products);
           listDto.add(dto);
        }
        return listDto;
    }
}
