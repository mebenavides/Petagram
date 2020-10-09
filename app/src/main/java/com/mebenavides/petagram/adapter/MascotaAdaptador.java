package com.mebenavides.petagram.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mebenavides.petagram.Mascota;
import com.mebenavides.petagram.R;
import com.mebenavides.petagram.db.ConstructorMascotas;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{
    ArrayList<Mascota> mascotas;

    public MascotaAdaptador(ArrayList<Mascota> mascotas){
        this.mascotas = mascotas;

    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder holder, int position) {
        final MascotaViewHolder holderMascota = holder;
        final Mascota mascota = mascotas.get(position);
        holderMascota.tvNombreCV.setText(mascota.getNombre());
        holderMascota.imgFoto.setImageResource(mascota.getFoto());
        holderMascota.tvRateCV.setText(String.valueOf(mascota.getRate()));;

        holderMascota.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Has dado like a " + mascota.getNombre().toString(), Toast.LENGTH_SHORT).show();
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(v.getContext());
                constructorMascotas.darLikeMascota(mascota);
                int likes = constructorMascotas.obtenerLikesMascota(mascota);
                holderMascota.tvRateCV.setText(String.valueOf(likes));
            }

        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvRateCV;
        private ImageButton btnLike;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCV = (TextView) itemView.findViewById(R.id.tvNombreCV);
            tvRateCV = (TextView) itemView.findViewById(R.id.tvRateCV);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);
        }
    }
}
