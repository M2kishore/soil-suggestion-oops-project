package login;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import javax.swing.JButton;

public class Admin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	public JButton btnNewButton;
	
	 public ArrayList<Farmer> userList(){
	        ArrayList<Farmer> usersList = new ArrayList<>();
	         try{
	            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/annam?useSSL=false", "root", "root");
	            String query1="SELECT * FROM farmer";
	            Statement st= (Statement) con.createStatement();
	            ResultSet rs= st.executeQuery(query1);
	            Farmer user;
	            while(rs.next()){
	                user=new Farmer(rs.getInt("ID"), rs.getString("gender"), rs.getString("name"),rs.getString("Password"), rs.getString("address"), rs.getString("email"));
	                usersList.add(user);
	            }
	         }
	         catch(Exception e){
	            JOptionPane.showMessageDialog(null, e);
	        }
	         return usersList;
	    }
	 
	   public void show_user(){
	        ArrayList<Farmer> list = userList();
	        DefaultTableModel model = (DefaultTableModel)table.getModel();
	        Object[] row = new Object[6];
	        for(int i=0;i<list.size();i++){
	            row[0]=list.get(i).getid();
	            row[3]=list.get(i).getgender();
	            row[1]=list.get(i).getname();
	            row[3]=list.get(i).getgender();
	            row[2]=list.get(i).getaddress();
	            row[4]=list.get(i).getemail();
	            model.addRow(row);
	        }
	    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	public Admin() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Gender", "Name", "PAssword", "Address", "Email"
			}
		));
		
		btnNewButton = new JButton("Display");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(41)
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(157)
							.addComponent(btnNewButton)))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addComponent(btnNewButton)
					.addGap(39))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton)
		{
			show_user();
	
	}
	
	}
}
