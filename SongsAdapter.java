package com.justforyou.bestnarutosongs;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Harshil jain on 02/08/2016.
 */
public class SongsAdapter extends ArrayAdapter<Songs> {
    int ColorPath = -1;
    public SongsAdapter(Activity context, ArrayList<Songs> Songss, int colorPath)
    {
        super(context, 0, Songss);
        ColorPath = colorPath;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Songs currentword = getItem(position);

        View VconvertView = convertView;
        if(VconvertView == null)
        {
            VconvertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView TV1 = (TextView) VconvertView.findViewById(R.id.Name_text_view);
        TV1.setText(currentword.getNameOfSong());

        TV1 = (TextView) VconvertView.findViewById(R.id.Rate_text_view);
        TV1.setText(currentword.getDeveloperRate());

        ImageView IV1 = (ImageView) VconvertView.findViewById(R.id.Image_View);

        IV1.setImageResource(currentword.getImage());
        IV1.setVisibility(View.VISIBLE);


        View textContainer = VconvertView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), ColorPath);
        textContainer.setBackgroundColor(color);

        return VconvertView;


    }
}
