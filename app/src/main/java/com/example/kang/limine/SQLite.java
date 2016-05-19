package com.example.kang.limine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class SQLite extends AppCompatActivity {
    SQLiteDatabase sql;
    DBhelper helper;
    String DATABASENAME = "k12.db";
    String TABLENAME = "ksu12";
    String CARTNAME = "ksl12";
    String PARKNAME = "kajef12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            if (sql == null) {
                helper = new DBhelper(getApplicationContext(), DATABASENAME, null, 1);
                sql = helper.getWritableDatabase();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createTable(SQLiteDatabase db){
        try {
            if(db != null){
                db.execSQL("create table "+TABLENAME+" (name text, id text, email text, phone text, address text, num text)");
                db.execSQL("create table "+CARTNAME+" (itemname text, itemprice text, itemlocation text, itemurl text, itemhow int, itemcheck text)");
                db.execSQL("create table "+PARKNAME+" (id text,inh int, inm int, ins int, outh int, outm int, outs int)");
                Toast.makeText(getApplicationContext(), TABLENAME + " 최초1회", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),CARTNAME+" 최초1회",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),PARKNAME+" 최초 1회",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"DB생성 최초1회",Toast.LENGTH_LONG).show();
            }
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }
    class DBhelper extends SQLiteOpenHelper {

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            createTable(db);
        }

        public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
    }

}
