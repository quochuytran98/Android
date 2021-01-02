package com.appsnipp.loginsamples.DAO;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.appsnipp.loginsamples.Data.DatabaseManager;

public class ProductDAO {
    Context thiscontext;
    private DatabaseManager data;

    public ProductDAO(Context thiscontext, DatabaseManager data) {
        this.thiscontext = thiscontext;
        this.data = data;
    }
    public Cursor GetDetailsProduct(Integer id){
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from product where id = id",null);
        return  cursor;

    }
}
