package com.order_now.demo.menu;

import com.order_now.demo.category.Category;
import com.order_now.demo.category.CategoryService;
import com.order_now.demo.product.ProductService;
import com.order_now.demo.restaurant.Restaurant;
import com.order_now.demo.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Category> showMenu(Long id){
        Restaurant restaurant = this.restaurantService.findById(id);
        List<Category> categories = this.categoryService.findByRestaurant(restaurant.getId());
        List<Menu> listMenu = new ArrayList<>();
        for(Category c: categories){
          listMenu.add(new Menu(restaurant,c.getProducts(),c));
        }
        return categories;
    }
}
