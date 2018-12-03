/**
 * Created by Róbert Németh, Mogyoród, Hungary, 01/12/2018
 * rnemeth@panadea.com
 */

package alizjava;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TreeStructure extends TreeItem {

	
	public String rootFolderPath;
	public boolean includeFiles;
	public boolean applyAccessRights;
	public boolean printStructureOnConsoleAlsoDuringLoadingFromFileSystem;
	public boolean indentLinesByLevelInTheTree;
	public boolean displayNameWithPath; 
	public boolean foldersInBrackets;
	public String pathItemDelimiter;
	public String indentText;
	public List<String> readableFolderPaths;
	public List<String> writableFolderPaths;
	public boolean displayOnlyTheWritableFolders;
	
	/* Constructors: */
	

	public TreeStructure() {
		super();
		this.resetConfig();
	}
	

	public TreeStructure(
			String rootFolderPath,
			boolean includeFiles,
			boolean applyAccessRights,
			boolean printStructureOnConsoleAlsoDuringLoadingFromFileSystem,
			boolean indentLinesByLevelInTheTree,
			boolean displayNameWithPath,
			boolean foldersInBrackets,
			String pathItemDelimiter,
			String indentText,
			boolean displayOnlyTheWritableFolders) {
		super();
		this.resetConfig();
		this.rootFolderPath = rootFolderPath;
		this.includeFiles = includeFiles;
		this.applyAccessRights = applyAccessRights;
		this.printStructureOnConsoleAlsoDuringLoadingFromFileSystem = printStructureOnConsoleAlsoDuringLoadingFromFileSystem;
		this.indentLinesByLevelInTheTree = indentLinesByLevelInTheTree;
		this.displayNameWithPath = displayNameWithPath;
		this.foldersInBrackets = foldersInBrackets;
		this.pathItemDelimiter = pathItemDelimiter;
		this.indentText = indentText;
		this.displayOnlyTheWritableFolders = displayOnlyTheWritableFolders;
	}
	
	/* Getters and Setters: */
	
	public String getRootFolderPath() {
		return rootFolderPath;
	}


	public void setRootFolderPath(String rootFolderPath) {
		this.rootFolderPath = rootFolderPath;
	}


	public boolean isIncludeFiles() {
		return includeFiles;
	}


	public void setIncludeFiles(boolean includeFiles) {
		this.includeFiles = includeFiles;
	}


	public boolean isApplyAccessRights() {
		return applyAccessRights;
	}


	public void setApplyAccessRights(boolean applyAccessRights) {
		this.applyAccessRights = applyAccessRights;
	}


	public boolean isPrintStructureOnConsoleAlsoDuringLoadingFromFileSystem() {
		return printStructureOnConsoleAlsoDuringLoadingFromFileSystem;
	}


	public void setPrintStructureOnConsoleAlsoDuringLoadingFromFileSystem(
			boolean printStructureOnConsoleAlsoDuringLoadingFromFileSystem) {
		this.printStructureOnConsoleAlsoDuringLoadingFromFileSystem = printStructureOnConsoleAlsoDuringLoadingFromFileSystem;
	}


	public boolean isIndentLinesByLevelInTheTree() {
		return indentLinesByLevelInTheTree;
	}


	public void setIndentLinesByLevelInTheTree(boolean indentLinesByLevelInTheTree) {
		this.indentLinesByLevelInTheTree = indentLinesByLevelInTheTree;
	}

	public boolean isDisplayNameWithPath() {
		return displayNameWithPath;
	}


	public void setDisplayNameWithPath(boolean displayNameWithPath) {
		this.displayNameWithPath = displayNameWithPath;
	}

	public boolean isFoldersInBrackets() {
		return foldersInBrackets;
	}


	public void setFoldersInBrackets(boolean foldersInBrackets) {
		this.foldersInBrackets = foldersInBrackets;
	}

	public String getPathItemDelimiter() {
		return pathItemDelimiter;
	}


	public void setPathItemDelimiter(String pathItemDelimiter) {
		this.pathItemDelimiter = pathItemDelimiter;
	}

	public String getIndentText() {
		return indentText;
	}


	public void setIndentText(String indentText) {
		this.indentText = indentText;
	}

	public List<String> getReadableFolderPaths() {
		return readableFolderPaths;
	}


	public void setReadableFolderPaths(List<String> readableFolderPaths) {
		this.readableFolderPaths = readableFolderPaths;
	}


	public List<String> getWritableFolderPaths() {
		return writableFolderPaths;
	}


	public void setWritableFolderPaths(List<String> writableFolderPaths) {
		this.writableFolderPaths = writableFolderPaths;
	}

	public boolean isDisplayOnlyTheWritableFolders() {
		return displayOnlyTheWritableFolders;
	}


	public void setDisplayOnlyTheWritableFolders(boolean displayOnlyTheWritableFolders) {
		this.displayOnlyTheWritableFolders = displayOnlyTheWritableFolders;
	}


	


	/* ----------- */
	

	public void resetConfig() {
		this.rootFolderPath = "test";
		this.includeFiles = false;
		this.applyAccessRights = false;
		this.printStructureOnConsoleAlsoDuringLoadingFromFileSystem = false;
		this.indentLinesByLevelInTheTree = true;
		this.displayNameWithPath = false;
		this.foldersInBrackets = true;
		this.pathItemDelimiter = "\\";
		this.indentText = "\t";
		this.readableFolderPaths = null;
		this.writableFolderPaths = null;
		this.displayOnlyTheWritableFolders = false;
	}
	
	private String bracketizeText(String text, boolean doIt) {
		return doIt ? ("[" + text + "]") : text;
	}
	
	
	private String indentByTreeLevel(int level) {
		String returnString = "";
		if (this.indentLinesByLevelInTheTree) {
            for (int i = 0; i < level; i++) { 
            	returnString += this.indentText; 
            }
		}
		return returnString;
	}
	

	private TreeItem recursiveLoadFromFileSystem(File[] fileList, int level, TreeItem parentFolderStructure) {  
		 
		for (File f : fileList) { 
		 
			TreeItem thisFolderStructure = null;

			if (f.isFile()) {
        		
				if (this.includeFiles) {

					if (this.printStructureOnConsoleAlsoDuringLoadingFromFileSystem) {
						System.out.print(indentByTreeLevel(level));
		                System.out.println(this.displayNameWithPath ? f.getPath() : f.getName());
					}
					
					thisFolderStructure = new TreeItem(f.getName(), true);
				}
        		
        	} else if (f.isDirectory()) {  
        		
        		if (this.printStructureOnConsoleAlsoDuringLoadingFromFileSystem) {
        			
					System.out.print(indentByTreeLevel(level));
	                System.out.println(bracketizeText(
	                	(this.displayNameWithPath ? f.getPath() : f.getName()), 
	                	this.foldersInBrackets));
        		}
                
    			thisFolderStructure = new TreeItem(f.getName());
              
    			thisFolderStructure = recursiveLoadFromFileSystem(f.listFiles(), level + 1, thisFolderStructure); 
            } 
			
			if (thisFolderStructure != null) {
				parentFolderStructure.addChild(thisFolderStructure);
			}
		} 
		return parentFolderStructure;
	} 	
	
	
	public void loadFolderStructureFromFileSystem() {
		
		File rootFolder = new File(this.rootFolderPath); 
		
		if (rootFolder.exists() && rootFolder.isDirectory()) {
			this.setName(rootFolder.getName());
			this.setChildren(null);
			
			TreeItem fullFolderStructure = new TreeItem(rootFolder.getName());
			File fileList[] = rootFolder.listFiles();
			fullFolderStructure = recursiveLoadFromFileSystem(fileList, 0, fullFolderStructure);
			this.setName(fullFolderStructure.getName());
			this.setChildren(fullFolderStructure.getChildren());
			this.setIsFile(fullFolderStructure.getIsFile());
		}
		
	}
	
	
	
	
	/* ------- */
	
	
	private List<String> getAllChildrenNameWithPath(TreeItem parentFolderStructure, String fullPath, List<String> folderList) {
	
		if (parentFolderStructure.getChildren() != null) {
			
            fullPath += (fullPath.equals("") ? "" : this.pathItemDelimiter) + parentFolderStructure.getName();
            
			for (TreeItem child : parentFolderStructure.getChildren()) { 
				if (!child.getIsFile()) {
					
	        		String nameWithFullPath = fullPath + this.pathItemDelimiter + child.getName();
	        		folderList.add(nameWithFullPath);
	        		folderList = getAllChildrenNameWithPath(child, fullPath, folderList);
					
				}
			}
		}
		return folderList;
	}
	
	
	private String removeLastPartFromFolderPath(String folderNameWithPath) {
	    int index = folderNameWithPath.lastIndexOf(this.pathItemDelimiter);
	    return (index < 1) ? folderNameWithPath : folderNameWithPath.substring(0, index);
	}
	

	private List<String> getAllParentsNameWithPath(String nameWithFullPath, String stopAtThisFolder) {
		
		List<String> folderList = new ArrayList<String>();
		
		String prevNameWithFullPath = "";
		while ( !nameWithFullPath.equals(stopAtThisFolder) && !nameWithFullPath.equals(prevNameWithFullPath)) {
			prevNameWithFullPath = nameWithFullPath;
			nameWithFullPath = removeLastPartFromFolderPath(nameWithFullPath);
			/*if (!nameWithFullPath.equals(stopAtThisFolder)) {*/
				folderList.add(nameWithFullPath);
				/*System.out.println(nameWithFullPath);*/
			/*}*/
		}

		return folderList;
	}
	
	
	private void recursivePrintFolderStructure(TreeItem parentFolderStructure, int level, String fullPath) {  
		 
		if (parentFolderStructure.getChildren() != null) {
			
            fullPath += (fullPath.equals("") ? "" : this.pathItemDelimiter) + parentFolderStructure.getName();
            
			for (TreeItem child : parentFolderStructure.getChildren()) { 
			 
				if (child.getIsFile()) {
	        		
					if (this.includeFiles) {
						System.out.print(indentByTreeLevel(level));
		        		System.out.println((this.displayNameWithPath ? (fullPath + this.pathItemDelimiter) : "") + child.getName());
					}
	        		
	        	} else {  

	        		String nameWithFullPath = fullPath + this.pathItemDelimiter + child.getName();
	        		
	        		boolean theFolderCanBeDisplayed = true;
	        		
	        		
	        		/* Check if the folder can be displayed (included in the list of the readable or writable folders, as well as has at least one writable subfolder): */
	        		if (this.applyAccessRights) { 
	        		
	        			/* 1. Check if the currently selected folder has the grant to read and/or write: */
	        			boolean currentFolderIsReadable = this.readableFolderPaths.contains(nameWithFullPath);
	        			boolean currentFolderIsWritable = this.writableFolderPaths.contains(nameWithFullPath);
	        			
	        			if (!currentFolderIsReadable && !currentFolderIsWritable) {
	        				
	        				theFolderCanBeDisplayed = false;
	        				
	        			} else if (currentFolderIsReadable && !currentFolderIsWritable) {
	        				
	        				theFolderCanBeDisplayed = false;

		        			/* 2. Get all children of the folder: */
		        			List<String> childrenFolderList = getAllChildrenNameWithPath(child, fullPath, new ArrayList<String>());
		        					        			
		        			if (childrenFolderList.size() > 0) {
			        			/* 3. Remove every non-writable folders from the children's list: */
			        			List<String> writableChildrenFolderList = new ArrayList<String>();
			        			for (String childFolder : childrenFolderList) {
			        				if (this.writableFolderPaths.contains(childFolder)) {
			        					writableChildrenFolderList.add(childFolder);
			        				}
			        			}
			        			
			        			if (writableChildrenFolderList.size() > 0) {
			        				
				        			theFolderCanBeDisplayed = true;
			        				for (String writableChildFolder : writableChildrenFolderList) {
			        					
					        			/* 4. Check if there's any writable children whose all parents are readable and/or writable: */
			    		        		
					        			List<String> childrensParentFolderList = getAllParentsNameWithPath(writableChildFolder, nameWithFullPath);
						        		for (String childsParentFolder : childrensParentFolderList) {
						        			
						        			if (!this.readableFolderPaths.contains(childsParentFolder) && !this.writableFolderPaths.contains(childsParentFolder)) {
						        				theFolderCanBeDisplayed = false;
						        				break;
						        			}
						        		}
			        					
			        					if (theFolderCanBeDisplayed == false) {
			        						break;
			        					}
			        					
			        				}
			        				
			        			}
			        			
		        			}
		        			
	        			}
		        		
	        		}
	        		
	        		
	        		if (theFolderCanBeDisplayed) {
	        		
	        			if (!this.displayOnlyTheWritableFolders || (this.displayOnlyTheWritableFolders && this.writableFolderPaths.contains(nameWithFullPath))) {
			        		System.out.print(indentByTreeLevel(level)); 
							System.out.println(bracketizeText(
									(this.displayNameWithPath ? nameWithFullPath : child.getName()), 
									this.foldersInBrackets));
	        			}
						
			    		recursivePrintFolderStructure(child, level + 1, fullPath);
	        		}
		                
		    			
	            } 
				
			}
		}
	} 			
		
	
	
	public void printFolderStructureFromDataModel() {
		
		/*this.applyAccessRights;*/
		recursivePrintFolderStructure(this, 0, "");
		
	}
 	
	
	
}
