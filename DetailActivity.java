package com.example.kang.playground;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by kang on 2017. 11. 24..
 */
public class DetailActivity extends AppCompatActivity {

    private String url;
    private int count = 0;

    //폰트적용할 엑티비티에 이 함수 적용하기
    @Override protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);

        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("1").setContent(R.id.tab1).setIndicator("공연정보");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("2").setContent(R.id.tab2).setIndicator("상세정보");

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);

        ImageButton backButton = (ImageButton)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title = (TextView)findViewById(R.id.title);
        TextView theater = (TextView)findViewById(R.id.theater);
        TextView date = (TextView)findViewById(R.id.date);
        TextView showTime = (TextView)findViewById(R.id.showTime);
        TextView runTime = (TextView)findViewById(R.id.runTime);
        TextView price = (TextView)findViewById(R.id.price);
        TextView discount = (TextView)findViewById(R.id.discount);
        TextView sponsor = (TextView)findViewById(R.id.sponsor);


        ImageView infoImage1 = (ImageView)findViewById(R.id.infoImage1);
        ImageView infoImage2 = (ImageView)findViewById(R.id.infoImage2);
        ImageView infoImage3 = (ImageView)findViewById(R.id.infoImage3);
        ImageView infoImage4 = (ImageView)findViewById(R.id.infoImage4);
        ImageView infoImage5 = (ImageView)findViewById(R.id.infoImage5);
        ImageView infoImage6 = (ImageView)findViewById(R.id.infoImage6);
        ImageView infoImage7 = (ImageView)findViewById(R.id.infoImage7);
        ImageView infoImage8 = (ImageView)findViewById(R.id.infoImage8);

        //Glide.with(this).load(intent.getStringExtra("posterURL").toString()).apply(new RequestOptions().override(4096, 4096)).into(image);
        title.setText(intent.getStringExtra("title").toString().equals(".")?" ":intent.getStringExtra("title").toString());
        theater.setText(intent.getStringExtra("theater").toString().equals(".")?" ":intent.getStringExtra("theater").toString());
        date.setText(intent.getStringExtra("date").toString().equals(".")?" ":intent.getStringExtra("date").toString());
        showTime.setText(intent.getStringExtra("showTime").toString().equals(".")?" ":intent.getStringExtra("showTime").toString());
        runTime.setText(intent.getStringExtra("runTime").toString().equals(".")?" ":intent.getStringExtra("runTime").toString());
        price.setText(intent.getStringExtra("price").toString().equals(".")?" ":intent.getStringExtra("price").toString());
        discount.setText(intent.getStringExtra("discount").toString().equals(".")?" ":intent.getStringExtra("discount").toString());
        sponsor.setText(intent.getStringExtra("sponsor").toString().equals(".")?" ":intent.getStringExtra("sponsor").toString());

        if(!intent.getStringExtra("infoImage1").toString().equals(" "))
            Glide.with(this).load(intent.getStringExtra("infoImage1").toString()).apply(new RequestOptions().override(1024, 4000)).into(infoImage1);
        if(!intent.getStringExtra("infoImage2").toString().equals(" "))
            Glide.with(this).load(intent.getStringExtra("infoImage2").toString()).apply(new RequestOptions().override(1024, 4000)).into(infoImage2);
        if(!intent.getStringExtra("infoImage3").toString().equals(" "))
            Glide.with(this).load(intent.getStringExtra("infoImage3").toString()).apply(new RequestOptions().override(1024, 4000)).into(infoImage3);
        if(!intent.getStringExtra("infoImage4").toString().equals(" "))
            Glide.with(this).load(intent.getStringExtra("infoImage4").toString()).apply(new RequestOptions().override(1024, 4000)).into(infoImage4);
        if(!intent.getStringExtra("infoImage5").toString().equals(" "))
            Glide.with(this).load(intent.getStringExtra("infoImage5").toString()).apply(new RequestOptions().override(1024, 4000)).into(infoImage5);
        if(!intent.getStringExtra("infoImage6").toString().equals(" "))
            Glide.with(this).load(intent.getStringExtra("infoImage6").toString()).apply(new RequestOptions().override(1024, 4000)).into(infoImage6);
        if(!intent.getStringExtra("infoImage7").toString().equals(" "))
            Glide.with(this).load(intent.getStringExtra("infoImage7").toString()).apply(new RequestOptions().override(1024, 4000)).into(infoImage7);
        if(!intent.getStringExtra("infoImage8").toString().equals(" "))
            Glide.with(this).load(intent.getStringExtra("infoImage8").toString()).apply(new RequestOptions().override(1024, 4000)).into(infoImage8);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
