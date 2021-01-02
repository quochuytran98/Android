package com.appsnipp.loginsamples.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.appsnipp.loginsamples.Model.Product;
import com.appsnipp.loginsamples.Model.Users;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {
    public  static  final String DATABASE_NAME = "db_andorid.db";
    private  static final  String USER = "users";
    private  static  final String ID = "id";
    private  static final  String NAME = "name";
    private  static final  String EMAIL = "email";
    private  static final  String PASSWORD = "password";

    private  static final  String CATEGORY = "category";
    private  static  final  String ID_CATE = "id";
    private  static  final  String NAME_CATE = "name";
    private  static  final  String IMAGE_CATE = "image";

    private  static final  String PRODUCT = "product";
    private  static  final  String ID_PRO = "id";
    private  static  final  String NAME_PRO = "name";
    private  static  final  String IMAGE_PRO = "image";
    private  static  final  String PRICE_PRODUCT = "price";
    private  static  final  String DESC_PRO = "description";
    private  static  final  String ID_CATEGORY = "id_cate";
    private  static  final  String TYPE_PRO = "type";
    private static SQLiteDatabase db ;

    private final Context context;


    public DatabaseManager( Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQLQuery = "CREATE TABLE " + USER + " (" + ID + " integer primary  key AUTOINCREMENT , " +
                NAME + " TEXT, " +
                EMAIL + " TEXT, " +
                PASSWORD + " TEXT) ";
        String SQLQueryCategory = "CREATE TABLE " + CATEGORY + " (" + ID_CATE + " integer primary  key AUTOINCREMENT , " +
                NAME_CATE + " TEXT, " +
                IMAGE_CATE + " TEXT ) ";
        String SQLQueryProduct = "CREATE TABLE " + PRODUCT + " (" + ID_PRO + " integer primary  key AUTOINCREMENT , " +
                NAME_PRO + " TEXT, " +
                PRICE_PRODUCT + " TEXT, " +
                IMAGE_PRO + " TEXT, " +
                ID_CATEGORY + " integer , " +
                TYPE_PRO + " integer , " +
                DESC_PRO + " TEXT ) ";



        sqLiteDatabase.execSQL(SQLQuery);
        sqLiteDatabase.execSQL(SQLQueryCategory);
        sqLiteDatabase.execSQL(SQLQueryProduct);
        // sqLiteDatabase.execSQL(SQLInsertCate);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+USER);
        db.execSQL("DROP TABLE IF EXISTS "+CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS "+PRODUCT);
        onCreate(db);

        Toast.makeText(context, "Drop successfully", Toast.LENGTH_SHORT).show();

    }


    public boolean checkmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" select * from users where email = ? ", new  String[]{email});
        if( cursor.getCount() > 0)
            return false;
        else
            return true;

    }


    public Cursor Profile_User(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" select * from users where email = ?", new  String[]{email });
        return cursor;
    }

    public Cursor getAllProducts(){
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = " select * from product";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;
    }

    public Cursor GetSSD(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from product where id_cate = 1",null);
        return  cursor;

    }
    public Cursor GetHDD(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from product where id_cate = 2",null);
        return  cursor;

    }
    public Cursor GetDetailsProduct(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from product where name = ?",new  String[]{String.valueOf(id)});
        return  cursor;

    }



}
