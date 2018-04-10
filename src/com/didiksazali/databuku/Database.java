package com.didiksazali.databuku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper{

	private static final String namaDatabase = "databuku.db";
	private static final int versiDatabase = 1;
	
	public Database(Context context) {
		super(context, namaDatabase, null, versiDatabase);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE buku (kode integer primary key, judul text null, penerbit text null);";
		Log.d("Data", "onCreate: " +sql);
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	
	
}
