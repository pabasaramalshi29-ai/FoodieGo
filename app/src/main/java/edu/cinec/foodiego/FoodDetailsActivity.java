package edu.cinec.foodiego;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.cinec.foodiego.data.DataManager;
import edu.cinec.foodiego.databinding.ActivityFoodDetailsBinding;
import edu.cinec.foodiego.model.CartItem;
import edu.cinec.foodiego.model.FoodItem;

public class FoodDetailsActivity extends AppCompatActivity {

    private ActivityFoodDetailsBinding binding;
    private FoodItem foodItem;
    private int quantity = 1;

    private static final double EXTRA_CHEDDAR_PRICE = 1.50;
    private static final double EXTRA_BACON_PRICE = 2.00;
    private static final double EXTRA_ONIONS_PRICE = 1.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get passed food item
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            foodItem = getIntent().getSerializableExtra("FOOD_ITEM", FoodItem.class);
        } else {
            foodItem = (FoodItem) getIntent().getSerializableExtra("FOOD_ITEM");
        }
        if (foodItem == null) {
            foodItem = DataManager.getInstance().getFoodItems().get(0);
        }

        populateViews();
        setupListeners();
        updateTotalPrice();
    }

    private void populateViews() {
        binding.ivDetailFood.setImageResource(foodItem.getImageResId());
        binding.tvDetailName.setText(foodItem.getName());
        binding.tvDetailBistro.setText("By " + foodItem.getRestaurantName());
        binding.tvDetailBasePrice.setText(String.format(Locale.US, "$%.2f", foodItem.getPrice()));
        binding.tvDetailRating.setText(String.format(Locale.US, "%.1f (%s)", foodItem.getRating(), foodItem.getReviewCount()));
        binding.tvDetailSpeed.setText(foodItem.getDeliveryTime());
        binding.tvDetailCalories.setText("🔥 " + foodItem.getCalories());
        binding.tvDetailDescription.setText(foodItem.getDescription());

        if (foodItem.isFavorite()) {
            binding.btnDetailFavorite.setImageResource(R.drawable.ic_favorite);
        } else {
            binding.btnDetailFavorite.setImageResource(R.drawable.ic_favorite_border);
        }
    }

    private void setupListeners() {
        binding.btnBack.setOnClickListener(v -> finish());

        binding.btnShare.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out " + foodItem.getName() + " on FoodieGo!");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Share Food"));
        });

        binding.btnDetailFavorite.setOnClickListener(v -> {
            foodItem.setFavorite(!foodItem.isFavorite());
            if (foodItem.isFavorite()) {
                binding.btnDetailFavorite.setImageResource(R.drawable.ic_favorite);
                Toast.makeText(this, "Saved to favorites", Toast.LENGTH_SHORT).show();
            } else {
                binding.btnDetailFavorite.setImageResource(R.drawable.ic_favorite_border);
            }
        });

        // Stepper
        binding.btnQtyMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                binding.tvDetailQuantity.setText(String.valueOf(quantity));
                updateTotalPrice();
            }
        });

        binding.btnQtyPlus.setOnClickListener(v -> {
            quantity++;
            binding.tvDetailQuantity.setText(String.valueOf(quantity));
            updateTotalPrice();
        });

        // Extras checkboxes
        binding.cbExtra1.setOnCheckedChangeListener((buttonView, isChecked) -> updateTotalPrice());
        binding.cbExtra2.setOnCheckedChangeListener((buttonView, isChecked) -> updateTotalPrice());
        binding.cbExtra3.setOnCheckedChangeListener((buttonView, isChecked) -> updateTotalPrice());

        // Add to Cart
        binding.btnAddToCart.setOnClickListener(v -> {
            List<String> selectedExtras = new ArrayList<>();
            double extrasCost = 0.0;

            if (binding.cbExtra1.isChecked()) {
                selectedExtras.add("Extra Cheddar Cheese");
                extrasCost += EXTRA_CHEDDAR_PRICE;
            }
            if (binding.cbExtra2.isChecked()) {
                selectedExtras.add("Crispy Smoked Bacon");
                extrasCost += EXTRA_BACON_PRICE;
            }
            if (binding.cbExtra3.isChecked()) {
                selectedExtras.add("Caramelized Sweet Onions");
                extrasCost += EXTRA_ONIONS_PRICE;
            }

            String specialNotes = binding.etSpecialInstructions.getText().toString().trim();

            CartItem cartItem = new CartItem(foodItem, quantity, selectedExtras, extrasCost, specialNotes);
            DataManager.getInstance().addToCart(cartItem);

            Toast.makeText(this, "Added to cart!", Toast.LENGTH_SHORT).show();

            // Open Cart Screen
            startActivity(new Intent(FoodDetailsActivity.this, CartActivity.class));
            finish();
        });
    }

    private void updateTotalPrice() {
        double unitPrice = foodItem.getPrice();
        if (binding.cbExtra1.isChecked()) unitPrice += EXTRA_CHEDDAR_PRICE;
        if (binding.cbExtra2.isChecked()) unitPrice += EXTRA_BACON_PRICE;
        if (binding.cbExtra3.isChecked()) unitPrice += EXTRA_ONIONS_PRICE;

        double total = unitPrice * quantity;
        binding.tvAddToCartLabel.setText(String.format(Locale.US, "Add to Cart • $%.2f", total));
    }
}
