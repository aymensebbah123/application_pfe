package com.example.applicationpfe;
import android.app.Notification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;



public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<NotificationClient> notifications = new ArrayList<>();

    public void addNotification(NotificationClient notification) {
        notifications.add(notification);
    }

    public void clearNotifications() {
        notifications.clear();
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationClient notification = notifications.get(position);
        holder.bind(notification);
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {

        private TextView clientNameTextView;
        private TextView dateTextView;
        private TextView descriptionTextView;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            clientNameTextView = itemView.findViewById(R.id.client_name_textview);
            dateTextView = itemView.findViewById(R.id.date_textview);
            descriptionTextView = itemView.findViewById(R.id.description_textview);
        }

        public void bind(NotificationClient notification) {
            clientNameTextView.setText(notification.getclientName());
            dateTextView.setText(notification.getDate());
            descriptionTextView.setText(notification.getDescription());
        }
    }
}
