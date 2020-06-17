package package2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SelfFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;


	
	/**
	 * Create the frame.
	 */
	
	public SelfFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(750, 300, 463, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户名");
		lblNewLabel.setBounds(59, 87, 72, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("原口令");
		lblNewLabel_1.setBounds(59, 147, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("新口令");
		lblNewLabel_2.setBounds(59, 201, 72, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("确认新口令");
		lblNewLabel_3.setBounds(36, 256, 113, 18);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("角色");
		lblNewLabel_4.setBounds(69, 316, 72, 18);
		contentPane.add(lblNewLabel_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 253, 126, 24);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(174, 144, 126, 24);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(174, 198, 126, 24);
		contentPane.add(passwordField_2);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fpassword = String.valueOf(passwordField_1.getPassword());
				if(!MainFrame.user.getPassword().equals(fpassword))
				{
					JOptionPane.showMessageDialog(null, "原密码错误");
				}
				else
				{
					String p1=String.valueOf(passwordField_2.getPassword());
					String p2=String.valueOf(passwordField.getPassword());
					if(!p1.equals(p2))
					{
						JOptionPane.showMessageDialog(null, "两次密码输入不一致","错误信息",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						try {
							LoginFrame.mclt.sendMessage("SELF_REVISE");
							MainFrame.user.changeSelfInfo(p1);
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "修改成功！");
					}
				}
			}
		});
		btnNewButton.setBounds(79, 381, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(205, 381, 113, 27);
		contentPane.add(button);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(174, 84, 126, 24);
		textField_4.setText(MainFrame.user.getName());
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText(MainFrame.user.getRole());
		textField_1.setBounds(174, 316, 128, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}

