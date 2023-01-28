package com.princesein.thereality.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.princesein.thereality.Domain.ClothingDomain;
import com.princesein.thereality.Helper.ManagementCart;
import com.princesein.thereality.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleText,priceText,descriptionText,numberOrderText;
    private ImageView minusBtn,plusBtn,picClothes;
    private ClothingDomain object;
    int numberOrder=1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object = (ClothingDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this )
                .load(drawableResourceId)
                .into(picClothes);
        titleText.setText(object.getTitle());
        priceText.setText("N$"+object.getPrice());
        descriptionText.setText(object.getDescription());
        numberOrderText.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder=numberOrder+1;
                numberOrderText.setText(String.valueOf(numberOrder));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder>1){
                    numberOrder=numberOrder-1;
                }
                numberOrderText.setText(String.valueOf(numberOrder));
            }
        });
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCart.insertClothes(object);
            }
        });

    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleText = findViewById(R.id.titleText);
        priceText = findViewById(R.id.priceText);
        descriptionText=findViewById(R.id.descriptionText);
        numberOrderText=findViewById(R.id.numberOrderText);
        minusBtn=findViewById(R.id.minusBtn);
        plusBtn=findViewById(R.id.plusBtn);
        picClothes= findViewById(R.id.picClothes);
    }
}