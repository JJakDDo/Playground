package com.example.kang.playground;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by kang on 2017. 11. 24..
 */
public class PlaysListAdapter extends BaseAdapter implements Filterable{

    private Context context;
    private List<Plays> playsList;
    private ArrayList<Plays> arrayList;
    private Typeface tf1, tf2;
    public PlaysListAdapter(Context context, List<Plays> playsList, ArrayList<Plays> arrayList) {
        this.context = context;
        this.playsList = playsList;
        this.arrayList = arrayList;
        tf1 = Typeface.createFromAsset(context.getAssets(), "NanumBarunpenB.ttf");
        tf2 = Typeface.createFromAsset(context.getAssets(), "NanumBarunpenR.ttf");
    }

    @Override
    public int getCount() {
        return playsList.size();
    }

    @Override
    public Object getItem(int position) {
        return playsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        PlaysViewHolder viewHolder;

        if(v == null) {
            v = View.inflate(context, R.layout.plays, null);

            viewHolder = new PlaysViewHolder();
            viewHolder.title = (TextView) v.findViewById(R.id.title);
            viewHolder.date = (TextView) v.findViewById(R.id.date);
            viewHolder.runTime = (TextView) v.findViewById(R.id.runTime);
            viewHolder.poster = (ImageView) v.findViewById(R.id.poster);
            viewHolder.theater = (TextView) v.findViewById(R.id.theater);

            viewHolder.title.setTypeface(tf1);
            viewHolder.date.setTypeface(tf2);
            viewHolder.runTime.setTypeface(tf2);
            viewHolder.theater.setTypeface(tf2);

            v.setTag(viewHolder);
        } else{
            viewHolder = (PlaysViewHolder) v.getTag();
        }

        viewHolder.title.setText(playsList.get(position).getTitle());
        if(playsList.get(position).getDate().length() < 15){
            viewHolder.date = setPartialColor("OPEN RUN", playsList.get(position).getDate(), "#FFFB5959", viewHolder.date);
        }
        else{
            int index = playsList.get(position).getDate().indexOf('~');
            viewHolder.date = setPartialColor(playsList.get(position).getDate().substring(index + 1), playsList.get(position).getDate().substring(0, index + 1), "#FFFB5959", viewHolder.date);
        }
        viewHolder.runTime.setText(playsList.get(position).getRunTime());
        viewHolder.theater.setText(playsList.get(position).getTheater());
        Glide.with(v).load(playsList.get(position).getPosterURL()).apply(new RequestOptions().override(1024, 1024)).into(viewHolder.poster);


        return v;
    }

    private TextView setPartialColor(String colorString, String string, String color, TextView textView){
        textView.setText("");
        SpannableStringBuilder builder = new SpannableStringBuilder(string + colorString);
        builder.setSpan(new ForegroundColorSpan(Color.parseColor(color)), string.length(), colorString.length() + string.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(builder);
        return textView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        playsList.clear();
        if(charText.length() == 0) {
            playsList.addAll(arrayList);
        }
        else {
            for (Plays p : arrayList) {
                if(p.getTitle().toLowerCase(Locale.getDefault()).contains(charText)){
                    playsList.add(p);
                }
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return null;
    }


    static class PlaysViewHolder
    {
        public TextView title;
        public TextView date;
        public TextView runTime;
        public TextView theater;
        public ImageView poster;
    }
}
