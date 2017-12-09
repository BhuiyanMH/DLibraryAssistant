import javax.lang.model.element.Element;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;

import javax.swing.Box;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import java.awt.FlowLayout;

import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.TabExpander;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Circulation extends JPanel {
	
	private JTextField textFieldIssueSearch;
	private JTable tableIssue;
	private JTextField textFieldIssueIssueNo;
	private JTextField textFieldIssueReaderID;
	private JTextField textFieldIssueIssueDate;
	private JTable tableIssueDet;
	private JTextField textFieldIssueDetSearch;
	private JTextField textFieldIssueDetIssueNum;
	private JTextField textFieldIssueDetAccNumber;
	private JTable tableReturn;
	private JTextField textFieldReturnSearch;
	private JTextField textFieldReturnIssueNo;
	private JTextField textFieldRetAccNum;
	private JTextField textFieldRetReaderID;
	private JTextField textFieldIssueRetDate;
	private JTextField textFieldReturnReturnDate;
	private JTabbedPane tabbedPaneCirculation;
	IconClass circulatioIconClass = new IconClass();

	/**
	 * Create the panel.
	 */
	public Circulation() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(5, 5));
		
		JLabel lblCirculation = new JLabel("CIRCULATION");
		lblCirculation.setHorizontalAlignment(SwingConstants.CENTER);
		lblCirculation.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(lblCirculation, BorderLayout.NORTH);
		
		JPanel panelCirculationButtons = new JPanel();
		panelCirculationButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panelCirculationButtons, BorderLayout.SOUTH);
		
		JButton btnLPSetting = new JButton("Setting");
		btnLPSetting.setMnemonic('S');
		btnLPSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.showPanel(MainFrame.SETTINGS_PANEL_STRING);
			}
		});
		btnLPSetting.setIcon(circulatioIconClass.settingIcon);
		btnLPSetting.setFont(MainFrame.buttonTextFont);
		panelCirculationButtons.add(btnLPSetting);
		
		JButton btnLPAdd = new JButton("Add");
		btnLPAdd.setMnemonic('A');
		btnLPAdd.setIcon(circulatioIconClass.insertIcon);
		btnLPAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				circulationAddBtnHandler();
			}
		});
		
		JButton btnProcess = new JButton("Process");
		btnProcess.setMnemonic('P');
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.showPanel(MainFrame.LIBRARY_PROCESSING_PANEL_STRING);
			}
		});
		btnProcess.setFont(MainFrame.buttonTextFont);
		btnProcess.setIcon(circulatioIconClass.acquisitionIcon);
		panelCirculationButtons.add(btnProcess);
		btnLPAdd.setFont(MainFrame.buttonTextFont);
		panelCirculationButtons.add(btnLPAdd);
		
		JButton btnLPDelete = new JButton("Delete");
		btnLPDelete.setMnemonic('D');
		btnLPDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				circulationDeleteBtnHandler();
			}
		});
		btnLPDelete.setIcon(circulatioIconClass.deleteIcon);
		btnLPDelete.setFont(MainFrame.buttonTextFont);
		panelCirculationButtons.add(btnLPDelete);
		
		JButton btnLPUpdate = new JButton("Update");
		btnLPUpdate.setMnemonic('U');
		btnLPUpdate.setIcon(circulatioIconClass.editIcon);
		btnLPUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnLPUpdate.setFont(MainFrame.buttonTextFont);
		panelCirculationButtons.add(btnLPUpdate);
		
		JButton btnLPCancel = new JButton("Cancel");
		btnLPCancel.setMnemonic('N');
		btnLPCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				circulationRefreshFields((byte)-1);
			}
		});
		btnLPCancel.setIcon(circulatioIconClass.cancelIcon);
		btnLPCancel.setFont(MainFrame.buttonTextFont);
		panelCirculationButtons.add(btnLPCancel);
		
		tabbedPaneCirculation = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneCirculation.setFont(new Font("Cambria Math", Font.BOLD, 18));
		add(tabbedPaneCirculation, BorderLayout.CENTER);
		
		JPanel panelCirculationIssue = new JPanel();
		tabbedPaneCirculation.addTab("Issue", null, panelCirculationIssue, "");
		tabbedPaneCirculation.setForegroundAt(0, new Color(0, 0, 0));
		panelCirculationIssue.setLayout(new BorderLayout(5, 5));
		
		JPanel panelIssueCenter = new JPanel();
		panelCirculationIssue.add(panelIssueCenter, BorderLayout.CENTER);
		panelIssueCenter.setLayout(new GridLayout(0, 2, 20, 0));
		
		JPanel panelIssueLeft = new JPanel();
		panelIssueLeft.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelIssueCenter.add(panelIssueLeft);
		GridBagLayout gbl_panelIssueLeft = new GridBagLayout();
		gbl_panelIssueLeft.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelIssueLeft.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelIssueLeft.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelIssueLeft.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelIssueLeft.setLayout(gbl_panelIssueLeft);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 0;
		panelIssueLeft.add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblIssueIssueNo = new JLabel("Issue Number : ");
		lblIssueIssueNo.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblIssueIssueNo = new GridBagConstraints();
		gbc_lblIssueIssueNo.anchor = GridBagConstraints.EAST;
		gbc_lblIssueIssueNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblIssueIssueNo.gridx = 1;
		gbc_lblIssueIssueNo.gridy = 1;
		panelIssueLeft.add(lblIssueIssueNo, gbc_lblIssueIssueNo);
		
		textFieldIssueIssueNo = new JTextField();
		textFieldIssueIssueNo.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldIssueIssueNo = new GridBagConstraints();
		gbc_textFieldIssueIssueNo.ipady = 6;
		gbc_textFieldIssueIssueNo.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldIssueIssueNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIssueIssueNo.gridx = 3;
		gbc_textFieldIssueIssueNo.gridy = 1;
		panelIssueLeft.add(textFieldIssueIssueNo, gbc_textFieldIssueIssueNo);
		textFieldIssueIssueNo.setColumns(10);
		
		JLabel lblIsssueReaderID = new JLabel("Reader ID : ");
		lblIsssueReaderID.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblIsssueReaderID = new GridBagConstraints();
		gbc_lblIsssueReaderID.anchor = GridBagConstraints.EAST;
		gbc_lblIsssueReaderID.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsssueReaderID.gridx = 1;
		gbc_lblIsssueReaderID.gridy = 2;
		panelIssueLeft.add(lblIsssueReaderID, gbc_lblIsssueReaderID);
		
		textFieldIssueReaderID = new JTextField();
		textFieldIssueReaderID.setFont(MainFrame.fieldFont);
		textFieldIssueReaderID.setText("");
		GridBagConstraints gbc_textFieldIssueReaderID = new GridBagConstraints();
		gbc_textFieldIssueReaderID.ipady = 6;
		gbc_textFieldIssueReaderID.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldIssueReaderID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIssueReaderID.gridx = 3;
		gbc_textFieldIssueReaderID.gridy = 2;
		panelIssueLeft.add(textFieldIssueReaderID, gbc_textFieldIssueReaderID);
		textFieldIssueReaderID.setColumns(10);
		
		JLabel lblIssueIssueDate = new JLabel("Issue Date : ");
		lblIssueIssueDate.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblIssueIssueDate = new GridBagConstraints();
		gbc_lblIssueIssueDate.anchor = GridBagConstraints.EAST;
		gbc_lblIssueIssueDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblIssueIssueDate.gridx = 1;
		gbc_lblIssueIssueDate.gridy = 3;
		panelIssueLeft.add(lblIssueIssueDate, gbc_lblIssueIssueDate);
		
		textFieldIssueIssueDate = new JTextField();
		textFieldIssueIssueDate.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldIssueIssueDate = new GridBagConstraints();
		gbc_textFieldIssueIssueDate.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldIssueIssueDate.ipady = 6;
		gbc_textFieldIssueIssueDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIssueIssueDate.gridx = 3;
		gbc_textFieldIssueIssueDate.gridy = 3;
		panelIssueLeft.add(textFieldIssueIssueDate, gbc_textFieldIssueIssueDate);
		textFieldIssueIssueDate.setColumns(10);
		
		JLabel lblIuusePropRetDate = new JLabel("Proposed Return Date : ");
		lblIuusePropRetDate.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblIuusePropRetDate = new GridBagConstraints();
		gbc_lblIuusePropRetDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblIuusePropRetDate.gridx = 1;
		gbc_lblIuusePropRetDate.gridy = 4;
		panelIssueLeft.add(lblIuusePropRetDate, gbc_lblIuusePropRetDate);
		
		textFieldIssueRetDate = new JTextField();
		textFieldIssueRetDate.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldIssueRetDate = new GridBagConstraints();
		gbc_textFieldIssueRetDate.ipady = 6;
		gbc_textFieldIssueRetDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIssueRetDate.gridx = 3;
		gbc_textFieldIssueRetDate.gridy = 4;
		panelIssueLeft.add(textFieldIssueRetDate, gbc_textFieldIssueRetDate);
		textFieldIssueRetDate.setColumns(10);
		
		JPanel panelIssueTable = new JPanel();
		panelIssueTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelIssueCenter.add(panelIssueTable);
		panelIssueTable.setLayout(new BorderLayout(5, 5));
		
		JPanel panelIssueSearch = new JPanel();
		panelIssueSearch.setBorder(new EmptyBorder(3, 20, 3, 80));
		panelIssueTable.add(panelIssueSearch, BorderLayout.NORTH);
		GridBagLayout gbl_panelIssueSearch = new GridBagLayout();
		gbl_panelIssueSearch.columnWidths = new int[]{43, 0, 0, 0};
		gbl_panelIssueSearch.rowHeights = new int[]{14, 0};
		gbl_panelIssueSearch.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelIssueSearch.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelIssueSearch.setLayout(gbl_panelIssueSearch);
		
		JLabel lblIssueSearch = new JLabel("Search : ");
		lblIssueSearch.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblIssueSearch = new GridBagConstraints();
		gbc_lblIssueSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblIssueSearch.gridx = 0;
		gbc_lblIssueSearch.gridy = 0;
		panelIssueSearch.add(lblIssueSearch, gbc_lblIssueSearch);
		
		textFieldIssueSearch = new JTextField();
		textFieldIssueSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				TableMethods.searchTable("issue", tableIssue, textFieldIssueSearch, "issueno","readerid","issuedate","proposedreturndate");
				tableIssue.getTableHeader().setFont(MainFrame.columnFont);
				setCirculationPanel();
			}
		});
		textFieldIssueSearch.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldIssueSearch = new GridBagConstraints();
		gbc_textFieldIssueSearch.ipady = 6;
		gbc_textFieldIssueSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIssueSearch.gridx = 2;
		gbc_textFieldIssueSearch.gridy = 0;
		panelIssueSearch.add(textFieldIssueSearch, gbc_textFieldIssueSearch);
		textFieldIssueSearch.setColumns(35);
		
		JButton btnIssueReload = new JButton("Reload");
		btnIssueReload.setMnemonic('R');
		btnIssueReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				circulationRefreshFields((byte)-1);
			}
		});
		btnIssueReload.setFont(MainFrame.smallBtnFont);
		btnIssueReload.setIcon(circulatioIconClass.reloadIcon);
		panelIssueTable.add(btnIssueReload, BorderLayout.SOUTH);
		
		JScrollPane scrollPaneIssue = new JScrollPane();
		panelIssueTable.add(scrollPaneIssue, BorderLayout.CENTER);
		
		tableIssue = new JTable();
		tableIssue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResultSet rst = TableMethods.selectTable(tableIssue,  "issue", "issueno",0);
				
				try {
					while (rst.next()) {
						
						textFieldIssueIssueNo.setText(rst.getString("issueno"));
						textFieldIssueIssueDate.setText(rst.getString("issuedate"));
						textFieldIssueReaderID.setText(rst.getString("readerid"));
						textFieldIssueRetDate.setText(rst.getString("proposedreturndate"));
					}
					rst.close();
					TableMethods.closeConnections();
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Table selection failed");
				}
			}
		});
		scrollPaneIssue.setViewportView(tableIssue);
		
		JPanel panelCirculationIssueDetail = new JPanel();
		tabbedPaneCirculation.addTab("Issue Detail", null, panelCirculationIssueDetail, null);
		panelCirculationIssueDetail.setLayout(new GridLayout(0, 2, 20, 0));
		
		JPanel panelIssueDetLeft = new JPanel();
		panelIssueDetLeft.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCirculationIssueDetail.add(panelIssueDetLeft);
		GridBagLayout gbl_panelIssueDetLeft = new GridBagLayout();
		gbl_panelIssueDetLeft.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelIssueDetLeft.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelIssueDetLeft.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelIssueDetLeft.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelIssueDetLeft.setLayout(gbl_panelIssueDetLeft);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 1;
		gbc_verticalStrut_1.gridy = 0;
		panelIssueDetLeft.add(verticalStrut_1, gbc_verticalStrut_1);
		
		JLabel lblIssueDetIssueNumber = new JLabel("Issue Number : ");
		lblIssueDetIssueNumber.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblIssueDetIssueNumber = new GridBagConstraints();
		gbc_lblIssueDetIssueNumber.anchor = GridBagConstraints.EAST;
		gbc_lblIssueDetIssueNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblIssueDetIssueNumber.gridx = 1;
		gbc_lblIssueDetIssueNumber.gridy = 1;
		panelIssueDetLeft.add(lblIssueDetIssueNumber, gbc_lblIssueDetIssueNumber);
		
		textFieldIssueDetIssueNum = new JTextField();
		textFieldIssueDetIssueNum.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldIssueDetIssueNum = new GridBagConstraints();
		gbc_textFieldIssueDetIssueNum.ipady = 6;
		gbc_textFieldIssueDetIssueNum.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldIssueDetIssueNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIssueDetIssueNum.gridx = 2;
		gbc_textFieldIssueDetIssueNum.gridy = 1;
		panelIssueDetLeft.add(textFieldIssueDetIssueNum, gbc_textFieldIssueDetIssueNum);
		textFieldIssueDetIssueNum.setColumns(10);
		
		JLabel lblIssueDetAccNumber = new JLabel("Accession Number : ");
		lblIssueDetAccNumber.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblIssueDetAccNumber = new GridBagConstraints();
		gbc_lblIssueDetAccNumber.anchor = GridBagConstraints.EAST;
		gbc_lblIssueDetAccNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblIssueDetAccNumber.gridx = 1;
		gbc_lblIssueDetAccNumber.gridy = 2;
		panelIssueDetLeft.add(lblIssueDetAccNumber, gbc_lblIssueDetAccNumber);
		
		textFieldIssueDetAccNumber = new JTextField();
		textFieldIssueDetAccNumber.setFont(MainFrame.fieldFont);
		textFieldIssueDetAccNumber.setText("");
		GridBagConstraints gbc_textFieldIssueDetAccNumber = new GridBagConstraints();
		gbc_textFieldIssueDetAccNumber.ipady = 6;
		gbc_textFieldIssueDetAccNumber.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldIssueDetAccNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIssueDetAccNumber.gridx = 2;
		gbc_textFieldIssueDetAccNumber.gridy = 2;
		panelIssueDetLeft.add(textFieldIssueDetAccNumber, gbc_textFieldIssueDetAccNumber);
		textFieldIssueDetAccNumber.setColumns(10);
		
		JPanel panelIssueDetTable = new JPanel();
		panelIssueDetTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCirculationIssueDetail.add(panelIssueDetTable);
		panelIssueDetTable.setLayout(new BorderLayout(5, 5));
		
		JPanel panelIssueDetSearch = new JPanel();
		panelIssueDetSearch.setBorder(new EmptyBorder(3, 20, 3, 80));
		panelIssueDetTable.add(panelIssueDetSearch, BorderLayout.NORTH);
		GridBagLayout gbl_panelIssueDetSearch = new GridBagLayout();
		gbl_panelIssueDetSearch.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelIssueDetSearch.rowHeights = new int[]{0, 0};
		gbl_panelIssueDetSearch.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelIssueDetSearch.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelIssueDetSearch.setLayout(gbl_panelIssueDetSearch);
		
		JLabel lblIssueDetSearch = new JLabel("Search: ");
		lblIssueDetSearch.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblIssueDetSearch = new GridBagConstraints();
		gbc_lblIssueDetSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblIssueDetSearch.gridx = 0;
		gbc_lblIssueDetSearch.gridy = 0;
		panelIssueDetSearch.add(lblIssueDetSearch, gbc_lblIssueDetSearch);
		
		textFieldIssueDetSearch = new JTextField();
		textFieldIssueDetSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				TableMethods.searchTable("issuedetail", tableIssueDet, textFieldIssueDetSearch, "issuenumber","accno");
				tableIssueDet.getTableHeader().setFont(MainFrame.columnFont);
			}
		});
		textFieldIssueDetSearch.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldIssueDetSearch = new GridBagConstraints();
		gbc_textFieldIssueDetSearch.ipady = 6;
		gbc_textFieldIssueDetSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIssueDetSearch.gridx = 2;
		gbc_textFieldIssueDetSearch.gridy = 0;
		panelIssueDetSearch.add(textFieldIssueDetSearch, gbc_textFieldIssueDetSearch);
		textFieldIssueDetSearch.setColumns(10);
		
		JButton btnIssueDetReload = new JButton("Reload");
		btnIssueDetReload.setMnemonic('R');
		btnIssueDetReload.setFont(MainFrame.smallBtnFont);
		btnIssueDetReload.setIcon(circulatioIconClass.reloadIcon);
		btnIssueDetReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				circulationRefreshFields((byte)-2);
			}
		});
		panelIssueDetTable.add(btnIssueDetReload, BorderLayout.SOUTH);
		
		JScrollPane scrollPaneIssueDet = new JScrollPane();
		panelIssueDetTable.add(scrollPaneIssueDet, BorderLayout.CENTER);
		
		tableIssueDet = new JTable();
		tableIssueDet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResultSet rst = TableMethods.selectTable(tableIssueDet,  "issuedetail", "issuenumber",0);
				try {
					while (rst.next()) {
						
						textFieldIssueDetIssueNum.setText(rst.getString("issuenumber"));
						textFieldIssueDetAccNumber.setText(rst.getString("accno"));
					}
					rst.close();
					TableMethods.closeConnections();
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Table selection failed");
				}
			}
		});
		scrollPaneIssueDet.setViewportView(tableIssueDet);
		
		JPanel panelCirculationReturn = new JPanel();
		tabbedPaneCirculation.addTab("Return", null, panelCirculationReturn, null);
		panelCirculationReturn.setLayout(new GridLayout(0, 2, 20, 0));
		
		JPanel panelCirReturnLeft = new JPanel();
		panelCirReturnLeft.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCirculationReturn.add(panelCirReturnLeft);
		GridBagLayout gbl_panelCirReturnLeft = new GridBagLayout();
		gbl_panelCirReturnLeft.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelCirReturnLeft.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCirReturnLeft.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelCirReturnLeft.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCirReturnLeft.setLayout(gbl_panelCirReturnLeft);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 1;
		gbc_verticalStrut_2.gridy = 0;
		panelCirReturnLeft.add(verticalStrut_2, gbc_verticalStrut_2);
		
		JLabel lblReturnIssueNo = new JLabel("Issue Number : ");
		lblReturnIssueNo.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblReturnIssueNo = new GridBagConstraints();
		gbc_lblReturnIssueNo.anchor = GridBagConstraints.EAST;
		gbc_lblReturnIssueNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturnIssueNo.gridx = 1;
		gbc_lblReturnIssueNo.gridy = 1;
		panelCirReturnLeft.add(lblReturnIssueNo, gbc_lblReturnIssueNo);
		
		textFieldReturnIssueNo = new JTextField();
		textFieldReturnIssueNo.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldReturnIssueNo = new GridBagConstraints();
		gbc_textFieldReturnIssueNo.ipady = 6;
		gbc_textFieldReturnIssueNo.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldReturnIssueNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldReturnIssueNo.gridx = 3;
		gbc_textFieldReturnIssueNo.gridy = 1;
		panelCirReturnLeft.add(textFieldReturnIssueNo, gbc_textFieldReturnIssueNo);
		textFieldReturnIssueNo.setColumns(10);
		
		JLabel lblReturnAccNum = new JLabel("Accession Number : ");
		lblReturnAccNum.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblReturnAccNum = new GridBagConstraints();
		gbc_lblReturnAccNum.anchor = GridBagConstraints.EAST;
		gbc_lblReturnAccNum.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturnAccNum.gridx = 1;
		gbc_lblReturnAccNum.gridy = 2;
		panelCirReturnLeft.add(lblReturnAccNum, gbc_lblReturnAccNum);
		
		textFieldRetAccNum = new JTextField();
		textFieldRetAccNum.setFont(MainFrame.fieldFont);
		textFieldRetAccNum.setText("");
		GridBagConstraints gbc_textFieldRetAccNum = new GridBagConstraints();
		gbc_textFieldRetAccNum.ipady = 6;
		gbc_textFieldRetAccNum.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldRetAccNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRetAccNum.gridx = 3;
		gbc_textFieldRetAccNum.gridy = 2;
		panelCirReturnLeft.add(textFieldRetAccNum, gbc_textFieldRetAccNum);
		textFieldRetAccNum.setColumns(10);
		
		JLabel lblReturnReaderId = new JLabel("Reader ID : ");
		lblReturnReaderId.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblReturnReaderId = new GridBagConstraints();
		gbc_lblReturnReaderId.anchor = GridBagConstraints.EAST;
		gbc_lblReturnReaderId.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturnReaderId.gridx = 1;
		gbc_lblReturnReaderId.gridy = 3;
		panelCirReturnLeft.add(lblReturnReaderId, gbc_lblReturnReaderId);
		
		textFieldRetReaderID = new JTextField();
		textFieldRetReaderID.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldRetReaderID = new GridBagConstraints();
		gbc_textFieldRetReaderID.ipady = 6;
		gbc_textFieldRetReaderID.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldRetReaderID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRetReaderID.gridx = 3;
		gbc_textFieldRetReaderID.gridy = 3;
		panelCirReturnLeft.add(textFieldRetReaderID, gbc_textFieldRetReaderID);
		textFieldRetReaderID.setColumns(10);
		
		JLabel lblReturnRetDate = new JLabel("Return Date : ");
		lblReturnRetDate.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblReturnRetDate = new GridBagConstraints();
		gbc_lblReturnRetDate.anchor = GridBagConstraints.EAST;
		gbc_lblReturnRetDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturnRetDate.gridx = 1;
		gbc_lblReturnRetDate.gridy = 5;
		panelCirReturnLeft.add(lblReturnRetDate, gbc_lblReturnRetDate);
		
		textFieldReturnReturnDate = new JTextField();
		textFieldReturnReturnDate.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldReturnReturnDate = new GridBagConstraints();
		gbc_textFieldReturnReturnDate.ipady = 6;
		gbc_textFieldReturnReturnDate.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldReturnReturnDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldReturnReturnDate.gridx = 3;
		gbc_textFieldReturnReturnDate.gridy = 5;
		panelCirReturnLeft.add(textFieldReturnReturnDate, gbc_textFieldReturnReturnDate);
		textFieldReturnReturnDate.setColumns(10);
		
		JPanel panelReturnTable = new JPanel();
		panelReturnTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCirculationReturn.add(panelReturnTable);
		panelReturnTable.setLayout(new BorderLayout(5, 5));
		
		JButton btnReturnReload = new JButton("Reload");
		btnReturnReload.setMnemonic('R');
		btnReturnReload.setIcon(circulatioIconClass.reloadIcon);
		btnReturnReload.setFont(MainFrame.smallBtnFont);
		btnReturnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				circulationRefreshFields((byte)-3);
			}
		});
		panelReturnTable.add(btnReturnReload, BorderLayout.SOUTH);
		
		JScrollPane scrollPaneReturn = new JScrollPane();
		panelReturnTable.add(scrollPaneReturn, BorderLayout.CENTER);
		
		tableReturn = new JTable();
		tableReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResultSet rst = TableMethods.selectTable(tableReturn,  "return", "issueno",0);
				try {
					while (rst.next()) {
						
						textFieldReturnIssueNo.setText(rst.getString("issueno"));
						textFieldRetAccNum.setText(rst.getString("accno"));
						textFieldRetReaderID.setText(rst.getString("readerid"));
						textFieldReturnReturnDate.setText(rst.getString("returndate"));
					}
					rst.close();
					TableMethods.closeConnections();
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Table selection failed");
				}
			}
		});
		scrollPaneReturn.setViewportView(tableReturn);
		
		JPanel panelReturnSearch = new JPanel();
		panelReturnSearch.setBorder(new EmptyBorder(3, 20, 3, 80));
		panelReturnTable.add(panelReturnSearch, BorderLayout.NORTH);
		GridBagLayout gbl_panelReturnSearch = new GridBagLayout();
		gbl_panelReturnSearch.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelReturnSearch.rowHeights = new int[]{0, 0};
		gbl_panelReturnSearch.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelReturnSearch.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelReturnSearch.setLayout(gbl_panelReturnSearch);
		
		JLabel lblReturnSearch = new JLabel("Search : ");
		lblReturnSearch.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblReturnSearch = new GridBagConstraints();
		gbc_lblReturnSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblReturnSearch.gridx = 0;
		gbc_lblReturnSearch.gridy = 0;
		panelReturnSearch.add(lblReturnSearch, gbc_lblReturnSearch);
		
		textFieldReturnSearch = new JTextField();
		textFieldReturnSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				TableMethods.searchTable("return", tableReturn, textFieldReturnSearch, "issueno","accno","readerid","returndate");
				tableReturn.getTableHeader().setFont(MainFrame.columnFont);
			}
		});
		textFieldReturnSearch.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldReturnSearch = new GridBagConstraints();
		gbc_textFieldReturnSearch.ipady = 6;
		gbc_textFieldReturnSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldReturnSearch.gridx = 2;
		gbc_textFieldReturnSearch.gridy = 0;
		panelReturnSearch.add(textFieldReturnSearch, gbc_textFieldReturnSearch);
		textFieldReturnSearch.setColumns(10);
		
		
		TableMethods.loadTable(tableIssue, "*","issue");
		TableMethods.loadTable(tableIssueDet, "*","issuedetail");
		TableMethods.loadTable(tableReturn, "*","return");
		setCirculationPanel();

	}
	
	
	
	public void circulationModifyButtonHandler()
	{
		JOptionPane.showMessageDialog(null, "Nothing to Modify");
	}
	
	
	public void circulationDeleteBtnHandler()
	{
		if(MainFrame.isCirculationPanelOn)
		{
			int s = tabbedPaneCirculation.getSelectedIndex();
			//JOptionPane.showMessageDialog(null, s);
			
			switch(s)
			{
				case 0:
						
						if(textFieldIssueIssueNo.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Please enter an issue number to delete.","Empty Field", JOptionPane.WARNING_MESSAGE);
						}
						else if(!TableMethods.isPresent("return","issueno",textFieldIssueIssueNo.getText(),"OR"))
						{
							JOptionPane.showMessageDialog(null, "Issue data can not be deleted.\nThe material is not returned yet !","Data May Lost", JOptionPane.WARNING_MESSAGE);
						}
						else {
							
							int op = JOptionPane.showConfirmDialog(null, "Are you sure ?\nIt will delete all data of this issue,", "Confirmatio", JOptionPane.YES_NO_OPTION);
							if(op==0)
							{
								TableMethods.deleteData("issue", "issueno", textFieldIssueIssueNo.getText());
								TableMethods.deleteData("issuedetail", "issuenumber", textFieldIssueIssueNo.getText());
								TableMethods.deleteData("return", "issueno", textFieldIssueIssueNo.getText());
								
								TableMethods.loadTable(tableIssue, "*","issue");
								circulationRefreshFields((byte)0);
							}
									
						}
						
						break;
						
				case 1:
				case 2:
					JOptionPane.showMessageDialog(null, "Please delete from issue tab.");
				break;
				
			}
			
		}
	}
	
	public void setCirculationPanel()
	{
		tableIssue.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableIssue.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableIssue.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableIssue.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableIssue.getColumnModel().getColumn(3).setPreferredWidth(250);
	}
	
	
	public void circulationRefreshFields( byte s)
	{
		if(s<0)
			s = (byte) tabbedPaneCirculation.getSelectedIndex();
		
		
		switch(s)
		{
			case 0:
				textFieldIssueIssueDate.setText("");
				textFieldIssueSearch.setText("");
				textFieldIssueRetDate.setText("");
				textFieldIssueReaderID.setText("");
				textFieldIssueIssueNo.setText("");
				TableMethods.loadTable(tableIssue, "*","issue");
				setCirculationPanel();
				break;
			case 1:
				
				textFieldIssueDetAccNumber.setText("");
				textFieldIssueDetIssueNum.setText("");
				textFieldIssueDetSearch.setText("");
				TableMethods.loadTable(tableIssueDet, "*","issuedetail");
				
				break;
			case 2: 
				textFieldRetAccNum.setText("");
				textFieldRetReaderID.setText("");
				textFieldReturnIssueNo.setText("");
				textFieldReturnReturnDate.setText("");
				textFieldReturnSearch.setText("");
				TableMethods.loadTable(tableReturn, "*","return");
				setCirculationPanel();
				break;
		}
	}
	
	
	public void circulationAddBtnHandler()
	{
		int s = tabbedPaneCirculation.getSelectedIndex();
		boolean isRedr = false,isAcccNum = false, isIssueNo = false;
		
		isRedr = TableMethods.isPresent("readers", "readerid", textFieldIssueReaderID.getText(), "OR");
		isAcccNum = TableMethods.isPresent("accesion", "accno", textFieldIssueDetAccNumber.getText(), "OR");
		isIssueNo = TableMethods.isPresent("issue", "issueno", textFieldIssueDetIssueNum.getText(), "OR");
		
		
		switch (s) {
		case 0:
		
			
			if(!checkCirFields())
			{
				JOptionPane.showMessageDialog(null, "Please give all necessary information","Empty Field", JOptionPane.ERROR_MESSAGE);
			}
			
			else {
				if(!isRedr)
				{
					isRedr = TableMethods.isPresent("readers", "readerid", textFieldIssueReaderID.getText(), "OR");
					JOptionPane.showMessageDialog(null, "Reader not found !", "Data Not Found", JOptionPane.WARNING_MESSAGE);
				}
				else if(TableMethods.isPresent("issue", "issueno", textFieldIssueIssueNo.getText(),"OR"))
				{
					JOptionPane.showMessageDialog(null, "The issue number is present.\nPlease try another one.","Duplicate Value",JOptionPane.WARNING_MESSAGE);
				}
				else {
					TableMethods.insertData("issue", "issueno", "issuedate", "readerid","proposedreturndate",textFieldIssueIssueNo.getText(), textFieldIssueIssueDate.getText(), textFieldIssueReaderID.getText(), textFieldIssueRetDate.getText());
					TableMethods.loadTable(tableIssue, "*","issue");
					setCirculationPanel();
					circulationRefreshFields((byte)0);
					
				}
			}
			break;
			
		case 1:
		
			isAcccNum = TableMethods.isPresent("accesion", "accno", textFieldIssueDetAccNumber.getText(), "OR");
			isIssueNo = TableMethods.isPresent("issue", "issueno", textFieldIssueDetIssueNum.getText(), "OR");
			
			if(!isAcccNum)
			{
				JOptionPane.showMessageDialog(null, "Accession Number not found !", "Data Not Found", JOptionPane.WARNING_MESSAGE);
			}
			else if(!isIssueNo)
			{
				JOptionPane.showMessageDialog(null, " Issue Number not found !", "Data Not Found", JOptionPane.WARNING_MESSAGE);
			}
			else {
				
				if(TableMethods.isPresent("accesion", "status", "IN","accno",textFieldIssueDetAccNumber.getText() ,"AND"))
				{
					TableMethods.insertData("issuedetail", "issuenumber", "accno" ,textFieldIssueDetIssueNum.getText(),textFieldIssueDetAccNumber.getText());
					TableMethods.loadTable(tableIssueDet, "*","issuedetail");
					TableMethods.updateData("accesion", "status", "OUT", "accno", textFieldIssueDetAccNumber.getText());
					circulationRefreshFields((byte)1);
				}
				else{
					JOptionPane.showMessageDialog(null, "Matterial not available.","Try Another",JOptionPane.WARNING_MESSAGE);	
				}
			}
			
			
			break;
			
		case 2:
			
			isRedr = TableMethods.isPresent("readers", "readerid", textFieldRetReaderID.getText(), "OR");
			isAcccNum = TableMethods.isPresent("accesion", "accno", textFieldRetAccNum.getText(), "OR");
			isIssueNo = TableMethods.isPresent("issue", "issueno", textFieldReturnIssueNo.getText(), "OR");
	
			if(!isRedr)
			{
				JOptionPane.showMessageDialog(null, "Reader not found !", "Data Not Found", JOptionPane.WARNING_MESSAGE);
			}
			else if(!isAcccNum) {
				
			JOptionPane.showMessageDialog(null, "Wrong Accession Number. \nAccession number not found !", "Data Not Found", JOptionPane.WARNING_MESSAGE);
			}
			else if(!isIssueNo)
			{
				JOptionPane.showMessageDialog(null, "Wrong Issue Number. \nIssue number not found !", "Data Not Found", JOptionPane.WARNING_MESSAGE);
			}
			else {
				
				TableMethods.insertData("return", "issueno", "accno", "readerid","returndate",textFieldReturnIssueNo.getText(), textFieldRetAccNum.getText(), textFieldRetReaderID.getText(), textFieldReturnReturnDate.getText());
				TableMethods.loadTable(tableReturn, "*","return");
				setCirculationPanel();
				TableMethods.updateData("accesion", "status", "IN", "accno", textFieldRetAccNum.getText());
				circulationRefreshFields((byte)2);
			}
			
		}
	}
	
	public boolean checkCirFields()
	{
		
		int s = tabbedPaneCirculation.getSelectedIndex();
		switch(s)
		{
			case 0:
				if(textFieldIssueReaderID.getText().equals("") || textFieldIssueIssueDate.getText().equals("")||textFieldIssueIssueNo.getText().equals("") || textFieldIssueRetDate.getText().equals("")){
					return false;
				}
				break;
			case 1:
				if(textFieldIssueDetAccNumber.getText().equals("")||textFieldIssueDetIssueNum.getText().equals(""))
					return false;
				break;
			case 2:
				if(textFieldRetAccNum.getText().equals("") || textFieldRetReaderID.getText().equals("")||textFieldReturnIssueNo.getText().equals("")||textFieldReturnReturnDate.getText().equals(""))
					return false;
				break;
		}
		return true;
	}
	
}
