package edu.cinec.foodiego.model;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private String name;
    private String cuisine;
    private String distance;
    private double rating;
    private String deliveryTime;
    private String deliveryFee;
    private int imageResId;

    public Restaurant(String name, String cuisine, String distance, double rating,
                      String deliveryTime, String deliveryFee, int imageResId) {
        this.name = name;
        this.cuisine = cuisine;
        this.distance = distance;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.deliveryFee = deliveryFee;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public String getCuisine() { return cuisine; }
    public String getDistance() { return distance; }
    public double getRating() { return rating; }
    public String getDeliveryTime() { return deliveryTime; }
    public String getDeliveryFee() { return deliveryFee; }
    public int getImageResId() { return imageResId; }
}
