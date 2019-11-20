package com.example.stayhydrated2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter {
    Context context;

    private final ArrayList<Data> list;

    public MyAdapter(Context context, ArrayList<Data> listParams) {
        super(context, R.layout.rowdesign, (List) listParams);
        this.list = listParams;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowdesign, null,true);

        TextView historyDataText = rowView.findViewById(R.id.quantity);
        TextView historyDataTimestamp = rowView.findViewById(R.id.Dates);
        ImageView historyDataImage = rowView.findViewById(R.id.icon);


        historyDataText.setText(list.get(position).getQty());
        historyDataTimestamp.setText(list.get(position).getDate());
        historyDataImage.setImageResource(list.get(position).getDrawable());
        historyDataImage.setTag(list.get(position).getDrawable());

        return rowView;


    }

}
