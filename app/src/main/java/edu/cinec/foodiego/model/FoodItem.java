package edu.cinec.foodiego.model;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private String id;
    private String name;
    private String restaurantName;
    private String description;
    private double price;
    private double rating;
    private String reviewCount;
    private String deliveryTime;
    private String calories;
    private int imageResId;
    private String category;
    private boolean isPopular;
    private boolean isFavorite;

    public FoodItem(String id, String name, String restaurantName, String description,
                    double price, double rating, String reviewCount, String deliveryTime,
                    String calories, int imageResId, String category, boolean isPopular) {
        this.id = id;
        this.name = name;
        this.restaurantName = restaurantName;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.deliveryTime = deliveryTime;
        this.calories = calories;
        this.imageResId = imageResId;
        this.category = category;
        this.isPopular = isPopular;
        this.isFavorite = false;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getRestaurantName() { return restaurantName; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public String getReviewCount() { return reviewCount; }
    public String getDeliveryTime() { return deliveryTime; }
    public String getCalories() { return calories; }
    public int getImageResId() { return imageResId; }
    public String getCategory() { return category; }
    public boolean isPopular() { return isPopular; }
    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
}
