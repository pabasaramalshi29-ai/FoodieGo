package edu.cinec.foodiego.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import edu.cinec.foodiego.data.DataManager;
import edu.cinec.foodiego.databinding.ItemCartBinding;
import edu.cinec.foodiego.model.CartItem;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    public interface OnCartChangeListener {
        void onCartChanged();
    }

    private Context context;
    private List<CartItem> cartItems;
    private OnCartChangeListener listener;

    public CartAdapter(Context context, List<CartItem> cartItems, OnCartChangeListener listener) {
        this.context = context;
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding binding = ItemCartBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.bind(item, position);
    }

    @Override
    public int getItemCount() {
        return cartItems != null ? cartItems.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCartBinding binding;

        ViewHolder(ItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(CartItem item, int position) {
            binding.ivCartItemThumb.setImageResource(item.getFoodItem().getImageResId());
            binding.tvCartItemName.setText(item.getFoodItem().getName());
            binding.tvCartItemExtras.setText(item.getExtrasFormatted());
            binding.tvCartItemPrice.setText(String.format(Locale.US, "$%.2f", item.getTotalPrice()));
            binding.tvQuantity.setText(String.valueOf(item.getQuantity()));

            // Plus quantity
            binding.btnPlus.setOnClickListener(v -> {
                int newQty = item.getQuantity() + 1;
                DataManager.getInstance().updateCartItemQuantity(position, newQty);
                notifyItemChanged(position);
                if (listener != null) listener.onCartChanged();
            });

            // Minus quantity
            binding.btnMinus.setOnClickListener(v -> {
                int newQty = item.getQuantity() - 1;
                if (newQty <= 0) {
                    DataManager.getInstance().removeCartItem(position);
                    notifyDataSetChanged();
                } else {
                    DataManager.getInstance().updateCartItemQuantity(position, newQty);
                    notifyItemChanged(position);
                }
                if (listener != null) listener.onCartChanged();
            });

            // Remove item
            binding.btnRemoveCartItem.setOnClickListener(v -> {
                DataManager.getInstance().removeCartItem(position);
                notifyDataSetChanged();
                if (listener != null) listener.onCartChanged();
            });
        }
    }
}
