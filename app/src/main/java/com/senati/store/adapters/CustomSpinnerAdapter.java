package com.senati.store.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    public CustomSpinnerAdapter(Context context, List<String> opciones) {
        super(context, android.R.layout.simple_spinner_item, opciones);
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}
