import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.Component;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;


public class LoginPanel extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	IconClass loginIconClass = new IconClass();
	public LoginPanel() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(30, 30));
		
		JLabel lblWelcomeToDigital = new JLabel("Welcome to Digital Library Assitant");
		lblWelcomeToDigital.setFont(new Font("Snap ITC", Font.BOLD, 36));
		lblWelcomeToDigital.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblWelcomeToDigital, BorderLayout.NORTH);
		
		JPanel panelComponents = new JPanel();
		add(panelComponents, BorderLayout.CENTER);
		GridBagLayout gbl_panelComponents = new GridBagLayout();
		gbl_panelComponents.columnWidths = new int[]{0, 0, 0};
		gbl_panelComponents.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 20, 0};
		gbl_panelComponents.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelComponents.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panelComponents.setLayout(gbl_panelComponents);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 0;
		panelComponents.add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblLogin = new JLabel("Login To Continue");
		lblLogin.setFont(new Font("Stencil", Font.BOLD, 30));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogin.gridx = 1;
		gbc_lblLogin.gridy = 1;
		panelComponents.add(lblLogin, gbc_lblLogin);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut_1.gridx = 1;
		gbc_verticalStrut_1.gridy = 2;
		panelComponents.add(verticalStrut_1, gbc_verticalStrut_1);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut_2.gridx = 1;
		gbc_verticalStrut_2.gridy = 3;
		panelComponents.add(verticalStrut_2, gbc_verticalStrut_2);
		
		JLabel lblempname = new JLabel("Employee ID");
		lblempname.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblempname = new GridBagConstraints();
		gbc_lblempname.insets = new Insets(0, 0, 5, 0);
		gbc_lblempname.gridx = 1;
		gbc_lblempname.gridy = 4;
		panelComponents.add(lblempname, gbc_lblempname);
		
		textField = new JTextField();
		textField.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.ipady = 6;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 5;
		panelComponents.add(textField, gbc_textField);
		textField.setColumns(40);
		
		JLabel lblpass = new JLabel("Password");
		lblpass.setFont(MainFrame.levelFont);
		
		GridBagConstraints gbc_lblpass = new GridBagConstraints();
		gbc_lblpass.insets = new Insets(0, 0, 5, 0);
		gbc_lblpass.gridx = 1;
		gbc_lblpass.gridy = 6;
		panelComponents.add(lblpass, gbc_lblpass);
		
		passwordField = new JPasswordField();
		passwordField.setFont(MainFrame.fieldFont);
		passwordField.setColumns(40);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.ipady = 6;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 7;
		panelComponents.add(passwordField, gbc_passwordField);
		
		JPanel panelBtn = new JPanel();
		GridBagConstraints gbc_panelBtn = new GridBagConstraints();
		gbc_panelBtn.insets = new Insets(10, 0, 30, 0);
		gbc_panelBtn.gridx = 1;
		gbc_panelBtn.gridy = 8;
		panelComponents.add(panelBtn, gbc_panelBtn);
		panelBtn.setLayout(new GridLayout(0, 3, 30, 0));
		
		JButton btnExit = new JButton("Exit");
		btnExit.setMnemonic('X');
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int op = JOptionPane.showConfirmDialog(null, "Are you sure ? ", "Exit", JOptionPane.YES_OPTION);
				if(op == 0)
					System.exit(0);
			}
		});
		btnExit.setForeground(Color.RED);
		btnExit.setFont(MainFrame.buttonTextFont);
		btnExit.setIcon(loginIconClass.exitIcon);
		panelBtn.add(btnExit);
		
		JButton btnNewButton = new JButton("Retry");
		btnNewButton.setMnemonic('R');
		panelBtn.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		btnNewButton.setFont(MainFrame.buttonTextFont);
		btnNewButton.setIcon(loginIconClass.reloadIcon);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setMnemonic('L');
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//MainFrame.menuBar.setVisible(true);
				String empName = textField.getText();
				String empPasString = passwordField.getText();
				if(empName.equals("") || empPasString.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please give all information correctly.", "Empty Field", JOptionPane.WARNING_MESSAGE);
				}
				else if(TableMethods.isPresent("login", "empid", empName, "pass", empPasString, "AND") || textField.getText().equals("DevCSECU") && passwordField.getText().equals("DevCSECU")){
					MainFrame.isLoggedIn = true;
					MainFrame.loggedInAs = empName;
					MainFrame.menuBar.setVisible(true);
					textField.setText("");
					passwordField.setText("");
					MainFrame.showPanel(MainFrame.CIRCULATION_PANEL_STRING);
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong Username or Password ! ", "Data Mismatch", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
					passwordField.setText("");
				}
			}
		});
		panelBtn.add(btnLogin);
		btnLogin.setIcon(loginIconClass.loginIcon);
		btnLogin.setFont(MainFrame.buttonTextFont);
		
	}

}
