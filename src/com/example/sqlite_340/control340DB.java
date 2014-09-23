package com.example.sqlite_340;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler.Value;
import android.util.Log;
public class control340DB extends SQLiteOpenHelper{
	private static final String DATABASE_NAME= "mydata340";
	private static final String TABLE_MEMBER = "members";
	private static final int DATABASE_VERSION =1;
	
	
	
	
	public control340DB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE " + TABLE_MEMBER
				+ "(MemberId INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "Name TEXT(100),"
				+ "Tel TEXT(100)); ");
		Log.d("CREATE TABLE", "Create Table Successfully");
		
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
		onCreate(db);
		
	
		
	}
	public long InsertData(String strName, String strTel) {
		try {
			SQLiteDatabase db;
			db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("Name", strName);
			values.put("Tel", strTel);
			long rows = db.insert(TABLE_MEMBER, null, values);
			db.close();
			return rows;
		} catch (Exception e) {
			return -1;
		}
	}
	
public String[] CheckData(String strMemberID) {
    try {
        String arrData[] = null;
        SQLiteDatabase db;
        db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MEMBER, new String[] { "*" },
                "MemberID=?", new String[] { String.valueOf(strMemberID) },
                null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                arrData = new String[cursor.getColumnCount()];
                arrData[0] = cursor.getString(0);
                arrData[1] = cursor.getString(1);
                arrData[2] = cursor.getString(2);
            }
        }
        cursor.close();
        db.close();
        return arrData;
    } catch (Exception e) {
        return null;
    }
}
   //Select All Data
    public ArrayList<HashMap<String,String>> SelectAllData(){
    	try{
    		ArrayList<HashMap<String, String>> arrayList =
    				new ArrayList<HashMap<String, String>>();
    		HashMap<String, String> map;
    		
    		SQLiteDatabase db;
    		db = this.getReadableDatabase();
    		
    		String strSQL = "SELECT * FROM " + TABLE_MEMBER;
    		Cursor cursor = db.rawQuery(strSQL, null);
    		
    		if(cursor !=null){
    			if(cursor.moveToFirst()){
    				do{
    					map = new HashMap<String, String>();
    					map.put("MemberID", cursor.getString(0));
    					map.put("Name", cursor.getString(1));
    					map.put("Tel", cursor.getString(2));
    					arrayList.add(map);
    				}while (cursor.moveToNext());
    			}
    		}
    		cursor.close();
    		db.close();
    		return arrayList;
    		 }catch (Exception e) {
    	return null;
    		 }
}
}