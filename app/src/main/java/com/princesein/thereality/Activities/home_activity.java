package com.princesein.thereality.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.princesein.thereality.Adaptor.CategoryAdaptor;
import com.princesein.thereality.Adaptor.PopularAdaptor;
import com.princesein.thereality.Domain.CategoryDomain;
import com.princesein.thereality.Domain.ClothingDomain;
import com.princesein.thereality.R;

import java.util.ArrayList;

public class home_activity extends AppCompatActivity {
    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewCategory();
        RecyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home_activity.this,CartListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home_activity.this,home_activity.class));
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        ArrayList<CategoryDomain> category = new ArrayList<>();

        category.add(new CategoryDomain("Shirts","cat_1"));
        category.add(new CategoryDomain("Trousers","cat_2"));
        category.add(new CategoryDomain("Hoodies","cat_3"));
        category.add(new CategoryDomain("Shorts","cat_4"));
        category.add(new CategoryDomain("Floppies","cat_5"));
        category.add(new CategoryDomain("Combos","cat_6"));
        category.add(new CategoryDomain("Family","cat_7"));

        adapter= new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
    private void RecyclerViewPopular(){
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<ClothingDomain> clothesList = new ArrayList<>();
        clothesList.add(new ClothingDomain("White Round Shirt","shirt1","White Shirt,logo in the middle,round neck,gender-neutral",150.00));
        clothesList.add(new ClothingDomain("Black Round Shirt","shirt2","Black Shirt,logo in the middle/elsewhere,round neck,gender-neutral",150.00));
        clothesList.add(new ClothingDomain("White Golfer Shirt","golfer1","White Shirt,logo in the middle/elsewhere,gender-neutral",200.00));
        clothesList.add(new ClothingDomain("Black Golfer Shirt","golfer2","Black golfer Shirt,logo in the middle/elsewhere,gender-neutral",200.00));
        clothesList.add(new ClothingDomain("White Long Shirt","sleeve1","White Long Sleeve Shirt,logo in the middle/elsewhere,gender-neutral",250.00));
        clothesList.add(new ClothingDomain("Black Long Shirt","sleeve2","Black Long Sleeve Shirt,logo in the middle/elsewhere,gender-neutral",250.00));
        clothesList.add(new ClothingDomain("White VNeck Shirt","vneck1","White V Neck Shirt,logo in the middle,gender-neutral",180.00));
        clothesList.add(new ClothingDomain("Black VNeck Shirt","vneck2","Black V Neck Shirt,logo in the middle,gender-neutral",180.00));
        clothesList.add(new ClothingDomain("Black Crop Top","crop1","White Crop Top,logo in the middle,gender:female",180.00));
        clothesList.add(new ClothingDomain("Black Crop Top","crop2","Black Crop Top,logo in the middle,gender:Female",180.00));
        clothesList.add(new ClothingDomain("White Trousers","trousers1","White trousers,logo on the left trouser,gender-neutral",250.00));
        clothesList.add(new ClothingDomain("Black Trousers","trousers2","Black Trousers,logo on the left trouser,gender-neutral",250.00));
        clothesList.add(new ClothingDomain("Black VNeck Shirt","vneck2","Black V Neck Shirt,logo in the middle,round neck,gender-neutral",180.00));
        clothesList.add(new ClothingDomain("Red Hat","hat1","Red Hat, one size fits all, gender neutral",100.00));

        adapter2 = new PopularAdaptor(clothesList);
        recyclerViewPopularList.setAdapter(adapter2);
    }
}