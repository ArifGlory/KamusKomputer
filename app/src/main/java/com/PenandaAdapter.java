package com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kamus.ws.kamus.R;

import java.util.ArrayList;
import java.util.Arrays;

public class PenandaAdapter extends BaseAdapter {
    private ListItemListener listener;
    private ListItemListener listenerBtnDelete;
    Context mContex;
    ArrayList<String> mSource;

    public PenandaAdapter(Context context, String[] source){
        this.mSource =  new ArrayList<>(Arrays.asList(source));
        this.mContex = context;
    }

    @Override
    public int getCount() {
        return mSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final   int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContex).inflate(R.layout.penanda_layout_item,viewGroup,false);
            viewHolder.textView = view.findViewById(R.id.tvWord);
            viewHolder.btnDelete = view.findViewById(R.id.btnDelete);

            view.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) view.getTag();
        }

        viewHolder.textView.setText(mSource.get(i));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                listener.onItemClick(i);
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listenerBtnDelete !=null)
                    listenerBtnDelete.onItemClick(i);
            }
        });
        return view;
    }

    public void removeItem(int position){
        mSource.remove(position);
    }
    public  void removeAll(){
        mSource.clear();
    }



    class ViewHolder {
        TextView textView;
        ImageView btnDelete;
    }

    public  void setOnItemClick(ListItemListener listItemListener){
        this.listener = listItemListener;

    }

    public  void setOnItemDeleteClick(ListItemListener listItemListener){
        this.listenerBtnDelete = listItemListener;

    }


}
