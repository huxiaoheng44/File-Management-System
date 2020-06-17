package package2;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import package1.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
public class FileFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JFrame fileframe;
	private JTable table;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileFrame frame = new FileFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	//读入文件信息
	public void WriteFile()
	{
		int row = 0;
		Enumeration<Doc> filelist = null;
		try {
			filelist = DataProcessing.getAllDocs();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(filelist.hasMoreElements())
		{
			Doc d = filelist.nextElement();
			table.setValueAt(d.getID(), row, 0);
			table.setValueAt(d.getCreator(), row, 1);
			table.setValueAt(d.getTimestamp(), row, 2);
			table.setValueAt(d.getFilename(), row, 3);
			table.setValueAt(d.getDescription(), row, 4);
			row++;
		}
	}
	/**
	 * Create the frame.
	 */
	public FileFrame(int index,String role) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 485, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 467, 331);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("文件下载", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 54, 407, 146);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null,null,null,null,null},
					{null,null,null,null,null},
					{null,null,null,null,null},
					{null,null,null,null,null},
					{null,null,null,null,null},
					{null,null,null,null,null},
					{null,null,null,null,null},
					{null,null,null,null,null},
					{null,null,null,null,null},
					{null,null,null,null,null},
					{null,null,null,null,null},
				},
				new String[] {
						"档案号","创建者","时间","文件名","描述"
				}
				));
		WriteFile();
		scrollPane.setViewportView(table);
		
		//执行下载文件操作
		JButton button_2 = new JButton("下载");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//直接调用user 的下载文件函数
				int index;
				String ID;
				index = table.getSelectedRow();
				ID = table.getValueAt(index, 0).toString();
				LoginFrame.mclt.sendMessage("download ID:"+ID);
				try {
					if(MainFrame.user.downloadFile(ID))
					{
						System.out.println("文件下载中.......1%");
						System.out.println("文件下载中.......14%");
						System.out.println("文件下载中.......25%");
						System.out.println("文件下载中.......47%");
						System.out.println("文件下载中.......79%");
						System.out.println("文件下载中.......99%");
						System.out.println("文件下载中.......100%");
						System.out.println("文件下载成功！");
						JOptionPane.showMessageDialog(null, "下载成功");
					}
					else
						JOptionPane.showMessageDialog(null, "下载失败");
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				//更新文件信息
				WriteFile();
			}
		});
		button_2.setBounds(90, 239, 113, 27);
		panel.add(button_2);
		
		JButton button_3 = new JButton("返回");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_3.setBounds(247, 239, 113, 27);
		panel.add(button_3);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("文件上传", null, panel_1, null);
		tabbedPane.setEnabledAt(1, true);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("档案号");
		label.setBounds(53, 66, 72, 18);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("档案描述");
		label_1.setBounds(53, 108, 72, 18);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("档案文件名");
		label_2.setBounds(53, 191, 105, 18);
		panel_1.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(181, 63, 86, 24);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(181, 188, 110, 24);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(181, 106, 130, 59);
		panel_1.add(textArea);
		
		JButton button = new JButton("打开");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog d = new FileDialog(fileframe, "打开文件", FileDialog.LOAD);
				d.setVisible(true);
				textField_2.setText(d.getDirectory()+d.getFile()+"\\");
			}
		});
		button.setBounds(317, 187, 63, 27);
		panel_1.add(button);
		
		JButton button_1 = new JButton("上传");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uploadpath,ID,description;
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				uploadpath = textField_2.getText();
				ID = textField.getText();
				description = textArea.getText();
				Operator temp=(Operator)MainFrame.user;
				
				//此处不能之间运行这里的main  因为user传过来是null无法执行！
				LoginFrame.mclt.sendMessage("upload ID:"+ID);
				try {
					if(temp.uploadFile(timestamp, uploadpath, ID, description))
						JOptionPane.showMessageDialog(null, "上传成功");
					else
						JOptionPane.showMessageDialog(null, "上传失败");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				//更新文件信息
				WriteFile();
			}
		});
		button_1.setBounds(98, 241, 113, 27);
		panel_1.add(button_1);
		
		JButton btnNewButton = new JButton("取消");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(236, 241, 113, 27);
		panel_1.add(btnNewButton);
		if(!role.equals("operator"))
		{
			tabbedPane.setEnabledAt(1, false);
		}
		tabbedPane.setSelectedIndex(index);
	}
}
