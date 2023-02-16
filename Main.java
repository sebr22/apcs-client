import com.google.firebase.database.*;

import java.io.IOException;

public class Main {
    public static Object obj;

    public static void update() {
      System.out.println(obj);
    }
  
    public static void main(String[] args) {
        
        Thread t=new Thread(new ShowDbChanges());
        t.run();
      
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}