package com.senati.store.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.senati.store.R;

import com.senati.store.models.StoreModel;

public class StoreViewHolder extends RecyclerView.ViewHolder {
    TextView tv_nombre, tv_desarrollador, tv_version, tv_espacioMB, tv_licencia, tv_precio;
    CardView cardView;

    public StoreViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_nombre = itemView.findViewById(R.id.tv_nombre);
        tv_desarrollador = itemView.findViewById(R.id.tv_desarrollador);
        tv_version = itemView.findViewById(R.id.tv_version);
        tv_espacioMB = itemView.findViewById(R.id.tv_espacioMB);
        tv_licencia = itemView.findViewById(R.id.tv_licencia);
        tv_precio = itemView.findViewById(R.id.tv_precio);

        cardView = itemView.findViewById(R.id.cardView);
    }

    public void bind(StoreModel storeModel) {
        tv_nombre.setText(storeModel.getNombre());
        tv_desarrollador.setText(storeModel.getDesarrollador());
        tv_version.setText(storeModel.getVersion());
        tv_espacioMB.setText(storeModel.getEspacioMb());
        tv_licencia.setText(storeModel.getLicencia());
        tv_precio.setText(String.valueOf(storeModel.getPrecio()));
    }
}