/**
 * Folder structure loader test application
 * v1.0.100
 * 
 * Created by Róbert Németh, Mogyoród, Hungary, 01/12/2018
 * rnemeth at panadea.com
 */

package alizjava;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LoadFolders {
	
	private static boolean parseYesNoBoolean(String booleanStr) {
		return booleanStr.trim().toLowerCase().equals("yes");
	}

	private static String parseBooleanToYesNo(boolean bool) {
		return (bool ? "yes" : "no");
	}
	
	
	public static void main(String[] args) {
		
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			System.out.println("--------------------------------------------------------------------------");
			System.out.println("---------------------- Loading the configuration... ----------------------");
			System.out.println("--------------------------------------------------------------------------");
			
			input = new FileInputStream("config/config.properties");
			prop.load(input);
			
			TreeStructure fullFolderStructure = new TreeStructure(
					prop.getProperty("RootFolderPath"),
					parseYesNoBoolean(prop.getProperty("IncludeFiles")),
					parseYesNoBoolean(prop.getProperty("ApplyAccessRights")),
					parseYesNoBoolean(prop.getProperty("PrintStructureOnConsoleAlsoDuringLoadingFromFileSystem")),
					parseYesNoBoolean(prop.getProperty("IndentLinesByLevelInTheTree")),
					parseYesNoBoolean(prop.getProperty("DisplayNameWithPath")),
					parseYesNoBoolean(prop.getProperty("FoldersInBrackets")),
					prop.getProperty("PathItemDelimiter"),
					prop.getProperty("IndentText"),
					parseYesNoBoolean(prop.getProperty("DisplayOnlyTheWritableFolders"))
					);
			
			String readableFoldersListFile = prop.getProperty("ReadableFoldersListFile");
			String writableFoldersListFile = prop.getProperty("WritableFoldersListFile");
			String commentOutCharacter = prop.getProperty("CommentOutCharacter");
			boolean printGrantFileContentsOnTheConsole = parseYesNoBoolean(prop.getProperty("PrintGrantFileContentsOnTheConsole"));
			
			System.out.println("");
			System.out.println("... DONE, configuration is loaded");
			System.out.println("");
			System.out.println("Current settings:");
			System.out.println("");
			System.out.println("Root folder (with absolute or relative path) : " + fullFolderStructure.getRootFolderPath());
			System.out.println("Also include the files                       : " + parseBooleanToYesNo(fullFolderStructure.isIncludeFiles()));
			System.out.println("Display the file system tree during loading  : " + parseBooleanToYesNo(fullFolderStructure.isPrintStructureOnConsoleAlsoDuringLoadingFromFileSystem()));
			System.out.println("Take access rights into consideration        : " + parseBooleanToYesNo(fullFolderStructure.isApplyAccessRights()));
			System.out.println("Read only grants configuration file          : " + readableFoldersListFile);
			System.out.println("Writable folder grants configuration file    : " + writableFoldersListFile);
			System.out.println("Print grant file contents on the console     : " + parseBooleanToYesNo(printGrantFileContentsOnTheConsole));
			System.out.println("Display the names with full path             : " + parseBooleanToYesNo(fullFolderStructure.isDisplayNameWithPath()));
			System.out.println("Path item delimiter                          : " + fullFolderStructure.getPathItemDelimiter());
			System.out.println("Indent text                                  : " + fullFolderStructure.getIndentText());
			System.out.println("Comment out character (for the grant lists)  : " + commentOutCharacter);
			System.out.println("Display only the writable folders            : " + parseBooleanToYesNo(fullFolderStructure.isDisplayOnlyTheWritableFolders()));
			
			
			System.out.println("");

			
			
			BufferedReader bufferedReader;
			String line;
			
			System.out.println("");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("------------------ Loading the READ ACCESS list file... ------------------");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("");
			
			bufferedReader = new BufferedReader(new FileReader(readableFoldersListFile));
			List<String> readableFolderPaths = new ArrayList<String>();

			while ( (line = bufferedReader.readLine()) != null) {
				if (!line.startsWith(commentOutCharacter)) {
					readableFolderPaths.add(line);
				    if (printGrantFileContentsOnTheConsole) {
				    	System.out.println(line);			    	
				    }
				}
			}
			bufferedReader.close();
			
			fullFolderStructure.setReadableFolderPaths(readableFolderPaths);
			readableFolderPaths = null;
			
			System.out.println("");
			System.out.println("... DONE, read-only access file is loaded");
			System.out.println("");

			
			
			System.out.println("");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("----------------- Loading the WRITE ACCESS list file... ------------------");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("");
			
			bufferedReader = new BufferedReader(new FileReader(writableFoldersListFile));
			List<String> writableFolderPaths = new ArrayList<String>();

			while ( (line = bufferedReader.readLine()) != null) {
				if (!line.startsWith(commentOutCharacter)) {
					writableFolderPaths.add(line);
				    if (printGrantFileContentsOnTheConsole) {
				    	System.out.println(line);
				    }
				}
			}
			bufferedReader.close();
			
			fullFolderStructure.setWritableFolderPaths(writableFolderPaths);
			writableFolderPaths = null;
			
			System.out.println("");
			System.out.println("... DONE, write access file is loaded");
			System.out.println("");

			
			
			
			
			/*TreeStructure fullFolderStructure = new TreeStructure();*/
			
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("--------- Loading the folder structure from the file system... -----------");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("");
			fullFolderStructure.loadFolderStructureFromFileSystem();

			System.out.println("");
			System.out.println("... DONE, folder structure is loaded into the data model.");
			
			
			
			System.out.println("");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("----------- Displaying the structure stored in the data model ------------");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("");
			
			if (fullFolderStructure.isApplyAccessRights()) {
				if (fullFolderStructure.isDisplayOnlyTheWritableFolders()) {
					System.out.println("-> Displaying ONLY THE WRITABLE FOLDERS (that can be accessed according to the configuration)");
				} else {
					System.out.println("-> Access rights are applied (coming from the configuration files)");
					if (!fullFolderStructure.isIncludeFiles()) {
						System.out.println("-> Only folders are displayed (files are ignored)");
					}
				}
					
			}
			
			if (fullFolderStructure.isDisplayNameWithPath()) {
				System.out.println("-> Full path is displayed for every item");
			}
			System.out.println("");
			
			fullFolderStructure.printFolderStructureFromDataModel();
			
			
			
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("--- TASK IS COMPLETED ---"); 
			System.out.println("");

			
			
		} catch (IOException ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
	}

}
