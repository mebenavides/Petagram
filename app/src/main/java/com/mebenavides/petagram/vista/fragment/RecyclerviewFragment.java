package com.mebenavides.petagram.vista.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mebenavides.petagram.Mascota;
import com.mebenavides.petagram.R;
import com.mebenavides.petagram.adapter.MascotaAdaptador;
import com.mebenavides.petagram.presentador.IRecyclerViewFragmentPresenter;
import com.mebenavides.petagram.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

public class RecyclerviewFragment extends Fragment implements IRecyclerViewFragmentView {
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IRecyclerViewFragmentPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }

    public void inicializarListaMascotas() {
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.jirafa,"Pato", 3));
        mascotas.add(new Mascota(R.drawable.leon,"María", 5));
        mascotas.add(new Mascota(R.drawable.tigre,"Ralf", 1));
        mascotas.add(new Mascota(R.drawable.mono,"Mono", 0));
        mascotas.add(new Mascota(R.drawable.leon,"Lion", 2));

    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}
