package package1;
import java.io.*;
import java.sql.SQLException;
import java.util.Enumeration;

public abstract class User {
	private String name;
	private String password;
	private String role;
	
	
	String uploadpath = "c:\\OOP\\uploadfile\\";
	String downloadpath = "c:\\OOP\\downloadfile\\";
	
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeSelfInfo(String password) throws SQLException{
		//写用户信息到存储
		if (DataProcessing.updateUser(name, password, role)){
			this.password=password;
			System.out.println("修改成功");
			return true;
		}else
			return false;
	}
	
	public abstract void showMenu() throws SQLException, IOException;
	
	public boolean downloadFile(String ID) throws SQLException, IOException{
		byte[] buffer = new byte[1024];
		
		Doc doc = DataProcessing.searchDoc(ID);
		
		if(doc==null)
			return false;
		else
		{
			File tempfile = new File(uploadpath+doc.getFilename());
			String filename = tempfile.getName();
			
			BufferedInputStream  infile = new BufferedInputStream(new FileInputStream(tempfile));
			BufferedOutputStream targetfile = new BufferedOutputStream(new FileOutputStream(downloadpath+filename));
			
			while(true)
			{
				int byteread=infile.read(buffer);
				if(byteread==-1)
					break;
				else
				{
					targetfile.write(buffer,0,byteread);
				}
			}
			infile.close();
			targetfile.close();
		}
		return true;
	}
	
	
	public void showFileList() throws SQLException{
		System.out.println("列表... ...");
		Enumeration<Doc> e = DataProcessing.getAllDocs();
		while(e.hasMoreElements())
		{
			Doc d = e.nextElement();
			System.out.println("ID:"+d.getID()+"\tCreator:"+d.getCreator()+"\tTime:"+d.getTimestamp()+"\tFileName:"+d.getFilename()+"\tDescription:"+d.getDescription());
		}
	}
	
	public void exitSystem(){
		System.out.println("系统退出, 谢谢使用 ! ");
		System.exit(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
