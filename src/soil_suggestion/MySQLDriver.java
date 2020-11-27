package soil_suggestion;
import login.RegistrationForm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDriver {
	Connection connection;
	MySQLDriver() {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/annam?useSSL=false", "root", "root"))
				{
	}
	catch (SQLException e){
			e.getStackTrace();
	}
		
	}
	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/annam?useSSL=false", "root", "root");
			Statement statement = connection.createStatement();
				ResultSet resultset =statement.executeQuery("select * from users");){
			while(resultset.next()) {
				int id = resultset.getInt("id");
				String name = resultset.getString("name");
				String email = resultset.getString("email");
				String country =resultset.getString("country");
				String password = resultset.getString("password");
				System.out.println(id+" "+name+" "+email+" "+country+" "+password);
				System.out.println("execution complete");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		new RegistrationForm();
	}
}
