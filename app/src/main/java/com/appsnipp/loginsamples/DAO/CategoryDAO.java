package com.appsnipp.loginsamples.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.appsnipp.loginsamples.Data.DatabaseManager;

public class CategoryDAO {
    private  static final  String CATEGORY = "category";
    private  static  final  String ID_CATE = "id";
    private  static  final  String NAME_CATE = "name";
    private  static  final  String IMAGE_CATE = "image";
    private DatabaseManager dbb;
    Context thiscontext;

    public CategoryDAO(DatabaseManager dbb) {
        this.dbb = dbb;

    }

    public Cursor GetSSD(){
        SQLiteDatabase db = dbb.getWritableDatabase();
        String qry= "select * from product where id_cate = 1 ";
        Cursor cursor = db.rawQuery(qry,null);
        return  cursor;
//        int id = 1;
//        String where = ID_CATE + " = ?";
//        String[] whereArgs = {Integer.toString(id)};
//
//        return db.query(PRODUCT,null,where,whereArgs,null,null,null);
    }


}
