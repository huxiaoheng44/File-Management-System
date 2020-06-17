package package2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import package1.*;

public class LoginFrame extends JFrame {
	private JPanel contentPane;
	private JButton bt1;
	private JButton bt2;
	private JLabel user;
	private JLabel password;
	public static JTextField txu;
	public static JPasswordField txp;
	public static Client mclt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		mclt = new Client("127.0.0.1");
		mclt.runClient();
		
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		setTitle("系统登陆");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		user = new JLabel("用户名");
		user.setBounds(20, 50, 60, 50);
		user.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
		
		password = new JLabel("密码");
		password.setBounds(20, 120, 60, 50);
		password.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
		
		txu = new JTextField();
		txu.setBounds(150, 50, 250, 50);
		txu.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
		
		txp = new JPasswordField();
		txp.setBounds(150, 120, 250, 50);
		txp.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
		
		bt1=new JButton("登陆");         
		bt1.setBounds(90, 250, 100, 50);
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txu.getText();
				String psw = String.valueOf(txp.getPassword());
				User t=null;
				try {
					t = DataProcessing.searchUser(name,psw);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(t!=null)
				{
					String message = "CLIENT_LOGIN\n" + "\n" + name;
					Client.sendMessage(message);
					MainFrame mainframe = new MainFrame(t);
					mainframe.setVisible(true);
					dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "用户名或密码错误！");
				
			}
		});
		bt1.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
		
		bt2=new JButton("取消");
		bt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Client.sendMessage("TERMINATE");
				System.exit(0);
			}
		});
		bt2.setBounds(250, 250, 100, 50);
		bt2.setFont(new Font("微软雅黑 Light", Font.BOLD, 16));
		
		contentPane.add(bt1);
		contentPane.add(bt2);
		contentPane.add(user);
		contentPane.add(password);
		contentPane.add(txu);
		contentPane.add(txp);
	}
	
	

}
