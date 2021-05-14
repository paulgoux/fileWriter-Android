Here is an all in one solution to save and read files on android. It might be restricted in some locations but for it writes to external storage no problem and will request permissions for you. Also I have packaged the permission class inside of the jar so if you want to you can simply call a new permission as follows.

To clarify you have the library that you can add to any sketch, make sure you are making use of the ketai library which is also attached.

The kboard folder contains and example sketch to show how to access the relevant functions.

The Bms.output.textBox can be repositioned with Bms.output.textBox.setPos(float x,float y);


github.com

paulgoux/fileWriter-Android
file writer reader for android. Contribute to paulgoux/fileWriter-Android development by creating an account on GitHub.

Permission p;

void setup(){
p = new Permission("CAMERA");
}
make sure your permission is also requested in your ide, whichever version that is. No need to make any changes to the xml file, good to go from the start.

please note there are still a few more tweaks required
