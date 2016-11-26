/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;


/**
 *
 * @author HungPham
 */
public class Food {

    int mafood;
    String foodname;
    Date dateupdateprice;
    int price;
    String picture;
    int soluong;
    public Food(int mafood, String foodname, int price, String picture) {
        this.mafood = mafood;
        this.foodname = foodname;
        this.price = price;
        this.picture = picture;
    }
    public Food(int mafood){
    	this.mafood = mafood;
    }
    
    public Food(String foodname){
    	this.foodname = foodname;
    }
    
    public Food(int mafood, String foodname) {
        this.mafood = mafood;
        this.foodname = foodname;
    }

    public String getPicture() {
        return picture;
    }

    @Override
    public String toString() {
        return foodname;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Food(int mafood, String foodname, Date dateupdateprice, int price, String picture) {
        this.mafood = mafood;
        this.foodname = foodname;
        this.dateupdateprice = dateupdateprice;
        this.price = price;
        this.picture = picture;
    }

    public int getMafood() {
        return mafood;
    }

    public void setMafood(int mafood) {
        this.mafood = mafood;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public Date getDateupdateprice() {
        return dateupdateprice;
    }

    public void setDateupdateprice(Date dateupdateprice) {
        this.dateupdateprice = dateupdateprice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
