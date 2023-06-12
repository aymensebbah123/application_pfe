package com.example.applicationpfe;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;



public class
FCMNotificationSender {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public void sendNotification(String fcmToken, String title, String body) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String apiKey = "YOUR_FCM_API_KEY"; // Remplacez par votre clé d'authentification FCM
        JSONObject json = new JSONObject();
        try {
            json.put("to", fcmToken);
            JSONObject notification = new JSONObject();
            notification.put("title", title);
            notification.put("body", body);
            json.put("notification", notification);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }


        RequestBody requestBody = RequestBody.create(json.toString(), JSON);



        Request request = new Request.Builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .post(requestBody)
                .addHeader("Authorization", "key=" + apiKey)
                .build();


        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful())
            {
                System.out.println("Notification envoyé avec succée.");
            }
            else
            {
                System.out.println("Notification ne pas envoyé avec succée.");
            }
        }
}

}
