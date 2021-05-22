package fileWriter;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.widget.TextView;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class fileOutput {
	BMS Bms;
	public PApplet applet;
	public FileWriter output;
	float x, y, w, h;
	public boolean save, onMouseUp, mdown, debug, append, appendFile, match, 
				   append2, overWrite, overWriteOnce = true, writeOnce, writeFile, click, saveImage,showDialogue,
				   getPermission = true, fileExists, reWrite, folderExists, overWriteFirst, overWritelast, 
				   writeFirst, folderCreated, saveAudio, saveVideo, getWritePermission, showDialog, checkFile;
	public int counter = -1, counter2, writeCount, failCount, folderSize;
	public File file, file2, file3;
	public File[] SDcards ; 
	public String location, filePath, folderPath = "";
	public String text = "oioijsofoivnsoindv", absolutePath, ext, fileName, fileContent = "";
	public String androidDialogueTitle = "oiahoidhao", 
			dialogueB1Title = "", dialogueB2Title = "", dialogueBody;
	public String title1 = "Would you like to create File";
	public String title2 = "Would you like to overWrite File";
	public String currentFile, lastFile;

	Permission p;
	Activity activity;
	Context context;
	public TextView msg;
	public int msgId;
	public PImage img;
	public dialogueBox dbox, dbox1, dbox2, dbox3;

	public fileOutput() {

	};

	public fileOutput(BMS bms) {
		Bms = bms;
		applet = bms.applet;
		p = new Permission(applet, "WRITE_EXTERNAL_STORAGE");
		init();
	};

	public fileOutput(boolean a,BMS bms) {
		Bms = bms;
		applet = bms.applet;
		p = new Permission(applet, "WRITE_EXTERNAL_STORAGE");
		overWrite = true;
		appendFile = true;
	};

	public fileOutput(PApplet app) {
		applet = app;
		p = new Permission(applet, "WRITE_EXTERNAL_STORAGE");
		init();
	};
	//currently unused
	public fileOutput(String location,BMS bms) {
		Bms = bms;
		applet = bms.applet;
		p = new Permission(applet, "WRITE_EXTERNAL_STORAGE");
		//img = applet.get();
		setLocation(location);
		init();
	};

	public fileOutput(BMS bms, String location) {

		Bms = bms;
		applet = bms.applet;
		this.applet = applet;
		p = new Permission(applet, "WRITE_EXTERNAL_STORAGE");
		setLocation(location);
		init();
	};

	public void getAndroidInfo() {
		activity = applet.getActivity(); 
		context = activity.getApplicationContext();
		absolutePath = new String(Environment.getExternalStorageDirectory().getAbsolutePath());
	};

	public void init() {
		x = 0;
		y = 0;
		w = applet.width;
		h = applet.height;
		//dbox = 

		String s1 = "Would you like to overWrite "+fileName+"."+ext+"?";
		//float dboxWidth = 120;
		//float tSize = 20;
		//applet.textSize(tSize);
		//float dw = applet.textWidth(s1)+100;
		//float dboxHeight = 150;
		//float dx = applet.width/2-dw/2;
		//float dy = applet.height/2-50/2;
	};

	public void loadStrings() {
		loadFile();
	};

	String loadFile() {

		FileInputStream fis = null;
		if (writeFile) {
			try {

				fis = new FileInputStream (new File(file2.getAbsolutePath()));

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
				applet.println("cannot fetch file", file2.getAbsolutePath());
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
		writeCount++;
		if (writeCount>0)append = true;

		try {
			output.flush();
			output.close();
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
	};

	public void write(float s) {

		if (!overWrite)checkLocation();
		try {

			output = new FileWriter(file2, append);
			printWrite(s);
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
		
	};

	public void writeLn(float s) {
		if (!overWrite)checkLocation();
		
		try {

			output = new FileWriter(file2, append);
			printWriteLn(s);
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
	};

	public void write(float[] s) {

		if (!overWrite)checkLocation();
		try {

			output = new FileWriter(file2, append);
			printWrite(s);
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
	};

	public void writeLn(float[] s) {
		if (!overWrite)checkLocation();
		try {

			output = new FileWriter(file2, append);
			printWriteLn(s);
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
	};

	public void write(String s) {
		if (!overWrite)checkLocation();
		try {

			output = new FileWriter(file2, append);
			printWrite(s);
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
	};

	public void writeLn(String s) {
		if (!overWrite)checkLocation();
		
		try {
			output = new FileWriter(file2, append);
			printWriteLn(s);
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
		
	};

	public void write(String []s) {
		if (!overWrite)checkLocation();
		try {
			output = new FileWriter(file2, append);
			printWrite(s);
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
		
	};

	public void writeLn(String []s) {
		if (!overWrite)checkLocation();
		try {
			output = new FileWriter(file2, append);
			printWriteLn(s);
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
		
	};

	public void printWrite(String s) {
		if (!overWrite)checkLocation();
		try {
			output.append(s);
		}
		catch(IOException e) {
			applet.println("pw no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("pw no such path",file2);
		}
	};

	public void printWrite(String[] s) {
		if (!overWrite)checkLocation();
		try {
			for (int i=0; i<s.length; i++) {
				output.append(s[i]);
			}
		}
		catch(IOException e) {
			applet.println("pw no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("pw no such path",file2);
		}
	};

	public void printWriteLn(String s) {
		if (!overWrite)checkLocation();
		try {
			output.append(s);
			output.append("\n");
		}
		catch(IOException e) {
			applet.println("pwln no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("pwln no such path",file2);
		}
	};

	public void printWrite(float s) {
		if (!overWrite)checkLocation();
		try {
			output.append(applet.str(s));
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
	};

	public void printWriteLn(float s) {
		if (!overWrite)checkLocation();
		try {
			output.append(applet.str(s));
			output.append("\n");
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
	};

	public void printWrite(float []s) {
		if (!overWrite)checkLocation();
		try {
			for (int i=0; i<s.length; i++) {
				output.append(applet.str(s[i]));
			}
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
	};

	public void printWriteLn(float []s) {
		if (!overWrite)checkLocation();
		try {
			for (int i=0; i<s.length; i++) {
				output.append(applet.str(s[i]));
				output.append("\n");
			}
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
	};

	public void printWriteLn(String []s) {
		if (!overWrite)checkLocation();
		try {
			for (int i=0; i<s.length; i++) {
				output.append(s[i]);
				output.append("\n");
			}
		}
		catch(IOException e) {
			applet.println("no such path io",file2);
		}catch(NullPointerException e) {
			applet.println("no such path",file2);
		}
	};
	
	File findFolder() {

		if (!folderExists) {
			try { 
				file = new File(absolutePath + "/" + folderPath);
				PApplet.println("checking folder", file);
				if (!file.exists()) {
					file.mkdirs();
					folderCreated = true;
					file2 = new File(file, "/"+fileName + "." + ext);
				} else {
					folderExists = true;
					PApplet.println("folder Exists");
				}
			} 
			catch (Exception e) { 
				failCount++;
				if (failCount<5)PApplet.println("Error while creating folder: " + absolutePath, folderPath);
			}
		}
		return file;
	};

	public void checkLastFile() {
		String s2 = file +"/"+fileName;
		String s1 = fileName + counter + "." + ext;
		if (!writeFile) {
			getFolderLength(file.getAbsolutePath());
			getLastIndex(file.getAbsolutePath());
		}
		//if(counter==-1)
		if (true) {
			if (writeFile&&(folderCreated||overWrite||counter==-1&&!overWrite)) {
				currentFile = s1;
				s2 = file +"/"+ fileName + "." + ext;
				//applet.println("create file/overWrite");
			} else if (writeFile) {
				s2 += counter + "." + ext;
				currentFile = s2;
				//applet.println("overfile");
			} else {
				currentFile = s1;
				s2 = file +"/"+ fileName + "." + ext;
				//applet.println("check condition");
			}
		}

		file2 = new File(s2);
		//if(!overWrite&&writeFile)
		applet.println("check last index", file2, s1);
		if (writeFile&&folderExists) {
			fileExists = true;
			if (!saveImage&&!saveAudio&&!saveVideo) {
				try {
					output = new FileWriter(file2, append);
				}
				catch(IOException e) {
				}
			}
			writeCount ++;
		}
	};

	public void checkLocation() {

		PApplet.println("find folder");
		findFolder();
		checkLastFile();
	};

	public void dboxLogic() {
		tab t = dbox.main;
		tab t1 = dbox1.main;
		if (Bms.mousePressed)showDialogue = true;
		if (getWritePermission)showDialogue = false;

		if (showDialogue) {
			if (Bms.click)applet.println("show Dialogue");
			if (!folderExists||!folderExists&&!fileExists) {
				if (Bms.click)applet.println("no folder");

				if (dbox!=null) {
					//if(!folderCreated)t.title.label = title1 +" " + fileName +"."+ext;
					//else 
					t.title.label = title1 +" " + fileName +"."+ext;
					if (t.toggle(0, this, "getWritePermission")) {
						writeFile = true;
						overWrite = true;
						getWritePermission = true;
						applet.println("folder found new file yes");
					}
					if (dbox.main.toggle(1, this, "getWritePermission")) {
						getWritePermission = true;
						applet.println("folder found new file no");
					}
				}
			} else {
				if (Bms.click)applet.println("file found", fileName +"."+ext);
				//androidDialogueTitle = file2.getAbsolutePath();
				//String s1 = file.getAbsolutePath();
				if (dbox1!=null) {
					if (fileExists)t1.title.label = title2 +" " + fileName +"."+ext;
					else t1.title.label = title1 +" " + fileName +"."+ext;
					if (t1.toggle(0, this, "getWritePermission")) {
						writeFile = true;
						overWrite = true;
						getWritePermission = true;
						applet.println("folder created new file yes", file2);
					}
					if (dbox1.main.toggle(1, this, "getWritePermission")) {
						if (fileExists)writeFile = true;

						getWritePermission = true;
						applet.println("folder created new file no");
					}
				}
			}
		}
	};

	public void logic() {
		if (Bms.mousePressed&&!mdown) {
			img = applet.get();
			mdown = true;
		}
		if (!Bms.mousePressed)mdown = false;
	};

	public void saveImage() {
		if (writeFile) {
			logic();
			applet.println("saveImage", file2, fileName, counter, ext);
			String s1 = file2.getAbsolutePath();
			applet.println(s1);
			img.save(s1);
			if (!overWrite)counter++;
			if (!overWrite)file2 = new File(file+"/"+fileName+counter+"."+ext);
			else file2 = new File(file+"/"+fileName+"."+ext);
		}
	};

	public void listen() {
		//if(writeFile&&dbox.main.getButton(0).click)checkLocation();
	};

	public void readContents() {
		if (fileContent!=null) {
			applet.fill(0);
			applet.text(counter, 20, 10);
			applet.text(fileContent, 20, 20);
		} else {
			applet.fill(0);
			applet.text("no file", 20, 20);
		}
	};


	public void setLocation(String s) {
		if (getPermission)getAndroidInfo();
		location = s;
		folderPath = s.substring(0, s.lastIndexOf("/"));
		fileName = s.replace(folderPath+"/", "");
		getExt(fileName);
		PApplet.println("Fname", folderPath);
		PApplet.println("fileName", fileName);
		PApplet.println("counter", counter);
		PApplet.println("ext", ext);
		checkLocation();
		init();
	}

	public void getExt(String location) {

		int count = 0;
		fileName = location.substring(0, location.indexOf("."));
		ext = location.replace(fileName, "");
		ext = ext.replace(".", "");
		ext = ext.replace(fileName, "");
	};

	public void drawDialogue() {
		dboxLogic();
		if (Bms.mousePressed)showDialog = true;
		if (getWritePermission)showDialog = false;
		if (showDialog) {
			applet.fill(255);
			applet.rect(0, 0, applet.width, applet.height);
			if (dbox!=null&&!fileExists&&!writeFile)dbox.draw();
			if (dbox1!=null&&fileExists&&!writeFile)dbox1.draw();
			if (dbox2!=null)dbox2.draw();
			if (dbox3!=null)dbox3.draw();
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

	public  boolean pos(PVector mouse) {
		return mouse.x>x&&mouse.x<x+w&&mouse.y>y&&mouse.y<y+h;
	};

	public  boolean pos() {
		return applet.mouseX>x&&applet.mouseX<x+w&&applet.mouseY>y&&applet.mouseY<y+h;
	};

	int getFolderLength(String s1) {
		String path = s1;
		File directory = new File(path);
		File[] files = directory.listFiles();
		if (files!=null) folderSize = files.length;
		return folderSize;
	};

	int getLastIndex(String s1) {
		String  []s = null;
		String path = s1;
		//applet.println("Files", "Path: " + path);
		File directory = new File(path);
		File[] files = directory.listFiles();
		int count = -1;

		if (!checkFile) {
			if (!writeFile)counter = -1;
			s = new String [files.length];
			//applet.println("Files", "Size: "+ files.length);
			for (int i = 0; i < files.length; i++) {
				s[i] = files[i].getName();
				if (s[i].contains(fileName)) {
					s[i] = s[i].replace(".txt", "");
					s[i] = s[i].replace(".doc", "");
					s[i] = s[i].replace(".docx", "");
					s[i] = s[i].replace(".mp3", "");
					s[i] = s[i].replace(".mp4", "");
					s[i] = s[i].replace(".jpg", "");
					s[i] = s[i].replace(".JPG", "");
					s[i] = s[i].replace(".bmp", "");
					s[i] = s[i].replace(".BMP", "");
					s[i] = s[i].replace(".gif", "");
					s[i] = s[i].replace(".GIF", "");
					s[i] = s[i].replace(".wav", "");
					s[i] = s[i].replace(".ogg", "");
					s[i] = s[i].replace(".wmv", "");
					s[i] = s[i].replace(fileName, "");
					s[i] = s[i].replace(fileName, "");
					int num = applet.parseInt(s[i]);
					if (isNumeric(s[i])&&num>=counter)counter = num;
					if (s[i].length()==0)count = 0;
				}
				if (s[i].contains(fileName+"."+ext))count++;
			}
			//applet.println("Counter:", counter,count);
			counter ++;
		}
		String s2 = path+"/"+fileName+"."+ext;
		String s3 = fileName+"."+ext;
		if (count>-1)fileExists = true;
		currentFile = s3;
		//applet.println("get last index:", s3);
		checkFile = true;
		return counter;
	};

	public boolean isNumeric(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		} 
		catch(NumberFormatException e) {  
			return false;
		}
	};
	
	public void keyboard() {
		
	};
	
	public void save() {
		
	};
	
	public void mlogic() {
		
	};
	
	//public void 
	
};