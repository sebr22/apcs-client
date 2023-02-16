import com.google.firebase.database.*;

import java.io.IOException;

public class ShowDbChanges implements Runnable {
  // public Object ob;

  public void run() {
    FireBaseService fbs = null;
    try {
      fbs = new FireBaseService();
    } catch (IOException e) {
      e.printStackTrace();
    }

    DatabaseReference ref = fbs.getDb()
        .getReference("/");
    ref.addValueEventListener(new ValueEventListener() {

      public void onDataChange(DataSnapshot dataSnapshot) {
        Object document = dataSnapshot.getValue();
        Main.obj=document;
        Main.update();
      }

      public void onCancelled(DatabaseError error) {
        System.out.print("Error: " + error.getMessage());
      }
    });

  }
}