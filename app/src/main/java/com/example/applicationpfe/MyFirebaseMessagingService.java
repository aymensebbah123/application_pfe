package com.example.applicationpfe;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // Traitez la notification push reçue ici
        // Par exemple, vous pouvez afficher une notification dans la barre d'état
        // avec les détails de la demande d'intervention
  }
}
