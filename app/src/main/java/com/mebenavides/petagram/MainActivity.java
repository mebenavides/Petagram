package com.mebenavides.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.mebenavides.petagram.adapter.PageAdapter;
import com.mebenavides.petagram.vista.fragment.PerfilFragment;
import com.mebenavides.petagram.vista.fragment.RecyclerviewFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int CODIGO_SOLICITUD_PERMISO = 1;
    private static final int CODIGO_SOLICITUD_HABILITAR_BLUETOOTH = 0;
    //SwipeRefreshLayout sfiMiIndicadorRefresh;
    //ListView lstMiLista;
    //Adapter adaptador;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Context context;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //androidx.appcompat.widget.Toolbar miActionBar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.miActionBar);
        //setSupportActionBar(miActionBar);
        context = getApplicationContext();
        activity = this;
        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();
        if (toolbar != null){
            setSupportActionBar(toolbar);
        }
        /*
        agregarFAB();
*/
        //Menú Contextual
        //TextView tvContextual = (TextView) findViewById(R.id.tvContextual);
        //registerForContextMenu(tvContextual);
        /*

        sfiMiIndicadorRefresh = (SwipeRefreshLayout) findViewById(R.id.sfiMiIndicadorRefresh);
        lstMiLista = (ListView) findViewById(R.id.lstMiLista);

        String[] planetas = getResources().getStringArray(R.array.planetas);
        lstMiLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, planetas));

        sfiMiIndicadorRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrescandoContenido();
            }
        });

 */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mAbout:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                break;
            case R.id.mContacto:
                Intent intentC = new Intent(this, Contacto.class);
                startActivity(intentC);
                break;
            case R.id.mFavoritos:
                Intent intentF = new Intent(this, Favoritos.class);
                startActivity(intentF);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mEditar:
                Toast.makeText(this, "EDITAR", Toast.LENGTH_LONG);
                //Intent intent = new Intent(this, About.class);
                //startActivity(intent);
                break;
            case R.id.mEliminar:
                Toast.makeText(this, "ELIMINAR", Toast.LENGTH_LONG);
                //Intent intentC = new Intent(this, Contacto.class);
                //startActivity(intentC);
                break;
        }

        return true;
    }

    public void levantarMenuPopup (View v){
        ImageView imagen = (ImageView) findViewById(R.id.imgFoto);
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mPopup1:
                        Toast.makeText(getBaseContext(), "1", Toast.LENGTH_LONG);
                        //Intent intent = new Intent(this, About.class);
                        //startActivity(intent);
                        break;
                    case R.id.mPopup2:
                        Toast.makeText(getBaseContext(), "2", Toast.LENGTH_LONG);
                        //Intent intentC = new Intent(this, Contacto.class);
                        //startActivity(intentC);
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    public void irFavoritos (View v){
        Intent intent = new Intent(this, Favoritos.class);
        startActivity(intent);
    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerviewFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }
    public void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.perro);
    }
        /*
    public void refrescandoContenido(){
        String[] planetas = getResources().getStringArray(R.array.planetas);
        lstMiLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, planetas));
        sfiMiIndicadorRefresh.setRefreshing(false);
    }

 */
            /*
    public void agregarFAB(){
        FloatingActionButton miFAB = (FloatingActionButton) findViewById(R.id.fabMiFab);

        miFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), getResources().getString(R.string.mensaje), Toast.LENGTH_SHORT).show();
                Snackbar.make(v, getResources().getString(R.string.mensaje), Snackbar.LENGTH_SHORT).setAction(getResources().getString(R.string.texto_accion), new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Log.i("SNACKBAR", "CLICK SNACKBAR");
                    }
                })
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .show();
            }
        });
    }
    */

    public void habilitarBluetooth (View v){
        solicitarPermiso();
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(MainActivity.this, "Tu dispositivo no tiene bluetooth", Toast.LENGTH_LONG).show();
        }
        if (!mBluetoothAdapter.isEnabled()){
            Intent habilitarBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(habilitarBluetoothIntent, CODIGO_SOLICITUD_HABILITAR_BLUETOOTH);
        }
    }

    public boolean checarStatusPermiso(){
        int resultado = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH);
        if (resultado == PackageManager.PERMISSION_GRANTED) {
            return true;
        }else{
            return false;
        }
    }

    public void solicitarPermiso(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.BLUETOOTH)){
            Toast.makeText(this, "El permiso ya fue otorgado", Toast.LENGTH_LONG).show();
        }else{
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.BLUETOOTH}, CODIGO_SOLICITUD_PERMISO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CODIGO_SOLICITUD_PERMISO:
                if (checarStatusPermiso()) {
                    Toast.makeText(MainActivity.this, "Ya está activo este permiso", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "No está activo este permiso", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}