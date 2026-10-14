package edu.cinec.foodiego;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.cinec.foodiego.data.DataManager;
import edu.cinec.foodiego.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadUserData();
        setupListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserData();
    }

    private void loadUserData() {
        binding.tvProfileName.setText(DataManager.getInstance().getUserName());
        binding.tvProfileEmail.setText(DataManager.getInstance().getUserEmail());
    }

    private void setupListeners() {
        binding.btnBack.setOnClickListener(v -> finish());

        binding.btnSettings.setOnClickListener(v -> {
            Toast.makeText(this, "Account Settings opened", Toast.LENGTH_SHORT).show();
        });

        binding.btnRedeemVoucher.setOnClickListener(v -> {
            Toast.makeText(this, "Voucher applied! Enjoy savings on next order", Toast.LENGTH_SHORT).show();
        });

        binding.menuOrderHistory.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, OrderTrackingActivity.class));
        });

        binding.menuSavedAddresses.setOnClickListener(v -> {
            Toast.makeText(this, "Saved Addresses: Home, Office", Toast.LENGTH_SHORT).show();
        });

        binding.menuPaymentMethods.setOnClickListener(v -> {
            Toast.makeText(this, "Payment Methods: Google Pay, Visa ending in 4242", Toast.LENGTH_SHORT).show();
        });

        binding.menuNotifications.setOnClickListener(v -> {
            Toast.makeText(this, "Push notifications enabled", Toast.LENGTH_SHORT).show();
        });

        // Log out
        binding.btnLogout.setOnClickListener(v -> {
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProfileActivity.this, WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
