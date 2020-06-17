package package1;
import java.io.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;
public class Operator extends User
{

	public Operator(String name, String password, String role) {
		super(name, password, role);
	}

	Scanner in = new Scanner(System.in);
	public void showMenu() throws SQLException, IOException {
		int i;
		do
		{
			System.out.println("****欢迎进入档案录入员菜单****");
			System.out.println("\t 1.上传文件");
			System.out.println("\t 2.下载文件");
			System.out.println("\t 3.文件列表");
			System.out.println("\t 4.修改密码");
			System.out.println("\t 5.退      出");
			System.out.println("****************************");
			System.out.println("请选择选项：");
			i=in.nextInt();
			switch(i){
			case 1:{
				String uploadpath,ID,description;
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				in.nextLine();
				System.out.println("请输入源文件名：");
				uploadpath=in.nextLine();
				System.out.println("请输入档案号：");
				ID = in.nextLine();
				System.out.println("请输入档案描述：");
				description=in.nextLine();
				if(uploadFile(timestamp,uploadpath,ID,description))
					System.out.println("上传成功！");
				else
					System.out.println("上传失败！");
				break;
			}
			case 2:{
				System.out.println("请输入档案号:");
				String ID;
				ID=in.next();
				if(downloadFile(ID))
					System.out.println("下载成功！");
				else
					System.out.println("下载失败！");
				break;
			}
			case 3:{
				showFileList();
				break;
			}
			case 4:{
				updatePassword();
				break;
			}
			case 5:{
				exitSystem();
				break;
			}
			default:
			System.out.println("没有这个选项！");
			}
		}while(i!=5);
	}
		
	
	/*public boolean downloadFile(){
		String filename;
		System.out.println("请输入文件名：");
		filename=in.nextLine();
		System.out.println("下载文件... ...");
		System.out.println("下载成功！");
		
		return true;
	}*/
	

	
	public boolean uploadFile(Timestamp timestamp,String uploadpath,String ID,String description) throws SQLException{
		File tempfile = new File(uploadpath);
		
		byte[] buffer = new byte[1024];
		try {
			BufferedInputStream infile =new BufferedInputStream(new FileInputStream(tempfile));
			BufferedOutputStream outfile = new BufferedOutputStream(new FileOutputStream("c:\\OOP\\uploadfile\\"+tempfile.getName()));
			
			while(infile.read(buffer)!=-1)
			{
				outfile.write(buffer);
			}
			infile.close();
			outfile.close();
		}catch(IOException e) {
			System.out.println("输入路径错误！"+e.getMessage());
			return false;
		}
		DataProcessing.insertDoc(ID, getName(), timestamp, description, tempfile.getName());
		
		return true;
	}
	
	public void updatePassword() throws SQLException
	{
		String newpassword;
		System.out.println("****修改密码****");
		System.out.println("请输入新密码：");
		newpassword=in.next();
		if(DataProcessing.updateUser(getName(),newpassword,"browser"))
			System.out.println("修改成功");
		else
			System.out.println("修改失败");
	}

}
