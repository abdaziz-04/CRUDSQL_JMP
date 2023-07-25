package com.pens.crudsql_jmp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pens.crudsql_jmp.db.DBSource;
import com.pens.crudsql_jmp.model.Barang;

public class TambahActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSubmit;

    EditText edNama, edHarga, edMerk;

    DBSource dbSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        edNama = findViewById(R.id.ed_nama);
        edHarga = findViewById(R.id.ed_harga);
        edMerk = findViewById(R.id.ed_merk);

        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(this);

        dbSource = new DBSource(this);
        dbSource.open();

    }

    @Override
    public void onClick(View view) {
        String nama = null;
        String merk = null;
        String harga = null;

        Barang barang = null;

        if(TextUtils.isEmpty(edNama.getText().toString().trim())
                && TextUtils.isEmpty((edHarga.getText().toString().trim()))
                && TextUtils.isEmpty(edMerk.getText().toString().trim())
        ){

            edNama.setError("Mohon Isi Data");
            edHarga.setError("Mohon Isi Data");
            edMerk.setError("Mohon Isi Data");

        }else if(TextUtils.isEmpty((edNama.getText().toString().trim())) ){
            edNama.setError("Mohon Isi Nama");

        }else if(TextUtils.isEmpty((edHarga.getText().toString().trim())) ){
            edHarga.setError("Mohon Isi Harga");

        }else if(TextUtils.isEmpty((edMerk.getText().toString().trim())) ){
            edMerk.setError("Mohon Isi Merk");

        }{
            nama = edNama.getText().toString().trim();
            harga = edHarga.getText().toString().trim();
            merk = edMerk.getText().toString().trim();

            if (view.getId() == R.id.btn_submit) {
                barang = dbSource.createBarang(nama, merk, harga);
                Toast.makeText(this, "Berhasil ditambah", Toast.LENGTH_LONG).show();
                finish();
            }


        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        dbSource.close();
    }

}