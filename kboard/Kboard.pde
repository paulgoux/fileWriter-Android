Keyboard key1;


void settings(){
  size(600,600,P2D);
};

BMScontrols b;
fileOutput f;

void setup(){
  orientation(LANDSCAPE); 
  b = new BMScontrols(this);
  f = b.output;
  f.setAndroidDialogue("No","Yessss");
};

void draw(){
  stroke(0);
  strokeWeight(1);
  background(255);
  b.run(mouseButton);
  if (f.fileContent!=null) {
    fill(0);
    text(f.counter, 20, 10);
    text(f.fileContent, 20, 20);
  } else {
    fill(0);
    text("no file", 20, 20);
  }
};

void mousePressed(){
    
    if (f.writeFile&&f.location!=null) {
    f.writeLn("iuhiuhgaiugf"+hour()+":"+minute()+":"+second());
    f.close();
    f.loadStrings();
  }
  b.mouseLogic();
  //f.mlogic2();
};

void keyPressed(){
  b.keyboard();
};
