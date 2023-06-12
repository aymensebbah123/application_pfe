package com.example.applicationpfe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class technicien_adapter extends RecyclerView.Adapter<technicien_adapter.TechnicienViewHolder> {

    private List<Technicien> techniciensList;
    private Context context;


    public technicien_adapter(List<Technicien> techniciensList, Context context) {
        this.techniciensList = techniciensList;
        this.context = context;

    }
    // Création de la vue d'un élément de la liste en utilisant le fichier de mise en page technicien_item.xml

    @NonNull
    @Override
    public TechnicienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.technicien_affichage_liste, parent, false);
        return new TechnicienViewHolder(itemView);
    }
    // Liaison des données du technicien avec les vues de l'élément de la liste

    @Override
    public void onBindViewHolder(@NonNull TechnicienViewHolder holder, int position) {
        Technicien technicien = techniciensList.get(position);

        holder.nomTextView.setText(technicien.getName());
        holder.localisationTextView.setText(technicien.getLocalisation());
        holder.distanceTextView.setText(String.valueOf(technicien.getDistance()));
    }
// Renvoie le nombre d'éléments dans la liste

    @Override
    public int getItemCount() {
        return techniciensList.size();
    }

    public class TechnicienViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nomTextView;
        public TextView localisationTextView;
        public TextView distanceTextView;

        public TechnicienViewHolder(@NonNull View itemView) {
            super(itemView);

            nomTextView = itemView.findViewById(R.id.nomTextView);
            localisationTextView = itemView.findViewById(R.id.localisationTextView);
            distanceTextView = itemView.findViewById(R.id.distanceTextView);
            // Configuration de l'écouteur de clic sur l'élément de la liste
            itemView.setOnClickListener(this);

        }

        public void bind(Technicien technicien) {
            // Liaison des données du technicien avec les vues de l'élément de la liste
            nomTextView.setText(technicien.getName());
            localisationTextView.setText(technicien.getLocalisation());
            distanceTextView.setText((int) technicien.getDistance());
        }


        @Override
        public void onClick(View v) {
            // Gestion du clic sur l'élément de la liste
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Technicien technicien = techniciensList.get(position);
                openDemandeInterventionActivity(technicien);
            }
        }

        // Création d'une intention pour ouvrir l'activité de demande d'intervention
        private void openDemandeInterventionActivity(Technicien technicien) {
            // Création d'une intention pour ouvrir l'activité de demande d'intervention
            Intent intent = new Intent(context, DemandeInterventionActivity.class);
            // Passage des données du technicien à l'activité de demande d'intervention
            intent.putExtra("technicien", (CharSequence) technicien);
            // Ajouter des données supplémentaires à l'intention
            intent.putExtra("technicien_nom", technicien.getName());
            intent.putExtra("technicien_localisation", technicien.getLocalisation());
            intent.putExtra("technicien_latitude", technicien.getLatitude());
            intent.putExtra("technicien_longitude", technicien.getLongitude());


            // Démarrage de l'activité de demande d'intervention
            context.startActivity(intent);
        }
    }
}







