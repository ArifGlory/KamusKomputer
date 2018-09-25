package com.kamus.ws.kamus;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.AmusFragment;
import com.BantuanFragment;
import com.DB;
import com.DetailFragment;
import com.FragmentListener;
import com.Global;
import com.PenandaFragment;
import com.TentangFragment;

public class MenuutamaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MenuItem menuSetting;
    Toolbar toolbar;

    AmusFragment amusFragment;
    PenandaFragment penandaFragment;
    TentangFragment tentangFragment;
    BantuanFragment bantuanFragment;
    private DBAdapter mDB;
    protected Cursor cursor;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuutama);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        amusFragment = new AmusFragment();
        penandaFragment = new PenandaFragment();
        tentangFragment = new TentangFragment();
        bantuanFragment = new BantuanFragment();
        goToFragment(amusFragment, true);


        amusFragment.setOnFragmentListener(new FragmentListener() {
            @Override
            public void onItemClick(String value) {
                goToFragment(DetailFragment.getNewInstance(value), false);
            }
        });
        penandaFragment.setOnFragmentListener(new FragmentListener() {
            @Override
            public void onItemClick(String value) {
                goToFragment(DetailFragment.getNewInstance(value), false);
            }
        });

        EditText edit_cari = (EditText) findViewById(R.id.edit_cari);
        edit_cari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                amusFragment.filterValue(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void refreshList(){

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuutama, menu);
        menuSetting = menu.findItem(R.id.action_settings);

       /* String id = Global.getState(this, "dic_type");
        if (id != null)
            onOptionsItemSelected(menu.findItem(Integer.valueOf(id)));
        else
            amusFragment.resetDataSource(DB.getData(R.id.action_semua));*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Global.saveState(this, "dic_type", String.valueOf(id));
        String[] source = DB.getData(id);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_semua) {
            amusFragment.kategoriSemua();
            menuSetting.setIcon(getDrawable(R.drawable.great));
        } else if (id == R.id.action_hardware) {
            amusFragment.kategoriHardware();
            menuSetting.setIcon(getDrawable(R.drawable.hardware));
        } else if (id == R.id.action_jaringan) {
            amusFragment.kategoriJaringan();
            menuSetting.setIcon(getDrawable(R.drawable.jaringan));
        } else if (id == R.id.action_software) {
            amusFragment.kategoriSoftware();
            menuSetting.setIcon(getDrawable(R.drawable.software));
        } else if (id == R.id.action_pemrograman) {
            amusFragment.kategoriPemrograman();
            menuSetting.setIcon(getDrawable(R.drawable.program));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_penanda) {
            String activeFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName();
            if (!activeFragment.equals(PenandaFragment.class.getSimpleName())) {
                goToFragment(penandaFragment, false);
            }
        } else if (id == R.id.nav_tentang) {
            goToFragment(tentangFragment, false);
        } else if (id == R.id.nav_bantuan) {
            goToFragment(bantuanFragment, false);
        } else if (id == R.id.nav_bagikan) {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String shareBody = "Rencana Ku";
            String shareSub = "Subjeknya Apa";
            myIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
            myIntent.putExtra(Intent.EXTRA_TEXT, shareSub);
            startActivity(Intent.createChooser(myIntent, "Bagikan Dengan"));
        }else if (id == R.id.nav_semua){
            goToFragment(amusFragment, false);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void goToFragment(Fragment fragment, boolean isTop) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if (!isTop)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String activeFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName();
        if (activeFragment.equals(PenandaFragment.class.getSimpleName())) {
            menuSetting.setVisible(false);
            toolbar.findViewById(R.id.edit_cari).setVisibility(View.GONE);
            toolbar.setTitle("Penanda");
        } else if (activeFragment.equals(BantuanFragment.class.getSimpleName())) {
            menuSetting.setVisible(false);
            //Toast.makeText(getApplicationContext(),"active frag:"+activeFragment,Toast.LENGTH_SHORT).show();
            toolbar.findViewById(R.id.edit_cari).setVisibility(View.GONE);
            toolbar.setTitle("Bantuan");
        } else if (activeFragment.equals(TentangFragment.class.getSimpleName())) {
            menuSetting.setVisible(false);
            toolbar.findViewById(R.id.edit_cari).setVisibility(View.GONE);
            toolbar.setTitle("Tentang");
          //  Toast.makeText(getApplicationContext(),"active frag:"+activeFragment,Toast.LENGTH_SHORT).show();
        } else {
            menuSetting.setVisible(true);
            toolbar.findViewById(R.id.edit_cari).setVisibility(View.VISIBLE);
            toolbar.setTitle("");
        }
        return true;
    }
}
