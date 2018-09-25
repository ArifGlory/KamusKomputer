package com;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.kamus.ws.kamus.DBAdapter;
import com.kamus.ws.kamus.DetailKataActivity;
import com.kamus.ws.kamus.R;

import java.util.ArrayList;
import java.util.List;


public class PenandaFragment extends Fragment {

    private FragmentListener listener;
    DBAdapter mDB;
    protected Cursor cursor;
    Button btnHapus;
    SQLiteDatabase db;
    public static List<String> list_id = new ArrayList();
    public static List<String> list_deskripsi = new ArrayList();
    public static List<String> list_gambar = new ArrayList();
    public static List<String> list_bookmark = new ArrayList();
    Intent i;

    public PenandaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mDB = DBAdapter.getInstance(getActivity());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_penanda, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        list_id.clear();
        list_bookmark.clear();
        list_deskripsi.clear();
        list_gambar.clear();

        db = mDB.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_semua where isbookmark ='1'",null);
        final String[] source = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            source[cc] = cursor.getString(1).toString();
            list_id.add(cursor.getString(0).toString());
            list_deskripsi.add(cursor.getString(2).toString());
            list_gambar.add(cursor.getString(5).toString());
            list_bookmark.add(cursor.getString(4).toString());
        }

        ListView penandaList = (ListView) view.findViewById(R.id.penandaList);
        btnHapus = (Button) view.findViewById(R.id.btnHapus);
        final PenandaAdapter adapter = new PenandaAdapter(getActivity(), source);
        penandaList.setAdapter(adapter);

        adapter.setOnItemClick(new ListItemListener() {
            @Override
            public void onItemClick(int position) {

               // Toast.makeText(getActivity()," ID : "+list_id.get(position).toString(),Toast.LENGTH_SHORT).show();

                i = new Intent(getActivity(), DetailKataActivity.class);
                i.putExtra("nama",source[position].toString());
                i.putExtra("deskripsi",list_deskripsi.get(position).toString());
                i.putExtra("gambar",list_gambar.get(position).toString());
                i.putExtra("bookmark",list_bookmark.get(position).toString());
                i.putExtra("id",list_id.get(position).toString());
                startActivity(i);
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (adapter.isEmpty()){
                    Toast.makeText(getActivity()," Penanda kosong",Toast.LENGTH_SHORT).show();
                }else {
                    SQLiteDatabase db = mDB.getWritableDatabase();
                    db.execSQL("update tb_semua set isbookmark ='"+0+"'");

                    adapter.removeAll();
                    adapter.notifyDataSetChanged();

                    Toast.makeText(getActivity()," Semua Penanda dihapus",Toast.LENGTH_SHORT).show();
                }

            }
        });


        adapter.setOnItemDeleteClick(new ListItemListener() {
            @Override
            public void onItemClick(int position) {
                SQLiteDatabase db = mDB.getWritableDatabase();
                int ID = Integer.parseInt(list_id.get(position).toString());
                db.execSQL("update tb_semua set isbookmark ='"+0+"' where id='"+ID+"'");

                Toast.makeText(getActivity()," Di hapus dari Penanda",Toast.LENGTH_SHORT).show();
                adapter.removeItem(position);
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setOnFragmentListener(FragmentListener listener) {
        this.listener = listener;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       // inflater.inflate(R.menu.menu_hapus, menu);
    }
}
