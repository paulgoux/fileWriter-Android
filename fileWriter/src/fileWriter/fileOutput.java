package fileWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.TextView;
import ketai.ui.KetaiKeyboard;
import android.view.Gravity;
import processing.core.PApplet;
import processing.core.PVector;
import android.app.Activity;
import android.app.AlertDialog;

public class fileOutput {
	BMScontrols Bms;
	public PApplet applet;
	public FileWriter output;
	public float x, y, w, h;
	public boolean save, onMouseUp, mdown, debug, append = true, appendFile, match, 
			append2, overWrite = true, overWriteOnce = true, writeOnce, writeFile, click, 
			getPermission = true, fileExists, reWrite,kdown;
	public int counter=-1, counter2;
	public File file, file2, file3;
	public File[] SDcards ; 
	public String location, filePath, folderPath = "";
	public String text = "oioijsofoivnsoindv", absolutePath, ext, fileName, fileContent = "";
	public String androidDialogueTitle = "Would you like to overWrite", 
			dialogueB1Title = "", dialogueB2Title = "", dialogueBody;
	Permission p;
	Activity activity;
	Context context;
	public TextView msg;
	public int msgId;
	public TextArea textBox;

	public fileOutput() {
	};

	public fileOutput(boolean a,BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
		p = new Permission(applet, "WRITE_EXTERNAL_STORAGE");
		overWrite = true;
		appendFile = true;
	};

	public fileOutput(BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
		p = new Permission(applet, "WRITE_EXTERNAL_STORAGE");
		textBox = new TextArea(20, 20, 400, 20, "Click to add text",bms);
		//init();
	};
	//currently unused
	public fileOutput(String location, BMScontrols bms) {
		Bms = bms;
		applet = bms.applet;
		p = new Permission(applet, "WRITE_EXTERNAL_STORAGE");
		setLocation(location);
		init();
	};

	public fileOutput(BMScontrols bms, String location) {
		Bms = bms;
		applet = bms.applet;
		p = new Permission(applet, "WRITE_EXTERNAL_STORAGE");
		setLocation(location);
		init();
	};

	public void getAndroidInfo() {
		activity = applet.getActivity(); 
		context = activity.getApplicationContext();
		absolutePath = Bms.absolutePath;
	};

	public void init() {
		x = 0;
		y = 0;
		w = applet.width;
		h = applet.height;

		String s1 = "Would you like to overWrite "+fileName+"."+ext+"?";
		float dboxWidth = 120;
		float tSize = 20;
		applet.textSize(tSize);
		float dw = applet.textWidth(s1)+100;
		float dboxHeight = 150;
		float dx = applet.width/2-dw/2;
		float dy = applet.height/2-50/2;
	};

	public void loadStrings() {
		loadFile(context);
	};

	String loadFile(Context context) {

		FileInputStream fis = null;
		if (writeFile) {
			try {
				if (!reWrite)fis = new FileInputStream (new File(file2.getAbsolutePath()));
				else fis = new FileInputStream (new File(file3.getAbsolutePath()));

				InputStreamReader isr = new InputStreamReader(fis);
				// READ STRING OF UNKNOWN LENGTH
				StringBuilder sb = new StringBuilder();
				char[] inputBuffer = new char[2048];
				int l;
				// FILL BUFFER WITH DATA
				while ((l = isr.read(inputBuffer)) != -1) {
					sb.append(inputBuffer, 0, l);
					applet.println("write data", inputBuffer, 0, l);
				}
				// CONVERT BYTES TO STRING
				fileContent = sb.toString();

				fis.close();
			}
			catch (Exception e) {
				applet.println("cannot fetch file", e);
			} 
			finally {
				if (fis != null) {

					fis = null;
				}
			}
		}
		return fileContent;
	};

	public void open() {
	};

	public void close() {
		try {
			output.flush();
			output.close();
		}
		catch(IOException e) {
		}
	};
	
	public void writeFinal(String s) {
		write(s);
		
		try {
			output.flush();
			output.close();
		}
		catch(IOException e) {
		}
	};
	
	public void writeFinal(float s) {
		write(s);
		
		try {
			output.flush();
			output.close();
		}
		catch(IOException e) {
		}
	};
	
	public void writeFinal(String [] s) {
		write(s);
		
		try {
			output.flush();
			output.close();
		}
		catch(IOException e) {
		}
	};
	
	public void writeFinalln(String s) {
		writeln(s);
		
		try {
			output.flush();
			output.close();
		}
		catch(IOException e) {
		}
	};
	
	public void writeFinalln(float s) {
//		writeln(s);
		
		try {
			output.flush();
			output.close();
		}
		catch(IOException e) {
		}
	};

	public void write(float s) {

		if (!overWrite)checkLocation();
		try {

			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWrite(s);
	};

	public void write(String s) {

		if (!overWrite)checkLocation();
		try {

			output = new FileWriter(file2, append);
		}
		catch(IOException e) {
		}
		printWrite(s);
	};

	public void writeln(String s) {
		if (writeFile&&location!=null) {
			if (!overWrite)checkLocation();
			try {
				output = new FileWriter(file2, append);
			}
			catch(IOException e) {
			}
			printwriteln(s);
		}
	};

	public void write(String []s) {
		if (writeFile&&location!=null) {
			if (!overWrite)checkLocation();
			try {
				output = new FileWriter(file2, append);
			}
			catch(IOException e) {
			}
			printWrite(s);
		}
	};

	public void writeln(String []s) {
		if (writeFile&&location!=null) {
			if (!overWrite)checkLocation();
			try {
				output = new FileWriter(file2, append);
			}
			catch(IOException e) {
			}
			printwriteln(s);
		}
	};

	public void printWrite(String s) {
		if (writeFile&&location!=null) {
			if (!overWrite)checkLocation();
			try {
				output.append(s);
			}
			catch(IOException e) {
			}
		}
	};

	public void printWrite(float s) {
		if (writeFile&&location!=null) {
			if (!overWrite)checkLocation();
			try {
				output.write(Float.toString(s));
			}
			catch(IOException e) {
			}
		}
	};

	public void printwriteln(String s) {
		if (writeFile&&location!=null) {
			if (!overWrite)checkLocation();
			try {
				output.append(s);
				output.append("\n");
			}
			catch(IOException e) {
			}
		}
	};

	public void printWrite(String []s) {
		if (writeFile&&location!=null) {
			if (!overWrite)checkLocation();
			try {
				for (int i=0; i<s.length; i++) {
					output.append(s[i]);
				}
			}
			catch(IOException e) {
			}
		}
	};

	public void printwriteln(String []s) {
		if (writeFile&&location!=null) {
			if (!overWrite)checkLocation();
			try {
				for (int i=0; i<s.length; i++) {
					output.append(s[i]);
					output.append("\n");
				}
			}
			catch(IOException e) {
			}
		}
	};

	public void checkLocation() {
		boolean k = false;
		String s1 = "Would you like to overWrite "+fileName+counter+"."+ext+"?";
		try {
			//
			file = new File(absolutePath, folderPath);
			if (!file.exists()&&counter==-1) {
				file.mkdirs();
				file2 = new File(file, "/"+fileName+"."+ext);
				androidDialogueTitle = file.getAbsolutePath();
				applet.println("checking file1", file2);
				if (file2.exists()) {
					counter ++;

					applet.println("File Exists");
					//dbox.main.title.label = s1;
				}
			} else if (counter==-1) {
				fileExists = true;
				counter = 0;
				k = true;
			} else k = true;
			if (overWrite&&writeOnce) {
				k=false;
			} else if (overWrite&&!writeOnce&&writeFile) {
				writeOnce = true;
				k = true;
			}
			boolean k1 = false;
			if (writeFile)
				while (k&&counter<100&&!k1) {
					s1 = fileName+counter+"."+ext+"?";
					try { 

						file2 = new File(absolutePath+"/"+folderPath+"/"+fileName+counter+"."+ext);
						file3 = new File(absolutePath+"/"+folderPath+"/"+fileName+(counter)+"."+ext);
						androidDialogueTitle = file3.getAbsolutePath();
						if (file2.exists()) { 
							counter++;
						} else {
							applet.println("checking file2", file2);
							//output = new FileWriter(file2, append);
							k = false;
							k1 = true;
							break;
						}
					} 
					catch (Exception e) { 
						applet.println("Error while saving file: " + e);
					}
				}
			if (k1||!writeFile) {
				//applet.println("file",file);
				//applet.println("Fname",folderName);
				//applet.println("fileName",fileName);
				//applet.println("counter",counter);
				//applet.println("ext",ext);
				if (file2==null&&counter<0)file2 = new File(file+"/"+fileName+"."+ext); 
				else if (file2==null)file2 = new File(file+"/"+fileName+"."+ext); 
				if (output==null) {
					//img.save(file+"/"+fileName+counter+"."+ext);

					if (writeFile)output = new FileWriter(file2, append);
					applet.println("File saved successfully.");
					//use write or append
				}
			}
			if (reWrite) {
				file2 = new File(file+"/"+fileName+counter+"."+ext); 
				output = new FileWriter(file2, append);
			}
			applet.println("write file", folderPath, fileName, counter, ext);
			if (reWrite)overWrite = true;
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	};

	public void listen() {
		//if(writeFile&&dbox.main.getButton(0).click)checkLocation();
		if (location==null) {
			textBox.setMouse();
			textBox.draw(true);
		}
		//else getPermission = true;
	};


	public void setLocation(String s) {
		if(getPermission)getAndroidInfo();
		location = s;
		int n = s.lastIndexOf("/");
		if(n>-1)folderPath = s.substring(0, n);
		else folderPath = "";
		fileName = s.replace(folderPath+"/", "");
		getExt(fileName);
		applet.println("Fname", folderPath);
		applet.println("fileName", fileName);
		applet.println("counter", counter);
		applet.println("ext", ext);
		checkLocation();
		init();
	}

	public void getExt(String location) {

		int n = location.indexOf(".");
		if(n>-1){
			fileName = location.substring(0, location.indexOf("."));
			ext = location.replace(fileName, "");
			ext = ext.replace(".", "");
			ext = ext.replace(fileName, "");
		}
		else {
			fileName = location;
			ext = "txt";
		}

	};

	public void keyboard() {
		Bms.keyboard.logic();
		textBox.getKey();
		if (Bms.keyboard.keyCode==66){
			setLocation(textBox.tempLine);
			KetaiKeyboard.toggle(applet);
		}

	};


	public void dialogBox() {

		if (location!=null&&!writeFile) {
			msg = new TextView(activity); 
			msg.setBackgroundColor(Color.BLUE);
			msg.setTextSize(32);
			msg.setText(dialogueBody); 
			msg.setGravity(Gravity.CENTER_HORIZONTAL); 
			msg.setTextColor(Color.WHITE); 

			activity.runOnUiThread(new Runnable() {
				public void run() {
					AlertDialog.Builder builder = new AlertDialog.Builder(activity);
					builder.setView(msg);

					androidDialogueTitle += " "+fileName+"."+ext+"?";
					builder.setTitle(androidDialogueTitle);
					builder.setPositiveButton(dialogueB1Title, 
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, 
								int which) {
							writeFile = true;
							overWrite = false;
							reWrite = true;
						}
					}
							);
					builder.setNegativeButton(dialogueB2Title, 
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, 
								int which) {
							writeFile = true;
							overWrite = true;
							//act.finish();
						}
					}
							)
					.show();
				}
			}
					);
		}
	};

	public void setAndroidDialogue(String s1, String s2) {
		dialogueB1Title = s1;
		dialogueB2Title = s2;
	};

	public void setAndroidDialogue(String s1, String s2, String s3) {
		androidDialogueTitle = s1;
		dialogueB1Title = s2;
		dialogueB2Title = s3;
	};

	public void setAndroidDialogueTitle(String s1) {
		androidDialogueTitle = s1;
	};

	public void setDialogueColor() {
	};

	public boolean click() {
		boolean k = false;
		if (pos()&&applet.mousePressed&&!click) {
			click = true;
			k = false;
		} else if (click&&!applet.mousePressed) {
			k = true;
			click = false;
		}
		return k;
	};

	public boolean pos(PVector mouse) {
		return mouse.x>x&&mouse.x<x+w&&mouse.y>y&&mouse.y<y+h;
	};

	public boolean pos() {
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	};

	public void mlogic2(){
		if (textBox.pos()&&location==null&&applet.mousePressed&&!kdown) {
			KetaiKeyboard.toggle(applet);
			kdown = true;
		}
		if(!applet.mousePressed)kdown = false;
		dialogBox();
	};

	public void mlogic(){
		if (textBox.pos()&&location==null)
			KetaiKeyboard.toggle(applet);
		dialogBox();
	};
};
