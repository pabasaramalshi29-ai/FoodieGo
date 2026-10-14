package edu.cinec.foodiego;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.cinec.foodiego.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Skip to Main Home
        binding.btnSkip.setOnClickListener(v -> {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        });

        // Get Started -> Login or Register screen
        binding.btnGetStarted.setOnClickListener(v -> {
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        });

        // Sign In link
        binding.tvSignIn.setOnClickListener(v -> {
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        });
    }
}
