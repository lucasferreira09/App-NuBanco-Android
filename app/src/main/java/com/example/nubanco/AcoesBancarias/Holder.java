package com.example.nubanco.AcoesBancarias;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nubanco.R;

public class Holder extends RecyclerView.ViewHolder {

    public ImageView btn;
    public TextView text;

    public Holder(@NonNull View itemView) {
        super(itemView);
        btn = itemView.findViewById(R.id.btn);
        text = itemView.findViewById(R.id.text);

    }
}
