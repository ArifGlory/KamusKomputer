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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.kamus.ws.kamus.DBAdapter;
import com.kamus.ws.kamus.DetailKataActivity;
import com.kamus.ws.kamus.R;

import java.util.ArrayList;
import java.util.List;


public class AmusFragment extends Fragment {

    private String value = "Selamat Datang";
    private FragmentListener listener;
    ArrayAdapter<String> adapter;
    ListView diclist;
    DBAdapter mDB;
    protected Cursor cursor;
    SQLiteDatabase db;
    public static List<String> list_id = new ArrayList();
    public static List<String> list_deskripsi = new ArrayList();
    public static List<String> list_gambar = new ArrayList();
    public static List<String> list_bookmark = new ArrayList();
    Intent i;

    public AmusFragment() {
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
        return inflater.inflate(R.layout.fragment_amus, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


             diclist = view.findViewById(R.id.listkamus);

        list_id.clear();
        list_deskripsi.clear();
        list_gambar.clear();
        list_bookmark.clear();

        db = mDB.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_semua",null);
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


        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,source);
        diclist.setAdapter(adapter);
        diclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               /* if(listener!= null)
                listener.onItemClick(getListOfWords()[position]);*/
            //    Toast.makeText(getActivity(),"isbookmark = "+list_bookmark.get(position).toString(),Toast.LENGTH_LONG).show();

                i = new Intent(getActivity(), DetailKataActivity.class);
                i.putExtra("nama",source[position].toString());
                i.putExtra("deskripsi",list_deskripsi.get(position).toString());
                i.putExtra("gambar",list_gambar.get(position).toString());
                i.putExtra("bookmark",list_bookmark.get(position).toString());
                i.putExtra("id",list_id.get(position).toString());
                startActivity(i);
            }
        });
    }

    public void resetDataSource(String[] source){
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,source);
        diclist.setAdapter(adapter);
    }

    public void kategoriSemua(){
        list_id.clear();
        list_deskripsi.clear();
        list_gambar.clear();
        list_bookmark.clear();

        db = mDB.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_semua",null);
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


        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,source);
        diclist.setAdapter(adapter);
        diclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               /* if(listener!= null)
                listener.onItemClick(getListOfWords()[position]);*/

                i = new Intent(getActivity(), DetailKataActivity.class);
                i.putExtra("nama",source[position].toString());
                i.putExtra("deskripsi",list_deskripsi.get(position).toString());
                i.putExtra("gambar",list_gambar.get(position).toString());
                i.putExtra("bookmark",list_bookmark.get(position).toString());
                i.putExtra("id",list_id.get(position).toString());
                startActivity(i);
            }
        });
    }

    public void kategoriHardware(){
        list_id.clear();
        list_deskripsi.clear();
        list_gambar.clear();
        list_bookmark.clear();

        db = mDB.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_semua where kategori = 'Hardware'",null);
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


        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,source);
        diclist.setAdapter(adapter);
        diclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               /* if(listener!= null)
                listener.onItemClick(getListOfWords()[position]);*/
                i = new Intent(getActivity(), DetailKataActivity.class);
                i.putExtra("nama",source[position].toString());
                i.putExtra("deskripsi",list_deskripsi.get(position).toString());
                i.putExtra("gambar",list_gambar.get(position).toString());
                i.putExtra("bookmark",list_bookmark.get(position).toString());
                i.putExtra("id",list_id.get(position).toString());
                startActivity(i);
            }
        });
    }

    public void kategoriSoftware(){
        list_id.clear();
        list_deskripsi.clear();
        list_gambar.clear();
        list_bookmark.clear();

        db = mDB.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_semua where kategori = 'Software'",null);
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


        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,source);
        diclist.setAdapter(adapter);
        diclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               /* if(listener!= null)
                listener.onItemClick(getListOfWords()[position]);*/
                i = new Intent(getActivity(), DetailKataActivity.class);
                i.putExtra("nama",source[position].toString());
                i.putExtra("deskripsi",list_deskripsi.get(position).toString());
                i.putExtra("gambar",list_gambar.get(position).toString());
                i.putExtra("bookmark",list_bookmark.get(position).toString());
                i.putExtra("id",list_id.get(position).toString());
                startActivity(i);
            }
        });
    }

    public void kategoriJaringan(){
        list_id.clear();
        list_deskripsi.clear();
        list_gambar.clear();
        list_bookmark.clear();

        db = mDB.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_semua where kategori = 'Jaringan'",null);
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


        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,source);
        diclist.setAdapter(adapter);
        diclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               /* if(listener!= null)
                listener.onItemClick(getListOfWords()[position]);*/
                i = new Intent(getActivity(), DetailKataActivity.class);
                i.putExtra("nama",source[position].toString());
                i.putExtra("deskripsi",list_deskripsi.get(position).toString());
                i.putExtra("gambar",list_gambar.get(position).toString());
                i.putExtra("bookmark",list_bookmark.get(position).toString());
                i.putExtra("id",list_id.get(position).toString());
                startActivity(i);
            }
        });
    }

    public void kategoriPemrograman(){
        list_id.clear();
        list_deskripsi.clear();
        list_gambar.clear();
        list_bookmark.clear();

        db = mDB.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_semua where kategori = 'Pemrograman'",null);
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


        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,source);
        diclist.setAdapter(adapter);
        diclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               /* if(listener!= null)
                listener.onItemClick(getListOfWords()[position]);*/
                i = new Intent(getActivity(), DetailKataActivity.class);
                i.putExtra("nama",source[position].toString());
                i.putExtra("deskripsi",list_deskripsi.get(position).toString());
                i.putExtra("gambar",list_gambar.get(position).toString());
                i.putExtra("bookmark",list_bookmark.get(position).toString());
                i.putExtra("id",list_id.get(position).toString());
                startActivity(i);
            }
        });
    }

    public void filterValue(String value){
//        adapter.getFilter().filter(value);
        int size = adapter.getCount();
        for (int i = 0 ;  i<size;i++){
            if(adapter.getItem(i).startsWith(value)){
                diclist.setSelection(i);
                break;
            }
        }
    }

    String[] getListOfWords(){
        String[] source = new String[]{
                "a"
                ,"abend (Abnormal end)"
                ,"ABI (Application Binary Interface)"
                ,"AbiWord"
                ,"Abort"
                ,"Access"
                ,"Accessibillity"
                ,"Access Method"
                ,"Access Time"
                ,"Accessories"
                ,"Accumulator"
                ,"Acknowledge"
                ,"Accoustic Coupler"
                ,"ACL (Access Control Unit)"
                ,"ACPI (Advanced Configuration Power Interface)"
                ,"Active Task Button"
                ,"Active"
                ,"ActiveX"
                ,"Adapter"
                ,"ADC (Analog/Digital Converter)"
                ,"Add-in"

        };
        return source;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setOnFragmentListener(FragmentListener listener){
        this.listener = listener;
    }

}
