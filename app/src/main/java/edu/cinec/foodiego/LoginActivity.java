package edu.cinec.foodiego;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.cinec.foodiego.data.DataManager;
import edu.cinec.foodiego.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Back button
        binding.btnBack.setOnClickListener(v -> finish());

        // Password Visibility Toggle
        binding.ivTogglePassword.setOnClickListener(v -> {
            isPasswordVisible = !isPasswordVisible;
            if (isPasswordVisible) {
                binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                binding.ivTogglePassword.setImageResource(R.drawable.ic_visibility_off);
            } else {
                binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                binding.ivTogglePassword.setImageResource(R.drawable.ic_visibility);
            }
            binding.etPassword.setSelection(binding.etPassword.getText().length());
        });

        // Login Action
        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            if (email.isEmpty()) {
                binding.etEmail.setError("Email is required");
                return;
            }
            if (password.isEmpty()) {
                binding.etPassword.setError("Password is required");
                return;
            }

            // Update user email in DataManager
            DataManager.getInstance().setUserEmail(email);

            Toast.makeText(LoginActivity.this, "Welcome back, " + DataManager.getInstance().getUserName() + "!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Sign Up Link
        binding.tvSignUp.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        // Forgot password
        binding.tvForgotPassword.setOnClickListener(v -> {
            Toast.makeText(LoginActivity.this, "Reset link sent to " + binding.etEmail.getText().toString(), Toast.LENGTH_SHORT).show();
        });
    }
}
