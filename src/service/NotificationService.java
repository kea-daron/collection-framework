package service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class NotificationService {
    private static final String BOT_TOKEN = "7654381600:AAH2DL7YEhyErf6DpjSOCDgg6-D7FkanzK8";
    private static final String CHAT_ID = "796542805";

    public void sendTelegramNotification(String message) {
        try {
            String encodedMessage = URLEncoder.encode(message, "UTF-8");

            String urlString = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage?chat_id=" + CHAT_ID + "&text=" + encodedMessage;

            System.out.println("Sending request to Telegram API: " + urlString);

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            System.out.println("Telegram API response code: " + responseCode);

            if (responseCode == 200) {
                System.out.println("Telegram Notification sent: " + message);
            } else {
                System.err.println("Failed to send Telegram notification. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}