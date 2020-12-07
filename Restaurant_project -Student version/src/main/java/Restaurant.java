import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {

        if((getCurrentTime().isAfter(openingTime)) && getCurrentTime().isBefore(closingTime)){  //checks if the user entry time lies within the operating time of the selected Restaurant.
            return true;
        }
        return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return this.menu;
    }

    private Item findItemByName(String itemName){ //searches item in menu object.
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName + "restaurant is not present in the list");

        menu.remove(itemToBeRemoved);
    }


    public void displayDetails(){ System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    //This method will implement Order Value feature, for selected items from menu by user, this method will calculate and return the total order value.
    public Double getTotalOrderValue(List<Item> selectedItems) {  // getTotalOrderValue: method with just arguments and a dummy return value, to be implemented correctly later.

        double price = 0;
        for(int i = 0 ; i < selectedItems.size() ; i++)
            price += findItemByName(selectedItems.get(i).getName()).getPrice();
        return price;
    }

}
