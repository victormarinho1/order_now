package com.order_now.demo.category;

import com.order_now.demo.restaurant.Restaurant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;


    @Test
    public void returnCategoryList(){
        Restaurant restaurant = new Restaurant();
        CategoryDTO dto = new CategoryDTO("Bebidas",0);
        Category category = new Category(restaurant,dto,0);
        Mockito.when(categoryRepository.findByRestaurantId(1L)).thenReturn(Collections.singletonList(category));
        List<Category> categoryList = this.categoryService.findByRestaurant(1L);

        Assertions.assertEquals(1,categoryList.size());
    }
}