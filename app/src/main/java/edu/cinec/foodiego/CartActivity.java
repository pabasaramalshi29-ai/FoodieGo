package edu.cinec.foodiego;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Locale;

import edu.cinec.foodiego.adapter.CartAdapter;
import edu.cinec.foodiego.data.DataManager;
import edu.cinec.foodiego.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding binding;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupCartList();
        setupListeners();
        updateBill();
    }

    private void setupCartList() {
        binding.rvCartItems.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(this, DataManager.getInstance().getCartItems(), this::updateBill);
        binding.rvCartItems.setAdapter(adapter);
    }

    private void setupListeners() {
        binding.btnBack.setOnClickListener(v -> finish());

        binding.btnProfile.setOnClickListener(v -> {
            startActivity(new Intent(CartActivity.this, ProfileActivity.class));
        });

        // Clear cart
        binding.btnClearCart.setOnClickListener(v -> {
            DataManager.getInstance().clearCart();
            adapter.notifyDataSetChanged();
            updateBill();
            Toast.makeText(this, "Cart cleared", Toast.LENGTH_SHORT).show();
        });

        // Change Address
        binding.btnChangeAddress.setOnClickListener(v -> {
            Toast.makeText(this, "Address selection opened", Toast.LENGTH_SHORT).show();
        });

        // Apply Coupon
        binding.btnApplyCoupon.setOnClickListener(v -> {
            String code = binding.etCouponCode.getText().toString().trim();
            if (code.equalsIgnoreCase("FOODIE30")) {
                DataManager.getInstance().setDiscount(5.00);
                Toast.makeText(this, "Coupon FOODIE30 applied! $5.00 discount", Toast.LENGTH_SHORT).show();
                updateBill();
            } else if (!code.isEmpty()) {
                Toast.makeText(this, "Invalid coupon code", Toast.LENGTH_SHORT).show();
            }
        });

        // Proceed to Checkout
        binding.btnProceedCheckout.setOnClickListener(v -> {
            if (DataManager.getInstance().getCartItems().isEmpty()) {
                Toast.makeText(this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(new Intent(CartActivity.this, CheckoutActivity.class));
        });
    }

    private void updateBill() {
        int count = DataManager.getInstance().getCartCount();
        binding.tvCartItemsCount.setText(count + " items");

        if (DataManager.getInstance().getCartItems().isEmpty()) {
            binding.tvEmptyCart.setVisibility(View.VISIBLE);
            binding.rvCartItems.setVisibility(View.GONE);
            binding.bottomCheckoutBar.setVisibility(View.GONE);
        } else {
            binding.tvEmptyCart.setVisibility(View.GONE);
            binding.rvCartItems.setVisibility(View.VISIBLE);
            binding.bottomCheckoutBar.setVisibility(View.VISIBLE);
        }

        double subtotal = DataManager.getInstance().getSubtotal();
        double delivery = DataManager.getInstance().getDeliveryFee();
        double tax = DataManager.getInstance().getTax();
        double discount = DataManager.getInstance().getDiscount();
        double total = DataManager.getInstance().getTotalAmount();

        binding.tvBillSubtotal.setText(String.format(Locale.US, "$%.2f", subtotal));
        binding.tvBillDelivery.setText(String.format(Locale.US, "$%.2f", delivery));
        binding.tvBillTax.setText(String.format(Locale.US, "$%.2f", tax));
        binding.tvBillDiscount.setText(String.format(Locale.US, "-$%.2f", discount));
        binding.tvBillTotal.setText(String.format(Locale.US, "$%.2f", total));
        binding.tvCheckoutBarTotal.setText(String.format(Locale.US, "$%.2f", total));
    }
}
