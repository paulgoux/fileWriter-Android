package fileWriter;

import processing.core.PApplet;

public class Permission{

	PApplet parent;
	String p;

	public Permission(PApplet pParent,String permissionName) {
		p = permissionName;
		parent = pParent;
		parent.requestPermission("android.permission."+ permissionName, "onPermissionResult", this);
		parent.println(permissionName);
	};

	public void onPermissionResult(boolean granted) {
		if (!granted) {
			PApplet.println("User did not grant",p, "permission.",p,"is disabled.");
		}
	};

};
