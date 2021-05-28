package com.novika.myspending;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SpendingsDB {
    private DBHelper dbHelper;



    public SpendingsDB(Context ctx){
        dbHelper = new DBHelper(ctx);
    }

    public void insertProduct(Spendings spendings) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_NAME, spendings.name);
        cv.put(DBHelper.FIELD_NOMINAL, spendings.nominal);
        cv.put(DBHelper.FIELD_DATE, spendings.date);

        db.insert(DBHelper.TABLE_NAME, null, cv);

        db.close();
    }

    public void updateProduct(Spendings spendings, int idDipilih){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_NAME, spendings.name);
        cv.put(DBHelper.FIELD_NOMINAL, spendings.nominal);

        String where = "id=?";
        String[] whereArgs = {"" + idDipilih};

        db.update(DBHelper.TABLE_NAME, cv, where, whereArgs);

        db.close();

    }


    public ArrayList<Spendings> getAllProducts(){

        ArrayList<Spendings> spendingArray = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);

        Spendings spendings = null;

        if (cursor.moveToFirst()){

            do {

                int idx_id = cursor.getColumnIndex(DBHelper.FIELD_ID);
                int idx_name = cursor.getColumnIndex(DBHelper.FIELD_NAME);
                int idx_nom = cursor.getColumnIndex(DBHelper.FIELD_NOMINAL);
                int idx_date = cursor.getColumnIndex(DBHelper.FIELD_DATE);

//                spendings.id = cursor.getInt(idx_id);
//                spendings.name = cursor.getString(idx_name);
//                spendings.nominal = cursor.getInt(idx_nom);
//                spendings.date = cursor.getString(idx_date);

                spendings = new Spendings(cursor.getString(idx_name), cursor.getString(idx_date), cursor.getInt(idx_id), cursor.getInt(idx_nom));
                spendingArray.add(spendings);
            } while (cursor.moveToNext());
        }


        cursor.close();
        db.close();

        return spendingArray;
    }

    public Spendings getProduct(int id){

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "id=?";
        String[] selectionArgs = {"" + id};

        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, selection, selectionArgs, null, null, null);

        Spendings spendings = null;

        if (cursor.moveToFirst()){
//            spendings = new Spendings();

            int idx_id = cursor.getColumnIndex(DBHelper.FIELD_ID);
            int idx_name = cursor.getColumnIndex(DBHelper.FIELD_NAME);
            int idx_nom = cursor.getColumnIndex(DBHelper.FIELD_NOMINAL);
            int idx_date = cursor.getColumnIndex(DBHelper.FIELD_DATE);

//            spendings.id = cursor.getInt(idx_id);
//            spendings.name = cursor.getString(idx_name);
//            spendings.nominal = cursor.getInt(idx_nom);
//            spendings.date = cursor.getString(idx_date);

            spendings = new Spendings(cursor.getString(idx_name), cursor.getString(idx_date), cursor.getInt(idx_id), cursor.getInt(idx_nom));
        }

        cursor.close();
        db.close();

        return spendings;
    }
}
