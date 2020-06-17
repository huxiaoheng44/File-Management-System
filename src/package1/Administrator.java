package package1;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

public class Administrator extends User {
	Scanner in = new Scanner(System.in);
	Administrator(String name, String password, String role) {
		super(name, password, role);
	}

	@Override
	public void showMenu() throws SQLException, IOException {
		int i;
		do
		{
			System.out.println("****欢迎进入档案管理员菜单****");
			System.out.println("\t 1.修改用户");
			System.out.println("\t 2.删除用户");
			System.out.println("\t 3.新增用户");
			System.out.println("\t 4.列出用户");
			System.out.println("\t 5.下载文件");
			System.out.println("\t 6.文件列表");
			System.out.println("\t 7.修改（本人）密码");
			System.out.println("\t 8.退      出");
			System.out.println("****************************");
			System.out.println("请选择选项：");
			i=in.nextInt();
			switch(i){
			case 1:{
				changeUserInfo();
				break;
			}
			case 2:{
				delUser();
				break;
			}
			case 3:{
				addUser();
				break;
			}
			case 4:{
				listUser();
				break;
			}
			case 5:{
				System.out.println("请输入档案号:");
				String ID;
				ID=in.next();
				downloadFile(ID);
				break;
			}
			case 6:{
				showFileList();
				break;
			}
			case 7:{
				updatePassword();
				break;
			}
			case 8:{
				exitSystem();
				break;
			}
			default:
			System.out.println("没有这个选项！");
			}
		}while(i!=8);
		
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
	public void changeUserInfo() throws SQLException {
		User t;
		int i;
		String name,password,role=null;
		System.out.println("请输入要修改的用户名称：");
		name = in.nextLine();
		t=DataProcessing.searchUser(name);
		if(t!=null) {
			System.out.println("请输入你要修改后的用户信息");
			System.out.println("密码：");
			password=in.nextLine();
			System.out.println("用户属性(1:Administrator2:Operator3:Browser)：");
			i=in.nextInt();
			if(i==1) 
				role = "administrator";
			else if(i==2)
				role = "operator";
			else if(i==3)
				role = "browser";
			else
				System.out.println("错误的属性！");
			if(DataProcessing.updateUser(name, password, role))
				System.out.println("修改成功！");
			else
				System.out.println("修改失败！");
		}
		else
			System.out.println("该用户名不存在！");
	}
	/*public boolean downloadFile(){
		String filename;
		System.out.println("请输入文件名：");
		filename=in.nextLine();
		System.out.println("下载文件... ...");
		System.out.println("下载成功！");
		
		return true;
	}*/
	
	public void delUser() throws SQLException {
		String name;
		System.out.println("请输入要删除的用户名称：");
		name = in.nextLine();
		if(DataProcessing.deleteUser(name)){
			System.out.println("删除成功!");
		}
		else {
			System.out.println("删除失败!");
		}
	}
	
	public void listUser() throws SQLException {
		User t;
		Enumeration<User> e;
		e=DataProcessing.getAllUser();
		System.out.println("*******用户信息********");
		while(e.hasMoreElements()) {
			t=e.nextElement();
			System.out.println("用户名："+t.getName()+"\t密码："+t.getPassword()+"\t属性："+t.getRole());
		}
		
	}
	
	public void addUser() throws SQLException {
		int i;
		String name,password,role=null;
		System.out.println("请输入要添加的用户名称：");
		name = in.nextLine();
		System.out.println("请输入要添加的用户密码：");
		password = in.nextLine();
		System.out.println("请输入要添加的用户属性(1:Administrator2:Operator3:Browser)：");
		i=in.nextInt();
		if(i==1) 
			role = "administrator";
		else if(i==2)
			role = "operator";
		else if(i==3)
			role = "browser";
		else
			System.out.println("错误的属性！");
		if(DataProcessing.insertUser(name, password, role))
			System.out.println("添加成功！");
		else
			System.out.println("添加失败！");
	}
	


}
