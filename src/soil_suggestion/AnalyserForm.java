package soil_suggestion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.Action;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.cj.protocol.Resultset;

import login.loginForm;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AnalyserForm extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JButton submit;
	private JButton update;
	private JButton view;
	private JButton back;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalyserForm frame = new AnalyserForm();
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
	public AnalyserForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		submit = new JButton("SUBMIT");
		submit.addActionListener((ActionListener) this);
		
		update = new JButton("UPDATE");
		
		view = new JButton("VIEW");
		
		back = new JButton("back");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(view, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addGap(47)
					.addComponent(submit, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(update, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(back)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
					.addGap(63))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(back))
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(update, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(view, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(submit, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(111))
		);
		contentPane.setLayout(gl_contentPane);
		update.addActionListener((ActionListener) this);
		view.addActionListener(this);
		back.addActionListener(this);
		
	}
	@Override
   public void actionPerformed(ActionEvent e){
		DataAnalyst da = new DataAnalyst();
	        if(e.getSource()==submit) {
	        	String s=textField.getText();
	        	int n=Integer.parseInt(s);
	        	da.getResult(n);
	        }
	        if(e.getSource()==update) {
	        	String s=textField.getText();
	        	int n=Integer.parseInt(s);
	        	da.updateDatabase(n);
	        }
	        if(e.getSource()==view) {
	        	TestMachine tm = new TestMachine();
	        	tm.getSubmission();
	        }
	        if(e.getSource()==back) {
	        	loginForm login = new loginForm();
	        	login.setVisible(true);
	        	dispose();
	        }
   	}
}
