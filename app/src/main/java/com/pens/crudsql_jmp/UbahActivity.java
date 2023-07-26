package com.pens.crudsql_jmp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pens.crudsql_jmp.db.DBSource;
import com.pens.crudsql_jmp.model.Barang;


public class UbahActivity extends AppCompatActivity {

    EditText edNama, edHarga, edMerk;
    Button btnSubmit, btnCancel;

    DBSource dbSource;


    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        edHarga = findViewById(R.id.ed_harga);
        edNama = findViewById(R.id.ed_nama);
        edMerk = findViewById(R.id.ed_merk);

        btnSubmit = findViewById(R.id.btn_submit);
        btnCancel = findViewById(R.id.btn_cancel);

        Bundle bun = this.getIntent().getExtras();

        id = bun.getLong("id");
        String harga = bun.getString("harga");
        String merk = bun.getString("merk");
        String nama = bun.getString("nama");

        edNama.setText(nama);
        edHarga.setText(harga);
        edMerk.setText(merk);

        dbSource = new DBSource(this);
        dbSource.open();

        btnSubmit.setOnClickListener(view -> updateData());

        btnCancel.setOnClickListener(view -> UbahActivity.this.finish());
    }

    private void updateData() {
        Barang barang = new Barang();
        barang.setNama(edNama.getText().toString().trim());
        barang.setHarga(edHarga.getText().toString().trim());
        barang.setMerk(edMerk.getText().toString().trim());
        barang.setId(id);
        dbSource.updateBarang(barang);

        Intent i = new Intent(UbahActivity.this, TampilActivity.class);

        startActivity(i);

        UbahActivity.this.finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbSource.close();

    }
}