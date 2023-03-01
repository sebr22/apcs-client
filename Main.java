// TODO:
// The command line is currently only tooled to take messages.
// It would be preferable for there to be a 

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.*;

//post
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
import java.util.*;

public class Main {
  public static Object obj;
  public static ArrayList<String> list = new ArrayList<String>();

  public static void update() {
ArrayList<String> temp = new ArrayList<>();
// get all the values from the HashMap
Collection<ArrayList<String>> values = ((HashMap<String, ArrayList<String>>) obj).values();
for (ArrayList<String> value : values) {
  // check if the value has at least two elements
  if (value.size() >= 2) {
    // get the second element and add it to list
    String second = value.get(1);
    temp.add(second);
  }
}
    // ONLY ADDS NEW ELEMENTS
    temp.removeAll(list);
    list.addAll(temp);

    System.out.println(list);
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    Thread t = new Thread(new ShowDbChanges());
    t.run();

    Runnable scannerRunnable = new Runnable() {
      @Override
      public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
          post(scanner.nextLine());
        }
      }
    };

    Thread scannerThread = new Thread(scannerRunnable);
    scannerThread.start();

    try {
      Thread.sleep(100000);
    } catch (InterruptedException e) {
      // e.printStackTrace();
    }

  }

  public static void post(String str) {
    try {
      HttpClient client = HttpClientBuilder.create().build();
      HttpPost post = new HttpPost("https://apcs-d25a1-default-rtdb.firebaseio.com/.json");
      // post.setHeader("Content-Type", "application/x-www-form-urlencoded");
      StringEntity entity = new StringEntity("{\"1\": \"" + str + "\"}");
      post.setEntity(entity);

      HttpResponse response = client.execute(post);
    } catch (Exception e) {
      System.out.println("THERE WAS AN ERROR");
    }
  }
}