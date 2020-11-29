package soil_suggestion;

import java.io.File;
import java.io.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

abstract class Test {
	int c;
	Timestamp time;
	File tempDirectory, machineStat;
	FileWriter logger;
	Connection connection;
	ArrayList<Double> n = new ArrayList<Double>();
	ArrayList<Double> p = new ArrayList<Double>();
	ArrayList<Double> k = new ArrayList<Double>();
	
	Test(){
		time = new Timestamp(System.currentTimeMillis());
		tempDirectory = new File(System.getProperty("java.io.tmpdir"));
		machineStat = new File(tempDirectory.getAbsolutePath()+File.separator+"MachineStat.log");
		System.out.println(tempDirectory.getAbsolutePath());
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/annam?useSSL=false", "root", "root");
			if(machineStat.createNewFile()) {
				System.out.println("log created");
			}else {
				System.out.println("logging...");
			}
			logger = new FileWriter(tempDirectory.getAbsolutePath()+File.separator+"MachineStat.log");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	void printResultset(ResultSet resultset) {
		ResultSetMetaData metadata;
		try {
			metadata = resultset.getMetaData();
			int columnCount = metadata.getColumnCount();    
		    for (int i = 1; i <= columnCount; i++) {
		        System.out.print(metadata.getColumnName(i) + ", ");      
		    }
		    System.out.println();
		    while (resultset.next()) {
		        String row = "";
		        for (int i = 1; i <= columnCount; i++) {
		            row += resultset.getString(i) + ", ";          
		        }
		        System.out.println();
		        System.out.print(row);
		} 
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class Report extends Test{
	int submissionId;
	int farmerId;
	String resultDate;
	String requestType;
	Statement statement;
	ResultSet resultset = null;
	void getReport() {
		//getting report
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery("select * from result where farmerID="+farmerId);
			while(resultset.next()) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

class DataAnalyst extends Test{
	int id;
	String name;
	Statement statement = null;
	ResultSet resultset = null;
	double n1;
	double p1;
	double k1;
	TestMachine tm;
	void getResult(int sid) {
		getnpk(sid);
		tm = new TestMachine();
		tm.generateResult(n1,p1,k1);
		updateDatabase(sid);
		
		
	}
	void getnpk(int sid) {
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery("select n,p,k from submission where sid="+sid);
			resultset.next();
			n1=resultset.getDouble("n");
			p1=resultset.getDouble("p");
			k1=resultset.getDouble("k");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void sendMessage() {
		//messaging service to send the result to the farmer
	}
	private void updateDatabase(int submission_id) {
		try {
			statement= connection.createStatement();
			if(statement.execute("UPDATE submission SET status='COMPLETE' WHERE sid="+submission_id)) {
				System.out.println("Successful");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
public class TestMachine extends Test{
	Statement statement;
	ResultSet resultset = null;
	protected int machineId = 1;
	TestMachine(){
		System.out.println("The Test Machine is turned on");
		try {
			//System.out.println(machineStat.canWrite());
			logger.append("Machine Started : "+ time+"\n");
			System.out.println("logged in Machine status");
			statement = connection.createStatement();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void getSubmission() {
		try {
			resultset = statement.executeQuery("select * from submission");
			printResultset(resultset);
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			System.out.println("SQL execution complete");
		}
	}
	void getAll() {
		try {
			resultset = statement.executeQuery("select * from crop");
			while(resultset.next()) {
				int id = resultset.getInt("id");
				String name = resultset.getString("name");
				String soil = resultset.getString("soil");
				int ph =resultset.getInt("ph");
				int ec = resultset.getInt("ec");
				double n = resultset.getDouble("n_content");
				double p = resultset.getDouble("p_content");
				double k = resultset.getDouble("k_content");
				System.out.println(id+" "+name+" "+soil+" "+ph+" "+ec+" "+n+" "+p+" "+k);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			System.out.println("SQL execution complete");
		}
		
	}
	void printList(ArrayList<Double> n) {
		for(int i=0;i<n.size();i++){
		    System.out.println(n.get(i));
		} 
	}
	void getReferences() {
		
		try{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultset=statement.executeQuery("SELECT n_content,p_content,k_content FROM crop");
			resultset.first();
			do {
				double nc=resultset.getDouble("n_content");
				double pc=resultset.getDouble("p_content");
				double kc=resultset.getDouble("k_content");
				n.add(nc);
				p.add(pc);
				k.add(kc);
			}
				while(resultset.next());
		}
		catch (SQLException e){
			e.getStackTrace();
		}
	}
	
	void generateResult(double n1, double p1, double k1) {
		getReferences();
		
		double distance = 1000;
		int idx = 1;
		for(int c = 0; c < n.size(); c++){
		    double cdistance = Math.abs(n.get(c)-n1);
		    if(cdistance < distance){
		        idx = c+1;
		        distance = cdistance;
		    }
		}
		System.out.println("Nitrogen:");
		printCrop(idx);
		idx=1;
		for(int c = 0; c < p.size(); c++){
		    double cdistance = Math.abs(p.get(c)-p1);
		    if(cdistance < distance){
		        idx = c+1;
		        distance = cdistance;
		    }
		}
		System.out.println("Phosperous:");
		printCrop(idx);
		idx=1;
		for(int c = 0; c < k.size(); c++){
		    double cdistance = Math.abs(k.get(c)-k1);
		    if(cdistance < distance){
		        idx = c+1;
		        distance = cdistance;
		    }
		}
		System.out.println("Potassium:");
		printCrop(idx);
	}
	
	void printCrop(int id) {
		try {
			ResultSet rs=statement.executeQuery("SELECT name FROM crop WHERE id="+id);
			while(rs.next()) {
				System.out.println(" "+rs.getString("name")+" ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void checkValidData() {
		try {
			while(resultset.next()) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			inconsistentData();
			nullValues();
		}
	}
	
	void checkSoil(double n,double p, double k){
				
	}
	
	public Report generateReport() {
		Report report = new Report();
		return report;
		
	}
	
	private void inconsistentData() {
		System.out.println("Inconsistent data are present");
	}
	
	private void nullValues() {
		System.out.println("Null values are present in data");
	}
	
}
