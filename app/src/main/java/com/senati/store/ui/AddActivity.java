package com.senati.store.ui;

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
import com.senati.store.models.StoreModel;
import com.senati.store.repositories.StoreHelper;

import java.util.Arrays;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    Spinner spinner_desarrollador, spinner_licencia;
    EditText et_nombre,et_version, et_espaciomb, et_precio;
    StoreHelper storeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        storeHelper = new StoreHelper(this);
        loadUI();
    }

    public void addItem(View view){
        if(!validateFields()) return;

        storeHelper.addData(new StoreModel(
                -1,
                spinner_desarrollador.getSelectedItem().toString(),
                et_nombre.getText().toString(),
                spinner_licencia.getSelectedItem().toString(),
                Integer.parseInt(et_version.getText().toString()),
                Integer.parseInt(et_espaciomb.getText().toString()),
                Integer.parseInt(et_precio.getText().toString())
        ));

        showToastShort(this, "Agregado exitosamente");
        clearFields();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void loadUI(){
        spinner_desarrollador = findViewById(R.id.spinner_desarrollador);
        spinner_licencia = findViewById(R.id.spinner_licencia);
        et_nombre = findViewById(R.id.et_nombre);
        et_version = findViewById(R.id.et_version);
        et_espaciomb = findViewById(R.id.et_espaciomb);
        et_precio = findViewById(R.id.et_precio);


        List<String> desarrolladores = Arrays.asList("Desarrollador",
                "Microsoft", "Apple", "Intel");

        List<String> licencias = Arrays.asList("Licencia",
                "Comercial", "GNU");

        CustomSpinnerAdapter desarrolladorAdapter = new CustomSpinnerAdapter(this, desarrolladores);
        CustomSpinnerAdapter licenciaAdapter = new CustomSpinnerAdapter(this, licencias);

        spinner_desarrollador.setAdapter(desarrolladorAdapter);
        spinner_licencia.setAdapter(licenciaAdapter);
    }

    private void clearFields() {
        spinner_desarrollador.setSelection(0);
        spinner_licencia.setSelection(0);
        et_nombre.setText("");
        et_version.setText("");
        et_espaciomb.setText("");
        et_precio.setText("");
    }

    private boolean validateFields() {
        //spiner_desarrollador
        //spinner_licencia
        if (isEmpty(et_nombre)) return false;
        if (isEmpty(et_version)) return false;
        if (isEmpty(et_espaciomb)) return false;
        if (isEmpty(et_precio)) return false;

        if (spinner_desarrollador.getSelectedItemPosition() == 0) {
            showToastShort(this, "seleccione un desarrollador.");
            return false;
        }

        if (spinner_licencia.getSelectedItemPosition() == 0) {
            showToastShort(this, "seleccione una licencia.");
            return false;
        }

        return true;
    }
}