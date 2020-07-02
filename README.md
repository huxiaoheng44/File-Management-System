# File-Management-System
File Management System  
## module design  

### <1> Login module  

The login interface is provided by Main.java To display, as long as the user is required to enter the user name and password, and then compare with the data in the hash table, if there is no same, the error message will be returned; if the corresponding user information is generated, the user with the corresponding attribute will be generated, and the showmenu function will be called to display the corresponding menu bar.  

### <2> Menu module  

Java polymorphism can generate different user interfaces for users with different attributes. User is the parent class of three classes, and three subclasses administrator, operator and browser are derived from it. Then each subclass inherits the member variables name, password, role and its member methods showmenu() and changeself(), so users have these two basic operations Some special users, such as administrators, have greater authority to modify other user information, add new users, delete users and so on.  

### <3> Data processing module  

The data is basically stored in DataProcessing.java The hash table in the file then stores the user names and passwords of the three basic users. Other operations such as changing password, adding or deleting users are also implemented by this hash table. Each time, only the corresponding key value pairs in the hash table are modified. Therefore, the modified operation will be reset after each program restart.  

### <4> File Module  
1. Upload files  
Create a new file class by reading the file address, and then create bufferedinputstream object and bufferedoutputstream object correspondingly. InputStream is used to read the file into the stream, and then use OutputStream to write the file from the stream to the corresponding address.  

2.Download files  
The operation of downloading a file is similar to that of uploading a file, except that the address of the file used to write is changed to the address used to read in, and then the address responsible for writing is changed to the corresponding download path. Make the above changes to complete the download operation.  

3.File list  
A doc class is created to store the hash table corresponding to the file. Then, each time the file is operated, the hash table must be modified accordingly. When displaying the file list, the information can be read from the hash table.  
