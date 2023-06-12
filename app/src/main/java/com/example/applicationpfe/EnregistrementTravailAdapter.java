package com.example.applicationpfe;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class EnregistrementTravailAdapter extends RecyclerView.Adapter<EnregistrementTravailAdapter.ViewHolder> {

    private List<Travail> enregistrements;

    public EnregistrementTravailAdapter(List<Travail> enregistrements) {
        this.enregistrements = enregistrements;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enregistrement_travail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Travail travail = enregistrements.get(position);
        holder.bind(travail);
    }

    @Override
    public int getItemCount() {
        return enregistrements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titreTextView;
        private TextView nomClientTextView;
        private TextView dateTextView;
        private TextView typeTextView;
        private TextView localisationTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titreTextView = itemView.findViewById(R.id.text_titre);
            nomClientTextView = itemView.findViewById(R.id.text_nom_client);
            dateTextView = itemView.findViewById(R.id.text_date);
            typeTextView = itemView.findViewById(R.id.text_type);
            localisationTextView = itemView.findViewById(R.id.text_localisation);
        }

        public void bind(Travail travail) {
            titreTextView.setText(travail.getTitle());
            nomClientTextView.setText(travail.getnomclient());
            dateTextView.setText(travail.getDate());
            typeTextView.setText(travail.gettypeentretien());
            localisationTextView.setText(travail.getlocalisation());
        }
    }
}
