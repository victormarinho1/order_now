package com.order_now.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> findByRestaurant(Long id){
        return this.productRepository.findByRestaurantId(id);
    }

    public List<Product> findByCategory(Long id){
        return this.findByCategory(id);
    }




}
