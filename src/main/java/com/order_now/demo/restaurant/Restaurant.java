package com.order_now.demo.restaurant;

import com.order_now.demo.category.Category;
import com.order_now.demo.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String url_img;

    @OneToMany(mappedBy = "restaurant")
    private List<Category> categories;

    @Column(nullable = false)
    private Boolean enabled = true;

    public RestaurantDTO toDto(){
        return new RestaurantDTO(this.id,this.name,this.phone,this.email,this.url_img,this.enabled);
    }

    public Restaurant(String name, String phone, String email, String url_img) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.url_img = url_img;
        this.enabled = true;
    }

}