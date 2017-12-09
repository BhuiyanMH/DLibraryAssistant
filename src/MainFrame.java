import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.management.openmbean.OpenDataException;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.*;
import java.util.*;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;



public class MainFrame {

	static public JFrame frame;
	
	static boolean isSettingPanel = false;
	static boolean isCirculationPanelOn = false;
	static boolean isLibProcessingOn = false;
	
	
	static String loggedInAs = null;
	static boolean isLoggedIn = false;
	
	static Connection databaseConnection = null;
	
	static CardLayout cardLayout;
	
	static SettingsPanel settingsPanel = new SettingsPanel();
	static LibraryProcessing libraryProcessingPanel = new LibraryProcessing();
	static Circulation circulationPanel = new Circulation();
	static LoginPanel loginPanel = new LoginPanel();
	static About aboutPanel = new About();
	
	static Font buttonTextFont = new Font("Cambria Math", Font.PLAIN, 20);
	static Font smallBtnFont = new Font("Cambria Math", Font.PLAIN, 15);
	static Font levelFont = new Font("Trebuchet MS", Font.PLAIN, 20);
	static Font fieldFont = new Font("Tahoma", Font.PLAIN, 15);
	static Font columnFont  = new Font("Tahoma", Font.BOLD,20);
	static Font tableDataFont  = new Font("Tahoma", Font.PLAIN,15);
	
	
	final static String SETTINGS_PANEL_STRING = "settingPanel";
	final static String LIBRARY_PROCESSING_PANEL_STRING = "libraryProcessingPanel";
	final static String CIRCULATION_PANEL_STRING = "circulationPanel";
	final static String LOGIN_PANEL_STRING = "loginPanel";
	final static String ABOUT_PANEL_STRING = "aboutPanel";
	
	static JMenuBar menuBar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		databaseConnection = DatabaseConnection.databaseConnector();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
		
			for(LookAndFeelInfo lF : UIManager.getInstalledLookAndFeels())
			{
				if(lF.getName().equals("Nimbus"))
				{
					UIManager.setLookAndFeel(lF.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Look and feel not changed.");
		}
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setTitle("Digital Library Assistant");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icons\\logo.jpg"));
		
		settingsPanel = new SettingsPanel();
		libraryProcessingPanel = new LibraryProcessing();
		circulationPanel = new Circulation();
		loginPanel = new LoginPanel();
		aboutPanel = new About();
		
		
		frame.getContentPane().add(settingsPanel,SETTINGS_PANEL_STRING);
		frame.getContentPane().add(libraryProcessingPanel, LIBRARY_PROCESSING_PANEL_STRING);
		frame.getContentPane().add(circulationPanel,CIRCULATION_PANEL_STRING);
		frame.getContentPane().add(loginPanel,LOGIN_PANEL_STRING);
		frame.getContentPane().add(aboutPanel, ABOUT_PANEL_STRING);
		
		cardLayout = (CardLayout)frame.getContentPane().getLayout();
		cardLayout.show(frame.getContentPane(), LOGIN_PANEL_STRING);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.setVisible(false);
		
		
		JMenu mnCirculation = new JMenu("CIRCULATION");
		mnCirculation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showPanel(CIRCULATION_PANEL_STRING);
			}
		});
		mnCirculation.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(mnCirculation);
		
		Component horizontalStrut = Box.createHorizontalStrut(5);
		menuBar.add(horizontalStrut);
		
		JMenu mnProcess = new JMenu("PROCESS");
		mnProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showPanel(LIBRARY_PROCESSING_PANEL_STRING);
			}
		});
		mnProcess.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(mnProcess);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(5);
		menuBar.add(horizontalStrut_1);
		
		JMenu mnSetting = new JMenu("SETTING");
		mnSetting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				showPanel(SETTINGS_PANEL_STRING);
			}
		});
		mnSetting.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(mnSetting);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(5);
		menuBar.add(horizontalStrut_2);
		
		JMenu mnLogout = new JMenu("LOGOUT");
		mnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			int op  =JOptionPane.showConfirmDialog(null, "Are you sure ?", "Logout", JOptionPane.YES_NO_OPTION);
				if(op == 0)
				{
					isLoggedIn = false;
					loggedInAs = null;
					menuBar.setVisible(false);
					showPanel(LOGIN_PANEL_STRING);
					
				}
			}
		});
		mnLogout.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(mnLogout);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(5);
		menuBar.add(horizontalStrut_3);
		
		JMenu mnAbout = new JMenu("ABOUT");
		mnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showPanel(ABOUT_PANEL_STRING);
			}
		});
		mnAbout.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(mnAbout);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(5);
		menuBar.add(horizontalStrut_4);
		
		JMenu mnExit = new JMenu("EXIT");
		mnExit.setForeground(Color.RED);
		mnExit.setFont(new Font("Segoe UI", Font.BOLD, 18));
		mnExit.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg){
				
				int sel = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if(sel == 0)
				{
					frame.dispose();
				}
				
				
			}
		});
		menuBar.add(mnExit);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		menuBar.add(horizontalGlue);
	}
	
	
	
	public static void showPanel(String panelName)
	{
		cardLayout = (CardLayout) frame.getContentPane().getLayout();
		cardLayout.show(frame.getContentPane(), panelName);
		
		switch (panelName) {
		
		case CIRCULATION_PANEL_STRING:
			isCirculationPanelOn = true;
			isLibProcessingOn = false;
			isSettingPanel = false;
			
			break;
		case SETTINGS_PANEL_STRING:
			isSettingPanel = true;
			isCirculationPanelOn = false;
			isLibProcessingOn = false;
			break;
		case LIBRARY_PROCESSING_PANEL_STRING:
			isLibProcessingOn= true;
			isCirculationPanelOn = false;
			isSettingPanel =false;
			break;
		default:
			isLibProcessingOn = false;
			isSettingPanel = false;
			isCirculationPanelOn = false;
			break;

		}
	}

}
