/**
 * Created by Róbert Németh, Mogyoród, Hungary, 01/12/2018
 * rnemeth at panadea.com
 */

package alizjava;

import java.util.ArrayList;
import java.util.List;

public class TreeItem {

	public String name;
	public List<TreeItem> children;
	public boolean isFile;
	
	/* Constructors: */

	public TreeItem(String name, List<TreeItem> children, boolean isFile) {
		super();
		this.name = name;
		this.children = children;
		this.isFile = isFile;
	}
	
	public TreeItem(String name, List<TreeItem> children) {
		super();
		this.name = name;
		this.children = children;
		this.isFile = false;
	}

	public TreeItem(String name) {
		super();
		this.name = name;
		this.children = null; /*new ArrayList<TreeItem>();*/
		this.isFile = false;
	}

	public TreeItem(String name, boolean isFile) {
		super();
		this.name = name;
		this.children = null; /*new ArrayList<TreeItem>();*/
		this.isFile = isFile;
	}

	public TreeItem() {
		super();
		this.reset();
	}
	
	/* Getters and Setters: */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeItem> getChildren() {
		return children;
	}

	public void setChildren(List<TreeItem> children) {
		this.children = children;
	}
	
	public boolean getIsFile() {
		return isFile;
	}

	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}

	/* Other methods: */
	
	public void reset() {
		this.name = "<root>";
		this.children = null; /*new ArrayList<TreeItem>();*/
		this.isFile = false;
	}
	
	public void addChild(TreeItem child) {
		if (child != null) {
			if (this.children == null) {
				this.children = new ArrayList<TreeItem>();
			}
			this.children.add(child);
		}
	}

	public void addChild(String childName) {
		this.addChild(new TreeItem(childName));
	}
	
	public void removeChildByName(String childName) {
		if ((this.children != null) && (childName != null)) {
			for (int i = 0; i < (this.children).size(); i++) {			
				if (this.children.get(i).getName() == childName) {
					this.children.remove(i);
				}
			}
		}
	}
	
	public void removeChildByIsFile(boolean isFile) {
		if (this.children != null) {
			for (int i = 0; i < (this.children).size(); i++) {			
				if (this.children.get(i).getIsFile() == isFile) {
					this.children.remove(i);
				}
			}
		}
	}
	
	public void removeChildren() {
		this.children = null;
	}

	
	
	
}
