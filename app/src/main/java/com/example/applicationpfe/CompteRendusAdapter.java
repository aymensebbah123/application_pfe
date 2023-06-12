package com.example.applicationpfe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompteRendusAdapter extends RecyclerView.Adapter<CompteRendusAdapter.CompteRenduViewHolder> {

    private List<CompteRenduclass> comptesRendusList;

    public CompteRendusAdapter(List<CompteRenduclass> comptesRendusList) {
        this.comptesRendusList = comptesRendusList;
    }

    @NonNull
    @Override
    public CompteRenduViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_compte_rendu, parent, false);
        return new CompteRenduViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CompteRenduViewHolder holder, int position) {
        CompteRenduclass compteRendu = comptesRendusList.get(position);

        holder.titreTextView.setText(compteRendu.getTitre());
        holder.descriptionTextView.setText(compteRendu.getDescription());
    }

    @Override
    public int getItemCount() {
        return comptesRendusList.size();
    }

    public void setComptesRendus(List<CompteRenduclass> comptesRendus) {
    }

    public class CompteRenduViewHolder extends RecyclerView.ViewHolder {
        TextView titreTextView;
        TextView descriptionTextView;

        public CompteRenduViewHolder(View itemView) {
            super(itemView);
            titreTextView = itemView.findViewById(R.id.text_view_titre);
            descriptionTextView = itemView.findViewById(R.id.text_view_description);
        }
    }
}