package com.example.cardatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class CarAdapter extends ArrayAdapter<Car> {

    Context parent_context;
    int layout_id;
    ArrayList<Car> al;

    public CarAdapter(Context context, int resource, ArrayList<Car> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        al = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tv = rowView.findViewById(R.id.tv);
        //ImageView iv = rowView.findViewById(R.id.iv);

        // Obtain the Android Version information based on the position
        Car currentCar = al.get(position);

        // Set values to the TextView to display the corresponding information
        tv.setText(currentCar.toString());



        return rowView;
    }
}
