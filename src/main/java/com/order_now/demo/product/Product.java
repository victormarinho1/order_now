package com.order_now.demo.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.order_now.demo.category.Category;
import com.order_now.demo.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;


    public ProductDTO toDto(){
        return new ProductDTO(this.name,this.description,this.price, this.category.getName());
    }

    public Product(String name, String description, Double price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}
