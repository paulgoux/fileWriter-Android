package fileWriter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import java.io.InputStream;
import processing.core.PApplet;
import processing.core.PImage;

public class imagePicker extends Activity {
   public BMScontrols Bms;
   public PApplet p;
   public Activity activity;
   public Context context;
   public PImage img;
   public boolean image_loaded;
   public Permission storage;

   public imagePicker(BMScontrols bms) {
      this.Bms = bms;
      this.p = bms.applet;
      this.storage = new Permission(this.p, "WRITE_EXTERNAL_STORAGE");
      this.init();
   }

   public imagePicker(PApplet bms) {
      this.p = bms;
      this.storage = new Permission(this.p, "WRITE_EXTERNAL_STORAGE");
      this.init();
   }

   public void init() {
      this.activity = this.p.getActivity();
      this.context = this.activity.getApplicationContext();
   }

   public void displayImage() {
      if (this.image_loaded) {
         this.p.image(this.img, 0, 0);
      }

   }

   public void activityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (requestCode == 1) {
         Activity activity = this.activity;
         if (resultCode == -1) {
            if (data != null) {
               Uri image_uri = data.getData();
               String[] filePathColumn = { MediaStore.Images.Media.DATA };
               Cursor cursor = this.context.getContentResolver().query(image_uri, filePathColumn, null, null, null);
               cursor.moveToFirst();
               int var7 = cursor.getColumnIndex(filePathColumn[0]);
               String imgDecodableString = cursor.getString(var7);
               cursor.close();
               PApplet.println(imgDecodableString);
               if (VERSION.SDK_INT >= 28) {
                  try {
                     InputStream ips = this.context.getContentResolver().openInputStream(image_uri);
                     Bitmap bitmap = BitmapFactory.decodeStream(ips);
                     this.img = new PImage(bitmap.getWidth(), bitmap.getHeight(), 2);
                     bitmap.getPixels(this.img.pixels, 0, this.img.width, 0, 0, this.img.width, this.img.height);
                     this.img.updatePixels();
                     this.image_loaded = true;
                     PApplet.println("success");
                  } catch (Exception e) {
                     e.printStackTrace();
                  }
               } else {
                  this.img = this.p.loadImage(imgDecodableString);
                  this.image_loaded = true;
                  PApplet.println(new Object[]{"success", this.img.width, this.img.height});
               }
            } else {
               PApplet.println("No data");
            }
         }
      }

   };

   public void openImageExplorer() {
      Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
      intent.setType("image/*");
      this.activity.startActivityForResult(intent, 1);
   };
};
