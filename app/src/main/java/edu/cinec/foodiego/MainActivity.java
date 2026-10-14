package edu.cinec.foodiego;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import edu.cinec.foodiego.adapter.PopularDishAdapter;
import edu.cinec.foodiego.adapter.RestaurantAdapter;
import edu.cinec.foodiego.data.DataManager;
import edu.cinec.foodiego.databinding.ActivityMainBinding;
import edu.cinec.foodiego.model.FoodItem;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PopularDishAdapter popularAdapter;
    private RestaurantAdapter restaurantAdapter;
    private String selectedCategory = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupRecyclerViews();
        setupCategories();
        setupSearch();
        setupNavigation();
        setupClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartBadge();
    }

    private void setupRecyclerViews() {
        // Popular Dishes Horizontal Strip
        binding.rvPopularDishes.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        popularAdapter = new PopularDishAdapter(
                this,
                DataManager.getInstance().getFoodItems(),
                this::updateCartBadge
        );
        binding.rvPopularDishes.setAdapter(popularAdapter);

        // Restaurants Vertical List
        binding.rvRestaurants.setLayoutManager(new LinearLayoutManager(this));
        restaurantAdapter = new RestaurantAdapter(this, DataManager.getInstance().getRestaurants());
        binding.rvRestaurants.setAdapter(restaurantAdapter);
    }

    private void setupCategories() {
        TextView[] chips = new TextView[]{
                binding.chipAll,
                binding.chipBurgers,
                binding.chipPizza,
                binding.chipBowls,
                binding.chipAsian,
                binding.chipDesserts
        };

        String[] categoryKeys = new String[]{
                "All", "Burgers", "Pizza", "Bowls", "Asian", "Desserts"
        };

        for (int i = 0; i < chips.length; i++) {
            final int index = i;
            chips[i].setOnClickListener(v -> {
                selectedCategory = categoryKeys[index];
                // Update visuals
                for (TextView chip : chips) {
                    chip.setBackgroundResource(R.drawable.bg_chip_unselected);
                    chip.setTextColor(getColor(R.color.text_primary));
                }
                chips[index].setBackgroundResource(R.drawable.bg_chip_selected);
                chips[index].setTextColor(getColor(R.color.white));

                filterItems();
            });
        }
    }

    private void setupSearch() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterItems();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.btnFilter.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Filter preferences opened", Toast.LENGTH_SHORT).show();
        });
    }

    private void filterItems() {
        String query = binding.etSearch.getText().toString().trim().toLowerCase();
        List<FoodItem> allItems = DataManager.getInstance().getFoodItems();
        List<FoodItem> filtered = new ArrayList<>();

        for (FoodItem item : allItems) {
            boolean matchesCategory = selectedCategory.equals("All") || item.getCategory().equalsIgnoreCase(selectedCategory);
            boolean matchesQuery = query.isEmpty()
                    || item.getName().toLowerCase().contains(query)
                    || item.getDescription().toLowerCase().contains(query);

            if (matchesCategory && matchesQuery) {
                filtered.add(item);
            }
        }
        popularAdapter.updateList(filtered);
    }

    private void setupNavigation() {
        // Nav Cart
        binding.navCart.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        });

        // Nav Orders
        binding.navOrders.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, OrderTrackingActivity.class));
        });

        // Nav Profile
        binding.navProfile.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        });

        // Nav Browse -> Focus search
        binding.navBrowse.setOnClickListener(v -> {
            binding.etSearch.requestFocus();
            Toast.makeText(MainActivity.this, "Search across all restaurants and cuisines", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupClickListeners() {
        // Notifications
        binding.btnNotifications.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "You have 2 new promo notifications!", Toast.LENGTH_SHORT).show();
        });

        // Profile Avatar in Top Bar
        binding.btnProfileAvatar.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        });

        // Address picker
        binding.btnAddressPicker.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Current Delivery: " + DataManager.getInstance().getDeliveryAddress(), Toast.LENGTH_SHORT).show();
        });

        // Favorites button
        binding.btnFavoritesHeader.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Showing your saved favorite dishes", Toast.LENGTH_SHORT).show();
        });

        // Claim Promo FOODIE30
        binding.btnClaimPromo.setOnClickListener(v -> {
            DataManager.getInstance().setDiscount(5.00);
            Toast.makeText(MainActivity.this, "Coupon FOODIE30 applied! Enjoy $5 OFF", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        });

        // Live Order Track Banner Button
        binding.btnTrackOrderSnack.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, OrderTrackingActivity.class));
        });

        binding.cardLiveOrderSnack.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, OrderTrackingActivity.class));
        });
    }

    private void updateCartBadge() {
        int count = DataManager.getInstance().getCartCount();
        if (count > 0) {
            binding.tvNavCartBadge.setVisibility(View.VISIBLE);
            binding.tvNavCartBadge.setText(String.valueOf(count));
        } else {
            binding.tvNavCartBadge.setVisibility(View.GONE);
        }
    }
}