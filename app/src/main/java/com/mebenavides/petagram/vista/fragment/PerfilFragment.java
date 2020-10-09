package com.mebenavides.petagram.vista.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mebenavides.petagram.Mascota;
import com.mebenavides.petagram.adapter.MascotaPerfilAdaptador;
import com.mebenavides.petagram.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotasPer);
        GridLayoutManager llm = new GridLayoutManager(getContext(), 3);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
        inicializarListaMascotas();
        inicializarAdaptador();
        return v;
    }

    public void inicializarAdaptador(){
        MascotaPerfilAdaptador adaptador = new MascotaPerfilAdaptador(mascotas);
        listaMascotas.setAdapter(adaptador);
    }
    public void inicializarListaMascotas() {
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.jirafa,"Pato", 3));
        mascotas.add(new Mascota(R.drawable.jirafa,"Pato", 4));
        mascotas.add(new Mascota(R.drawable.jirafa,"Pato", 2));
        mascotas.add(new Mascota(R.drawable.jirafa,"Pato", 3));
        mascotas.add(new Mascota(R.drawable.jirafa,"Pato", 1));
        mascotas.add(new Mascota(R.drawable.jirafa,"Pato", 5));
        mascotas.add(new Mascota(R.drawable.jirafa,"Pato", 3));
        mascotas.add(new Mascota(R.drawable.jirafa,"Pato", 5));

    }
}