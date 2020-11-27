package soil_suggestion;

import java.io.File;
import java.io.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	
}

class Report extends Test{
	int requestId;
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
	ResultSet getData(int submission_id) {
		
		try{
			statement = connection.createStatement();
			resultset=statement.executeQuery("SELECT n_component,p_component,k_component FROM sumbission WHERE s_ID="+submission_id);
		}
		catch (SQLException e){
			e.getStackTrace();
		}
		listData();
		return resultset;
		
	}
	private void listData() {
		try {
			while(resultset.next()) {
				n.add(resultset.getDouble("n_component"));
				p.add(resultset.getDouble("p_component"));
				k.add(resultset.getDouble("k_component"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void getResult(int sid, double n1, double p1, double k1) {
		double distance = 0;
		int idx = 0;
		for(int c = 0; c < n.size(); c++){
		    double cdistance = n.get(c) - n1;
		    if(cdistance < distance){
		        idx = c;
		        distance = cdistance;
		    }
		}
		System.out.print("Nitrogen:")
		printCrop(idx);
		
	}
	void printCrop(int id) {
		try {
			ResultSet rs=statement.executeQuery("SELECT name FROM crop WHERE id="+id);
			while(rs.next()) {
				System.out.print(", "+rs.getString("name"));
			}
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
			statement.executeQuery("UPDATE TABLE submission SET Status='PROVIDED' WHERE id="+submission_id);
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
	
	void getAll() {
		try {
			resultset =statement.executeQuery("select * from crop");
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
