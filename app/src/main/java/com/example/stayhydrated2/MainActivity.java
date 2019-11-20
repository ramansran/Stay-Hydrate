package com.example.stayhydrated2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


// RamanJeet Singh - 1892488
// RamanDeep Kaur  - 1892891


public class MainActivity extends AppCompatActivity {
ListView listView;
int progress;
ImageView Droplet,Glass,Bottle;
List<Data> list = new ArrayList<Data>();
String expected_timeStamp;
MyAdapter myAdapter;
ProgressBar progressBar;
long mLastClickTime = 0;
SQLiteDatabase sq;
database dtbb;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dtbb = new database(this);
        myAdapter = new MyAdapter(this, (ArrayList<Data>) list);

        listView = findViewById(R.id.list);

        listView.setAdapter(myAdapter);

        progressBar = findViewById(R.id.progressBar);
        Droplet= findViewById(R.id.droplet);
        Glass   = findViewById(R.id.glass);
        Bottle  = findViewById(R.id.bottle);


        readData();



        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
             expected_timeStamp = new SimpleDateFormat("HH:mm - dd/MM", Locale.US).format(new Date());
                someHandler.postDelayed(this, 1000);
            }
        }, 10);




        Droplet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress += 50;
                progressBar.setProgress(progress);
                list.add(new Data(R.drawable.icons8_water_droplet,"50ml",expected_timeStamp));
                myAdapter.notifyDataSetChanged();

            }
        });

        Glass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress+= 200;
                progressBar.setProgress(progress);
                list.add(new Data(R.drawable.icons8_water_glass,"200ml",expected_timeStamp));
                myAdapter.notifyDataSetChanged();

            }
        });

        Bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progress+= 300;
                progressBar.setProgress(progress);
                list.add(new Data(R.drawable.icons8_water_bottle,"350ml",expected_timeStamp));

                myAdapter.notifyDataSetChanged();
            }
        });





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                long currTime = Calendar.getInstance().getTimeInMillis();
                if (currTime - mLastClickTime < ViewConfiguration.getDoubleTapTimeout()) {
                    onItemDoubleClick(parent, view, position, id);
                }
                mLastClickTime = currTime;
            }

            public void onItemDoubleClick(AdapterView<?> parent, View view, int position, long l) {

                if (list.get(position).getDrawable() == R.drawable.icons8_water_droplet) {
                    list.remove(position);
                    progress -= 50;
                    progressBar.setProgress(progress);
                } else if (list.get(position).getDrawable() == R.drawable.icons8_water_glass) {
                    list.remove(position);
                    progress -= 200;
                    progressBar.setProgress(progress);
                } else if (list.get(position).getDrawable() == R.drawable.icons8_water_bottle) {
                    list.remove(position);
                    progress -= 350;
                    progressBar.setProgress(progress);
                }

               myAdapter.notifyDataSetChanged();

            }
        });




    }


    @Override
    protected void onPause() {
        super.onPause();
        savedata();
    }

    public void savedata(){

        for (Data a : list){

            String quantity = a.getQty();
            String timeStamp = a.getDate();
            int imageid = a.getDrawable();
            int pV = progress;

            SQLiteDatabase sqLiteDatabase =dtbb.getWritableDatabase();

            Cursor cursor = dtbb.getData(sqLiteDatabase);

            if (cursor.getCount()>1)
            {
              dtbb.delete(sqLiteDatabase);

                for (Data b : list){

                    String quantity2 = b.getQty();
                    String timeStamp2 = b.getDate();
                    int imageid2 = b.getDrawable();
                    int pV2 = progress;

                    dtbb.addData(quantity2,timeStamp2,imageid2,pV2,sqLiteDatabase);
                }
            }

            else {
                dtbb.addData(quantity,timeStamp,imageid,pV,sqLiteDatabase);
            }

            dtbb.close();
            Toast.makeText(this, "saved", Toast.LENGTH_LONG).show();

        }



    }


    public void readData(){


        SQLiteDatabase sqLiteDatabase = dtbb.getReadableDatabase();

        Cursor cursor = dtbb.getData(sqLiteDatabase);

        while (cursor.moveToNext()){

            String one    = cursor.getString(cursor.getColumnIndex(database.Quantity));
            String two   = cursor.getString(cursor.getColumnIndex(database.TimeStamp));
            int threee    = cursor.getInt(cursor.getColumnIndex(database.ImageId));
            progress   = cursor.getInt(cursor.getColumnIndex(database.progressValue));

           Data data = new Data(threee,one,two);
           list.add(data);
           progressBar.setProgress(progress);
           myAdapter.notifyDataSetChanged();


        }

    }




}
