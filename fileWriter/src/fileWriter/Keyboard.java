package fileWriter;
import processing.core.PApplet;

public class Keyboard{
	BMScontrols Bms;
	PApplet p;
	public boolean getKey,key1,key2,kd;
	public String s1= "";
	public char key;
	public int keyCode;
	
	
	public Keyboard(PApplet p){
		this.p = p;
	};

	public Keyboard(BMScontrols bms){
		Bms = bms;
		p = bms.applet;
		this.p = p;
	};

	public void run(){
		if(key1&&getKey&&!p.mousePressed){
			getKey = false;
			s1 += p.key;
			this.key = p.key;
			this.keyCode = p.keyCode;
			kd = true;
		}else {
			s1 = "";
			kd = false;
			key1 = false;
		}
		    if(kd)p.println("key",s1,this.key,this.keyCode);

	};

	public void getKey(){
		if(!getKey){
			key1 = true;
			getKey = true;

		}
	};

	public void logic(){
		getKey();
		run();
//		p.println("keyboard logic, key",this.key,this.keyCode);
	};

};