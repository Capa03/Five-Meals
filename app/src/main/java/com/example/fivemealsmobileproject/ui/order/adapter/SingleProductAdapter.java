package com.example.fivemealsmobileproject.ui.order.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.datasource.room.OrderProduct;
import com.example.fivemealsmobileproject.ui.main.TimeHelper;

import java.util.ArrayList;
import java.util.List;

public class SingleProductAdapter extends RecyclerView.Adapter<SingleProductAdapter.BaseProductViewHolder> {

    private List<OrderProduct> products = new ArrayList<>();
    private Context context;
    private final SingleProductEventListener singleProductEventListener;

    public interface SingleProductEventListener {
        void onRemoveProductClick(OrderProduct orderProduct, int position);
    }

    public SingleProductAdapter(SingleProductEventListener singleProductEventListener) {
        this.singleProductEventListener = singleProductEventListener;
    }

    @NonNull
    @Override
    public BaseProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        if (viewType == OrderProduct.PENDING_STATE) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_single_product_type_pending, parent, false);
            return new PendingProductViewHolder(layout);
        } else if (viewType == OrderProduct.WAITING_APPROVAL_STATE) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_single_product_type_waiting_for_approval, parent, false);
            return new WaitingProductViewHolder(layout);
        } else if (viewType == OrderProduct.PROCESSING_STATE) {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_single_product_type_processing, parent, false);
            return new ProcessingProductViewHolder(layout);
        } else {
            View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order_single_product_type_delivered, parent, false);
            return new DeliveredProductViewHolder(layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return products.get(position).getState();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseProductViewHolder holder, int position) {
        OrderProduct orderProduct = products.get(position);

        holder.setName(orderProduct.getProductName());
        holder.setPrice(orderProduct.getProductPrice());
        PendingProductViewHolder pendingProductViewHolder  = (PendingProductViewHolder) holder;
        pendingProductViewHolder.removeButton.setOnClickListener(view -> {
            singleProductEventListener.onRemoveProductClick(orderProduct, position);
        });

        // TODO rever os states
        /*
        Product product = AppDataBase.getInstance(this.context).getProductDAO().getById(orderProduct.getProductID());


        if (holder instanceof PendingProductViewHolder) {
            PendingProductViewHolder pendingProductViewHolder = (PendingProductViewHolder) holder;
            pendingProductViewHolder.setName(product.getName());
            pendingProductViewHolder.setPrice(product.getPrice());

            int progress = TimeHelper.getRemainingCancelTime(orderProduct.getOrderedTime());
            pendingProductViewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Eliminar o pedido");
                    builder.setMessage("Deseja eliminar este pedido?");
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            singleProductEventListener.onRemoveProductClick(orderProduct);
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
            pendingProductViewHolder.setProgress(progress);
            pendingProductViewHolder.setTime(TimeHelper.getRemainingCancelTimeInTimeStamp(orderProduct.getOrderedTime()));
            if (progress >= 100) {
                orderProduct.setState(OrderProduct.PROCESSING_STATE);
                AppDataBase.getInstance(context).getOrderProductDAO().updateOrderProduct(orderProduct);
            }

        } else if (holder instanceof WaitingProductViewHolder) {
            WaitingProductViewHolder waitingProductViewHolder = (WaitingProductViewHolder) holder;
            waitingProductViewHolder.setName(product.getName());
            waitingProductViewHolder.setPrice(product.getPrice());
            waitingProductViewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Eliminar pedido");
                    builder.setMessage("Deseja eliminar este pedido?");
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            singleProductEventListener.onRemoveProductClick(orderProduct);
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            });
            if (waitingProductViewHolder.checkBox.isChecked()) {
                orderProduct.setState(OrderProduct.PENDING_STATE);
                orderProduct.setOrderedTime(System.currentTimeMillis());
                AppDataBase.getInstance(context).getOrderProductDAO().updateOrderProduct(orderProduct);
            }
        } else if (holder instanceof ProcessingProductViewHolder) {
            ProcessingProductViewHolder processingProductViewHolder = (ProcessingProductViewHolder) holder;
            processingProductViewHolder.setName(product.getName());
            processingProductViewHolder.setPrice(product.getPrice());
            int progress = TimeHelper.getProgressInPercentage(
                    product.getMinAverageTime(), product.getMaxAverageTime(), orderProduct.getOrderedTime());

            processingProductViewHolder.setProgress(progress);
            processingProductViewHolder.setTime(TimeHelper.getProgressInTimeStamp(
                    product.getMinAverageTime(), product.getMaxAverageTime(), orderProduct.getOrderedTime()));
            if (progress >= 100) {
                orderProduct.setState(OrderProduct.DELIVERED_STATE);
                AppDataBase.getInstance(context).getOrderProductDAO().updateOrderProduct(orderProduct);
            }
        } else {
            DeliveredProductViewHolder deliveredProductViewHolder = (DeliveredProductViewHolder) holder;
            deliveredProductViewHolder.setName(product.getName());
            deliveredProductViewHolder.setPrice(product.getPrice());
        }
        */
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void updateData(List<OrderProduct> products) {
        this.products = products;
        notifyDataSetChanged();
    }


    public abstract class BaseProductViewHolder extends RecyclerView.ViewHolder {
        public BaseProductViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void setName(String name);

        public abstract void setPrice(float price);
    }

    public class PendingProductViewHolder extends BaseProductViewHolder {
        private final TextView textViewName;
        private final TextView textViewPrice;
        private final TextView textViewTimePassed;
        private final ImageView removeButton;
        private final ProgressBar progressBar;


        public PendingProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewOrderSingleProductName);
            this.textViewPrice = itemView.findViewById(R.id.textViewOrderSingleProductPrice);
            this.removeButton = itemView.findViewById(R.id.imageViewOrderRemoveSingleProductButton);
            this.progressBar = itemView.findViewById(R.id.progressBarOrderSingleProductTimeTaken);
            this.textViewTimePassed = itemView.findViewById(R.id.textViewOrderSingleProductTimePassed);
        }

        public void setName(String name) {
            this.textViewName.setText(name);
        }

        public void setPrice(float price) {
            this.textViewPrice.setText((price + " €"));
        }

        public void setProgress(int progress) {
            this.progressBar.setProgress(progress);
        }

        public void setTime(String time) {
            this.textViewTimePassed.setText(time);
        }
    }

    public class WaitingProductViewHolder extends BaseProductViewHolder {
        private final TextView textViewName;
        private final TextView textViewPrice;
        private final ImageView removeButton;
        private final CheckBox checkBox;

        public WaitingProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewOrderSingleProductName);
            this.textViewPrice = itemView.findViewById(R.id.textViewOrderSingleProductPrice);
            this.removeButton = itemView.findViewById(R.id.imageViewOrderRemoveSingleProductButton);
            this.checkBox = itemView.findViewById(R.id.checkBoxSingleProductOrderNow);
        }

        public void setName(String name) {
            this.textViewName.setText(name);
        }

        public void setPrice(float price) {
            this.textViewPrice.setText((price + " €"));
        }

    }

    public class ProcessingProductViewHolder extends BaseProductViewHolder {
        private final TextView textViewName;
        private final TextView textViewPrice;
        private final TextView textViewTimePassed;
        private final ProgressBar progressBar;

        public ProcessingProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewOrderSingleProductName);
            this.textViewPrice = itemView.findViewById(R.id.textViewOrderSingleProductPrice);
            this.progressBar = itemView.findViewById(R.id.progressBarOrderSingleProductTimeTaken);
            this.textViewTimePassed = itemView.findViewById(R.id.textViewOrderSingleProductTimePassed);
        }

        public void setName(String name) {
            this.textViewName.setText(name);
        }

        public void setPrice(float price) {
            this.textViewPrice.setText((price + " €"));
        }

        public void setProgress(int progress) {
            this.progressBar.setProgress(progress);
        }

        public void setTime(String time) {
            this.textViewTimePassed.setText(time);
        }
    }

    public class DeliveredProductViewHolder extends BaseProductViewHolder {
        private final TextView textViewName;
        private final TextView textViewPrice;

        public DeliveredProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewOrderSingleProductName);
            this.textViewPrice = itemView.findViewById(R.id.textViewOrderSingleProductPrice);
        }

        public void setName(String name) {
            this.textViewName.setText(name);
        }

        public void setPrice(float price) {
            this.textViewPrice.setText((String.valueOf(price) + " €"));
        }
    }
}
