package com.example.stayhydrated2;

import android.graphics.drawable.Drawable;

public class Data {

    int drawable;
    String Qty;
    String date;

    public Data(int drawable, String qty, String date) {
        this.drawable = drawable;
        Qty = qty;
        this.date = date;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
