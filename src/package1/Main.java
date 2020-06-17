package package1;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws  IOException, SQLException {
		String tip_system = "档案系统";
		String tip_exit="系统退出，谢谢使用！";
		String infos="****欢迎进入"+tip_system+"****\n\t    "+
		             "1.登陆\n\t    2.退出\n"+
				     "********************";
		int x;
		Scanner in = new Scanner(System.in);
		do
		{
			System.out.println(infos);
			System.out.println("请选择菜单：");
			x=in.nextInt();
			if(x==1)
			{
				String name,password;
				User t;
				System.out.println("请输入用户名：");
				name=in.next();
				System.out.println("请输入密码：");
				password=in.next();
				t=DataProcessing.searchUser(name,password);
				if(t!=null)
				{
					t.showMenu();
				}
				else
					System.out.println("用户名或密码错误！");
			}		
		}while(x!=2);	
	}

}
