package com.order_now.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/restaurants")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @GetMapping("{restaurant_id}/products")
//    public ResponseEntity<List<ProductDTO>> findAll(@PathVariable Long restaurant_id){
//        List<Product> list = this.productService.findByRestaurant(restaurant_id);
//        List<ProductDTO> listDto = new ArrayList<>();
//        for(Product p : list){
//            listDto.add(p.toDto());
//        }
//        return ResponseEntity.ok(listDto);
//    }
}
