package com.princesein.thereality.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.princesein.thereality.Adaptor.CartListAdapter;
import com.princesein.thereality.Helper.ManagementCart;
import com.princesein.thereality.Interface.ChangeNumberItemListener;
import com.princesein.thereality.R;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalPriceText,taxText,deliveryText,totalText,emptyText;
    private double tax;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this,CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this,home_activity.class));
            }
        });
    }

    private void initView() {
        recyclerViewList =  findViewById(R.id.recyclerView);
        totalPriceText = findViewById(R.id.totalPriceText);
        taxText = findViewById(R.id.totalText);
        deliveryText = findViewById(R.id.deliveryText);
        totalText = findViewById(R.id.totalText);
        emptyText = findViewById(R.id.emptyText);
        scrollView= findViewById(R.id.scrollView2);
        recyclerViewList = findViewById(R.id.viewCart);
    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter =new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });

        recyclerViewList.setAdapter(adapter);

        if (managementCart.getListCart().isEmpty()){
            emptyText.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);

        }else{
            emptyText.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void CalculateCart(){
        double percentTax=0.02;
        double delivery=10;

        tax=Math.round((managementCart.getTotalPrice()*percentTax)*100)/100;
        double total = Math.round((managementCart.getTotalPrice()+tax+delivery)*100)/100;
        double itemTotal = Math.round(managementCart.getTotalPrice()*100)/100;

        totalPriceText.setText("N$"+itemTotal);
        taxText.setText("N$"+tax);
        deliveryText.setText("N$"+delivery);
        totalText.setText("N$"+total);
    }
}