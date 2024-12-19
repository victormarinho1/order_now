package com.order_now.demo.menu;

import com.order_now.demo.category.Category;
import com.order_now.demo.product.Product;
import com.order_now.demo.product.ProductDTO;
import com.order_now.demo.product.ProductService;
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

    @GetMapping("/{restaurant_id}/menu")
    public ResponseEntity<List<MenuDTO>> findAll(@PathVariable Long restaurant_id){

        List<Category>  listDto = this.menuService.showMenu(restaurant_id);
        List<MenuDTO> menuDTOList = new ArrayList<>();

        for(Category c : listDto){
            menuDTOList.add(new MenuDTO(c.getName(),c.getProducts()));
        }
        return ResponseEntity.ok(menuDTOList);
    }
}
