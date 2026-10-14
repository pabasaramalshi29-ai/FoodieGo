package edu.cinec.foodiego.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import edu.cinec.foodiego.databinding.ItemRestaurantBinding;
import edu.cinec.foodiego.model.Restaurant;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private Context context;
    private List<Restaurant> restaurants;

    public RestaurantAdapter(Context context, List<Restaurant> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRestaurantBinding binding = ItemRestaurantBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.bind(restaurant);
    }

    @Override
    public int getItemCount() {
        return restaurants != null ? restaurants.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemRestaurantBinding binding;

        ViewHolder(ItemRestaurantBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Restaurant r) {
            binding.ivRestaurant.setImageResource(r.getImageResId());
            binding.tvRestaurantName.setText(r.getName());
            binding.tvDistance.setText(r.getDistance());
            binding.tvCuisine.setText(r.getCuisine());
            binding.tvRestRating.setText(String.format(Locale.US, "%.1f", r.getRating()));
            binding.tvTime.setText(r.getDeliveryTime());
            binding.tvFeeTag.setText(r.getDeliveryFee());

            binding.getRoot().setOnClickListener(v -> {
                Toast.makeText(context, "Opening " + r.getName() + " menu...", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
