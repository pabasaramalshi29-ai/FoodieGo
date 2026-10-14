package edu.cinec.foodiego.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartItem implements Serializable {
    private FoodItem foodItem;
    private int quantity;
    private List<String> selectedExtras;
    private double extrasPrice;
    private String specialInstructions;

    public CartItem(FoodItem foodItem, int quantity, List<String> selectedExtras, double extrasPrice, String specialInstructions) {
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.selectedExtras = selectedExtras != null ? selectedExtras : new ArrayList<>();
        this.extrasPrice = extrasPrice;
        this.specialInstructions = specialInstructions != null ? specialInstructions : "";
    }

    public FoodItem getFoodItem() { return foodItem; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public List<String> getSelectedExtras() { return selectedExtras; }
    public double getExtrasPrice() { return extrasPrice; }
    public String getSpecialInstructions() { return specialInstructions; }

    public double getUnitPrice() {
        return foodItem.getPrice() + extrasPrice;
    }

    public double getTotalPrice() {
        return getUnitPrice() * quantity;
    }

    public String getExtrasFormatted() {
        if (selectedExtras.isEmpty()) {
            return "Standard preparation";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < selectedExtras.size(); i++) {
            sb.append(selectedExtras.get(i));
            if (i < selectedExtras.size() - 1) {
                sb.append(" • ");
            }
        }
        return sb.toString();
    }
}
