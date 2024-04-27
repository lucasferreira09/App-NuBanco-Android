package com.example.nubanco.AcoesBancarias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nubanco.R;

import java.util.List;

public class AdapterAcoes extends RecyclerView.Adapter<Holder> {

    private List<AcaoBancaria> acaoBancarias;
    private Context context;
    private ClickAcoesBancaria clickAcoesBancaria;

    public AdapterAcoes(List<AcaoBancaria> acaoBancarias, Context context, ClickAcoesBancaria clickAcoesBancaria) {
        this.acaoBancarias = acaoBancarias;
        this.context = context;
        this.clickAcoesBancaria = clickAcoesBancaria;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.btn.setImageResource(acaoBancarias.get(position).getIdImageAcaoBancaria());
        holder.text.setText(acaoBancarias.get(position).getDescricaoAcaoBancaria());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAcoesBancaria.clickAcaoBancaria(acaoBancarias.get(position).getFuncao());
             }
        });
    }

    @Override
    public int getItemCount() {
        return acaoBancarias.size();
    }
}
