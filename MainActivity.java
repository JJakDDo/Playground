package com.example.kang.playground;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.tsengvn.typekit.TypekitContextWrapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private PlaysListAdapter adapter;
    private List<Plays> playsList;
    private ArrayList<Plays> arrayList;
    private String today;
    private EditText searchText;
    private InputMethodManager imm;

    @Override protected void attachBaseContext(Context newBase){
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "";


        imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        listView = (ListView) findViewById(R.id.listViewPlays);
        playsList = new ArrayList<Plays>();
        arrayList = new ArrayList<Plays>();
        adapter = new PlaysListAdapter(getApplicationContext(), playsList, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id){
                        Intent detailIntent = new Intent(getApplicationContext(), DetailActivity.class);
                        detailIntent.putExtra("title", playsList.get(i).getTitle());
                        detailIntent.putExtra("date", playsList.get(i).getDate());
                        detailIntent.putExtra("runTime", playsList.get(i).getRunTime());
                        detailIntent.putExtra("theater", playsList.get(i).getTheater());
                        detailIntent.putExtra("posterURL", playsList.get(i).getPosterURL());
                        detailIntent.putExtra("sponsor", playsList.get(i).getSponsor());
                        detailIntent.putExtra("price", playsList.get(i).getPrice());
                        detailIntent.putExtra("discount", playsList.get(i).getDiscount());
                        detailIntent.putExtra("showTime", playsList.get(i).getShowTime());
                        detailIntent.putExtra("infoText", playsList.get(i).getInfoText());
                        detailIntent.putExtra("infoImage1", playsList.get(i).getInfoImage1());
                        detailIntent.putExtra("infoImage2", playsList.get(i).getInfoImage2());
                        detailIntent.putExtra("infoImage3", playsList.get(i).getInfoImage3());
                        detailIntent.putExtra("infoImage4", playsList.get(i).getInfoImage4());
                        detailIntent.putExtra("infoImage5", playsList.get(i).getInfoImage5());
                        detailIntent.putExtra("infoImage6", playsList.get(i).getInfoImage6());
                        detailIntent.putExtra("infoImage7", playsList.get(i).getInfoImage7());
                        detailIntent.putExtra("infoImage8", playsList.get(i).getInfoImage8());
                        startActivity(detailIntent);
                    }
                }
        );

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        today = sdf.format(date);

        searchText = (EditText) findViewById(R.id.searchText);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //AdapterCall
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = searchText.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }
        });
        searchText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });


        BackgroundTask backgroundTask = new BackgroundTask(url);
        backgroundTask.execute();
    }

    public class BackgroundTask extends AsyncTask<Void, Void, String> {

        private String url;


        public BackgroundTask(String url) {
            this.url = url;
        }
        @Override
        protected String doInBackground(Void... params) {
            String result;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url);
            return result;
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);

            try {
                playsList.clear();
                Date today = new Date();
                SimpleDateFormat sdfToday = new SimpleDateFormat("yyyy-MM-dd");
                String strToday = sdfToday.format(today);
                System.out.println(strToday);
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String genre;
                String title;
                String date;
                String runTime;
                String theater;
                String posterURL;
                String sponsor;
                String price;
                String discount;
                String showTime;
                String infoText;
                String infoImage1;
                String infoImage2;
                String infoImage3;
                String infoImage4;
                String infoImage5;
                String infoImage6;
                String infoImage7;
                String infoImage8;
                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    title = object.getString("title");
                    genre = object.getString("genre");
                    if(object.getString("endDate").equals("0000-00-00"))
                        date = object.getString("startDate") + " ~ ";
                    else if(object.getString("endDate").compareTo(strToday) < 0){
                        count++;
                        continue;
                    }
                    else date = object.getString("startDate") + " ~ " + object.getString("endDate");
                    runTime = object.getString("runTime");
                    posterURL = object.getString("image");
                    theater = object.getString("theater");
                    sponsor = object.getString("sponsor");
                    showTime = object.getString("showTime");
                    infoText = object.getString("infoText");
                    infoImage1 = object.getString("infoImage1");
                    infoImage2 = object.getString("infoImage2");
                    infoImage3 = object.getString("infoImage3");
                    infoImage4 = object.getString("infoImage4");
                    infoImage5 = object.getString("infoImage5");
                    infoImage6 = object.getString("infoImage6");
                    infoImage7 = object.getString("infoImage7");
                    infoImage8 = object.getString("infoImage8");
                    price = object.getString("price");
                    discount = object.getString("discount");
                    Plays plays = new Plays(genre, title, date.replace("-", "."), runTime, theater, posterURL, sponsor, price, discount, showTime, infoText, infoImage1, infoImage2, infoImage3, infoImage4, infoImage5, infoImage6, infoImage7, infoImage8);
                    playsList.add(plays);
                    arrayList.add(plays);
                    count++;
                }

                if(count == 0){
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    dialog = builder.setMessage("NO RESULT!!!")
                            .setPositiveButton("OK", null)
                            .create();
                    dialog.show();
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
