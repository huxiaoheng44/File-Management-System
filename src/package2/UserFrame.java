package package2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import package1.Client;
import package1.DataProcessing;
import package1.User;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import java.awt.Color;

public class UserFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JTable table;
	private JComboBox comboBox_1 = new JComboBox();
	private Client mclt;

	
	
	//写入用户函数
	public  void WriteUser()
	{
		DefaultTableModel model =(DefaultTableModel) table.getModel();
		/*
		 * 清空表格
		 */
		while(model.getRowCount()>0){
		model.removeRow(model.getRowCount()-1);
		table.invalidate();
		}
		Enumeration<User> e = null;
		try {
			e = DataProcessing.getAllUser();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int row = 0;
		while(e.hasMoreElements()) {
			model.addRow(new Object[][] {null,null,null});
			User user = e.nextElement();
			table.setValueAt(user.getName(), row, 0);
			table.setValueAt(user.getPassword(), row, 1);
			table.setValueAt(user.getRole(), row, 2);
			row++;
		}
	}
	
	private void UNameList()
	{
		comboBox_1.removeAllItems();
		try {
			Enumeration<User>ulist = DataProcessing.getAllUser();
			while(ulist.hasMoreElements())
			{
				comboBox_1.addItem(ulist.nextElement().getName());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public UserFrame(int indexofpane) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 300, 529, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 511, 409);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("新增用户", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("用户名");
		label.setBounds(77, 86, 72, 18);
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("口令");
		lblNewLabel.setBounds(77, 158, 72, 18);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("角色");
		lblNewLabel_1.setBounds(77, 233, 72, 18);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(227, 83, 125, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(227, 155, 125, 24);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBounds(227, 230, 125, 24);
		comboBox.addItem("browser");
		comboBox.addItem("operator");
		comboBox.addItem("administrator");
		panel.add(comboBox);
		
		//确定执行添加用户信息
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index;
				String u = textField.getText();
				String p = textField_1.getText();
				index = comboBox.getSelectedIndex();
				String r = comboBox.getItemAt(index);
				System.out.println(u+" "+p+" "+r);
				if(u.equals("")||p.equals(""))
					JOptionPane.showMessageDialog(null, "输入信息不完整！","错误信息",JOptionPane.ERROR_MESSAGE);
				else
				{
					int n = JOptionPane.showConfirmDialog(null, "你确定要添加用户嘛?", "再一次确认",JOptionPane.YES_NO_OPTION);
					if(n==0)
					{
						try {
							if(DataProcessing.insertUser(u, p, r))
								JOptionPane.showMessageDialog(null, "添加成功");
							else
								JOptionPane.showMessageDialog(null, "添加失败");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					//更新用户信息
					WriteUser();
					UNameList();
				}
			}
		});
		btnNewButton.setBounds(122, 310, 113, 27);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(274, 310, 113, 27);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("修改用户", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("用户名");
		lblNewLabel_2.setBounds(83, 95, 72, 18);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("口令");
		lblNewLabel_3.setBounds(83, 161, 72, 18);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("角色");
		lblNewLabel_4.setBounds(83, 237, 72, 18);
		panel_1.add(lblNewLabel_4);
		
		//用户列表框
		comboBox_1.setBounds(263, 92, 103, 24);
		try {
			Enumeration<User>ulist = DataProcessing.getAllUser();
			while(ulist.hasMoreElements())
			{
				comboBox_1.addItem(ulist.nextElement().getName());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panel_1.add(comboBox_1);
		
		//修改用户属性
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(263, 234, 103, 24);
		comboBox_2.addItem("browser");
		comboBox_2.addItem("operator");
		comboBox_2.addItem("administrator");
		panel_1.add(comboBox_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(263, 158, 103, 24);
		panel_1.add(passwordField);
		
		//修改用户信息确定执行
		JButton button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String p = String.valueOf(passwordField.getPassword());
				int i1,i2;
				i1=comboBox_1.getSelectedIndex();
				String u = (String) comboBox_1.getItemAt(i1);
				i2=comboBox_2.getSelectedIndex();
				String r = (String) comboBox_2.getItemAt(i2);
				if(p.equals(""))
					JOptionPane.showMessageDialog(null, "口令不能为空！");
				else
				{
					try {
						if(DataProcessing.updateUser(u, p, r))
							JOptionPane.showMessageDialog(null, "修改成功");
						else
							JOptionPane.showMessageDialog(null, "修改失败");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					passwordField.setText("");
					//更新用户信息
					WriteUser();
					UNameList();
				}
				
			}
		});
		button.setBounds(104, 312, 113, 27);
		panel_1.add(button);
		
		JButton button_1 = new JButton("取消");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(263, 312, 113, 27);
		panel_1.add(button_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("删除用户", null, panel_2, null);
		panel_2.setLayout(null);
		
		
		
		
		
		//删除用户板块
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 48, 492, 172);
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"用户名", "口令", "角色"
			}
		));
		scrollPane.setViewportView(table);
		
		//删除用户执行操作
		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				String name = table.getValueAt(index, 0).toString();
				try {
					if(DataProcessing.deleteUser(name))
					{
						JOptionPane.showMessageDialog(null, "删除成功");
					}
					else
						JOptionPane.showMessageDialog(null, "删除失败", "错误信息",JOptionPane.ERROR_MESSAGE );
						
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				//更新用户信息
				WriteUser();
				UNameList();
			}
		});
		button_2.setBounds(69, 282, 113, 27);
		panel_2.add(button_2);
		
		JButton button_3 = new JButton("取消");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_3.setBounds(277, 282, 113, 27);
		panel_2.add(button_3);
		tabbedPane.setSelectedIndex(indexofpane);
		
		//将用户信息写到表格中
		WriteUser();
		/*
		Enumeration<User> e = null;
		try {
			e = DataProcessing.getAllUser();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int row = 0;
		while(e.hasMoreElements()) {
			User user = e.nextElement();
			table.setValueAt(user.getName(), row, 0);
			table.setValueAt(user.getPassword(), row, 1);
			table.setValueAt(user.getRole(), row, 2);
			row++;
		}
		*/
		
		
		
	}
}
