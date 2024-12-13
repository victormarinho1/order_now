package com.order_now.demo.restaurant;

import com.order_now.demo.core.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantValidator restaurantValidator;


    public List<Restaurant> findAll(){
        return this.restaurantRepository.findAllByOrderByIdAsc();
    }

    public Restaurant findById(Long id){
        Optional<Restaurant> optRestaurant = this.restaurantRepository.findById(id);
        if(optRestaurant.isPresent()){
            return optRestaurant.get();
        }
        throw new RestaurantNotFoundException();
    }

    public void create(RestaurantDTO restaurantDTO){
        Restaurant restaurant = new Restaurant(restaurantDTO.name(),restaurantDTO.phone(),
                restaurantDTO.email(),restaurantDTO.url_img());

        restaurantValidator.isValid(restaurant);
        this.restaurantRepository.save(restaurant);
    }

    public void update(Long id, RestaurantDTO restaurantDTO){
        Optional<Restaurant> optRestaurant = this.restaurantRepository.findById(id);
        if(optRestaurant.isPresent()){
            updateData(optRestaurant.get(),restaurantDTO);
            restaurantValidator.isValid(optRestaurant.get());
            this.restaurantRepository.save(optRestaurant.get());
        }
    }

    public void delete(Long id){
        Optional<Restaurant> optRestaurant = this.restaurantRepository.findById(id);
        if(optRestaurant.isPresent()){
            restaurantRepository.delete(optRestaurant.get().getId());
        }
    }

    private void updateData(Restaurant restaurant,RestaurantDTO restaurantDTO){
        restaurant.setName(restaurantDTO.name());
        restaurant.setEmail(restaurantDTO.email());
        restaurant.setPhone(restaurantDTO.phone());
        restaurant.setUrl_img(restaurantDTO.url_img());
    }
}
