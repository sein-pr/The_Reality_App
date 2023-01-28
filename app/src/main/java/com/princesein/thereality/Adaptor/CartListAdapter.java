package com.princesein.thereality.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.princesein.thereality.Domain.ClothingDomain;
import com.princesein.thereality.Helper.ManagementCart;
import com.princesein.thereality.Interface.ChangeNumberItemListener;
import com.princesein.thereality.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<ClothingDomain> clothingDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemListener changeNumberItemListener;

    public CartListAdapter(ArrayList<ClothingDomain> clothingDomains, Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.clothingDomains = clothingDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate=LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(clothingDomains.get(position).getTitle());
        holder.priceEachItem.setText(String.valueOf(clothingDomains.get(position).getPrice()));
        holder.totalEachItem.setText(String.valueOf(Math.round((clothingDomains.get(position).getNumberInCart()*clothingDomains.get(position).getPrice())*100)/100));
        holder.num.setText(String.valueOf(clothingDomains.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(clothingDomains.get(position).getPic()
        ,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberFood(clothingDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });

        holder.minuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberFood(clothingDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return clothingDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,priceEachItem;
        ImageView pic,plusItem,minuItem;
        TextView totalEachItem,num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.titleText);
            priceEachItem = itemView.findViewById(R.id.priceEachItemText);
            pic = itemView.findViewById(R.id.picCart);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemText);
            plusItem=itemView.findViewById(R.id.plusCartBtn);
            minuItem=itemView.findViewById(R.id.minusCartBtn);

        }
    }
}
