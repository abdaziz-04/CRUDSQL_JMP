package com.pens.crudsql_jmp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pens.crudsql_jmp.db.DBSource;
import com.pens.crudsql_jmp.model.Barang;

import java.util.ArrayList;

public class TampilActivity extends AppCompatActivity {

    ListView listView;

    private DBSource dbSource;

    private ArrayList<Barang> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        listView = findViewById(R.id.listView);

        dbSource = new DBSource(this);
        dbSource.open();
        values = dbSource.getAllBarang();

        ArrayAdapter<Barang> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Barang b = (Barang) adapterView.getAdapter().getItem(i);

            Barang edit = dbSource.getBarang(b.getId());

            Intent intent = new Intent(TampilActivity.this, UbahActivity.class);
            Bundle bun = new Bundle();

            bun.putLong("id", edit.getId());
            bun.putString("nama", edit.getNama());
            bun.putString("harga", edit.getHarga());
            bun.putString("merk", edit.getMerk());
            intent.putExtras(bun);

            startActivity(intent);

            TampilActivity.this.finish();
        });


        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {

            Barang b = (Barang) adapterView.getAdapter().getItem(i);
            dbSource.deleteBarang(b.getId());
            finish();
            startActivity(getIntent());
            return true;
        });

    }


    @Override
    protected void onStop() {
        super.onStop();

        dbSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();

        dbSource.open();
    }
}