package com.didiksazali.databuku;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends Activity{

	protected Cursor cursor;
	Database database;
	Button btnBack;
	TextView txtKode, txtJudul, txtPenerbit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		database = new Database(this);
		txtKode = (TextView)findViewById(R.id.txtKode);
		txtJudul = (TextView)findViewById(R.id.txtJudul);
		txtPenerbit = (TextView)findViewById(R.id.txtPenerbit);
		
		SQLiteDatabase db = database.getWritableDatabase();
		cursor = db.rawQuery("SELECT * FROM buku WHERE kode = '" +getIntent().getStringExtra("kode")+ "' ", null);
		cursor.moveToFirst();
		if (cursor.getCount()>0) {
			cursor.moveToPosition(0);
			txtKode.setText(cursor.getString(0).toString());
			txtJudul.setText(cursor.getString(1).toString());
			txtPenerbit.setText(cursor.getString(2).toString());
		}
		
		btnBack = (Button)findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
				
		 
	}

	
	
}
