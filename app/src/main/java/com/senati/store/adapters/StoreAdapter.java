package com.senati.store.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.senati.store.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.senati.store.models.StoreModel;
import com.senati.store.repositories.StoreHelper;
import com.senati.store.ui.MainActivity;
import com.senati.store.ui.UpdateActivity;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreViewHolder> {
    private final Context context;
    private final ArrayList<StoreModel> storeList;

    public StoreAdapter(
            Context context,
            ArrayList<StoreModel> storeList
    ) {
        this.context = context;
        this.storeList = storeList;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        StoreModel storeModel = storeList.get(position);
        holder.bind(storeModel);

        holder.cardView.setOnLongClickListener(view -> {
            deleteEntry(storeModel.getId());
            return true;
        });

        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("DESARROLLADOR", storeModel.getDesarrollador());
            intent.putExtra("NOMBRE", storeModel.getNombre());
            intent.putExtra("NOMBRE",storeModel.getNombre());
            intent.putExtra("LICENCIA",storeModel.getLicencia());
            intent.putExtra("VERSION",storeModel.getVersion());
            intent.putExtra("ESPACIO_MB",storeModel.getEspacioMb());
            intent.putExtra("PRECIO",storeModel.getPrecio());

            intent.putExtra("ID", storeModel.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    private void deleteEntry(int id) {
        new android.app.AlertDialog.Builder(context)
                .setTitle("Eliminar item")
                .setMessage("¿Está seguro de que desea eliminar este item?")
                .setPositiveButton("ELIMINAR", (dialog, which) -> {

                    StoreHelper storeHelper = new StoreHelper(context);
                    storeHelper.deleteData(String.valueOf(id));
                    dialog.dismiss();
                    context.startActivity(new Intent(context, MainActivity.class));

                })
                .setNegativeButton("CANCELAR", (dialog, which) -> dialog.dismiss())
                .show();
    }
}