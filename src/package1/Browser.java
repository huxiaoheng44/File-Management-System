package package1;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class Browser extends User {
	Scanner in =new Scanner(System.in);

	Browser(String name, String password, String role) {
		super(name, password, role);
	}

	public void showMenu() throws SQLException, IOException {
		int i;
		do
		{
			System.out.println("****欢迎进入档案操作员菜单****");
			System.out.println("\t 1.下载文件");
			System.out.println("\t 2.文件列表");
			System.out.println("\t 3.修改密码");
			System.out.println("\t 4.退      出");
			System.out.println("****************************");
			System.out.println("请选择选项：");
			i=in.nextInt();
			switch(i){
			case 1:{
				System.out.println("请输入档案号:");
				String ID;
				ID=in.next();
				downloadFile(ID);
				break;
			}
			case 2:{
				showFileList();
				break;
			}
			case 3:{
				updatePassword();
				break;
			}
			case 4:{
				exitSystem();
				break;
			}
			default:
			System.out.println("没有这个选项！");
			}
		}while(i!=4);
	}
	
	/*public boolean downloadFile(){
		String filename;
		System.out.println("请输入文件名：");
		filename=in.nextLine();
		System.out.println("下载文件... ...");
		System.out.println("下载成功！");
		
		return true;
	}*/
	
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