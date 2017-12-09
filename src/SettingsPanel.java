import javax.lang.model.element.Element;
import javax.security.sasl.AuthorizeCallback;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.border.EtchedBorder;
import javax.swing.JTabbedPane;

import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.text.TabExpander;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.rmi.server.LoaderHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale.Category;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class SettingsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JTabbedPane tabbedPane;
	JTextField textFieldEmpId;
	JTextArea textAreaCatNote ;
	JPasswordField passwordFieldEmPass;
	JPasswordField passwordFieldEmpConPass;
	JTable tableEmployee;
	JTextField textFieldMatInfoSearch;
	JTextField textFieldMatID;
	JTextField textFieldMatInfoTitle;
	JTextField textFieldMatInfoPublisher;
	JTextField textFieldMatInfoEdition;
	JTextField textFieldPublishingYear;
	JTextField textFieldCategoryID;
	JTextField textFieldMatInfoSummary;
	JTextField textFieldTypeID;
	JTextField textFieldMatTypeSearch;
	JTextField textFieldMatTypeId;
	JTextField textfieldMatTypeTypeTitle;
	JTextField textFieldReadersSearch;
	JTextField textFieldMatInfoCallNo;
	JTextField textFieldReaderID;
	JTextField textFieldGroupID;
	JTextField textFieldCategorySearch;
	JTable tableCategory;
	JTextField textFieldCatID;
	JTextField textFieldCategoryTitle;
	JTextField textFieldSearchAuthors;
	JTable tableAuthors;
	JTextField textFieldAuthorMatID;
	JTextField textFieldAurhorName;
	private JTable tableMatInfo;
	private JTable tableReaders;
	private JTable tableMatType;
	IconClass settingIconClass = new IconClass();
	
	
	public SettingsPanel() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(10, 10));
		
		JLabel lblSettings = new JLabel("SETTINGS");
		lblSettings.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSettings, BorderLayout.NORTH);
		
		JPanel panelSettingButtons = new JPanel();
		panelSettingButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panelSettingButtons, BorderLayout.SOUTH);
		
		JButton rocessing = new JButton("Process");
		rocessing.setMnemonic('P');
		rocessing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.showPanel(MainFrame.LIBRARY_PROCESSING_PANEL_STRING);
			}
		});
		rocessing.setIcon(settingIconClass.acquisitionIcon);
		rocessing.setFont(MainFrame.buttonTextFont);
		panelSettingButtons.add(rocessing);
		
		JButton btnCirculation = new JButton("Circulation");
		btnCirculation.setMnemonic('C');
		btnCirculation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.showPanel(MainFrame.CIRCULATION_PANEL_STRING);
			}
		});
		btnCirculation.setIcon(settingIconClass.circulationIcon);
		btnCirculation.setFont(MainFrame.buttonTextFont);
		panelSettingButtons.add(btnCirculation);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setMnemonic('A');
		btnAdd.setIcon(settingIconClass.insertIcon);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addButtonHandler();
			}
		});
		btnAdd.setFont(MainFrame.buttonTextFont);
		panelSettingButtons.add(btnAdd);
		
		JButton btnModify = new JButton("Modify");
		btnModify.setMnemonic('M');
		btnModify.setIcon(settingIconClass.editIcon);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modifyButtonHandler();
			}
		});
		btnModify.setFont(MainFrame.buttonTextFont);
		panelSettingButtons.add(btnModify);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setMnemonic('D');
		btnDelete.setIcon(settingIconClass.deleteIcon);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteButtonHandler();
			}
		});
		btnDelete.setFont(MainFrame.buttonTextFont);
		panelSettingButtons.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setMnemonic('N');
		btnCancel.setIcon(settingIconClass.cancelIcon);
		btnCancel.setFont(MainFrame.buttonTextFont);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settingPanelRefreshFields((byte)-1);
			}
		});
		panelSettingButtons.add(btnCancel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				//JOptionPane.showMessageDialog(null, tabbedPane.getSelectedIndex());
			}
		});
		tabbedPane.setFont(new Font("Cambria Math", Font.BOLD, 18));
		add(tabbedPane, BorderLayout.CENTER);
		//scrollPane.setViewportView(tableEmployee);
		
		JPanel panelSettingMatInfo = new JPanel();
		tabbedPane.addTab("Material Info", null, panelSettingMatInfo, "Use this tab to manage material informations.");
		panelSettingMatInfo.setLayout(new GridLayout(0, 2, 20, 0));
		
		JPanel panelSetMatInfoLeft = new JPanel();
		panelSetMatInfoLeft.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSettingMatInfo.add(panelSetMatInfoLeft);
		GridBagLayout gbl_panelSetMatInfoLeft = new GridBagLayout();
		gbl_panelSetMatInfoLeft.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelSetMatInfoLeft.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelSetMatInfoLeft.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelSetMatInfoLeft.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelSetMatInfoLeft.setLayout(gbl_panelSetMatInfoLeft);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 0;
		panelSetMatInfoLeft.add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblMatInfoMatID = new JLabel("Material ID : ");
		
		GridBagConstraints gbc_lblGroupID = new GridBagConstraints();
		gbc_lblGroupID.anchor = GridBagConstraints.EAST;
		lblMatInfoMatID.setFont(MainFrame.levelFont);
		gbc_lblGroupID.insets = new Insets(0, 0, 5, 5);
		gbc_lblGroupID.gridx = 0;
		gbc_lblGroupID.gridy = 4;
		panelSetMatInfoLeft.add(lblMatInfoMatID, gbc_lblGroupID);
		
		textFieldMatID = new JTextField();
		textFieldMatID.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldMatID = new GridBagConstraints();
		gbc_textFieldMatID.ipady = 6;
		gbc_textFieldMatID.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMatID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMatID.gridx = 3;
		gbc_textFieldMatID.gridy = 4;
		panelSetMatInfoLeft.add(textFieldMatID, gbc_textFieldMatID);
		textFieldMatID.setColumns(10);
		
		JLabel lblMatInfoCallNo = new JLabel("Call No. : ");
		lblMatInfoCallNo.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblMatInfoCallNo = new GridBagConstraints();
		gbc_lblMatInfoCallNo.anchor = GridBagConstraints.EAST;
		gbc_lblMatInfoCallNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatInfoCallNo.gridx = 0;
		gbc_lblMatInfoCallNo.gridy = 5;
		panelSetMatInfoLeft.add(lblMatInfoCallNo, gbc_lblMatInfoCallNo);
		
		textFieldMatInfoCallNo = new JTextField();
		textFieldMatInfoCallNo.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldMatInfoCallNo = new GridBagConstraints();
		gbc_textFieldMatInfoCallNo.ipady = 6;
		gbc_textFieldMatInfoCallNo.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMatInfoCallNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMatInfoCallNo.gridx = 3;
		gbc_textFieldMatInfoCallNo.gridy = 5;
		panelSetMatInfoLeft.add(textFieldMatInfoCallNo, gbc_textFieldMatInfoCallNo);
		textFieldMatInfoCallNo.setColumns(10);
		
		JLabel lblMatInfoTitle = new JLabel("Title : ");
		lblMatInfoTitle.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblMatInfoTitle = new GridBagConstraints();
		gbc_lblMatInfoTitle.anchor = GridBagConstraints.EAST;
		gbc_lblMatInfoTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatInfoTitle.gridx = 0;
		gbc_lblMatInfoTitle.gridy = 6;
		panelSetMatInfoLeft.add(lblMatInfoTitle, gbc_lblMatInfoTitle);
		
		textFieldMatInfoTitle = new JTextField();
		textFieldMatInfoTitle.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldMatInfoTitle = new GridBagConstraints();
		gbc_textFieldMatInfoTitle.ipady = 6;
		gbc_textFieldMatInfoTitle.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMatInfoTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMatInfoTitle.gridx = 3;
		gbc_textFieldMatInfoTitle.gridy = 6;
		panelSetMatInfoLeft.add(textFieldMatInfoTitle, gbc_textFieldMatInfoTitle);
		textFieldMatInfoTitle.setColumns(10);
		
		JLabel lblMatInfoPublisher = new JLabel("Publisher : ");
		lblMatInfoPublisher.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblMatInfoPublisher = new GridBagConstraints();
		gbc_lblMatInfoPublisher.anchor = GridBagConstraints.EAST;
		gbc_lblMatInfoPublisher.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatInfoPublisher.gridx = 0;
		gbc_lblMatInfoPublisher.gridy = 7;
		panelSetMatInfoLeft.add(lblMatInfoPublisher, gbc_lblMatInfoPublisher);
		
		textFieldMatInfoPublisher = new JTextField();
		textFieldMatInfoPublisher.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldMatInfoPublisher = new GridBagConstraints();
		gbc_textFieldMatInfoPublisher.ipady = 6;
		gbc_textFieldMatInfoPublisher.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMatInfoPublisher.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMatInfoPublisher.gridx = 3;
		gbc_textFieldMatInfoPublisher.gridy = 7;
		panelSetMatInfoLeft.add(textFieldMatInfoPublisher, gbc_textFieldMatInfoPublisher);
		textFieldMatInfoPublisher.setColumns(10);
		
		JLabel lblMatInfoEdition = new JLabel("Edition : ");
		lblMatInfoEdition.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblMatInfoEdition = new GridBagConstraints();
		gbc_lblMatInfoEdition.anchor = GridBagConstraints.EAST;
		gbc_lblMatInfoEdition.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatInfoEdition.gridx = 0;
		gbc_lblMatInfoEdition.gridy = 8;
		panelSetMatInfoLeft.add(lblMatInfoEdition, gbc_lblMatInfoEdition);
		
		textFieldMatInfoEdition = new JTextField();
		textFieldMatInfoEdition.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldMatInfoEdition = new GridBagConstraints();
		gbc_textFieldMatInfoEdition.ipady = 6;
		gbc_textFieldMatInfoEdition.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMatInfoEdition.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMatInfoEdition.gridx = 3;
		gbc_textFieldMatInfoEdition.gridy = 8;
		panelSetMatInfoLeft.add(textFieldMatInfoEdition, gbc_textFieldMatInfoEdition);
		textFieldMatInfoEdition.setColumns(10);
		
		JLabel lblPublishingYear = new JLabel("Publishing Year : ");
		lblPublishingYear.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblPublishingYear = new GridBagConstraints();
		gbc_lblPublishingYear.anchor = GridBagConstraints.EAST;
		gbc_lblPublishingYear.insets = new Insets(0, 0, 5, 5);
		gbc_lblPublishingYear.gridx = 0;
		gbc_lblPublishingYear.gridy = 9;
		panelSetMatInfoLeft.add(lblPublishingYear, gbc_lblPublishingYear);
		
		textFieldPublishingYear = new JTextField();
		textFieldPublishingYear.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldPublishingYear = new GridBagConstraints();
		gbc_textFieldPublishingYear.ipady = 6;
		gbc_textFieldPublishingYear.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPublishingYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPublishingYear.gridx = 3;
		gbc_textFieldPublishingYear.gridy = 9;
		panelSetMatInfoLeft.add(textFieldPublishingYear, gbc_textFieldPublishingYear);
		textFieldPublishingYear.setColumns(10);
		
		JLabel lblMatInfoCatId = new JLabel("Category ID : ");
		lblMatInfoCatId.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblMatInfoCatId = new GridBagConstraints();
		gbc_lblMatInfoCatId.anchor = GridBagConstraints.EAST;
		gbc_lblMatInfoCatId.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatInfoCatId.gridx = 0;
		gbc_lblMatInfoCatId.gridy = 10;
		panelSetMatInfoLeft.add(lblMatInfoCatId, gbc_lblMatInfoCatId);
		
		textFieldCategoryID = new JTextField();
		textFieldCategoryID.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldCategoryID = new GridBagConstraints();
		gbc_textFieldCategoryID.ipady = 6;
		gbc_textFieldCategoryID.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCategoryID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCategoryID.gridx = 3;
		gbc_textFieldCategoryID.gridy = 10;
		panelSetMatInfoLeft.add(textFieldCategoryID, gbc_textFieldCategoryID);
		textFieldCategoryID.setColumns(10);
		
		JLabel lblSummary = new JLabel("Summary : ");
		lblSummary.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblSummary = new GridBagConstraints();
		gbc_lblSummary.anchor = GridBagConstraints.EAST;
		gbc_lblSummary.insets = new Insets(0, 0, 5, 5);
		gbc_lblSummary.gridx = 0;
		gbc_lblSummary.gridy = 11;
		panelSetMatInfoLeft.add(lblSummary, gbc_lblSummary);
		
		textFieldMatInfoSummary = new JTextField();
		textFieldMatInfoSummary.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldMatInfoSummary = new GridBagConstraints();
		gbc_textFieldMatInfoSummary.ipady = 6;
		gbc_textFieldMatInfoSummary.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMatInfoSummary.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMatInfoSummary.gridx = 3;
		gbc_textFieldMatInfoSummary.gridy = 11;
		panelSetMatInfoLeft.add(textFieldMatInfoSummary, gbc_textFieldMatInfoSummary);
		textFieldMatInfoSummary.setColumns(10);
		
		JLabel lblMatInfoTypeId = new JLabel("Type ID: ");
		lblMatInfoTypeId.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblMatInfoTypeId = new GridBagConstraints();
		gbc_lblMatInfoTypeId.anchor = GridBagConstraints.EAST;
		gbc_lblMatInfoTypeId.insets = new Insets(0, 0, 0, 5);
		gbc_lblMatInfoTypeId.gridx = 0;
		gbc_lblMatInfoTypeId.gridy = 12;
		panelSetMatInfoLeft.add(lblMatInfoTypeId, gbc_lblMatInfoTypeId);
		
		textFieldTypeID = new JTextField();
		textFieldTypeID.setText("");
		textFieldTypeID.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldTypeID = new GridBagConstraints();
		gbc_textFieldTypeID.ipady = 6;
		gbc_textFieldTypeID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTypeID.gridx = 3;
		gbc_textFieldTypeID.gridy = 12;
		panelSetMatInfoLeft.add(textFieldTypeID, gbc_textFieldTypeID);
		textFieldTypeID.setColumns(10);
		
		JPanel panelSetMatinfoTable = new JPanel();
		
		panelSetMatinfoTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSettingMatInfo.add(panelSetMatinfoTable);
		panelSetMatinfoTable.setLayout(new BorderLayout(0, 5));
		
		JPanel panelMatInfoSEarch = new JPanel();
		panelMatInfoSEarch.setBorder(new EmptyBorder(5, 5, 3, 20));
		panelSetMatinfoTable.add(panelMatInfoSEarch, BorderLayout.NORTH);
		GridBagLayout gbl_panelMatInfoSEarch = new GridBagLayout();
		gbl_panelMatInfoSEarch.columnWidths = new int[]{0, 0, 0};
		gbl_panelMatInfoSEarch.rowHeights = new int[]{0, 0, 0};
		gbl_panelMatInfoSEarch.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelMatInfoSEarch.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelMatInfoSEarch.setLayout(gbl_panelMatInfoSEarch);
		
		JLabel lblSearch = new JLabel("Search: ");
		lblSearch.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 0;
		panelMatInfoSEarch.add(lblSearch, gbc_lblSearch);
		
		textFieldMatInfoSearch = new JTextField();
		textFieldMatInfoSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				TableMethods.searchTable("materialinfo", tableMatInfo, textFieldMatInfoSearch, "matid","title","publisher","edition","publishyear","catid","typeid","callno","summary");
				tableMatInfo.getTableHeader().setFont(MainFrame.columnFont);
				setColumns();
			}
		});
		textFieldMatInfoSearch.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldMatInfoSearch = new GridBagConstraints();
		gbc_textFieldMatInfoSearch.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMatInfoSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMatInfoSearch.gridx = 1;
		gbc_textFieldMatInfoSearch.gridy = 0;
		panelMatInfoSEarch.add(textFieldMatInfoSearch, gbc_textFieldMatInfoSearch);
		textFieldMatInfoSearch.setColumns(10);
		
		JScrollPane scrollPaneMaterialInfo = new JScrollPane(tableMatInfo, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelSetMatinfoTable.add(scrollPaneMaterialInfo, BorderLayout.CENTER);
		
		tableMatInfo = new JTable();
		tableMatInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				ResultSet rst = TableMethods.selectTable(tableMatInfo,  "materialinfo", "matid", 0 );
				
				try {
					while (rst.next()) {
						
						textFieldMatID.setText(rst.getString("matid"));
						textFieldMatInfoCallNo.setText(rst.getString("callno"));
						textFieldMatInfoEdition.setText(rst.getString("edition"));
						textFieldMatInfoPublisher.setText(rst.getString("publisher"));
						textFieldPublishingYear.setText(rst.getString("publishyear"));
						textFieldMatInfoSummary.setText(rst.getString("summary"));
						textFieldMatInfoTitle.setText(rst.getString("title"));
						textFieldTypeID.setText(rst.getString("typeid"));
						textFieldCategoryID.setText(rst.getString("catid"));
					}
					
					rst.close();
					TableMethods.closeConnections();
					
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Table selection failed");
				}
				
			}
		});
		tableMatInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneMaterialInfo.setViewportView(tableMatInfo);
		
		JButton btnMatInfoReload = new JButton("Reload");
		btnMatInfoReload.setMnemonic('R');
	
		btnMatInfoReload.setFont(MainFrame.smallBtnFont);
		btnMatInfoReload.setIcon(settingIconClass.reloadIcon);
		btnMatInfoReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settingPanelRefreshFields((byte)0);
			}
		});
		panelSetMatinfoTable.add(btnMatInfoReload, BorderLayout.SOUTH);
		
		JPanel panelSettingReaders = new JPanel();
		tabbedPane.addTab("Readers", null, panelSettingReaders, "Use this tab to manage readers.");
		panelSettingReaders.setLayout(new GridLayout(1, 0, 20, 10));
		
		JPanel panelReadersField = new JPanel();
		panelReadersField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSettingReaders.add(panelReadersField);
		GridBagLayout gbl_panelReadersField = new GridBagLayout();
		gbl_panelReadersField.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelReadersField.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelReadersField.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelReadersField.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelReadersField.setLayout(gbl_panelReadersField);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 0;
		gbc_verticalStrut_2.gridy = 0;
		panelReadersField.add(verticalStrut_2, gbc_verticalStrut_2);
		
		JLabel lblReaderId = new JLabel("Reader ID: ");
		lblReaderId.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblReaderId = new GridBagConstraints();
		gbc_lblReaderId.insets = new Insets(0, 0, 5, 5);
		gbc_lblReaderId.gridx = 0;
		gbc_lblReaderId.gridy = 2;
		panelReadersField.add(lblReaderId, gbc_lblReaderId);
		
		textFieldReaderID = new JTextField();
		textFieldReaderID.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldReaderID = new GridBagConstraints();
		gbc_textFieldReaderID.ipady = 6;
		gbc_textFieldReaderID.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldReaderID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldReaderID.gridx = 2;
		gbc_textFieldReaderID.gridy = 2;
		panelReadersField.add(textFieldReaderID, gbc_textFieldReaderID);
		textFieldReaderID.setColumns(10);
		
		JLabel lblGroupID = new JLabel("Group ID : ");
		lblGroupID.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblGroupID1 = new GridBagConstraints();
		gbc_lblGroupID1.insets = new Insets(0, 0, 0, 5);
		gbc_lblGroupID1.gridx = 0;
		gbc_lblGroupID1.gridy = 3;
		panelReadersField.add(lblGroupID, gbc_lblGroupID1);
		
		textFieldGroupID = new JTextField();
		textFieldGroupID.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldGroupID = new GridBagConstraints();
		gbc_textFieldGroupID.ipady = 6;
		gbc_textFieldGroupID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldGroupID.gridx = 2;
		gbc_textFieldGroupID.gridy = 3;
		panelReadersField.add(textFieldGroupID, gbc_textFieldGroupID);
		textFieldGroupID.setColumns(10);
		
		JPanel panelReadersTable = new JPanel();
		panelReadersTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSettingReaders.add(panelReadersTable);
		panelReadersTable.setLayout(new BorderLayout(0, 5));
		
		JButton btnReadersReload = new JButton("Reload");
		btnReadersReload.setMnemonic('R');
		btnReadersReload.setIcon(settingIconClass.reloadIcon);
		btnReadersReload.setFont(MainFrame.smallBtnFont);
		btnReadersReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settingPanelRefreshFields((byte)1);
			}
		});
		panelReadersTable.add(btnReadersReload, BorderLayout.SOUTH);
		
		JPanel panelReadersSearch = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelReadersSearch.getLayout();
		flowLayout_1.setHgap(10);
		panelReadersSearch.setBorder(new EmptyBorder(5, 10, 5, 0));
		panelReadersTable.add(panelReadersSearch, BorderLayout.NORTH);
		
		JLabel lblReadersSearch = new JLabel("Search : ");
		lblReadersSearch.setFont(MainFrame.levelFont);
		panelReadersSearch.add(lblReadersSearch);
		
		textFieldReadersSearch = new JTextField();
		textFieldReadersSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				TableMethods.searchTable("readers", tableReaders, textFieldReadersSearch, "readerid","groupid");
				tableReaders.getTableHeader().setFont(MainFrame.columnFont);
			}
		});
		textFieldReadersSearch.setFont(MainFrame.fieldFont);
		panelReadersSearch.add(textFieldReadersSearch);
		textFieldReadersSearch.setColumns(35);
		JScrollPane scrollPaneReaders = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelReadersTable.add(scrollPaneReaders, BorderLayout.CENTER);
		
		tableReaders = new JTable();
		tableReaders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResultSet rst = TableMethods.selectTable(tableReaders,  "readers", "readerid",0);
				
				try {
					while (rst.next()) {
						textFieldReaderID.setText(rst.getString("readerid"));
						textFieldGroupID.setText(rst.getString("groupid"));
					}
					rst.close();
					TableMethods.closeConnections();
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Table selection failed");
				}
				
			}
		});
		scrollPaneReaders.setViewportView(tableReaders);
		
		JPanel panelSettingAuthor = new JPanel();
		tabbedPane.addTab("Authors", null, panelSettingAuthor, "Use this tab to manage authors.");
		panelSettingAuthor.setLayout(new GridLayout(0, 2, 20, 5));
		
		JPanel panelAuthorsField = new JPanel();
		panelAuthorsField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSettingAuthor.add(panelAuthorsField);
		GridBagLayout gbl_panelAuthorsField = new GridBagLayout();
		gbl_panelAuthorsField.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelAuthorsField.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelAuthorsField.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelAuthorsField.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAuthorsField.setLayout(gbl_panelAuthorsField);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_4 = new GridBagConstraints();
		gbc_verticalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_4.gridx = 0;
		gbc_verticalStrut_4.gridy = 0;
		panelAuthorsField.add(verticalStrut_4, gbc_verticalStrut_4);
		
		JLabel lblAuthorMatID = new JLabel("Material ID : ");
		lblAuthorMatID.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAuthorMatID = new GridBagConstraints();
		gbc_lblAuthorMatID.insets = new Insets(0, 0, 5, 5);
		gbc_lblAuthorMatID.gridx = 0;
		gbc_lblAuthorMatID.gridy = 1;
		panelAuthorsField.add(lblAuthorMatID, gbc_lblAuthorMatID);
		
		textFieldAuthorMatID = new JTextField();
		textFieldAuthorMatID.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldAuthorMatID = new GridBagConstraints();
		gbc_textFieldAuthorMatID.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAuthorMatID.ipady = 6;
		gbc_textFieldAuthorMatID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAuthorMatID.gridx = 2;
		gbc_textFieldAuthorMatID.gridy = 1;
		panelAuthorsField.add(textFieldAuthorMatID, gbc_textFieldAuthorMatID);
		textFieldAuthorMatID.setColumns(10);
		
		JLabel lblAuthorName = new JLabel("Author Name : ");
		lblAuthorName.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAuthorName = new GridBagConstraints();
		gbc_lblAuthorName.insets = new Insets(0, 0, 0, 5);
		gbc_lblAuthorName.gridx = 0;
		gbc_lblAuthorName.gridy = 2;
		panelAuthorsField.add(lblAuthorName, gbc_lblAuthorName);
		
		textFieldAurhorName = new JTextField();
		textFieldAurhorName.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldAurhorName = new GridBagConstraints();
		gbc_textFieldAurhorName.ipady = 6;
		gbc_textFieldAurhorName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAurhorName.gridx = 2;
		gbc_textFieldAurhorName.gridy = 2;
		panelAuthorsField.add(textFieldAurhorName, gbc_textFieldAurhorName);
		textFieldAurhorName.setColumns(10);
		
		JPanel panelAuthorTable = new JPanel();
		panelAuthorTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSettingAuthor.add(panelAuthorTable);
		panelAuthorTable.setLayout(new BorderLayout(0, 5));
		
		JPanel panelAuthorsSearch = new JPanel();
		panelAuthorsSearch.setBorder(new EmptyBorder(5, 5, 5, 0));
		panelAuthorTable.add(panelAuthorsSearch, BorderLayout.NORTH);
		
		JLabel lblSearchAuthors = new JLabel("Search : ");
		lblSearchAuthors.setFont(MainFrame.levelFont);
		panelAuthorsSearch.add(lblSearchAuthors);
		
		textFieldSearchAuthors = new JTextField();
		textFieldSearchAuthors.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				TableMethods.searchTable("authors", tableAuthors, textFieldSearchAuthors, "matid","authorname");
				tableAuthors.getTableHeader().setFont(MainFrame.columnFont);
			}
		});
		textFieldSearchAuthors.setFont(MainFrame.fieldFont);
		panelAuthorsSearch.add(textFieldSearchAuthors);
		textFieldSearchAuthors.setColumns(35);
		
		JButton btnAuthorsReload = new JButton("Reload");
		btnAuthorsReload.setMnemonic('R');
		btnAuthorsReload.setIcon(settingIconClass.reloadIcon);
		btnAuthorsReload.setFont(MainFrame.smallBtnFont);
		btnAuthorsReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settingPanelRefreshFields((byte)2);
			}
		});
		
		panelAuthorTable.add(btnAuthorsReload, BorderLayout.SOUTH);
		
		JScrollPane scrollPaneAuthorsSearch = new JScrollPane();
		panelAuthorTable.add(scrollPaneAuthorsSearch, BorderLayout.CENTER);
		
		tableAuthors = new JTable();
		tableAuthors.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResultSet rst = TableMethods.selectTable(tableAuthors,  "authors", "authorname",1);
				try {
					while (rst.next()) {
						
						textFieldAurhorName.setText(rst.getString("authorname"));
						textFieldAuthorMatID.setText(rst.getString("matid"));
					}
					rst.close();
					TableMethods.closeConnections();
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Table selection failed");
				}
			}
		});
		scrollPaneAuthorsSearch.setViewportView(tableAuthors);
		
		JPanel panelSettingLogin = new JPanel();
		tabbedPane.addTab("Login", null, panelSettingLogin, "Use this tab to manage users.");
		panelSettingLogin.setLayout(new GridLayout(0, 2, 20, 10));
		
		JPanel panelSettingFields = new JPanel();
		panelSettingFields.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSettingLogin.add(panelSettingFields);
		panelSettingFields.setLayout(new BorderLayout(10, 5));
		
		JLabel lblOperations = new JLabel("OPERATIONS");
		lblOperations.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperations.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelSettingFields.add(lblOperations, BorderLayout.NORTH);
		
		JPanel panelSettLoginComponents = new JPanel();
		panelSettLoginComponents.setBorder(new EmptyBorder(20, 20, 10, 20));
		panelSettingFields.add(panelSettLoginComponents, BorderLayout.CENTER);
		GridBagLayout gbl_panelSettLoginComponents = new GridBagLayout();
		gbl_panelSettLoginComponents.columnWidths = new int[]{191, 1, 0};
		gbl_panelSettLoginComponents.rowHeights = new int[]{1, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelSettLoginComponents.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelSettLoginComponents.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelSettLoginComponents.setLayout(gbl_panelSettLoginComponents);
		
		JLabel lblEmpId = new JLabel("Employee ID");
		lblEmpId.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblEmpId = new GridBagConstraints();
		gbc_lblEmpId.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpId.gridx = 0;
		gbc_lblEmpId.gridy = 1;
		panelSettLoginComponents.add(lblEmpId, gbc_lblEmpId);
		
		textFieldEmpId = new JTextField();
		textFieldEmpId.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldEmpId = new GridBagConstraints();
		gbc_textFieldEmpId.ipady = 7;
		gbc_textFieldEmpId.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmpId.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmpId.gridx = 0;
		gbc_textFieldEmpId.gridy = 2;
		panelSettLoginComponents.add(textFieldEmpId, gbc_textFieldEmpId);
		textFieldEmpId.setColumns(10);
		
		JLabel lblEmpPass = new JLabel("Employee Password");
		lblEmpPass.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblEmpPass = new GridBagConstraints();
		gbc_lblEmpPass.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpPass.gridx = 0;
		gbc_lblEmpPass.gridy = 3;
		panelSettLoginComponents.add(lblEmpPass, gbc_lblEmpPass);
		
		passwordFieldEmPass = new JPasswordField();
		passwordFieldEmPass.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_passwordFieldEmPass = new GridBagConstraints();
		gbc_passwordFieldEmPass.ipady = 7;
		gbc_passwordFieldEmPass.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldEmPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldEmPass.gridx = 0;
		gbc_passwordFieldEmPass.gridy = 4;
		panelSettLoginComponents.add(passwordFieldEmPass, gbc_passwordFieldEmPass);
		
		JLabel lblconfirmEmpId = new JLabel("Confirm Employee Password");
		lblconfirmEmpId.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblconfirmEmpId = new GridBagConstraints();
		gbc_lblconfirmEmpId.insets = new Insets(0, 0, 5, 5);
		gbc_lblconfirmEmpId.gridx = 0;
		gbc_lblconfirmEmpId.gridy = 5;
		panelSettLoginComponents.add(lblconfirmEmpId, gbc_lblconfirmEmpId);
		
		passwordFieldEmpConPass = new JPasswordField();
		passwordFieldEmpConPass.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_passwordFieldEmpConPass = new GridBagConstraints();
		gbc_passwordFieldEmpConPass.ipady = 7;
		gbc_passwordFieldEmpConPass.insets = new Insets(0, 0, 0, 5);
		gbc_passwordFieldEmpConPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldEmpConPass.gridx = 0;
		gbc_passwordFieldEmpConPass.gridy = 6;
		panelSettLoginComponents.add(passwordFieldEmpConPass, gbc_passwordFieldEmpConPass);
		
		JPanel panelSetLoginTable = new JPanel();
		panelSetLoginTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSettingLogin.add(panelSetLoginTable);
		panelSetLoginTable.setLayout(new BorderLayout(3, 5));
		
		JLabel lblUsers = new JLabel("EMPLOYEE");
		lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelSetLoginTable.add(lblUsers, BorderLayout.NORTH);
		
		tableEmployee = new JTable();
		tableEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResultSet rst = TableMethods.selectTable(tableEmployee,  "login", "empid",0);
				try {
					while (rst.next()) {
						
						textFieldEmpId.setText(rst.getString("empid"));
						passwordFieldEmPass.setText(rst.getString("pass"));
						passwordFieldEmpConPass.setText(rst.getString("pass"));
					}
					rst.close();
					TableMethods.closeConnections();
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Table selection failed");
				}
			}
		});
		panelSetLoginTable.add(tableEmployee, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane(tableEmployee, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//JScrollPane scrollPaneStdTable = new JScrollPane(tableEmployee, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelSetLoginTable.add(scrollPane, BorderLayout.CENTER);
		
		JButton btnSetLoginReload = new JButton("Reload");
		btnSetLoginReload.setMnemonic('R');
		btnSetLoginReload.setIcon(settingIconClass.reloadIcon);
		btnSetLoginReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settingPanelRefreshFields((byte)3);
			}
		});
		btnSetLoginReload.setFont(MainFrame.smallBtnFont);
		panelSetLoginTable.add(btnSetLoginReload, BorderLayout.SOUTH);
		
		JPanel panelSettingMatType = new JPanel();
		tabbedPane.addTab("Material Type", null, panelSettingMatType, null);
		panelSettingMatType.setLayout(new GridLayout(0, 2, 20, 0));
		
		JPanel panelMatTypeFields = new JPanel();
		panelMatTypeFields.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSettingMatType.add(panelMatTypeFields);
		GridBagLayout gbl_panelMatTypeFields = new GridBagLayout();
		gbl_panelMatTypeFields.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelMatTypeFields.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelMatTypeFields.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelMatTypeFields.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMatTypeFields.setLayout(gbl_panelMatTypeFields);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 0;
		gbc_verticalStrut_1.gridy = 0;
		panelMatTypeFields.add(verticalStrut_1, gbc_verticalStrut_1);
		
		JLabel lblTypeId = new JLabel("Type ID : ");
		lblTypeId.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblTypeId = new GridBagConstraints();
		gbc_lblTypeId.anchor = GridBagConstraints.EAST;
		gbc_lblTypeId.insets = new Insets(0, 0, 5, 5);
		gbc_lblTypeId.gridx = 0;
		gbc_lblTypeId.gridy = 2;
		panelMatTypeFields.add(lblTypeId, gbc_lblTypeId);
		
		textFieldMatTypeId = new JTextField();
		textFieldMatTypeId.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldMatTypeId = new GridBagConstraints();
		gbc_textFieldMatTypeId.ipady = 6;
		gbc_textFieldMatTypeId.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMatTypeId.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMatTypeId.gridx = 2;
		gbc_textFieldMatTypeId.gridy = 2;
		panelMatTypeFields.add(textFieldMatTypeId, gbc_textFieldMatTypeId);
		textFieldMatTypeId.setColumns(10);
		
		JLabel lblTypeTitle = new JLabel("Type Title : ");
		lblTypeTitle.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblTypeTitle = new GridBagConstraints();
		gbc_lblTypeTitle.anchor = GridBagConstraints.EAST;
		gbc_lblTypeTitle.insets = new Insets(0, 0, 0, 5);
		gbc_lblTypeTitle.gridx = 0;
		gbc_lblTypeTitle.gridy = 3;
		panelMatTypeFields.add(lblTypeTitle, gbc_lblTypeTitle);
		
		textfieldMatTypeTypeTitle = new JTextField();
		GridBagConstraints gbc_textfieldMatTypeTypeTitle = new GridBagConstraints();
		gbc_textfieldMatTypeTypeTitle.ipady = 6;
		gbc_textfieldMatTypeTypeTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_textfieldMatTypeTypeTitle.gridx = 2;
		gbc_textfieldMatTypeTypeTitle.gridy = 3;
		panelMatTypeFields.add(textfieldMatTypeTypeTitle, gbc_textfieldMatTypeTypeTitle);
		textfieldMatTypeTypeTitle.setColumns(10);
		
		JPanel panelMatTypeTable = new JPanel();
		panelMatTypeTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSettingMatType.add(panelMatTypeTable);
		panelMatTypeTable.setLayout(new BorderLayout(0, 5));
		
		JButton btnReload = new JButton("Reload");
		btnReload.setMnemonic('R');
		btnReload.setIcon(settingIconClass.reloadIcon);
		btnReload.setFont(MainFrame.smallBtnFont);
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settingPanelRefreshFields((byte)4);
			}
		});
		panelMatTypeTable.add(btnReload, BorderLayout.SOUTH);
		
		JPanel panelMatTypeSearch = new JPanel();
		panelMatTypeSearch.setBorder(new EmptyBorder(5, 20, 3, 0));
		FlowLayout flowLayout = (FlowLayout) panelMatTypeSearch.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setHgap(10);
		panelMatTypeTable.add(panelMatTypeSearch, BorderLayout.NORTH);
		
		JLabel lblMatTypeSearch = new JLabel("Search : ");
		lblMatTypeSearch.setFont(MainFrame.levelFont);
		lblMatTypeSearch.setHorizontalAlignment(SwingConstants.LEFT);
		panelMatTypeSearch.add(lblMatTypeSearch);
		
		textFieldMatTypeSearch = new JTextField();
		textFieldMatTypeSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				TableMethods.searchTable("materialtype", tableMatType, textFieldMatTypeSearch, "typeid","typetitle");
				tableMatType.getTableHeader().setFont(MainFrame.columnFont);
			}
		});
		textFieldMatTypeSearch.setFont(MainFrame.fieldFont);
		panelMatTypeSearch.add(textFieldMatTypeSearch);
		textFieldMatTypeSearch.setColumns(35);
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane(tableMatType, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelMatTypeTable.add(scrollPane_1, BorderLayout.CENTER);
		
		tableMatType = new JTable();
		tableMatType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResultSet rst = TableMethods.selectTable(tableMatType,  "materialtype", "typeid",0);
				try {
					while (rst.next()) {
						
						textfieldMatTypeTypeTitle.setText(rst.getString("typetitle"));
						textFieldMatTypeId.setText(rst.getString("typeid"));
					}
					rst.close();
					TableMethods.closeConnections();
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Table selection failed");
				}
			}
		});
		scrollPane_1.setViewportView(tableMatType);
		
		
		
		JPanel panelSettingCategory = new JPanel();
		tabbedPane.addTab("Category", null, panelSettingCategory, "Use  this tab to manage categories.");
		panelSettingCategory.setLayout(new GridLayout(0, 2, 20, 10));
		
		JPanel panelCategoryFields = new JPanel();
		panelCategoryFields.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSettingCategory.add(panelCategoryFields);
		GridBagLayout gbl_panelCategoryFields = new GridBagLayout();
		gbl_panelCategoryFields.columnWidths = new int[]{1, 78, 289, 0};
		gbl_panelCategoryFields.rowHeights = new int[]{1, 20, 20, 0, 0};
		gbl_panelCategoryFields.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelCategoryFields.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelCategoryFields.setLayout(gbl_panelCategoryFields);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_3 = new GridBagConstraints();
		gbc_verticalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_3.gridx = 0;
		gbc_verticalStrut_3.gridy = 0;
		panelCategoryFields.add(verticalStrut_3, gbc_verticalStrut_3);
		
		JLabel lblCategoryID = new JLabel("Category ID : ");
		lblCategoryID.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblCategoryID = new GridBagConstraints();
		gbc_lblCategoryID.anchor = GridBagConstraints.EAST;
		gbc_lblCategoryID.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoryID.gridx = 1;
		gbc_lblCategoryID.gridy = 1;
		panelCategoryFields.add(lblCategoryID, gbc_lblCategoryID);
		
		textFieldCatID = new JTextField();
		textFieldCatID.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldCatID = new GridBagConstraints();
		gbc_textFieldCatID.ipady = 6;
		gbc_textFieldCatID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCatID.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCatID.gridx = 2;
		gbc_textFieldCatID.gridy = 1;
		panelCategoryFields.add(textFieldCatID, gbc_textFieldCatID);
		textFieldCatID.setColumns(10);
		
		JLabel lblCategoryTitle = new JLabel("Category Title : ");
		lblCategoryTitle.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblCategoryTitle = new GridBagConstraints();
		gbc_lblCategoryTitle.anchor = GridBagConstraints.EAST;
		gbc_lblCategoryTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoryTitle.gridx = 1;
		gbc_lblCategoryTitle.gridy = 2;
		panelCategoryFields.add(lblCategoryTitle, gbc_lblCategoryTitle);
		
		textFieldCategoryTitle = new JTextField();
		textFieldCategoryTitle.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldCategoryTitle = new GridBagConstraints();
		gbc_textFieldCategoryTitle.ipady = 6;
		gbc_textFieldCategoryTitle.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCategoryTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCategoryTitle.gridx = 2;
		gbc_textFieldCategoryTitle.gridy = 2;
		panelCategoryFields.add(textFieldCategoryTitle, gbc_textFieldCategoryTitle);
		textFieldCategoryTitle.setColumns(10);
		
		JLabel lblCategoryNote = new JLabel("Category Note : ");
		lblCategoryNote.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblCategoryNote = new GridBagConstraints();
		gbc_lblCategoryNote.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblCategoryNote.insets = new Insets(0, 0, 0, 5);
		gbc_lblCategoryNote.gridx = 1;
		gbc_lblCategoryNote.gridy = 3;
		panelCategoryFields.add(lblCategoryNote, gbc_lblCategoryNote);
		
		textAreaCatNote = new JTextArea();
		textAreaCatNote.setRows(10);
		textAreaCatNote.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textAreaCatNote = new GridBagConstraints();
		gbc_textAreaCatNote.anchor = GridBagConstraints.NORTH;
		gbc_textAreaCatNote.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAreaCatNote.gridx = 2;
		gbc_textAreaCatNote.gridy = 3;
		panelCategoryFields.add(textAreaCatNote, gbc_textAreaCatNote);
		
		JPanel panelCategoryTable = new JPanel();
		panelCategoryTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelSettingCategory.add(panelCategoryTable);
		panelCategoryTable.setLayout(new BorderLayout(0, 5));
		
		JPanel panelCategorySearch = new JPanel();
		panelCategorySearch.setBorder(new EmptyBorder(5, 10, 3, 0));
		FlowLayout flowLayout_2 = (FlowLayout) panelCategorySearch.getLayout();
		flowLayout_2.setHgap(10);
		panelCategoryTable.add(panelCategorySearch, BorderLayout.NORTH);
		
		JLabel lblCategorySearch = new JLabel("Search : ");
		lblCategorySearch.setFont(MainFrame.levelFont);
		lblCategorySearch.setHorizontalAlignment(SwingConstants.LEFT);
		panelCategorySearch.add(lblCategorySearch);
		
		textFieldCategorySearch = new JTextField();
		textFieldCategorySearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				TableMethods.searchTable("category", tableCategory, textFieldCategorySearch, "catid","cattitle","catnote");
				tableCategory.getTableHeader().setFont(MainFrame.columnFont);
				setColumns();
			}
		});
		textFieldCategorySearch.setFont(MainFrame.fieldFont);
		textFieldCategorySearch.setHorizontalAlignment(SwingConstants.LEFT);
		panelCategorySearch.add(textFieldCategorySearch);
		textFieldCategorySearch.setColumns(35);
		
		JScrollPane scrollPaneCatTable = new JScrollPane();
		panelCategoryTable.add(scrollPaneCatTable, BorderLayout.CENTER);
		
		tableCategory = new JTable();
		tableCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResultSet rst = TableMethods.selectTable(tableCategory,  "category", "catid",0);
				
				try {
					while (rst.next()) {
						textFieldCatID.setText(rst.getString("catid"));
						textFieldCategoryTitle.setText(rst.getString("cattitle"));
						textAreaCatNote.setText(rst.getString("catnote"));
					}
					rst.close();
					TableMethods.closeConnections();
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Table category selection failed");
				}
				
			}
		});
		scrollPaneCatTable.setViewportView(tableCategory);
		
		JButton btnCategoryReload = new JButton("Reload");
		btnCategoryReload.setMnemonic('R');
		btnCategoryReload.setIcon(settingIconClass.reloadIcon);
		btnCategoryReload.setFont(MainFrame.smallBtnFont);
		btnCategoryReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settingPanelRefreshFields((byte)5);
			}
		});
		panelCategoryTable.add(btnCategoryReload, BorderLayout.SOUTH);
		
		TableMethods.loadTable(tableEmployee, "empid", "login");
		TableMethods.loadTable(tableAuthors, "*", "authors");
		TableMethods.loadTable(tableCategory, "*", "category");
		TableMethods.loadTable(tableMatInfo, "*", "materialinfo");
		TableMethods.loadTable(tableReaders, "*", "readers");
		TableMethods.loadTable(tableMatType, "*", "materialtype");
		
		
		setColumns();
		
		
	}
	
	public void settingPanelRefreshFields(byte s)
	{
		if(s<0)
			s=(byte)tabbedPane.getSelectedIndex();
		
		switch(s)
		{
			case 0:
				textFieldMatID.setText("");
				textFieldCategoryID.setText("");
				textFieldMatInfoCallNo.setText("");
				textFieldMatInfoEdition.setText("");
				textFieldMatInfoPublisher.setText("");
				textFieldMatInfoSearch.setText("");
				textFieldMatInfoSummary.setText("");
				textFieldMatInfoTitle.setText("");
				textFieldTypeID.setText("");
				textFieldPublishingYear.setText("");
				
				
				TableMethods.loadTable(tableMatInfo, "*","materialinfo");
				setColumns();
				break;
			case 1:
				
				textFieldReaderID.setText("");
				textFieldReadersSearch.setText("");
				textFieldGroupID.setText("");
				TableMethods.loadTable(tableReaders, "*","readers");
				break;
			case 2:
				
				textFieldAurhorName.setText("");
				textFieldAuthorMatID.setText("");
				
				TableMethods.loadTable(tableAuthors, "*", "authors");
				break;
			case 3:
				textFieldEmpId.setText("");
				passwordFieldEmPass.setText("");
				passwordFieldEmpConPass.setText("");
				TableMethods.loadTable(tableEmployee, "empid", "login");
				break;
			case 4:
				textFieldMatTypeId.setText("");
				textfieldMatTypeTypeTitle.setText("");
				textFieldMatTypeSearch.setText("");
				
				TableMethods.loadTable(tableMatType, "*", "materialtype");
				break;
			case 5:
				textFieldCatID.setText("");
				textFieldCategorySearch.setText("");
				textFieldCategoryTitle.setText("");
				textAreaCatNote.setText("");
				TableMethods.loadTable(tableCategory, "*", "category");
				setColumns();
				break;
		}
	}
	
	public void setColumns() //Set column  width
	{
		tableCategory.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableMatInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		tableMatInfo.getColumnModel().getColumn(0).setPreferredWidth(120);
		tableMatInfo.getColumnModel().getColumn(1).setPreferredWidth(400);
		tableMatInfo.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableMatInfo.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableMatInfo.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableMatInfo.getColumnModel().getColumn(5).setPreferredWidth(150);
		tableMatInfo.getColumnModel().getColumn(6).setPreferredWidth(150);
		tableMatInfo.getColumnModel().getColumn(7).setPreferredWidth(400);
		tableMatInfo.getColumnModel().getColumn(8).setPreferredWidth(150);
		
		tableCategory.getColumnModel().getColumn(0).setPreferredWidth(120);
		tableCategory.getColumnModel().getColumn(1).setPreferredWidth(400);
		tableCategory.getColumnModel().getColumn(2).setPreferredWidth(700);
	}
	
	
	public void addButtonHandler()
	{
		if(MainFrame.isSettingPanel)
		{
			int s = tabbedPane.getSelectedIndex();
			
			switch(s)
			{
				case 0:
					if(isEmpty())
						showEmptyMessage();
					else if (!(TableMethods.isPresent("materialtype","typeid",textFieldTypeID.getText(),"OR"))) {
						JOptionPane.showMessageDialog(null, "Type ID is not found.","Data Not Found",JOptionPane.WARNING_MESSAGE);
					}
					else if (!(TableMethods.isPresent("category","catid",textFieldCategoryID.getText(),"OR"))) {
						JOptionPane.showMessageDialog(null, "Category ID is not found.","Data Not Found",JOptionPane.WARNING_MESSAGE);
					}
					else {
							
						TableMethods.insertData("materialinfo", "matid", "title","edition", "publisher","publishyear","callno","catid","summary", "typeid",textFieldMatID.getText(), textFieldMatInfoTitle.getText(),textFieldMatInfoEdition.getText(),textFieldMatInfoPublisher.getText(), textFieldPublishingYear.getText(), textFieldMatInfoCallNo.getText(),textFieldCategoryID.getText(),textFieldMatInfoSummary.getText(), textFieldTypeID.getText());;
						TableMethods.loadTable(tableMatInfo, "*","materialinfo");
						setColumns();
						settingPanelRefreshFields((byte)0);
					}
					break;
				case 1:
					if(isEmpty())
						showEmptyMessage();
					else {
						TableMethods.insertData("readers", "readerid", "groupid", textFieldReaderID.getText(), textFieldGroupID.getText());
						TableMethods.loadTable(tableReaders,"*" , "readers");
						settingPanelRefreshFields((byte)1);
					}
					break;
				case 2:
					if(isEmpty())
						showEmptyMessage();
					else if(TableMethods.isPresent("authors","matid",textFieldAuthorMatID.getText(),"OR"))
					{
						JOptionPane.showMessageDialog(null, "Material ID is not found.","Data Not Found", JOptionPane.WARNING_MESSAGE);
					}
					else {
						TableMethods.insertData("authors", "matid", "authorname", textFieldAuthorMatID.getText(), textFieldAurhorName.getText());
						TableMethods.loadTable(tableAuthors, "*", "authors");
						settingPanelRefreshFields((byte)2);
					}
					break;
				case 3:
					if(isEmpty())
						showEmptyMessage();
					else {
						TableMethods.insertData("login", "empid", "pass", textFieldEmpId.getText(), passwordFieldEmPass.getText());
						TableMethods.loadTable(tableEmployee, "empid", "login");
						settingPanelRefreshFields((byte)3);
					}
					break;
				case 4:
					if(isEmpty())
						showEmptyMessage();
					else {
						TableMethods.insertData("materialtype", "typeid", "typetitle", textFieldMatTypeId.getText(), textfieldMatTypeTypeTitle.getText());
						TableMethods.loadTable(tableMatType, "*", "materialinfo");
						settingPanelRefreshFields((byte)4);

					}
					break;
				case 5:
					if(isEmpty())
						showEmptyMessage();
					else {
						TableMethods.insertData("category", "catid", "cattitle","catnote", textFieldCatID.getText(), textFieldCategoryTitle.getText(), textAreaCatNote.getText());
						TableMethods.loadTable(tableCategory, "*","category");
						settingPanelRefreshFields((byte)5);
						setColumns();
					}
					break;
			}
			
		}
	}
	public void modifyButtonHandler()
	{
		if(MainFrame.isSettingPanel)
		{
			int s = tabbedPane.getSelectedIndex();
			
			switch(s)
			{
				case 0:
						if(isEmpty())
						{
							showEmptyMessage();
						}
						else if (!(TableMethods.isPresent("materialtype","typeid",textFieldTypeID.getText(),"OR"))) {
							JOptionPane.showMessageDialog(null, "Type ID is not found.","Data Not Found",JOptionPane.WARNING_MESSAGE);
						}
						else if (!(TableMethods.isPresent("category","catid",textFieldCategoryID.getText(),"OR"))) {
							JOptionPane.showMessageDialog(null, "Category ID is not found.","Data Not Found",JOptionPane.WARNING_MESSAGE);
						}
						else {
							TableMethods.updateData("materialinfo","title",textFieldMatInfoTitle.getText(), "publisher", textFieldMatInfoPublisher.getText(),"edition",textFieldMatInfoEdition.getText(), "publishyear",textFieldPublishingYear.getText(),"catid",textFieldCategoryID.getText(), "summary",textFieldMatInfoSummary.getText(), "typeid",textFieldTypeID.getText(), "callno", textFieldMatInfoCallNo.getText(), "edition",textFieldMatInfoEdition.getText(),"publishyear",textFieldPublishingYear.getText(),"matid", textFieldMatID.getText());
							TableMethods.loadTable(tableMatInfo, "*","materialinfo");
							settingPanelRefreshFields((byte)0);
							setColumns();
							break;
						}
				case 1:
					if(isEmpty())
						showEmptyMessage();
					else {

						TableMethods.updateData("readers","groupid", textFieldGroupID.getText(),"readerid", textFieldReaderID.getText());;
						TableMethods.loadTable(tableReaders, "*", "readers");
						settingPanelRefreshFields((byte)1);
						break;
					}
				case 2:
					if(isEmpty())
						showEmptyMessage();
					else if(TableMethods.isPresent("authors","matid",textFieldAuthorMatID.getText(),"OR"))
					{
						JOptionPane.showMessageDialog(null, "Material ID is not found.","Data Not Found", JOptionPane.WARNING_MESSAGE);
					}
					else {
						TableMethods.updateData("authors", "matid", textFieldAuthorMatID.getText(), "authorname", textFieldAurhorName.getText(),"authorname", textFieldAurhorName.getText());
						TableMethods.loadTable(tableAuthors,"*", "authors");
						settingPanelRefreshFields((byte)2);
					}
					break;
				case 3:
					if(isEmpty())
						showEmptyMessage();
					if((textFieldEmpId.getText().equals(MainFrame.loggedInAs)) || MainFrame.loggedInAs.equals("DevCSECU"))
					{
						TableMethods.updateData("login","empid", textFieldEmpId.getText(),"pass", passwordFieldEmPass.getText(), "empid", textFieldEmpId.getText());;
						TableMethods.loadTable(tableEmployee, "empid", "login");
						settingPanelRefreshFields((byte)3);
					}
					else {
						JOptionPane.showMessageDialog(null, "You cannot change others password", "Unauthorized Operation", JOptionPane.WARNING_MESSAGE);
					}
					break;
				case 4:
					if(isEmpty())
						showEmptyMessage();
					else {
						TableMethods.updateData("materialtype","typetitle", textfieldMatTypeTypeTitle.getText(),"typeid", textFieldMatTypeId.getText());;
						TableMethods.loadTable(tableMatType, "*", "materialtype");
						settingPanelRefreshFields((byte)4);
					}
					
					break;
				case 5:
					if(isEmpty())
						showEmptyMessage();
					else {
						TableMethods.updateData("category","cattitle", textFieldCategoryTitle.getText(),"catnote", textAreaCatNote.getText(), "catid", textFieldCatID.getText());;
						TableMethods.loadTable(tableCategory, "*", "category");
						settingPanelRefreshFields((byte)5);
						setColumns();
					}
					break;
			}
			
		}
	}
	
	public void showEmptyMessage()
	{
		JOptionPane.showMessageDialog(null, "Please give all the values.", "Empty Field", JOptionPane.WARNING_MESSAGE);
	}
	public boolean isEmpty()
	{
		int s = tabbedPane.getSelectedIndex();
		
		switch(s)
		{
			case 0:
				if(textFieldMatInfoCallNo.getText().equals("") || textFieldMatInfoEdition.getText().equals("") || textFieldMatInfoPublisher.getText().equals("") || textFieldMatInfoTitle.getText().equals("")||textFieldCategoryID.getText().equals("")||textFieldTypeID.getText().equals(""))
					return true;
				break;
			case 1:
				if(textFieldReaderID.getText().equals("") || textFieldGroupID.getText().equals(""))
					return true;
				break;
			case 2:
				if(textFieldAurhorName.getText().equals("") || textFieldAuthorMatID.getText().equals(""))
					return true;
				break;
			case 3:
				if(textFieldEmpId.getText().equals("") || passwordFieldEmPass.getText().equals(""))
					return true;
				break;
			case 4:
				if(textFieldMatTypeId.getText().equals("") ||textfieldMatTypeTypeTitle.getText().equals(""))
					return true;
				break;
			case 5:
				if(textFieldCategoryID.getText().equals("") || textFieldCategoryTitle.getText().equals(""))
					return true;
				break;
		}
		return false;
	}
	
	public void deleteButtonHandler()
	{
		if(MainFrame.isSettingPanel)
		{
			int s = tabbedPane.getSelectedIndex();
		
			switch(s)
			{
				case 0:
						TableMethods.deleteData("materialinfo", "matid", textFieldMatID.getText());
						TableMethods.loadTable(tableMatInfo, "*","materialinfo");
						setColumns();
						settingPanelRefreshFields((byte)0);
						break;
				case 1:
					TableMethods.deleteData("readers", "readerid", textFieldReaderID.getText());
					TableMethods.loadTable(tableReaders, "*","readers");
					settingPanelRefreshFields((byte)1);
					break;
				case 2:
					TableMethods.deleteData("authors", "authorname", textFieldAurhorName.getText());
					TableMethods.loadTable(tableAuthors, "*","authors");
					settingPanelRefreshFields((byte)2);
					break;
				case 3:
					TableMethods.deleteData("login", "empid", textFieldEmpId.getText());
					TableMethods.loadTable(tableEmployee, "*","login");
					settingPanelRefreshFields((byte)3);
					break;
				case 4:
					TableMethods.deleteData("materialtype", "typeid", textFieldMatTypeId.getText());
					TableMethods.loadTable(tableMatType, "*","materialtype");
					settingPanelRefreshFields((byte)4);
					break;
				case 5:
					TableMethods.deleteData("category", "catid", textFieldCatID.getText());
					TableMethods.loadTable(tableCategory, "*","category");
					settingPanelRefreshFields((byte)5);
					setColumns();
					break;
			}
			
		}
	}
}
