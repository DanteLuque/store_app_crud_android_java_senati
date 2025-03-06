package com.senati.store.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.senati.store.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.senati.store.adapters.StoreAdapter;
import com.senati.store.models.StoreModel;
import com.senati.store.repositories.StoreHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<StoreModel> storeList = new ArrayList<>();
    StoreHelper storeHelper;
    StoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        storeHelper = new StoreHelper(this);
        loadUI();
        setupRecyclerView();
        loadData();
    }

    public void Add(View view){
        startActivity(new Intent(MainActivity.this, AddActivity.class));
    }

    private void loadUI() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StoreAdapter(this, storeList);
        recyclerView.setAdapter(adapter);
    }

    private void loadData() {
        Cursor cursor = storeHelper.showData();
        storeList.clear(); // Limpiando antes de listar
        while (cursor.moveToNext()) {
            storeList.add(new StoreModel(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getDouble(6)
            ));
        }
        cursor.close();
        adapter.notifyDataSetChanged(); // notificando los cambios al adaptador
    }

}