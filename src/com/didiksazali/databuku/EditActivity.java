package com.didiksazali.databuku;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends Activity{

	protected Cursor cursor;
	Database database;
	Button btnEdit, btnBack;
	EditText edtKode, edtJudul, edtPenerbit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		database = new Database(this);
		edtKode = (EditText)findViewById(R.id.edtKode);
		edtJudul = (EditText)findViewById(R.id.edtJudul);
		edtPenerbit = (EditText)findViewById(R.id.edtPenerbit);
		SQLiteDatabase db = database.getReadableDatabase();
		cursor = db.rawQuery("SELECT * FROM buku WHERE kode = '" +getIntent().getStringExtra("kode")+ "'", null);
		cursor.moveToFirst();
		if (cursor.getCount()>0) {
			cursor.moveToPosition(0);
			edtKode.setText(cursor.getString(0).toString());
			edtJudul.setText(cursor.getString(1).toString());
			edtPenerbit.setText(cursor.getString(2).toString());
		}
		
		btnEdit = (Button)findViewById(R.id.btnEdit);
		btnBack = (Button)findViewById(R.id.btnBack);
		
		btnEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db = database.getWritableDatabase();
				String sql = "UPDATE buku SET judul = '" +edtJudul.getText().toString()+ "' , penerbit = '" +edtPenerbit.getText().toString()+ "' WHERE kode = '" +edtKode.getText().toString()+ "' ";
				db.execSQL(sql);
				Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
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
