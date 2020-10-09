package com.mebenavides.petagram.vista.fragment;

import com.mebenavides.petagram.Mascota;
import com.mebenavides.petagram.adapter.MascotaAdaptador;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {
    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
