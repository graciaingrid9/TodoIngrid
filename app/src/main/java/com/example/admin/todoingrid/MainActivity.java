package com.example.admin.todoingrid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final Object FileUtils =;
    ArrayList<String> items = new ArrayList<String>(FileUtils.readLines(getDataFile(),Charset.defaultCharset()));
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);
        //mock data
        items.add("First item");
        items.add("second item");

        setupListViewListtener();
    }
        public void onAddItem(View v) {
            EditText etNewItems = (EditText) findViewById(R.id.etNewItem);
            String itemText = etNewItems.getText().toString();
            itemsAdapter.add(itemText);
            etNewItems.setText("");
            Toast.makeText(getApplicationContext(),"Item added to list", Toast.LENGTH_SHORT).show();

    }
private void setupListViewListtener() {
        Log.i("MainActivity", "Setting up listener on list view");
    lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Log.i("MainActivity", ("Item remove from list");
            items.remove(position);
            itemsAdapter.notifyDataSetChanged();
            writeItems();
            return true;
        }
    });
}

private File getDataFile(){
        return new File(getFilesDir(),"todo.txt");
    }

    private void readItems(){
        try{
            items = new ArrayList<String>(FileUtils.readLines(getDataFile(),Charset.defaultCharset()));
        } catch (IOException e){
            Log.e("MainActivity", "Error reading file",e);
            items = new ArrayList<>();
            }
        }
        private void writeItems(){
        try{
            FileUtils.writeLines(getDataFile(),items);
        } catch (IOException e){
            Log.e("MainActivity", "Error wriing file", e);
        }
    }
}
