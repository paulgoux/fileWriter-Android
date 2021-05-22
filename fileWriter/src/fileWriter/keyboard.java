package fileWriter;

import processing.core.PApplet;

public class keyboard {
   BMS Bms;
   PApplet p;
   public boolean getKey;
   public boolean key1;
   public boolean key2;
   public boolean kd;
   public String s1 = "";
   public char key;
   public int keyCode;

   public keyboard(PApplet app) {
      p = app;
   }

   public keyboard(BMS bms) {
      Bms = bms;
      p = bms.applet;
   }

   public void run() {
      if (key1 && getKey && !p.mousePressed) {
         getKey = false;
         s1 = s1 + p.key;
         key = p.key;
         keyCode = p.keyCode;
         kd = true;
      } else {
         s1 = "";
         kd = false;
         key1 = false;
      }

      if (kd) {
         PApplet.println("key", s1, key, keyCode);
      }

   }

   public void getKey() {
      if (!getKey) {
         key1 = true;
         getKey = true;
      }

   }

   public void logic() {
      getKey();
      run();
   }
}
