package edu.cinec.foodiego.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edu.cinec.foodiego.FoodDetailsActivity;
import edu.cinec.foodiego.R;
import edu.cinec.foodiego.data.DataManager;
import edu.cinec.foodiego.databinding.ItemPopularDishBinding;
import edu.cinec.foodiego.model.CartItem;
import edu.cinec.foodiego.model.FoodItem;

public class PopularDishAdapter extends RecyclerView.Adapter<PopularDishAdapter.ViewHolder> {

    public interface OnItemCartUpdatedListener {
        void onCartUpdated();
    }

    private Context context;
    private List<FoodItem> items;
    private OnItemCartUpdatedListener listener;

    public PopularDishAdapter(Context context, List<FoodItem> items, OnItemCartUpdatedListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    public void updateList(List<FoodItem> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPopularDishBinding binding = ItemPopularDishBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemPopularDishBinding binding;

        ViewHolder(ItemPopularDishBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(FoodItem item) {
            binding.ivDishImage.setImageResource(item.getImageResId());
            binding.tvDishTitle.setText(item.getName());
            binding.tvDishDesc.setText(item.getDescription());
            binding.tvPrice.setText(String.format(Locale.US, "$%.2f", item.getPrice()));
            binding.tvRating.setText(String.format(Locale.US, "%.1f", item.getRating()));
            binding.tvReviews.setText("(" + item.getReviewCount() + ")");
            binding.tvDeliveryTime.setText(item.getDeliveryTime());

            // Favorite toggle
            if (item.isFavorite()) {
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite);
            } else {
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite_border);
            }

            binding.btnFavorite.setOnClickListener(v -> {
                item.setFavorite(!item.isFavorite());
                if (item.isFavorite()) {
                    binding.btnFavorite.setImageResource(R.drawable.ic_favorite);
                    Toast.makeText(context, item.getName() + " added to favorites", Toast.LENGTH_SHORT).show();
                } else {
                    binding.btnFavorite.setImageResource(R.drawable.ic_favorite_border);
                }
            });

            // Card click -> Food Details Activity
            binding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(context, FoodDetailsActivity.class);
                intent.putExtra("FOOD_ITEM", item);
                context.startActivity(intent);
            });

            // Quick add button
            binding.btnAddDish.setOnClickListener(v -> {
                CartItem cartItem = new CartItem(item, 1, new ArrayList<>(), 0.0, "");
                DataManager.getInstance().addToCart(cartItem);
                Toast.makeText(context, "Added " + item.getName() + " to cart", Toast.LENGTH_SHORT).show();
                if (listener != null) {
                    listener.onCartUpdated();
                }
            });
        }
    }
}
