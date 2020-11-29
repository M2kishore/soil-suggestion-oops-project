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

import javax.swing.Action;

public class AnalyserForm extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JButton submit;
	private JButton update;

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
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textField = new JTextField();
		contentPane.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		submit = new JButton("SUBMIT");
		contentPane.add(submit, BorderLayout.CENTER);
		
		update = new JButton("UPDATE");
		contentPane.add(update, BorderLayout.EAST);
		submit.addActionListener((ActionListener) this);
		
	}
	@Override
   public void actionPerformed(ActionEvent e){
	        if(e.getSource()==submit) {
	        	String s=textField.getText();
	        	int n=Integer.parseInt(s);
	        	DataAnalyst da = new DataAnalyst();
	        	da.getResult(n);
	        }
	        if(e.getSource()==update) {
	        	String s=textField.getText();
	        	int n=Integer.parseInt(s);
	        	DataAnalyst da = new DataAnalyst();
	        	da.updateDatabase(n);
	        }
   	}
}
