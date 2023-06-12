package com.example.applicationpfe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Travail> {
    private Context context;
    private List<Travail> itemList;

    public CustomListAdapter(Context context, List<Travail> itemList) {
        super(context, 0, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        // Inflate the custom layout for each list item
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.activity_affichage_liste_des_travails_enrengistres, parent, false);
        }

        // Get the current item from the itemList
        Travail travail = itemList.get(position);

        // Set the data to the views in the custom layout
        TextView titleTextView = view.findViewById(R.id.text_title);
        TextView nomclient = view.findViewById(R.id.text_nomclient);
        TextView dateTextView = view.findViewById(R.id.text_date);
        TextView typeentretien  = view.findViewById(R.id.text_typeentretien);
        TextView localisation = view.findViewById(R.id.text_localisation);

        titleTextView.setText(travail.getTitle());
        nomclient.setText(travail.getnomclient());
        dateTextView.setText(travail.getDate());

        typeentretien.setText(travail. gettypeentretien());
        localisation.setText(travail.getlocalisation());

        return view;
    }
}

