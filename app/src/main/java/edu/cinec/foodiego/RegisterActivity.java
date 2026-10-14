package edu.cinec.foodiego;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.cinec.foodiego.data.DataManager;
import edu.cinec.foodiego.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup Country Code Spinner
        String[] countryCodes = new String[]{"+1", "+44", "+94", "+91", "+61", "+81"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryCodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCountryCode.setAdapter(adapter);

        // Back button
        binding.btnBack.setOnClickListener(v -> finish());

        // Password visibility toggle
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

        // Register Action
        binding.btnRegister.setOnClickListener(v -> {
            String name = binding.etFullName.getText().toString().trim();
            String email = binding.etEmail.getText().toString().trim();
            String phone = binding.etPhone.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();
            String countryCode = binding.spinnerCountryCode.getSelectedItem() != null ? binding.spinnerCountryCode.getSelectedItem().toString() : "+1";
            String fullPhone = countryCode + " " + phone;

            if (name.isEmpty()) {
                binding.etFullName.setError("Name is required");
                return;
            }
            if (email.isEmpty()) {
                binding.etEmail.setError("Email is required");
                return;
            }
            if (password.length() < 6) {
                binding.etPassword.setError("Password must be at least 6 characters");
                return;
            }
            if (!binding.cbTerms.isChecked()) {
                Toast.makeText(RegisterActivity.this, "Please accept the Terms & Conditions", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save user profile in DataManager
            DataManager.getInstance().setUserProfile(name, email, fullPhone);

            Toast.makeText(RegisterActivity.this, "Account created successfully! Welcome, " + name, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Sign In Link
        binding.tvSignIn.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }
}
