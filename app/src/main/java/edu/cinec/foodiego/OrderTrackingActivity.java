package edu.cinec.foodiego;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.cinec.foodiego.databinding.ActivityOrderTrackingBinding;

public class OrderTrackingActivity extends AppCompatActivity {

    private ActivityOrderTrackingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderTrackingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListeners();
    }

    private void setupListeners() {
        binding.btnBack.setOnClickListener(v -> finish());

        binding.btnProfile.setOnClickListener(v -> {
            startActivity(new Intent(OrderTrackingActivity.this, ProfileActivity.class));
        });

        // Share tracking
        binding.btnShareTracking.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Track my FoodieGo delivery in real-time: https://foodiego.app/track/FG-88294");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Share Tracking"));
        });

        // Recenter GPS
        binding.btnRecenter.setOnClickListener(v -> {
            Toast.makeText(this, "Recentered on courier Carlos (6 min away)", Toast.LENGTH_SHORT).show();
        });

        // Call courier
        binding.btnCallCourier.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:+15550192834"));
            startActivity(callIntent);
        });

        // Message courier
        binding.btnMessageCourier.setOnClickListener(v -> {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("sms:+15550192834"));
            smsIntent.putExtra("sms_body", "Hi Carlos, please leave the food at front door!");
            startActivity(smsIntent);
        });

        // Order Help
        binding.btnOrderHelp.setOnClickListener(v -> {
            Toast.makeText(this, "Connecting to FoodieGo Live Support...", Toast.LENGTH_SHORT).show();
        });
    }
}
