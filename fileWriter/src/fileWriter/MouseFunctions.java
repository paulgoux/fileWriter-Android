package fileWriter;

import processing.core.PApplet;
import processing.event.MouseEvent;

//MouseFunctions class
public class MouseFunctions {
	PApplet app;
	public boolean click,mousePressed,drag,mup,mouseReleased,mouseClicked,mouseDragged;
	
	public MouseFunctions() {
		
	};

	public MouseFunctions(PApplet p) {
		this.app = p;
		app.registerMethod("mouseEvent", this);
	};
	
	public void registerMouse() {
		app.registerMethod("mouseEvent", this);
	};
	
	public void destroy(){
		//destroys the registermethod or something like that 
	};
	
	public void mouseEvent(final MouseEvent evt) {
		switch(evt.getAction()) {
		case MouseEvent.PRESS:
			mousePressed();
			break;
		case MouseEvent.RELEASE:
			mouseReleased();
			break;
		case MouseEvent.MOVE:
			mouseMoved();
			break;
		case MouseEvent.DRAG:
			mouseDragged();
			break;
		case MouseEvent.CLICK:
			mouseClicked();
			break;
		}
	}
	public void mousePressed() {
		
	};
	
	public void mouseReleased() {
		
	};
	
	public void mouseMoved() {
		
	};
	public void mouseDragged() {
		
	};
	public void mouseClicked() {
		
	};
};
