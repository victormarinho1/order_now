package com.order_now.demo.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private Boolean enabled;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public CategoryDTO toDto(){
        return new CategoryDTO(this.name);
    }

    public CategoryListDTO toListDto(){
        return new CategoryListDTO(this.id,this.name,this.enabled);
    }


    public Category(String name) {
        this.name = name;
    }
    public Category(Restaurant restaurant,CategoryDTO dto){
        this.name = dto.name();
        this.restaurant = restaurant;
    }
}
