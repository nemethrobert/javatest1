# Javatest1 - Folder structure experiment

This is an experimental project to load and display a custom folder structure, according to the configuration and the specified read/write rights. The application is flexible and highly configurable.

## Details

### Main features

* Reads the configuration from external files (including the list of folders for read/write user rights)
* Loads the folder structure from the file system, starting from the root folder specified in the configuration
* Displays the loaded folder/file structure in several ways: tree-like or plain format, with files or just folders, just names or full paths, etc.
* Moreover, when displaying the folder structure a set of read/write rights can be applied (by following certain rules, please see the next section)

### Folder visiblity rules

The following rules are applied only if using the access rights are enabled in the configuration ('ApplyAccessRights' setting)

A folder
* ... can be visible only if there is read and/or write access on it
* ... is anyway visible if it has write access grant - except if the folder could be reached just through non-readable folder(s)
* ... cannot be displayed if it has read-only access but has no any children folder that has write access granted
* ... also cannot be displayed if it is read-only, has writable child but this child is in a folder that is not set as readable

### Implementation

* This is a standard Java SE application
* It is a simple command line application, has no any user interface
* The input data source, as well as the behaviour of the application can be set up in the main configuration file
* The output will appear on the console
* The output content can be varying depending on the input source and the other configuration settings

## Installing

Download all three folders from the repository into a workspace. Edit the configuration according to your taste. Then simply build and run the Java application.

### Main configuration

[config/config.properties](https://github.com/nemethrobert/javatest1/blob/master/config/config.properties)

This file contains all settings of the application except the read/write rights.

#### RootFolderPath

The root folder of the folder structure to be read; 

Values: Either relative or absolute path can be specified here

Default value: test

#### IncludeFiles

Defines whether the displayed structure will contains folders and files, or just folders.

Values: yes / no  (everything except 'yes' will mean 'no')

Default value: no

#### ApplyAccessRights

If set to 'yes' then the access rights (and the related rules) will be also taken into consideration when deciding what to display.

Values: yes / no  (everything except 'yes' will mean 'no')

Default value: yes

#### ReadableFoldersListFile

The location and file name of the readable folders' list.

Values: File name with relative or absolute path

Default value: config/readablefolders.txt

#### WritableFoldersListFile

The location and file name of the writable folders' list.

Values: File name with relative or absolute path

Default value: config/writablefolders.txt

#### PrintStructureOnConsoleAlsoDuringLoadingFromFileSystem

If set to 'yes' then it prints the folder structure on the console directly when it is being loaded from the file system.

Values: yes / no  (everything except 'yes' will mean 'no')

Default value: no

#### PrintGrantFileContentsOnTheConsole

When set to 'yes' then the read content of the read/write access files are displayed on the console output (the commented out lines of the files are simply ignored).

Values: yes / no  (everything except 'yes' will mean 'no')

Default value: yes

#### DisplayNameWithPath

Defines if just the file/folder name is displayed or its full path.

Values: yes / no  (everything except 'yes' will mean 'no')

Default value: no

#### FoldersInBrackets=yes

When turned on (by setting 'yes') the folder names are put into square brackets (to be able to easily distinguish them from the files)

Values: yes / no  (everything except 'yes' will mean 'no')

Default value: yes

#### PathItemDelimiter

Delimiter between the file system path elements. 

Values: On Windows it is normally '\' (backslash) while on Linux systems it is '/' (slash);  IMPORTANT! The special characters have to be escaped properly

Default value: \\   (= backslash character, escaped)

#### IndentLinesByLevelInTheTree

If 'yes' then the tree elements (files and folders) will be indented according to their position (level) in the tree.

Values: yes / no  (everything except 'yes' will mean 'no')

Default value: yes

#### IndentText

The character or short string that is used for indenting the tree elements.

Values: any character or short string; IMPORTANT! The special characters have to be escaped properly

Default value: \t   (= tabulator character, escaped)

#### CommentOutCharacter

The character or short string that is used for indenting the tree elements.

Values: any character or short string (preferably special characters)

Default value: #

#### DisplayOnlyTheWritableFolders

When it is set to 'yes' then only those folders are displayed in the output which have write access defined.

Values: yes / no  (everything except 'yes' will mean 'no')

Default value: no

### Main configuration examples


#### Sample 1: Printing just the folders (with full path) with write access:

Config file:
```
RootFolderPath=test
IncludeFiles=no
ApplyAccessRights=yes
ReadableFoldersListFile=config/readablefolders.txt
WritableFoldersListFile=config/writablefolders.txt
PrintStructureOnConsoleAlsoDuringLoadingFromFileSystem=no
PrintGrantFileContentsOnTheConsole=yes
DisplayNameWithPath=yes
FoldersInBrackets=yes
PathItemDelimiter=\\
IndentLinesByLevelInTheTree=no
IndentText=\t
CommentOutCharacter=#
DisplayOnlyTheWritableFolders=yes
```
Output:
```
--------------------------------------------------------------------------
---------------------- Loading the configuration... ----------------------
--------------------------------------------------------------------------

... DONE, configuration is loaded

Current settings:

Root folder (with absolute or relative path) : test
Also include the files                       : no
Display the file system tree during loading  : no
Take access rights into consideration        : yes
Read only grants configuration file          : config/readablefolders.txt
Writable folder grants configuration file    : config/writablefolders.txt
Print grant file contents on the console     : yes
Display the names with full path             : yes
Path item delimiter                          : \
Indent text                                  : 	
Comment out character (for the grant lists)  : #
Display only the writable folders            : yes


--------------------------------------------------------------------------
------------------ Loading the READ ACCESS list file... ------------------
--------------------------------------------------------------------------

test\testfolder1
test\testfolder1\testfolder12
test\testfolder1\testfolder12\testfolder121
test\testfolder1\testfolder12\testfolder121\testfolder1211
test\testfolder1\testfolder12\testfolder122
test\testfolder2
test\testfolder2\testfolder21
test\testfolder2\testfolder22
test\testfolder2\testfolder22\testfolder221
test\testfolder2\testfolder22\testfolder222
test\testfolder2\testfolder22\testfolder222\testfolder2221
test\testfolder2\testfolder22\testfolder223
test\testfolder2\testfolder23
test\testfolder3
test\testfolder4
test\testfolder5\testfolder51
test\testfolder5\testfolder51\testfolder511
test\testfolder5\testfolder51\testfolder512
test\testfolder5\testfolder51\testfolder513
test\testfolder6
test\testfolder7
test\testfolder7\folder_a
test\testfolder7\folder_a\folder_a
test\testfolder7\folder_a\folder_b
test\testfolder7\folder_a\folder_c
test\testfolder7\folder_b
test\testfolder8

... DONE, read-only access file is loaded


--------------------------------------------------------------------------
----------------- Loading the WRITE ACCESS list file... ------------------
--------------------------------------------------------------------------

test\testfolder2\testfolder22\testfolder221
test\testfolder2\testfolder23
test\testfolder5\testfolder51\testfolder512
test\testfolder7\folder_a\folder_a

... DONE, write access file is loaded

--------------------------------------------------------------------------
--------- Loading the folder structure from the file system... -----------
--------------------------------------------------------------------------


... DONE, folder structure is loaded into the data model.

--------------------------------------------------------------------------
----------- Displaying the structure stored in the data model ------------
--------------------------------------------------------------------------

-> Displaying ONLY THE WRITABLE FOLDERS (that can be accessed according to the configuration)
-> Full path is displayed for every item

[test\testfolder2\testfolder22\testfolder221]
[test\testfolder2\testfolder23]
[test\testfolder7\folder_a\folder_a]




--- TASK IS COMPLETED ---

```

#### Sample 2: Printing complete folder structure including the files, displaying short names and do not care with access rights: 

Config file:
```
RootFolderPath=test
IncludeFiles=yes
ApplyAccessRights=no
ReadableFoldersListFile=config/readablefolders.txt
WritableFoldersListFile=config/writablefolders.txt
PrintStructureOnConsoleAlsoDuringLoadingFromFileSystem=no
PrintGrantFileContentsOnTheConsole=no
DisplayNameWithPath=no
FoldersInBrackets=yes
PathItemDelimiter=\\
IndentLinesByLevelInTheTree=yes
IndentText=\t
CommentOutCharacter=#
DisplayOnlyTheWritableFolders=no
```

Output:
```
--------------------------------------------------------------------------
---------------------- Loading the configuration... ----------------------
--------------------------------------------------------------------------

... DONE, configuration is loaded

Current settings:

Root folder (with absolute or relative path) : test
Also include the files                       : yes
Display the file system tree during loading  : no
Take access rights into consideration        : no
Read only grants configuration file          : config/readablefolders.txt
Writable folder grants configuration file    : config/writablefolders.txt
Print grant file contents on the console     : no
Display the names with full path             : no
Path item delimiter                          : \
Indent text                                  : 	
Comment out character (for the grant lists)  : #
Display only the writable folders            : no


--------------------------------------------------------------------------
------------------ Loading the READ ACCESS list file... ------------------
--------------------------------------------------------------------------


... DONE, read-only access file is loaded


--------------------------------------------------------------------------
----------------- Loading the WRITE ACCESS list file... ------------------
--------------------------------------------------------------------------


... DONE, write access file is loaded

--------------------------------------------------------------------------
--------- Loading the folder structure from the file system... -----------
--------------------------------------------------------------------------


... DONE, folder structure is loaded into the data model.

--------------------------------------------------------------------------
----------- Displaying the structure stored in the data model ------------
--------------------------------------------------------------------------


[testfolder1]
	testfile1.txt
	testfile5.txt
	[testfolder11]
		testfile5.txt
	[testfolder12]
		testfile2.txt
		[testfolder121]
			[testfolder1211]
				testfile1.txt
				testfile3.txt
		[testfolder122]
			testfile1.txt
[testfolder2]
	testfile1.txt
	testfile2.txt
	testfile3.txt
	[testfolder21]
		testfile2.txt
		testfile3.txt
	[testfolder22]
		[testfolder221]
			testfile1.txt
		[testfolder222]
			testfile2.txt
			[testfolder2221]
				testfile1.txt
		[testfolder223]
			testfile3.txt
	[testfolder23]
		testfile3.txt
[testfolder3]
	testfile1.txt
	testfile2.txt
	testfile3.txt
[testfolder4]
	testfile4.txt
[testfolder5]
	[testfolder51]
		testfile2.txt
		testfile3.txt
		[testfolder511]
			testfile55.txt
		[testfolder512]
			testfile12.txt
		[testfolder513]
			testfile21.txt
[testfolder6]
	testfile6.txt
[testfolder7]
	[folder_a]
		[folder_a]
			testfile5.txt
		[folder_b]
			testfile_ab.txt
		[folder_c]
			testfile7.txt
			testfile8.txt
		testfile1.txt
	[folder_b]
		testfile_b.txt
	testfile1.txt
[testfolder8]
	testfile9.txt




--- TASK IS COMPLETED ---

```


### Access right files

* These two files are responsible to define the read and the write access rights
* The files contain the list of folders with full paths, one folder per line, one file with the list of readable folders and another one for the writable folders
* The names and the location of these files are not strictly defined: these can be named to anything and can be put to anywhere in the file system (because this information can be flexibly changed in the main configuration file)
* However, it is preferred to use the pre-defined files: [config/readablefolders.txt](https://github.com/nemethrobert/javatest1/tree/master/config/readablefolders.txt) and [config/writablefolders.txt](https://github.com/nemethrobert/javatest1/tree/master/config/readablefolders.txt)
* Comment characters can be also used, for example to temporarily ignore folders

Example file:
```
test\testfolder1
#test\testfolder1\testfolder11
test\testfolder1\testfolder12
test\testfolder1\testfolder12\testfolder121
test\testfolder1\testfolder12\testfolder121\testfolder1211
test\testfolder1\testfolder12\testfolder122
test\testfolder2
test\testfolder2\testfolder21
test\testfolder2\testfolder22
test\testfolder2\testfolder22\testfolder221
test\testfolder2\testfolder22\testfolder222
test\testfolder2\testfolder22\testfolder222\testfolder2221
test\testfolder2\testfolder22\testfolder223
test\testfolder2\testfolder23
test\testfolder3
test\testfolder4
#test\testfolder5
test\testfolder5\testfolder51
test\testfolder5\testfolder51\testfolder511
test\testfolder5\testfolder51\testfolder512
test\testfolder5\testfolder51\testfolder513
test\testfolder6
test\testfolder7
test\testfolder7\folder_a
test\testfolder7\folder_a\folder_a
test\testfolder7\folder_a\folder_b
test\testfolder7\folder_a\folder_c
test\testfolder7\folder_b
test\testfolder8
```

## Running a test

The 'test' folder of the project contains a complex sample folder structure with many subfolders and files. By default, the configuration is set to load this folder tree. Also, there are two sample read/write access right files, with list of these test folders. Many access right combinations can be tested by simply commenting out some of them.

## Authors

* **Robert Németh** - [nemethrobert](https://github.com/nemethrobert)

## License

This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/nemethrobert/javatest1/blob/master/LICENSE.md) file for details
