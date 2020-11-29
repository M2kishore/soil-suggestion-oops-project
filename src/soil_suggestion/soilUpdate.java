package soil_suggestion;
	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.logging.Level;
	import java.util.logging.Logger;
	import javax.swing.JOptionPane;
	/**
	 *
	 * @author tharu
	 */
	public class soilUpdate {

	    /**
	     *
	     * @param brand
	     * @param model
	     * @param price
	     * @param qty
	     * @param description
	     * @param imagePath
	     */
	    public static void update(int id,String name,String soil,int ph, int ec, int n_content, int p_content, int k_content){
	        try {
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/annam?useSSL=false");
	         
	            PreparedStatement ps = con.prepareStatement("INSERT INTO crop(id, name, soil, ph, ec, n_content, p_content, k_content) VALUES(?,?,?,?,?,?,?,?)");
	            
	            ps.setInt(1, id);
	            ps.setString(2, name);
	            ps.setString(3, soil);
	            ps.setInt(4, ph);
	            ps.setInt(5, ec);
	            ps.setInt(6, n_content);
	            ps.setInt(7, p_content);
	            ps.setInt(8, k_content);
	          
	            
	            if(ps.executeUpdate()==1)
	                JOptionPane.showMessageDialog(null, "Entry successful!");
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(soilUpdate.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    
	}
	}
}
