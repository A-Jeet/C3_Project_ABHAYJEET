import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import sun.java2d.loops.BlitBg;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class RestaurantTest {

    Restaurant restaurant;

    @BeforeEach
    void setup(){                                               // setting up a sample restaurant and it's menu.
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {


        LocalTime userEntryTime = LocalTime.parse("12:20:00");
        Restaurant restaurantSelectedByUser = Mockito.spy(restaurant);
        Mockito.when(restaurantSelectedByUser.getCurrentTime()).thenReturn(userEntryTime);

        boolean isRestaurantAllowingEntryNow = restaurantSelectedByUser.isRestaurantOpen();

        assertEquals(true,isRestaurantAllowingEntryNow);


    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){


        LocalTime userEntryTime = LocalTime.parse("09:30:00");

        Restaurant restaurantSelectedByUser = Mockito.spy(restaurant);
        Mockito.when(restaurantSelectedByUser.getCurrentTime()).thenReturn(userEntryTime);
        boolean isRestaurantAllowingEntryNow = restaurantSelectedByUser.isRestaurantOpen();

        assertEquals(false,isRestaurantAllowingEntryNow);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){


        int initialMenuSize = restaurant.getMenu().size();

        restaurant.addToMenu("Sizzling brownie",319);

        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {


        int initialMenuSize = restaurant.getMenu().size();

        restaurant.removeFromMenu("Vegetable lasagne");

        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //<<<<<<<<<<<<<<<<<<<<<<<<<ORDER VALUE OF SELECTED ITEMS FROM MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void selected_items_from_a_particular_restaurant_must_return_the_total_order_amount()
    {

        Item firstSelectedItem = new Item("Sweet corn soup",119);          //creating item objects with items already present in setup
        Item secondSelectedItem = new Item("Vegetable lasagne", 269);
        List<Item> selectedItems = new ArrayList<>();
        selectedItems.add(firstSelectedItem);
        selectedItems.add(secondSelectedItem);
        double expectedOrderValue = firstSelectedItem.getPrice() + secondSelectedItem.getPrice();

        double calculatedOrderValue = restaurant.getTotalOrderValue(selectedItems);  // getTotalOrderValue: method with just definition and a dummy return value.

        assertEquals(expectedOrderValue, calculatedOrderValue);  // getTotalOrderValue: method to be implemented correctly later.
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<ORDER VALUE OF SELECTED ITEMS FROM MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}