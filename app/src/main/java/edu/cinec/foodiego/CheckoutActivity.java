package edu.cinec.foodiego;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import edu.cinec.foodiego.data.DataManager;
import edu.cinec.foodiego.databinding.ActivityCheckoutBinding;

public class CheckoutActivity extends AppCompatActivity {

    private ActivityCheckoutBinding binding;
    private double currentDeliveryFee = 1.99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        updateTotal();
        setupListeners();
    }

    private void setupListeners() {
        binding.btnBack.setOnClickListener(v -> finish());

        // Delivery speed radio buttons
        binding.rbInstant.setOnClickListener(v -> {
            currentDeliveryFee = 1.99;
            DataManager.getInstance().setDeliveryFee(currentDeliveryFee);
            updateTotal();
        });

        binding.rbStandard.setOnClickListener(v -> {
            currentDeliveryFee = 0.00;
            DataManager.getInstance().setDeliveryFee(currentDeliveryFee);
            updateTotal();
        });

        binding.cardSpeedInstant.setOnClickListener(v -> {
            binding.rbInstant.setChecked(true);
            currentDeliveryFee = 1.99;
            DataManager.getInstance().setDeliveryFee(currentDeliveryFee);
            updateTotal();
        });

        binding.cardSpeedStandard.setOnClickListener(v -> {
            binding.rbStandard.setChecked(true);
            currentDeliveryFee = 0.00;
            DataManager.getInstance().setDeliveryFee(currentDeliveryFee);
            updateTotal();
        });

        // Place Order Action
        binding.btnPlaceOrder.setOnClickListener(v -> {
            Toast.makeText(this, "Order #FG-88294 placed successfully! 🚀", Toast.LENGTH_LONG).show();

            // Clear current cart now that order is placed
            DataManager.getInstance().clearCart();

            // Navigate to Live Order Tracking
            Intent intent = new Intent(CheckoutActivity.this, OrderTrackingActivity.class);
            intent.putExtra("ORDER_ID", "FG-88294");
            startActivity(intent);
            finish();
        });
    }

    private void updateTotal() {
        double total = DataManager.getInstance().getTotalAmount();
        binding.tvCheckoutTotal.setText(String.format(Locale.US, "$%.2f", total));
        binding.tvPlaceOrderLabel.setText(String.format(Locale.US, "Place Order • $%.2f", total));
    }
}
