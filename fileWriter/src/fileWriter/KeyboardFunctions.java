package fileWriter;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

//MouseFunctions class
public class KeyboardFunctions {
  public PApplet applet;
  public boolean getKey,key1,key2,kd,keydown;
  public String hist= "";
  public char key;
  public int keyCode;
  public TextArea textBox;
  
  public KeyboardFunctions() {
    
  };
  
  public void destroy(){
    //destroys the registermethod or something like that 
  };
  
  public void register(TextArea p){
    applet = p.applet;
    textBox = p;
    applet.registerMethod("keyEvent", this);
    applet.registerMethod("mouseEvent", this);
  };
  
  public void keyEvent(final KeyEvent evt) {
    switch(evt.getAction()) {
    case KeyEvent.PRESS:
      this.key = applet.key;
      hist += applet.key;
      this.keyCode = applet.keyCode;
      keyPressed();
      kd = true;
      break;
      case KeyEvent.RELEASE:
      keyReleased();
      kd = false;
      break;
    }
  };
  
  public void mouseEvent(final MouseEvent evt) {
    switch(evt.getAction()) {
    case MouseEvent.PRESS:
      mousePressed();
      break;
    }
  };
  
  public void mousePressed(){
    
  };
  
  public void keyPressed() {
    //runAll();
  };
  
  public void keyReleased() {
    
  };
  
  
};