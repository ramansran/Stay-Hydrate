package com.example.stayhydrated2;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Timestamp;

import static android.os.FileObserver.DELETE;
import static javax.xml.datatype.DatatypeConstants.DATETIME;

public class database extends SQLiteOpenHelper {


    public static final String DatabaseName ="StayHydreateDatabase3";
    public static final int    DatabaseVersion = 3;


    public static final String TableName = "StayHydrateDataTable";
    public static final String Quantity = "Quantity";
    public static final String TimeStamp = "TimeStamp";
    public static final String ImageId = "id";
    public static final String progressValue = "progressValue";



    public static final String CreateTable =" create table " + TableName + "(" + Quantity + " text , " +
            TimeStamp + " text , " + ImageId + " INTEGER , " + progressValue + " INTEGER );";

    public static final String DropTable = " drop table if exists "+ TableName;

    private MainActivity context;

    public database(MainActivity context) {

        super(context,DatabaseName, null, DatabaseVersion);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CreateTable);
        Log.d("DataBase Operation","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int currentVersion ,int newVersion) {

        db.execSQL(DropTable);
        onCreate(db);

    }



    public void addData(String qntity, String timestmp, int imgid,int pv,SQLiteDatabase dtb){


        ContentValues contentValues =new ContentValues();

        contentValues.put(Quantity,qntity);
        contentValues.put(TimeStamp,timestmp);
        contentValues.put(ImageId,imgid);
        contentValues.put(progressValue,pv);


        dtb.insert(TableName,null,contentValues);

        Log.d("Databse Operation","Database Craeted");

    }


    public Cursor getData(SQLiteDatabase database){
        String [] projections ={Quantity,TimeStamp, String.valueOf(ImageId), String.valueOf(progressValue)};

        Cursor cursor = database.query(TableName,projections,null,null,null,null
        ,null);
        return cursor;
    }



    public void delete(SQLiteDatabase db){
        db.execSQL("delete from " + TableName);
    }



}
