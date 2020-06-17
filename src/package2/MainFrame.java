package package2;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import package1.*;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	public static User user=null;

	
	public MainFrame(User u) {
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				LoginFrame.mclt.sendMessage("TERMINATE");
			}
		});
		user = u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 997, 887);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("用户管理");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("新增用户");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame frame = new UserFrame(0);
				LoginFrame.mclt.sendMessage("NEW_USERS");
				frame.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("修改用户");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame frame = new UserFrame(1);
				LoginFrame.mclt.sendMessage("REVISE_USERS");
				frame.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("删除用户");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame frame = new UserFrame(2);
				LoginFrame.mclt.sendMessage("DELETE_USERS");
				frame.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("档案管理");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("档案上传");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFrame frame = new FileFrame(1,user.getRole());
				LoginFrame.mclt.sendMessage("DOC_UPLOAD");
				frame.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("档案下载");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFrame frame = new FileFrame(0,user.getRole());
				LoginFrame.mclt.sendMessage("DOC_DOWNLOAD");
				frame.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_2 = new JMenu("个人信息管理");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem menuItem = new JMenuItem("信息修改");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelfFrame frame = new SelfFrame();
				LoginFrame.mclt.sendMessage("SELF_MOD");
				frame.setVisible(true);
			}
		});
		mnNewMenu_2.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JLabel  bg=new   JLabel(new ImageIcon("C:\\OOP\\timg.jpg\\"));
		contentPane.add(bg);
		if(user.getRole().equals("administrator"))
		{
			setTitle("档案管理员界面");
			mntmNewMenuItem_3.setEnabled(false);
		}
		else if(user.getRole().equals("browser"))
		{
			setTitle("档案浏览员界面");
			mntmNewMenuItem_3.setEnabled(false);
			mnNewMenu.setEnabled(false);
		}
		else
		{
			setTitle("档案录入员界面");
			mnNewMenu.setEnabled(false);
		}
	}

}
