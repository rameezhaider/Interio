package com.google.ar.core.examples.java.helloar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    //ArrayList to store data added to Adapter
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;

    // ListViewAdapter constructor
    public ListViewAdapter() {

    }

    // Returns the number of data used in the Adapter. : Required implementation
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        //Inflate "listview_item" Layout to get convertView reference.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        // Get a reference to the widget from the view to be displayed on the screen (the layout is inflated)
        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView1) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;

        // Get data reference located at position in Data Set(listViewItemList)
        ListViewItem listViewItem = listViewItemList.get(position);

        // Data is reflected in each widget within the item
        iconImageView.setImageDrawable(listViewItem.getIcon());
        titleTextView.setText(listViewItem.getTitle());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // Add data to the Adapter
    public void addItem(Drawable icon, String title) {
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);

        listViewItemList.add(item);
    }
}