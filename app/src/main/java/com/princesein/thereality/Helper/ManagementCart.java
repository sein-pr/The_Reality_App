package com.princesein.thereality.Helper;

import android.content.Context;
import android.widget.Toast;

import com.princesein.thereality.Domain.ClothingDomain;
import com.princesein.thereality.Interface.ChangeNumberItemListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB= new TinyDB(context);
    }
    public void insertClothes(ClothingDomain item){
        ArrayList<ClothingDomain>listClothes=getListCart();
                boolean existAlready=false;
                int n=0;
        for (int i = 0; i < listClothes.size(); i++) {
            if (listClothes.get(i).getTitle().equals(item.getTitle())){
                existAlready=true;
                n=1;
                break;
            }
        }
        if (existAlready){
            listClothes.get(n).setNumberInCart(item.getNumberInCart());
        }else{
            listClothes.add(item);
        }
        tinyDB.putListObject("CartList",listClothes);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<ClothingDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<ClothingDomain>listClothes, int position, ChangeNumberItemListener changeNumberItemListener){
        listClothes.get(position).setNumberInCart(listClothes.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList",listClothes);
        changeNumberItemListener.changed();
    }

    public void minusNumberFood(ArrayList<ClothingDomain>listClothes, int position, ChangeNumberItemListener changeNumberItemListener){
        if (listClothes.get(position).getNumberInCart()==1){
            listClothes.remove(position);
        }else{
            listClothes.get(position).setNumberInCart(listClothes.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList",listClothes);
        changeNumberItemListener.changed();
    }

    public Double getTotalPrice(){
        ArrayList<ClothingDomain>listClothes = getListCart();
        double price=0;

        for (int i = 0; i < listClothes.size(); i++) {
            price = price+(listClothes.get(i).getPrice()*listClothes.get(i).getNumberInCart());
        }
        return price;
    }
}
