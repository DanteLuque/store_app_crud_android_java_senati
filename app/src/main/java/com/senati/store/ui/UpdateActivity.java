package com.senati.store.ui;

import static com.senati.store.constants.Constants.DESARROLLADORES;
import static com.senati.store.constants.Constants.LICENCIAS;
import static com.senati.store.utils.FieldsUtils.isEmpty;
import static com.senati.store.utils.ToastUtils.showToastShort;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import com.senati.store.R;
import com.senati.store.adapters.CustomSpinnerAdapter;
import com.senati.store.repositories.StoreHelper;

import java.util.Arrays;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {
    Spinner update_spinner_desarrollador, update_spinner_licencia;
    EditText update_et_nombre,update_et_version, update_et_espaciomb, update_et_precio;

    private String desarrollador, nombre, licencia, version, espaciomb, precio, idItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loadUI();
        getIntentData();
        setFields();
    }
    public void updateItem(View view){
        if(!validateFields()) return;

        StoreHelper storeHelper = new StoreHelper(this);
        storeHelper.updateData(
                update_spinner_desarrollador.getSelectedItem().toString(),
                update_et_nombre.getText().toString(),
                update_spinner_licencia.getSelectedItem().toString(),
                Integer.parseInt(update_et_version.getText().toString()),
                Integer.parseInt(update_et_espaciomb.getText().toString()),
                Double.parseDouble(update_et_precio.getText().toString()),
                idItem
        );

        showToastShort(this, "Actualizado exitosamente");
        startActivity(new Intent(this, MainActivity.class));
    }

    public void loadUI(){
        update_spinner_desarrollador = findViewById(R.id.update_spinner_desarrollador);
        update_spinner_licencia = findViewById(R.id.update_spinner_licencia);
        update_et_nombre = findViewById(R.id.update_et_nombre);
        update_et_version = findViewById(R.id.update_et_version);
        update_et_espaciomb = findViewById(R.id.update_et_espaciomb);
        update_et_precio = findViewById(R.id.update_et_precio);

        CustomSpinnerAdapter desarrolladorAdapter = new CustomSpinnerAdapter(this, DESARROLLADORES);
        CustomSpinnerAdapter licenciaAdapter = new CustomSpinnerAdapter(this, LICENCIAS);

        update_spinner_desarrollador.setAdapter(desarrolladorAdapter);
        update_spinner_licencia.setAdapter(licenciaAdapter);
    }

    private void setFields() {
        int devPosition = DESARROLLADORES.indexOf(desarrollador);
        if (devPosition != -1) {
            update_spinner_desarrollador.setSelection(devPosition);
        }

        int licenciaPosition = LICENCIAS.indexOf(licencia);
        if (licenciaPosition != -1) {
            update_spinner_licencia.setSelection(licenciaPosition);
        }

        update_et_nombre.setText(nombre);
        update_et_version.setText(version);
        update_et_espaciomb.setText(espaciomb);
        update_et_precio.setText(precio);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            desarrollador = intent.getStringExtra("DESARROLLADOR");
            licencia = intent.getStringExtra("LICENCIA");
            nombre = intent.getStringExtra("NOMBRE");
            version = String.valueOf(intent.getIntExtra("VERSION",0));
            espaciomb = String.valueOf(intent.getIntExtra("ESPACIO_MB",0));
            precio = String.valueOf(intent.getDoubleExtra("PRECIO",0));
            idItem = String.valueOf(intent.getIntExtra("ID", 0));
        }
    }

    private boolean validateFields() {
        if (isEmpty(update_et_nombre)) return false;
        if (isEmpty(update_et_version)) return false;
        if (isEmpty(update_et_espaciomb)) return false;
        if (isEmpty(update_et_precio)) return false;

        if (update_spinner_desarrollador.getSelectedItemPosition() == 0) {
            showToastShort(this, "seleccione un desarrollador");
            return false;
        }

        if (update_spinner_licencia.getSelectedItemPosition() == 0) {
            showToastShort(this, "seleccione una licencia");
            return false;
        }

        return true;
    }
}