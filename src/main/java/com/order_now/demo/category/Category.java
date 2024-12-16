package com.order_now.demo.category;

import com.order_now.demo.product.Product;
import com.order_now.demo.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public CategoryDTO toDto(){
        return new CategoryDTO(this.name);
    }


    public Category(String name) {
        this.name = name;
    }
}
