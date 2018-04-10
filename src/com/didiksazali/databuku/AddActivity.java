package com.didiksazali.databuku;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity{

	protected Cursor cursor;
	Database database;
	Button btnSave, btnBack;
	EditText edtKode, edtJudul, edtPenerbit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		database = new Database(this);
		edtKode = (EditText)findViewById(R.id.edtKode);
		edtJudul = (EditText)findViewById(R.id.edtJudul);
		edtPenerbit = (EditText)findViewById(R.id.edtPenerbit);
		btnSave = (Button)findViewById(R.id.btnSave);
		btnBack = (Button)findViewById(R.id.btnBack);
		
		btnSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = database.getWritableDatabase();
				String sql = "INSERT INTO buku (kode, judul, penerbit) VALUES ('" +edtKode.getText().toString()+ "' , '" +edtJudul.getText().toString()+ "' , '" +edtPenerbit.getText().toString()+ "' )";
				db.execSQL(sql);
				Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan", Toast.LENGTH_LONG).show();
				MainActivity.ma.refreshListView();
				finish();
		}
		});
		
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
	
	
	
}
