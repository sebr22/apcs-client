import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;
import java.util.HashMap;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.Map;

public class Main {
  public static Object obj;

  public static void update() {
    System.out.println(obj);
    System.out.println(((Map<?, ?>) obj).size());
  }

  public static void main(String[] args) {
    Thread add = new Thread() {
      public void run() {
        pwrite(((Map<?, ?>) obj).size(), "test");
      }
    };
    Thread t = new Thread(new ShowDbChanges());
    t.run();

    try {
      Thread.sleep(100000);
    } catch (InterruptedException e) {
      // e.printStackTrace();
    }

  }

  public static void pwrite(int key, String str) {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpPatch httpPatch = new HttpPatch("https://apcs-d25a1-default-rtdb.firebaseio.com/.json");

    String json = "{\" " + key + " \": \" " + str + " \"}";
    StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
    httpPatch.setEntity(entity);

    CloseableHttpResponse response = null;
    try {
      response = httpclient.execute(httpPatch);
      HttpEntity responseEntity = response.getEntity();
      if (responseEntity != null) {
        // System.out.println(EntityUtils.toString(responseEntity,
        // StandardCharsets.UTF_8));
      }
    } catch (IOException e) {
      // e.printStackTrace();
    } finally {
      try {
        if (response != null) {
          response.close();
        }
        httpclient.close();
      } catch (IOException e) {
        // e.printStackTrace();
      }
    }
  }
}