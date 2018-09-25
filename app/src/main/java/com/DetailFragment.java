package com;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kamus.ws.kamus.R;


public class DetailFragment extends Fragment {

    private String value = "";
    private TextView tvWord;
    private ImageButton btnPenanda;
    private WebView tvWordDefinisi;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment getNewInstance(String value) {
        DetailFragment fragment = new DetailFragment();
        fragment.value = value;
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvWord = (TextView) view.findViewById(R.id.tvWord);
        tvWordDefinisi = (WebView) view.findViewById(R.id.tvWordDefinisi);
        btnPenanda = (ImageButton) view.findViewById(R.id.btnPenanda);

        btnPenanda.setTag(0);

        btnPenanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             int i =   (int)btnPenanda.getTag();
             if (i == 0){
                    btnPenanda.setImageResource(R.drawable.ic_bookmark_isi);
                    btnPenanda.setTag(1);
              }else if (i == 1){
                     btnPenanda.setImageResource(R.drawable.ic_bookmark_border);
                     btnPenanda.setTag(0);
             }

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

}
