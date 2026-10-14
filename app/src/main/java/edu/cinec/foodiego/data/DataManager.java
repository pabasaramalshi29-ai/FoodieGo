package edu.cinec.foodiego.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.cinec.foodiego.R;
import edu.cinec.foodiego.model.CartItem;
import edu.cinec.foodiego.model.FoodItem;
import edu.cinec.foodiego.model.Restaurant;

public class DataManager {
    private static DataManager instance;

    private List<FoodItem> foodItems;
    private List<Restaurant> restaurants;
    private List<CartItem> cartItems;
    private String deliveryAddress = "742 Evergreen Terrace, Apt 4B";
    private String deliverySpeed = "Instant";
    private double deliveryFee = 1.99;
    private double discount = 5.00;

    // User Profile Information
    private String userName = "Alex Rider";
    private String userEmail = "alex.rider@example.com";
    private String userPhone = "+1 (555) 019-2834";

    private DataManager() {
        initData();
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    private void initData() {
        foodItems = new ArrayList<>();
        // Item 1: Gourmet Double Smash Burger
        foodItems.add(new FoodItem(
                "item_1",
                "Double Smash Burger",
                "Craft & Patty Bistro",
                "Two fresh smashed 100% Angus beef patties layered with melted American cheddar, crisp butter lettuce, vine-ripened tomatoes, and house secret truffle aioli on a toasted brioche bun.",
                12.99,
                4.8,
                "1.2k",
                "20-25 min",
                "650 kcal",
                R.drawable.img_burger,
                "Burgers",
                true
        ));

        // Item 2: Neapolitan Artisanal Pepperoni Pizza
        foodItems.add(new FoodItem(
                "item_2",
                "Neapolitan Pepperoni",
                "Bella Napoli Pizzeria",
                "Wood-fired crust made with fermented sourdough, San Marzano tomato sauce, fior di latte mozzarella, artisanal pepperoni slices, and fresh basil leaves.",
                16.50,
                4.9,
                "850",
                "25-30 min",
                "820 kcal",
                R.drawable.img_pizza,
                "Pizza",
                true
        ));

        // Item 3: Fresh Salmon Avocado Poke Bowl
        foodItems.add(new FoodItem(
                "item_3",
                "Salmon Poke Bowl",
                "Ono Island Poke",
                "Wild sushi-grade Atlantic salmon cubes tossed in sesame ponzu, over sushi rice, hass avocado, edamame beans, cucumber ribbons, and furikake seasoning.",
                14.20,
                4.7,
                "620",
                "15-20 min",
                "510 kcal",
                R.drawable.img_poke,
                "Bowls",
                true
        ));

        // Item 4: Truffle Crinkle Fries
        foodItems.add(new FoodItem(
                "item_4",
                "Truffle Crinkle Fries",
                "Craft & Patty Bistro",
                "Golden crisp crinkle cut french fries dusted with fragrant black truffle zest, shaved parmesan, and fresh parsley flakes in an appetizing ceramic bowl.",
                4.99,
                4.9,
                "430",
                "15-20 min",
                "380 kcal",
                R.drawable.img_fries,
                "Burgers",
                false
        ));

        // Nearby Restaurants
        restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(
                "BiteCraft Burgers & Shakes",
                "Burgers • American • Craft Fries",
                "1.2 km",
                4.8,
                "25 mins",
                "Free delivery >$20",
                R.drawable.img_burger
        ));

        restaurants.add(new Restaurant(
                "Bella Napoli Pizzeria",
                "Italian • Wood Fired • Pasta",
                "2.4 km",
                4.9,
                "30 mins",
                "$1.99 Delivery",
                R.drawable.img_pizza
        ));

        restaurants.add(new Restaurant(
                "Ono Island Poke",
                "Hawaiian • Fresh Fish • Healthy Bowls",
                "0.8 km",
                4.7,
                "15 mins",
                "Free delivery",
                R.drawable.img_poke
        ));

        // Initial Cart Items matching Stitch Cart screen
        cartItems = new ArrayList<>();
        cartItems.add(new CartItem(
                foodItems.get(0),
                1,
                Arrays.asList("Extra Cheddar", "Brioche Bun"),
                1.50,
                ""
        ));
        cartItems.add(new CartItem(
                foodItems.get(1),
                1,
                Arrays.asList("Spicy Honey Drizzle", "Fresh Basil"),
                0.00,
                ""
        ));
        cartItems.add(new CartItem(
                foodItems.get(3),
                1,
                Arrays.asList("Truffle Aioli Dip"),
                0.00,
                ""
        ));
    }

    public List<FoodItem> getFoodItems() { return foodItems; }
    public List<Restaurant> getRestaurants() { return restaurants; }
    public List<CartItem> getCartItems() { return cartItems; }

    public FoodItem getFoodItemById(String id) {
        for (FoodItem item : foodItems) {
            if (item.getId().equals(id)) return item;
        }
        return foodItems.get(0);
    }

    public void addToCart(CartItem item) {
        // Check if item already exists
        for (CartItem ci : cartItems) {
            if (ci.getFoodItem().getId().equals(item.getFoodItem().getId())
                    && ci.getExtrasFormatted().equals(item.getExtrasFormatted())) {
                ci.setQuantity(ci.getQuantity() + item.getQuantity());
                return;
            }
        }
        cartItems.add(item);
    }

    public void removeCartItem(int index) {
        if (index >= 0 && index < cartItems.size()) {
            cartItems.remove(index);
        }
    }

    public void updateCartItemQuantity(int index, int quantity) {
        if (index >= 0 && index < cartItems.size()) {
            if (quantity <= 0) {
                cartItems.remove(index);
            } else {
                cartItems.get(index).setQuantity(quantity);
            }
        }
    }

    public void clearCart() {
        cartItems.clear();
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (CartItem item : cartItems) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }

    public double getDeliveryFee() {
        return cartItems.isEmpty() ? 0 : deliveryFee;
    }

    public void setDeliveryFee(double fee) {
        this.deliveryFee = fee;
    }

    public double getTax() {
        return cartItems.isEmpty() ? 0 : getSubtotal() * 0.08; // 8% tax
    }

    public double getDiscount() {
        return cartItems.isEmpty() ? 0 : discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalAmount() {
        if (cartItems.isEmpty()) return 0;
        double total = getSubtotal() + getDeliveryFee() + getTax() - getDiscount();
        return Math.max(0, total);
    }

    public int getCartCount() {
        int count = 0;
        for (CartItem item : cartItems) {
            count += item.getQuantity();
        }
        return count;
    }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String address) { this.deliveryAddress = address; }

    public String getUserName() { return userName; }
    public void setUserName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.userName = name.trim();
        }
    }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String email) {
        if (email != null && !email.trim().isEmpty()) {
            this.userEmail = email.trim();
        }
    }

    public String getUserPhone() { return userPhone; }
    public void setUserPhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            this.userPhone = phone.trim();
        }
    }

    public void setUserProfile(String name, String email, String phone) {
        setUserName(name);
        setUserEmail(email);
        setUserPhone(phone);
    }
}
