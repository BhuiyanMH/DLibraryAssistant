import java.sql.*;
import javax.swing.*;

public class DatabaseConnection {
	
	Connection dbConnection = null; //variable to connect datadase
	
	public static Connection databaseConnector()
	{
		try {
			Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:DLA.sqlite");
			//JOptionPane.showMessageDialog(null, "Database connection established");
			return dbConnection; 
		} catch (Exception dbException) {
			
			//JOptionPane.showMessageDialog(null, "Database connection is not established !");
			return null;
		}
	}
	
	public static Connection databaseConnector(String databaseName)
	{
		try {
			 //String str = "Student.sqlite";
			//if(path.equals(null))
			 //path = "G:\\EDUCATION\\PROGRAMS\\JAVA\\DLA\\";
			 //String str = "BookDatabase.sqlite";
			Class.forName("org.sqlite.JDBC");  //define class for connection to sqLite
			Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:"+databaseName);
			//JOptionPane.showMessageDialog(null,"Database Connection Succcessful "+dbConnection);
			//JOptionPane.showConfirmDialog(null,"noo");
			//JOptionPane.showInternalInputDialog(null, null, null, 0);
			return dbConnection; 
		} catch (Exception dbException) {
			
			JOptionPane.showMessageDialog(null, "Database connection is not established !");
			return null;
		}
	}
	
	public static Connection databaseConnector(String path, String databaseName)
	{
		try {
			 //String str = "Student.sqlite";
			//if(path.equals(null))
			 path = "G:\\EDUCATION\\PROGRAMS\\JAVA\\DLA\\";
			// String str = "BookDatabase.sqlite";
			Class.forName("org.sqlite.JDBC");  //define class for connection to sqLite
			Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:"+path+databaseName);
			//JOptionPane.showMessageDialog(null,"Database Connection Succcessful "+dbConnection);
			//JOptionPane.showConfirmDialog(null,"noo");
			//JOptionPane.showInternalInputDialog(null, null, null, 0);
			return dbConnection; 
		} catch (Exception dbException) {
			
			JOptionPane.showMessageDialog(null, "Database connection is not established !");
			return null;
		}
	}

}
