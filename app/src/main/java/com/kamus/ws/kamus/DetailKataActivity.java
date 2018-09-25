package com.kamus.ws.kamus;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailKataActivity extends AppCompatActivity {

    Intent i;
    String nama,deskripsi,namaGambar,getBookmark,getID;
    ImageView imgIlustasi;
    TextView txtNama,txtDeskripsi;
    private Uri gambarUri;
    int bookmark,ID;
    Button btnBookmark;
    DBAdapter mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kata);

        i = getIntent();
        nama = i.getStringExtra("nama");
        deskripsi = i.getStringExtra("deskripsi");
        namaGambar = i.getStringExtra("gambar");
        getBookmark = i.getStringExtra("bookmark");
        getID = i.getStringExtra("id");
        ID = Integer.parseInt(getID);

        bookmark = Integer.parseInt(getBookmark);

        imgIlustasi = (ImageView) findViewById(R.id.imgIlustrasi);
        txtNama = (TextView) findViewById(R.id.txtNama);
        txtDeskripsi = (TextView) findViewById(R.id.txtDeskripsi);
        btnBookmark = (Button) findViewById(R.id.btnBookmark);
        mDB = DBAdapter.getInstance(getApplicationContext());

        txtNama.setText(nama);
        txtDeskripsi.setText(deskripsi);

        String dataResourceDirectory = "drawable";
        String dataResoruceFilename = namaGambar;

        gambarUri  = Uri.parse("android.resource://" + getPackageName() + "/" +
                dataResourceDirectory + "/" + dataResoruceFilename);

       imgIlustasi.setImageURI(gambarUri);
        if (bookmark == 0){
            btnBookmark.setText("Bookmark This");
        }else {
            btnBookmark.setText("Bookmarked");
        }
    //    Toast.makeText(getApplicationContext(),"ID = "+ID,Toast.LENGTH_SHORT).show();

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bookmark == 0){

                    int marked = 1;
                    SQLiteDatabase db = mDB.getWritableDatabase();
                    db.execSQL("update tb_semua set isbookmark ='"+1+"' where id='"+ID+"'");
                    Toast.makeText(getApplicationContext(),"Berhasil Ditandai",Toast.LENGTH_LONG).show();
                    btnBookmark.setText("Bookmarked");

                }else {

                    Toast.makeText(getApplicationContext(),"Sudah di bookmark",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        i = new Intent(getApplicationContext(),MenuutamaActivity.class);
        startActivity(i);
    }
}
