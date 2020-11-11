package com.example.testepicpic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testepicpic.R;
import com.example.testepicpic.model.Alimentacao;

import java.util.List;

public class AlimentacoesAdapter extends RecyclerView.Adapter<AlimentacoesAdapter.ViewHolder> {

    private List<Alimentacao> alimentacaos;
    private Context context;

    public AlimentacoesAdapter(List<Alimentacao> alimentacaos, Context c) {
        this.alimentacaos = alimentacaos;
        this.context = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nivel_item, parent, false);

        return new ViewHolder(listItem);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Alimentacao alimentacao = alimentacaos.get( position );



    }

    @Override
    public int getItemCount() {
        return alimentacaos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView comidas, tipo, verMais;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tipo = itemView.findViewById(R.id.txtNivel);
            comidas = itemView.findViewById(R.id.txtHora);
            verMais = itemView.findViewById(R.id.txtverMais);

        }
    }

}
