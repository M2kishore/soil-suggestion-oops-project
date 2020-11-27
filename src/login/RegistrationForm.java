package login;

import javax.swing.*; 

import java.awt.event.*; 

import java.awt.*; 

import java.sql.*; 

 

public class RegistrationForm implements ActionListener { 

     

    private String dates[]  

            = { "1", "2", "3", "4", "5",  

                "6", "7", "8", "9", "10",  

                "11", "12", "13", "14", "15",  

                "16", "17", "18", "19", "20",  

                "21", "22", "23", "24", "25",  

                "26", "27", "28", "29", "30",  

                "31" };  

        private String months[]  

            = { "Jan", "feb", "Mar", "Apr",  

                "May", "Jun", "July", "Aug",  

                "Sup", "Oct", "Nov", "Dec" };  

        private String years[]  

            = { "1995", "1996", "1997", "1998",  

                "1999", "2000", "2001", "2002",  

                "2003", "2004", "2005", "2006",  

                "2007", "2008", "2009", "2010",  

                "2011", "2012", "2013", "2014",  

                "2015", "2016", "2017", "2018",  

                "2019" };  

 

 

JFrame frame; 

    String[] gender={"Male","Female"}; 

    JLabel nameLabel=new JLabel("FARMER NAME"); 

    JLabel genderLabel=new JLabel("GENDER"); 

    JLabel IDLabel=new JLabel("FARMER ID"); 

    JLabel passwordLabel=new JLabel("PASSWORD"); 

    JLabel confirmPasswordLabel=new JLabel("CONFIRM PASSWORD"); 

    JLabel addressLabel=new JLabel("ADDRESS"); 

    JLabel emailLabel=new JLabel("EMAIL"); 

    JLabel AdhaarLabel= new JLabel("AADHAAR"); 

    JTextField nameTextField=new JTextField(); 

    JTextField Farmerid=new JTextField(); 

    JTextField Aadhaartext= new JTextField(); 

    JComboBox<String> genderComboBox=new JComboBox<>(gender); 

    JTextField fatherTextField=new JTextField(); 

    JPasswordField passwordField=new JPasswordField(); 

    JPasswordField confirmPasswordField=new JPasswordField(); 

    JTextField AddressTextField=new JTextField(); 

    JTextField emailTextField=new JTextField(); 

    JLabel dob = new JLabel("DOB");  

    JComboBox<String> date= new JComboBox<>(dates);  

    JComboBox<String> month= new JComboBox<>(months);  

    JComboBox<String> year= new JComboBox<>(years);  

    JButton registerButton=new JButton("REGISTER"); 

    JButton resetButton=new JButton("RESET"); 

     

     

 

 

 

    public RegistrationForm() 

    { 

        createWindow(); 

        setLocationAndSize(); 

        addComponentsToFrame(); 

        actionEvent(); 

    } 

    public void createWindow() 

    { 

        frame=new JFrame(); 

        frame.setTitle("Registration"); 

        frame.setBounds(100,100,600,600); 

        frame.getContentPane().setBackground(Color.blue); 

        frame.getContentPane().setLayout(null); 

        frame.setVisible(true); 

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        frame.setResizable(false); 

    } 

    public void setLocationAndSize() 

    { 

        nameLabel.setBounds(20,20,140,70); 

        genderLabel.setBounds(20,70,80,70); 

        passwordLabel.setBounds(20,170,100,70); 

        confirmPasswordLabel.setBounds(20,220,140,70); 

        addressLabel.setBounds(20,270,100,70); 

        emailLabel.setBounds(20,320,100,70); 

        nameTextField.setBounds(180,43,165,23); 

        genderComboBox.setBounds(180,93,165,23); 

        dob.setBounds(20,120,40,70); 

        date.setBounds(180, 143, 60, 23); 

        month.setBounds(250, 143, 60, 23); 

        year.setBounds(320, 143, 60, 23); 

        Aadhaartext.setBounds(180,143,165,23); 

        passwordField.setBounds(180,193,165,23); 

        confirmPasswordField.setBounds(180,243,165,23); 

        AddressTextField.setBounds(180,293,165,23); 

        emailTextField.setBounds(180,343,165,23); 

        registerButton.setBounds(70,400,100,35); 

        resetButton.setBounds(220,400,100,35); 

    } 

    public void addComponentsToFrame() 

    { 

        frame.add(nameLabel); 

        frame.add(genderLabel); 

        frame.add(AdhaarLabel); 

        frame.add(dob); 

        frame.add(date); 

        frame.add(month); 

        frame.add(year); 

        frame.add(passwordLabel); 

        frame.add(confirmPasswordLabel); 

        frame.add(addressLabel); 

        frame.add(emailLabel); 

        frame.add(nameTextField); 

        frame.add(genderComboBox); 

        frame.add(passwordField); 

        frame.add(confirmPasswordField); 

        frame.add(AddressTextField); 

        frame.add(emailTextField); 

        frame.add(registerButton); 

        frame.add(resetButton); 

    } 

    public void actionEvent() 

    { 

        registerButton.addActionListener(this); 

        resetButton.addActionListener(this); 

    } 

 

 

    @Override 

    public void actionPerformed(ActionEvent e) { 

        if(e.getSource()==registerButton) 

        { 

            try { 

                //Creating Connection Object 

                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/annam?useSSL=false","root","root"); 

                //Prepared Statement 

                PreparedStatement Pstatement=connection.prepareStatement("insert into FarmerDetails values(?,?,?,?,?,?,?)"); 

                //Specifying the values of it's parameter 

                Pstatement.setString(1,nameTextField.getText()); 

                Pstatement.setString(2,genderComboBox.getSelectedItem().toString()); 

                Pstatement.setString(3,fatherTextField.getText()); 

                Pstatement.setString(4,passwordField.getText()); 

                Pstatement.setString(5,confirmPasswordField.getText()); 

                Pstatement.setString(6,AddressTextField.getText()); 

                Pstatement.setString(7,emailTextField.getText()); 

                //Checking for the Password match 

                if(passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText())) 

                { 

                    //Executing query 

                    Pstatement.executeUpdate(); 

                    JOptionPane.showMessageDialog(null,"Data Registered Successfully"); 

                } 

                else 

                { 

                    JOptionPane.showMessageDialog(null,"password did not match"); 

                } 

 

            } catch (SQLException e1) { 

                e1.printStackTrace(); 

            } 

 

 

        } 

        if(e.getSource()==resetButton) 

        { 

            //Clearing Fields 

            nameTextField.setText(""); 

            genderComboBox.setSelectedItem("Male"); 

            fatherTextField.setText(""); 

            passwordField.setText(""); 

            confirmPasswordField.setText(""); 

            AddressTextField.setText(""); 

            emailTextField.setText(""); 

        } 

 

    } 

} 