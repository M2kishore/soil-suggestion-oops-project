package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import soil_suggestion.AnalyserForm;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.SwingConstants;

public class loginForm extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField UserText;
	private JPasswordField passwordField;
	private JButton Login;
	private JButton reset;
	private JLabel errorLable;
	private JButton register;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginForm frame = new loginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel FName = new JLabel("");
		FName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel = new JLabel("User ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		UserText = new JTextField();
		UserText.setColumns(10);
		
		passwordField = new JPasswordField();
		
		Login = new JButton("LOGIN");
		Login.addActionListener((ActionListener) this);
		
		Login.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		reset = new JButton("Reset");
		reset.setFont(new Font("Tahoma", Font.PLAIN, 10));
		reset.addActionListener(this);
		
		errorLable = new JLabel("");
		errorLable.setForeground(Color.RED);
		
		register = new JButton("REGISTER");
		register.addActionListener(this);
		register.setFont(new Font("Tahoma", Font.PLAIN, 10));
		register.setHorizontalAlignment(SwingConstants.RIGHT);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(375)
					.addComponent(reset)
					.addGap(179))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(248, Short.MAX_VALUE)
							.addComponent(Login, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addComponent(register, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(72)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(UserText, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
								.addComponent(errorLable, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(134)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(UserText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(33)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(errorLable)
					.addGap(18)
					.addComponent(reset)
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(register, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(Login, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
					.addContainerGap(68, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public void actionPerformed(ActionEvent e){
        if(e.getSource()==Login) {
        	String u=UserText.getText();
        	int u1=Integer.parseInt(u);
        	String p=passwordField.getText();
        	if(loginCheck(u1,p)) {
        		System.out.println("Welcome");
        		AnalyserForm o = new AnalyserForm();
        		o.setVisible(true);
        		dispose();        
        		
        		
        }
        	else {
        		System.out.println("Error");
        		errorLable.setText("userID or Password is wrong");
        	}
	}
        else if(e.getSource()==reset) {
        	UserText.setText("");
        	passwordField.setText("");
        }
        else if(e.getSource()==register) {
        	RegistrationForm rf = new RegistrationForm();
        	rf.setVisible(true);
        	dispose();
        }
	}
	   boolean loginCheck(int id,String password){
	        try {
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/annam?useSSL=false","root","root");
	         
	            PreparedStatement ps = con.prepareStatement("Select * from test where username=? and password = ?");
	            
	            ps.setInt(1, id);
	            ps.setString(2, password);
	            ResultSet rs = ps.executeQuery();
	            
	            if (rs.next()){
	                return true ;
	            }
	            else 
	                return false;
	            
	        } catch (SQLException ex) {
	            ex.getStackTrace();
	    }
	        return false;
	    }
}
