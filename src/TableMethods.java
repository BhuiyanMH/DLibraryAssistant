import java.sql.*;

import javax.mail.search.FromStringTerm;
import javax.swing.*;
import javax.swing.table.TableModel;
import javax.xml.transform.Templates;

import org.sqlite.core.DB;

import net.proteanit.sql.DbUtils;

import java.awt.*;


public class TableMethods {

	private static Connection databaseConnection = null;
	
	static PreparedStatement preparedStatement = null;
	static ResultSet resultSet = null;
	
	
	
	public static void updateData(String ... arguments)
	{
		
		try {

			databaseConnection = DatabaseConnection.databaseConnector();

			String queryString = "update "+arguments[0]+" set "+arguments[1]+" = '"+arguments[2]+"'";
			int length = arguments.length;
			for(int j=3; j<length-2; j+=2)
			{
				queryString+=", "+arguments[j];
				queryString += " = '"+arguments[j+1]+"' ";
				
			}
			
			queryString += " where "+arguments[length-2]+" = '"+arguments[length-1]+"'";
			
			//JOptionPane.showMessageDialog(null, queryString);
			
			preparedStatement = databaseConnection.prepareStatement(queryString);
			preparedStatement.execute();
			closeConnections();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Data is not Updataed");
			e.printStackTrace();
		}
	}
	
	
	public static ResultSet selectTable(JTable tableName, String dbTableName, String selectValue, int colNum) // select particular row from the table
	{
		databaseConnection = DatabaseConnection.databaseConnector();
		
		try {
			
			int row = tableName.getSelectedRow();
			String value = tableName.getModel().getValueAt(row, colNum).toString();	
			
			String query = "select * from "+ dbTableName + " where "+ selectValue + " =  '"+value+"' ";
			preparedStatement = databaseConnection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			//JOptionPane.showMessageDialog(null, resultSet);
			return resultSet;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Table selection failed", "Table not loded", JOptionPane.ERROR_MESSAGE);
			
			//e.printStackTrace();
			return null;
		}
	}
	
	public static void loadTable(JTable tableName,String ... arguments)//gui table name, first column names, at last database table name, 
	{
		MainFrame.databaseConnection = DatabaseConnection.databaseConnector();
	
		
		int length = arguments.length;
		String query = "select "+arguments[0];
		for(int i=1; i<length-1; i++)
		{
			query+=", "+arguments[i];
		}
		query+=" from "+arguments[length-1];
		
		//JOptionPane.showMessageDialog(null, query);
		try {
			preparedStatement = MainFrame.databaseConnection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			TableModel tableModel = DbUtils.resultSetToTableModel(resultSet);
			
			tableName.setModel(tableModel);
			tableName.setFont(MainFrame.tableDataFont);
			tableName.getTableHeader().setFont(MainFrame.columnFont);
		
			preparedStatement.close();
			resultSet.close();
			MainFrame.databaseConnection = null;
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Table not loaded !");
			e.printStackTrace();
			
		}
	
	}
	
	public static boolean isPresent(String ...args) //tablename, (columnName, Value)... (Logic)
	{
		try {
			
			databaseConnection = DatabaseConnection.databaseConnector();
			
			String query = "select * from "+ args[0]+" where "+args[1]+" = '"+args[2]+ "' ";
			
			int length = args.length;
			
			for(int i=3; i<length-1; i+=2)
			{
				query += args[length-1] + " "+ args[i] +" = '"+args[i+1]+"'";
			}
			
			preparedStatement = databaseConnection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			String temp = null;
			while (resultSet.next()) {
				closeConnections();
				return true;
			}
			closeConnections();
			return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Value presence check failed. ", "Check Failed", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			closeConnections();
			return false;
		}
	}
	
	public static boolean deleteData(String tableName, String pk, String pkValue)
	{
		databaseConnection = DatabaseConnection.databaseConnector();
		try {
			int i = JOptionPane.showConfirmDialog(null, "Are you sure ? ", "Delete", JOptionPane.YES_NO_OPTION);
			if(i==0)
			{
				String query = "delete from "+tableName+ " where "+pk+" = '"+pkValue+"' ";
				JOptionPane.showMessageDialog(null, query);
				preparedStatement = databaseConnection.prepareStatement(query);
				preparedStatement.execute();
				closeConnections();
			}

		} catch (SQLException e) {
			closeConnections();
			JOptionPane.showMessageDialog(null, "Data is not deleted");
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean insertData(String ... arguments)//table name, column names, values
	{
		String query = "insert into "+arguments[0]+" ( " + arguments[1];
		int length = arguments.length;
		int limit  = (length-1)/2; // half are columnnames
		
		for(int i =2; i<=limit; i++)
		{
			query+=", "+arguments[i];
		}
		query += " ) values ( '"+arguments[limit+1]+"'";
		for(int j=limit+2; j<length; j++)
		{
			query+=", '"+arguments[j]+"'";
		}
		query+=" ) ";
		//JOptionPane.showMessageDialog(null, query);
		databaseConnection = DatabaseConnection.databaseConnector();
		try {
			preparedStatement = databaseConnection.prepareStatement(query);
			preparedStatement.execute();
			closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Insertion not possible because \n"+e.getMessage());
			closeConnections();
			return false;
		}
		
		//JOptionPane.showMessageDialog(null, query);
		return true;
	}
	
	
	public static void searchTable(String databaseName, JTable table, JTextField searchBox, String ...columnNames)
	{
		
		databaseConnection = DatabaseConnection.databaseConnector();
		
		String value =searchBox.getText();
		
		String query = "select * from " +databaseName+ " where "+ columnNames[0] + " LIKE '%"+value+"%' ";
		
		int length = columnNames.length;
		
		for(int i = 1; i<length; i++)
		{
			query+=" OR "+columnNames[i] + " LIKE '%"+value+"%'";
		}
			
		//JOptionPane.showMessageDialog(null, query);
		try {
			
			preparedStatement = databaseConnection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));	
			table.getTableHeader().setFont(MainFrame.tableDataFont);

			resultSet.close();
			preparedStatement.close();
			databaseConnection = null;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Searching is not done");
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void closeConnections()
	{
		try {
			preparedStatement.close();
			resultSet.close();
			databaseConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Connection close not poassible");
		}
		
	}
	public static void closeConnections(boolean b)
	{
		try {
			preparedStatement.close();
			resultSet.close();
			databaseConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Connection close not poassible");
		}
		
	}
}
