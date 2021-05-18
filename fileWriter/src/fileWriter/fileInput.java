package fileWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class fileInput extends PApplet {
	BMScontrols Bms;
	PApplet p;
	public boolean folder, file, imageLoaded, image, audio, video, getPermission = true;
	public String location, folderPath, fileName, absolutePath, fileContent, ext;
	public PImage img;
	public Activity act;
	public Context context;
	public Permission wStorage;
	public ArrayList<String>Files = new ArrayList<String>();
	public ArrayList<String>images = new ArrayList<String>();
	public ArrayList<String>audioFiles = new ArrayList<String>();
	public ArrayList<String>textFiles = new ArrayList<String>();
	public ArrayList<String>videoFiles = new ArrayList<String>();
	public ArrayList<String>otherFiles = new ArrayList<String>();

	public fileInput(PApplet app) {
		p = app;
		init();
	};
	
	public fileInput(BMScontrols bms) {
		Bms = bms;
		p = bms.applet;
		init();
	};
	
	public fileInput(Boolean b, BMScontrols bms) {
		Bms = bms;
		p = bms.applet;
		init();
	};

	public void init() {
		wStorage = new Permission(p, "WRITE_EXTERNAL_STORAGE");
		absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
		getPermission = false;
		act = p.getActivity(); 
		context = act.getApplicationContext();
	};

	public void getItem() {
		openImageExplorer();
		getFolder();
		getFile();
	};

	public void openImageExplorer() {
		if (image) {
			Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			intent.setType("image/*");
			act.startActivityForResult(intent, 1);
		};
	};

	public void getFolder() {
		if (folder) {
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) { 
				Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
				act.startActivityForResult(intent, 9999);
			}
		}
	};

	public void getFile() {
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) { 
			Intent chooseFile;
			Intent intent;
			chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
			chooseFile.setType("file/*");
			intent = Intent.createChooser(chooseFile, "Choose a file");
			act.startActivityForResult(intent, 9999);
		}
	};

	public void handleActivity(int requestCode, int resultCode, Intent data) {
		activityResult(requestCode, resultCode, data);
		activityResultImage(requestCode, resultCode, data);
		onActivityResultFile(requestCode, resultCode, data);
	};


	public void activityResult(int requestCode, int resultCode, Intent data) {
		if (folder) {
			onActivityResult(requestCode, resultCode, data);

			switch(requestCode) {
			case 9999:
				String s1 = data.getData().toString();
				int i = s1.indexOf("primary%");
				s1 = s1.substring(i+10, s1.length());
				s1 = absolutePath +"/"+s1;
				s1 = s1.replace("%2F", "/");
				println("Test", "path" + s1);
				listFiles(s1);

				break;
			}
		}
	};

	public void activityResultImage(int requestCode, int resultCode, Intent data) {
		if (image) {
			onActivityResult(requestCode, resultCode, data);
			if (requestCode == 1) {
				if (resultCode == -1) {
					if (data != null) {
						Uri image_uri = data.getData();
						String[] filePathColumn = { MediaStore.Images.Media.DATA };
						Cursor cursor = context.getContentResolver().query(image_uri, filePathColumn, null, null, null);
						cursor.moveToFirst();
						int var7 = cursor.getColumnIndex(filePathColumn[0]);
						String imgDecodableString = cursor.getString(var7);
						cursor.close();
						PApplet.println(imgDecodableString);
						if (Build.VERSION.SDK_INT >= 28) {
							try {
								InputStream ips = context.getContentResolver().openInputStream(image_uri);
								Bitmap bitmap = BitmapFactory.decodeStream(ips);
								img = new PImage(bitmap.getWidth(), bitmap.getHeight(), 2);
								bitmap.getPixels(img.pixels, 0, img.width, 0, 0, img.width, img.height);
								img.updatePixels();
								imageLoaded = true;
								PApplet.println("success", img.width, img.height);
							} 
							catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							img = p.loadImage(imgDecodableString);
							imageLoaded = true;
							PApplet.println("success", img.width, img.height);
						}
					} else {
						PApplet.println("No data");
					}
				}
			}
		}
	};

	public void onActivityResultFile(int requestCode, int resultCode, Intent data) {
		if (file&&!image&&!audio&&!video) {
			onActivityResult(requestCode, resultCode, data);

			switch(requestCode) {
			case 9999:
				String s1 = data.getData().toString();
				int i = s1.indexOf("external_files");
				s1 = s1.substring(i+15, s1.length());
				s1 = absolutePath +"/"+s1;
				s1 = s1.replace("%2F", "/");
				setLocation(s1);
				loadFile(s1);
				println("Test", "path " + s1);
				break;
			}
		}
	};

	public String[] listFiles(String s1) {
		String  []s = null;
		String path = s1;
		println("Files", "Path: " + path);
		File directory = new File(path);
		File[] files = directory.listFiles();
		if (files!=null) {
			s = new String [files.length];
			println("Files", "Size: "+ files.length);
			for (int i = 0; i < files.length; i++)
			{
				s[i] = files[i].getName();
				Files.add(s[i]);
				filterAll(s[i]);

				println("Files", "FileName: " + files[i].getName());
			}
		}
		println("Images", images.size());
		println("text", textFiles.size());
		println("Audio", audioFiles.size());
		println("Other", otherFiles.size());
		return s;
	};

	public void filterAll(String s) {
		filterImage(s);
		filterText(s);
		filterAudio(s);
		filterVideo(s);
		filterOthers(s);
	};

	public void filterImage(String s) {
		if (s.contains("jpg")
				||s.contains("JPG")
				||s.contains("bmp")
				||s.contains("BMP")
				||s.contains("png")
				||s.contains("PNG")
				||s.contains("gif")
				||s.contains("GIF")) {
			if (!images.contains(s))images.add(s);
		}
	};

	public void filterText(String s) {
		if (s.contains("txt")
				||s.contains("doc")
				||s.contains("docx")
				||s.contains("csv")) {
			if (!textFiles.contains(s))textFiles.add(s);
		}
	};

	public void filterAudio(String s) {
		if (s.contains("ogg")
				||s.contains("mp3")
				||s.contains("wav")) {
			if (!audioFiles.contains(s))audioFiles.add(s);
		}
	};

	public void filterVideo(String s) {
		if (s.contains("mp4")
				||s.contains("wmv")
				||s.contains("avi")
				||s.contains("mkv")) {
			if (!videoFiles.contains(s))videoFiles.add(s);
		}
	};

	public void filterOthers(String s) {
		if (!images.contains(s)
				&&!textFiles.contains(s)
				&&!videoFiles.contains(s)
				&&!audioFiles.contains(s)) {
			if (!otherFiles.contains(s))otherFiles.add(s);
		}
	};

	public String loadFile(String s) {
		println("test Location",location);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream (new File(s));
			println("try reading file");
			InputStreamReader isr = new InputStreamReader(fis);
			// READ STRING OF UNKNOWN LENGTH
			StringBuilder sb = new StringBuilder();
			char[] inputBuffer = new char[2048];
			int l;
			// FILL BUFFER WITH DATA
			while ((l = isr.read(inputBuffer)) != -1) {
				sb.append(inputBuffer, 0, l);
				println("write data", inputBuffer, 0, l);
			}
			// CONVERT BYTES TO STRING
			fileContent = sb.toString();
			println("file",fileName+"."+ext,fileContent);
			fis.close();
		}
		catch (Exception e) {
			println("cannot fetch file", e);
		} 
		finally {
			if (fis != null) {

				fis = null;
			}
		}
		return fileContent;
	};

	public void setLocation(String s) {
		location = s;
		folderPath = s.substring(0, s.lastIndexOf("/"));
		fileName = s.replace(folderPath+"/", "");
		getExt(fileName);
		PApplet.println("Fname", folderPath);
		PApplet.println("fileName", fileName);
		PApplet.println("ext", ext);
		init();
	}

	public void getExt(String location) {

		int count = 0;
		fileName = location.substring(0, location.indexOf("."));
		ext = location.replace(fileName, "");
		ext = ext.replace(".", "");
		ext = ext.replace(fileName, "");
	};

	public void displayImage() {
		if (imageLoaded) {
			p.image(img, 0, 0);
		}
	};

	public void displayImage(int x, int y) {
		if (imageLoaded) {
			p.image(img, x, y);
		}
	};

	public void displayImage(PGraphics p, int x, int y) {
		if (imageLoaded) {
			p.image(img, x, y);
		}
	};

	public void readContents() {
		if (fileContent!=null) {
			p.fill(255,0,0);
			p.text(fileContent, 20, 40);
		} else {
			p.fill(0,255,0);
			p.text("no file", 20, 20);
		}
	};
};
