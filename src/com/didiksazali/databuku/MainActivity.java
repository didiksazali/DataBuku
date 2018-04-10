package com.didiksazali.databuku;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	Button btnAdd;
	String[] daftarBuku;
	ListView lvBuku;
	Menu menu;
	protected Cursor cursor;
	Database database;
	public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, AddActivity.class);
				startActivity(intent);
			}
		});
        
        ma = this;
        database = new Database(this);
        refreshListView();
        
    }
    
    public void refreshListView(){
    	SQLiteDatabase db = database.getReadableDatabase();
    	cursor = db.rawQuery("SELECT * FROM buku", null);
    	daftarBuku = new String[cursor.getCount()];
    	cursor.moveToFirst();
    	
    	for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToPosition(i);
			daftarBuku[i] = cursor.getString(0).toString();
		}
    	
    	lvBuku = (ListView)findViewById(R.id.lvBuku);
    	lvBuku.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, daftarBuku));
    	lvBuku.setSelected(true);
    	lvBuku.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				final String pilihan = daftarBuku[arg2];
				final CharSequence[] dialogItem = {"View", "Edit", "Delete"};
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Pilih Action");
				builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int item) {
						// TODO Auto-generated method stub
						switch (item) {
						case 0:
							Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
							intent.putExtra("kode", pilihan);
							startActivity(intent);
							break;
							
						case 1:
							Intent intent2 = new Intent(getApplicationContext(), EditActivity.class);
							intent2.putExtra("kode", pilihan);
							startActivity(intent2);
							break;
							
						case 2:
							SQLiteDatabase db = database.getWritableDatabase();
							String sql = "DELETE FROM buku WHERE kode = '"+pilihan+"' ";
							db.execSQL(sql);
							refreshListView();
							break;

						default:
							break;
						}
					}
				});
				builder.create().show();
			}
    		
		});
   
    	((ArrayAdapter<?>)lvBuku.getAdapter()).notifyDataSetInvalidated();
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
