import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        Restaurant foundRestaurant = null;

        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName() == restaurantName) {
                foundRestaurant = restaurant;
                break;
            }
        }

        if(foundRestaurant == null){
            throw new restaurantNotFoundException(restaurantName);
        }

        return foundRestaurant;
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public int calculateOrderValue(String[] items) {
        int currentOrderValue = 0, currentItemCost ;

        for(String item : items){
            String[] currentItems = item.split(":", 2);
            currentItemCost = Integer.parseInt(currentItems[1].substring(0, currentItems[1].length()-1));
            currentOrderValue += currentItemCost;
        }

        return currentOrderValue;
    }
}
