import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;

import javax.swing.Box;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import java.awt.FlowLayout;

import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class LibraryProcessing extends JPanel {
	
	private JTextField textFieldAcqSearch;
	private JTable tableAcq;
	private JTextField textFieldAcqNo;
	private JTextField textFieldAcqDate;
	private JTextField textFieldAcqRefDetail;
	private JTable tableAcqDet;
	private JTextField textFieldAcqDetSearch;
	private JTextField textFieldAcqDetAcqNum;
	private JTextField textFieldAcqDetMatId;
	private JTextField textFieldAcqDetQuantity;
	private JTable tableAcc;
	private JTextField textFieldAccSearch;
	private JTextField textFieldAccMatId;
	private JTextField textFieldAccAccNum;
	private JTextField textFieldAccLocId;
	private JTabbedPane tabbedPaneLP;
	private JComboBox comboBoxAccStatus;
	private JTextField textFieldAccStatus;
	
	IconClass lpIconClass = new IconClass();
	/**
	 * Create the panel.
	 */
	public LibraryProcessing() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(5, 5));
		
		JLabel lblLibraryProcessint = new JLabel("LIBRARY PROCESSING");
		lblLibraryProcessint.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryProcessint.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(lblLibraryProcessint, BorderLayout.NORTH);
		
		JPanel panelLPButtons = new JPanel();
		panelLPButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panelLPButtons, BorderLayout.SOUTH);
		
		JButton btnLPSetting = new JButton("Settings");
		btnLPSetting.setMnemonic('S');
		btnLPSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.showPanel(MainFrame.SETTINGS_PANEL_STRING);
			}
		});
		btnLPSetting.setIcon(lpIconClass.settingIcon);
		btnLPSetting.setFont(MainFrame.buttonTextFont);
		panelLPButtons.add(btnLPSetting);
		
		JButton btnLPAdd = new JButton("Add");
		btnLPAdd.setMnemonic('A');
		btnLPAdd.setFont(MainFrame.buttonTextFont);
		btnLPAdd.setIcon(lpIconClass.insertIcon);
		btnLPAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				libProcessingAddBtnHandler();
			}
		});
		
		JButton btnCirculation = new JButton("Circulation");
		btnCirculation.setMnemonic('C');
		btnCirculation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.showPanel(MainFrame.CIRCULATION_PANEL_STRING);
			}
		});
		btnCirculation.setFont(MainFrame.buttonTextFont);
		btnCirculation.setIcon(lpIconClass.circulationIcon);
		panelLPButtons.add(btnCirculation);
		btnLPAdd.setFont(MainFrame.buttonTextFont);
		panelLPButtons.add(btnLPAdd);
		
		JButton btnLPDelete = new JButton("Delete");
		btnLPDelete.setMnemonic('D');
		btnLPDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				libProcessingDeleteBtnHandler();
			}
		});
		btnLPDelete.setIcon(lpIconClass.deleteIcon);
		btnLPDelete.setFont(MainFrame.buttonTextFont);
		panelLPButtons.add(btnLPDelete);
		
		JButton btnLPUpdate = new JButton("Update");
		btnLPUpdate.setMnemonic('U');
		btnLPUpdate.setIcon(lpIconClass.editIcon);
		btnLPUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				libProcessingmodifyBtnHandler();
			}
		});
		btnLPUpdate.setFont(MainFrame.buttonTextFont);
		panelLPButtons.add(btnLPUpdate);
		
		JButton btnLPCancel = new JButton("Cancel");
		btnLPCancel.setMnemonic('N');
		btnLPCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				libProcessingReloadBtnHandler((byte)-1);
			}
		});
		btnLPCancel.setIcon(lpIconClass.cancelIcon);
		btnLPCancel.setFont(MainFrame.buttonTextFont);
		panelLPButtons.add(btnLPCancel);
		
		tabbedPaneLP = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneLP.setFont(new Font("Cambria Math", Font.BOLD, 18));
		add(tabbedPaneLP, BorderLayout.CENTER);
		
		JPanel panelAcquisition = new JPanel();
		tabbedPaneLP.addTab("Acqusition", null, panelAcquisition, "");
		tabbedPaneLP.setForegroundAt(0, new Color(0, 0, 0));
		panelAcquisition.setLayout(new BorderLayout(5, 5));
		
		JPanel panelAcqusitionCenter = new JPanel();
		panelAcquisition.add(panelAcqusitionCenter, BorderLayout.CENTER);
		panelAcqusitionCenter.setLayout(new GridLayout(0, 2, 20, 0));
		
		JPanel panelAcqLeft = new JPanel();
		panelAcqLeft.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelAcqusitionCenter.add(panelAcqLeft);
		GridBagLayout gbl_panelAcqLeft = new GridBagLayout();
		gbl_panelAcqLeft.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelAcqLeft.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelAcqLeft.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelAcqLeft.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAcqLeft.setLayout(gbl_panelAcqLeft);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 0;
		panelAcqLeft.add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblAcqNo = new JLabel("Acquisition Number : ");
		lblAcqNo.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAcqNo = new GridBagConstraints();
		gbc_lblAcqNo.anchor = GridBagConstraints.EAST;
		gbc_lblAcqNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblAcqNo.gridx = 1;
		gbc_lblAcqNo.gridy = 1;
		panelAcqLeft.add(lblAcqNo, gbc_lblAcqNo);
		
		textFieldAcqNo = new JTextField();
		textFieldAcqNo.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldAcqNo = new GridBagConstraints();
		gbc_textFieldAcqNo.ipady = 6;
		gbc_textFieldAcqNo.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAcqNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAcqNo.gridx = 3;
		gbc_textFieldAcqNo.gridy = 1;
		panelAcqLeft.add(textFieldAcqNo, gbc_textFieldAcqNo);
		textFieldAcqNo.setColumns(10);
		
		JLabel lblAcqDate = new JLabel("Acqusition Date : ");
		lblAcqDate.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAcqDate = new GridBagConstraints();
		gbc_lblAcqDate.anchor = GridBagConstraints.EAST;
		gbc_lblAcqDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblAcqDate.gridx = 1;
		gbc_lblAcqDate.gridy = 2;
		panelAcqLeft.add(lblAcqDate, gbc_lblAcqDate);
		
		textFieldAcqDate = new JTextField();
		textFieldAcqDate.setFont(MainFrame.fieldFont);
		textFieldAcqDate.setText("");
		GridBagConstraints gbc_textFieldAcqDate = new GridBagConstraints();
		gbc_textFieldAcqDate.ipady = 6;
		gbc_textFieldAcqDate.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAcqDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAcqDate.gridx = 3;
		gbc_textFieldAcqDate.gridy = 2;
		panelAcqLeft.add(textFieldAcqDate, gbc_textFieldAcqDate);
		textFieldAcqDate.setColumns(10);
		
		JLabel lblAcqReferenceDetail = new JLabel("Reference Detail : ");
		lblAcqReferenceDetail.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAcqReferenceDetail = new GridBagConstraints();
		gbc_lblAcqReferenceDetail.anchor = GridBagConstraints.EAST;
		gbc_lblAcqReferenceDetail.insets = new Insets(0, 0, 0, 5);
		gbc_lblAcqReferenceDetail.gridx = 1;
		gbc_lblAcqReferenceDetail.gridy = 3;
		panelAcqLeft.add(lblAcqReferenceDetail, gbc_lblAcqReferenceDetail);
		
		textFieldAcqRefDetail = new JTextField();
		textFieldAcqRefDetail.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldAcqRefDetail = new GridBagConstraints();
		gbc_textFieldAcqRefDetail.ipady = 6;
		gbc_textFieldAcqRefDetail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAcqRefDetail.gridx = 3;
		gbc_textFieldAcqRefDetail.gridy = 3;
		panelAcqLeft.add(textFieldAcqRefDetail, gbc_textFieldAcqRefDetail);
		textFieldAcqRefDetail.setColumns(10);
		
		JPanel panelAcqTable = new JPanel();
		panelAcqTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelAcqusitionCenter.add(panelAcqTable);
		panelAcqTable.setLayout(new BorderLayout(5, 5));
		
		JPanel panelAcqSearch = new JPanel();
		panelAcqSearch.setBorder(new EmptyBorder(3, 20, 3, 80));
		panelAcqTable.add(panelAcqSearch, BorderLayout.NORTH);
		GridBagLayout gbl_panelAcqSearch = new GridBagLayout();
		gbl_panelAcqSearch.columnWidths = new int[]{43, 0, 0, 0};
		gbl_panelAcqSearch.rowHeights = new int[]{14, 0};
		gbl_panelAcqSearch.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelAcqSearch.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelAcqSearch.setLayout(gbl_panelAcqSearch);
		
		JLabel lblAcqSearch = new JLabel("Search : ");
		lblAcqSearch.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAcqSearch = new GridBagConstraints();
		gbc_lblAcqSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblAcqSearch.gridx = 0;
		gbc_lblAcqSearch.gridy = 0;
		panelAcqSearch.add(lblAcqSearch, gbc_lblAcqSearch);
		
		textFieldAcqSearch = new JTextField();
		textFieldAcqSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				TableMethods.searchTable("acquisition", tableAcq, textFieldAcqSearch, "acqno","acqdate","refdetail");
				tableAcq.getTableHeader().setFont(MainFrame.columnFont);
			}
		});
		textFieldAcqSearch.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldAcqSearch = new GridBagConstraints();
		gbc_textFieldAcqSearch.ipady = 6;
		gbc_textFieldAcqSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAcqSearch.gridx = 2;
		gbc_textFieldAcqSearch.gridy = 0;
		panelAcqSearch.add(textFieldAcqSearch, gbc_textFieldAcqSearch);
		textFieldAcqSearch.setColumns(35);
		
		JButton btnAcqReload = new JButton("Reload");
		btnAcqReload.setMnemonic('R');
		btnAcqReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				libProcessingReloadBtnHandler((byte) -2);
			}
		});
		btnAcqReload.setFont(MainFrame.smallBtnFont);
		btnAcqReload.setIcon(lpIconClass.reloadIcon);
		panelAcqTable.add(btnAcqReload, BorderLayout.SOUTH);
		
		JScrollPane scrollPaneAcq = new JScrollPane();
		panelAcqTable.add(scrollPaneAcq, BorderLayout.CENTER);
		
		tableAcq = new JTable();
		tableAcq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResultSet rst = TableMethods.selectTable(tableAcq,  "acquisition", "acqno",0);
				try {
					while (rst.next()) {
						
						textFieldAcqNo.setText(rst.getString("acqno"));
						textFieldAcqDate.setText(rst.getString("acqdate"));
						textFieldAcqRefDetail.setText(rst.getString("refdetail"));
					}
					rst.close();
					TableMethods.closeConnections();
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Table selection failed");
				}
			}
		});
		scrollPaneAcq.setViewportView(tableAcq);
		
		JPanel panelAcquisitionDetail = new JPanel();
		tabbedPaneLP.addTab("Acquisition Detail", null, panelAcquisitionDetail, null);
		panelAcquisitionDetail.setLayout(new GridLayout(0, 2, 20, 0));
		
		JPanel panelAcqDetLeft = new JPanel();
		panelAcqDetLeft.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelAcquisitionDetail.add(panelAcqDetLeft);
		GridBagLayout gbl_panelAcqDetLeft = new GridBagLayout();
		gbl_panelAcqDetLeft.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelAcqDetLeft.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelAcqDetLeft.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelAcqDetLeft.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAcqDetLeft.setLayout(gbl_panelAcqDetLeft);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 1;
		gbc_verticalStrut_1.gridy = 0;
		panelAcqDetLeft.add(verticalStrut_1, gbc_verticalStrut_1);
		
		JLabel lblAcqDetAcqusitionNumber = new JLabel("Acqusition Number : ");
		lblAcqDetAcqusitionNumber.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAcqDetAcqusitionNumber = new GridBagConstraints();
		gbc_lblAcqDetAcqusitionNumber.anchor = GridBagConstraints.EAST;
		gbc_lblAcqDetAcqusitionNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblAcqDetAcqusitionNumber.gridx = 1;
		gbc_lblAcqDetAcqusitionNumber.gridy = 1;
		panelAcqDetLeft.add(lblAcqDetAcqusitionNumber, gbc_lblAcqDetAcqusitionNumber);
		
		textFieldAcqDetAcqNum = new JTextField();
		textFieldAcqDetAcqNum.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldAcqDetAcqNum = new GridBagConstraints();
		gbc_textFieldAcqDetAcqNum.ipady = 6;
		gbc_textFieldAcqDetAcqNum.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAcqDetAcqNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAcqDetAcqNum.gridx = 2;
		gbc_textFieldAcqDetAcqNum.gridy = 1;
		panelAcqDetLeft.add(textFieldAcqDetAcqNum, gbc_textFieldAcqDetAcqNum);
		textFieldAcqDetAcqNum.setColumns(10);
		
		JLabel lblAcqDetMaterialId = new JLabel("Material ID : ");
		lblAcqDetMaterialId.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAcqDetMaterialId = new GridBagConstraints();
		gbc_lblAcqDetMaterialId.anchor = GridBagConstraints.EAST;
		gbc_lblAcqDetMaterialId.insets = new Insets(0, 0, 5, 5);
		gbc_lblAcqDetMaterialId.gridx = 1;
		gbc_lblAcqDetMaterialId.gridy = 2;
		panelAcqDetLeft.add(lblAcqDetMaterialId, gbc_lblAcqDetMaterialId);
		
		textFieldAcqDetMatId = new JTextField();
		textFieldAcqDetMatId.setFont(MainFrame.fieldFont);
		textFieldAcqDetMatId.setText("");
		GridBagConstraints gbc_textFieldAcqDetMatId = new GridBagConstraints();
		gbc_textFieldAcqDetMatId.ipady = 6;
		gbc_textFieldAcqDetMatId.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAcqDetMatId.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAcqDetMatId.gridx = 2;
		gbc_textFieldAcqDetMatId.gridy = 2;
		panelAcqDetLeft.add(textFieldAcqDetMatId, gbc_textFieldAcqDetMatId);
		textFieldAcqDetMatId.setColumns(10);
		
		JLabel lblAcqDetQuantity = new JLabel("Quantity : ");
		lblAcqDetQuantity.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAcqDetQuantity = new GridBagConstraints();
		gbc_lblAcqDetQuantity.anchor = GridBagConstraints.EAST;
		gbc_lblAcqDetQuantity.insets = new Insets(0, 0, 0, 5);
		gbc_lblAcqDetQuantity.gridx = 1;
		gbc_lblAcqDetQuantity.gridy = 3;
		panelAcqDetLeft.add(lblAcqDetQuantity, gbc_lblAcqDetQuantity);
		
		textFieldAcqDetQuantity = new JTextField();
		textFieldAcqDetQuantity.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldAcqDetQuantity = new GridBagConstraints();
		gbc_textFieldAcqDetQuantity.ipady = 6;
		gbc_textFieldAcqDetQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAcqDetQuantity.gridx = 2;
		gbc_textFieldAcqDetQuantity.gridy = 3;
		panelAcqDetLeft.add(textFieldAcqDetQuantity, gbc_textFieldAcqDetQuantity);
		textFieldAcqDetQuantity.setColumns(10);
		
		JPanel panelAcqDetTable = new JPanel();
		panelAcqDetTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelAcquisitionDetail.add(panelAcqDetTable);
		panelAcqDetTable.setLayout(new BorderLayout(5, 5));
		
		JPanel panelAcqDetSearch = new JPanel();
		panelAcqDetSearch.setBorder(new EmptyBorder(3, 20, 3, 80));
		panelAcqDetTable.add(panelAcqDetSearch, BorderLayout.NORTH);
		GridBagLayout gbl_panelAcqDetSearch = new GridBagLayout();
		gbl_panelAcqDetSearch.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelAcqDetSearch.rowHeights = new int[]{0, 0};
		gbl_panelAcqDetSearch.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelAcqDetSearch.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelAcqDetSearch.setLayout(gbl_panelAcqDetSearch);
		
		JLabel lblAcqDetSearch = new JLabel("Search: ");
		lblAcqDetSearch.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAcqDetSearch = new GridBagConstraints();
		gbc_lblAcqDetSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblAcqDetSearch.gridx = 0;
		gbc_lblAcqDetSearch.gridy = 0;
		panelAcqDetSearch.add(lblAcqDetSearch, gbc_lblAcqDetSearch);
		
		textFieldAcqDetSearch = new JTextField();
		textFieldAcqDetSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				TableMethods.searchTable("acqdetail", tableAcqDet, textFieldAcqDetSearch, "acqno","matid","qty");
				tableAcqDet.getTableHeader().setFont(MainFrame.columnFont);
			}
		});
		textFieldAcqDetSearch.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldAcqDetSearch = new GridBagConstraints();
		gbc_textFieldAcqDetSearch.ipady = 6;
		gbc_textFieldAcqDetSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAcqDetSearch.gridx = 2;
		gbc_textFieldAcqDetSearch.gridy = 0;
		panelAcqDetSearch.add(textFieldAcqDetSearch, gbc_textFieldAcqDetSearch);
		textFieldAcqDetSearch.setColumns(10);
		
		JButton btnAcqDetReload = new JButton("Reload");
		btnAcqDetReload.setMnemonic('R');
		btnAcqDetReload.setIcon(lpIconClass.reloadIcon);
		btnAcqDetReload.setFont(MainFrame.smallBtnFont);
		btnAcqDetReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				libProcessingReloadBtnHandler((byte) -1);
			}
		});
		panelAcqDetTable.add(btnAcqDetReload, BorderLayout.SOUTH);
		
		JScrollPane scrollPaneAcqDet = new JScrollPane();
		panelAcqDetTable.add(scrollPaneAcqDet, BorderLayout.CENTER);
		
		tableAcqDet = new JTable();
		tableAcqDet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResultSet rst = TableMethods.selectTable(tableAcqDet,  "acqdetail", "acqno",0);
				try {
					while (rst.next()) {
						
						textFieldAcqDetAcqNum.setText(rst.getString("acqno"));
						textFieldAcqDetMatId.setText(rst.getString("matid"));
						textFieldAcqDetQuantity.setText(rst.getString("qty"));
					}
					rst.close();
					TableMethods.closeConnections();
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Table selection failed");
				}
			}
		});
		scrollPaneAcqDet.setViewportView(tableAcqDet);
		
		JPanel panelAccession = new JPanel();
		tabbedPaneLP.addTab("Accession", null, panelAccession, null);
		panelAccession.setLayout(new GridLayout(0, 2, 20, 0));
		
		JPanel panelAccLeft = new JPanel();
		panelAccLeft.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelAccession.add(panelAccLeft);
		GridBagLayout gbl_panelAccLeft = new GridBagLayout();
		gbl_panelAccLeft.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelAccLeft.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelAccLeft.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelAccLeft.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAccLeft.setLayout(gbl_panelAccLeft);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 1;
		gbc_verticalStrut_2.gridy = 0;
		panelAccLeft.add(verticalStrut_2, gbc_verticalStrut_2);
		
		JLabel lblAccMatId = new JLabel("Material ID : ");
		lblAccMatId.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAccMatId = new GridBagConstraints();
		gbc_lblAccMatId.anchor = GridBagConstraints.EAST;
		gbc_lblAccMatId.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccMatId.gridx = 1;
		gbc_lblAccMatId.gridy = 1;
		panelAccLeft.add(lblAccMatId, gbc_lblAccMatId);
		
		textFieldAccMatId = new JTextField();
		textFieldAccMatId.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldAccMatId = new GridBagConstraints();
		gbc_textFieldAccMatId.ipady = 6;
		gbc_textFieldAccMatId.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAccMatId.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAccMatId.gridx = 3;
		gbc_textFieldAccMatId.gridy = 1;
		panelAccLeft.add(textFieldAccMatId, gbc_textFieldAccMatId);
		textFieldAccMatId.setColumns(10);
		
		JLabel lblAccAccNum = new JLabel("Accession Number : ");
		lblAccAccNum.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAccAccNum = new GridBagConstraints();
		gbc_lblAccAccNum.anchor = GridBagConstraints.EAST;
		gbc_lblAccAccNum.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccAccNum.gridx = 1;
		gbc_lblAccAccNum.gridy = 2;
		panelAccLeft.add(lblAccAccNum, gbc_lblAccAccNum);
		
		textFieldAccAccNum = new JTextField();
		textFieldAccAccNum.setFont(MainFrame.fieldFont);
		textFieldAccAccNum.setText("");
		GridBagConstraints gbc_textFieldAccAccNum = new GridBagConstraints();
		gbc_textFieldAccAccNum.ipady = 6;
		gbc_textFieldAccAccNum.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAccAccNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAccAccNum.gridx = 3;
		gbc_textFieldAccAccNum.gridy = 2;
		panelAccLeft.add(textFieldAccAccNum, gbc_textFieldAccAccNum);
		textFieldAccAccNum.setColumns(10);
		
		JLabel lblAccLocationId = new JLabel("Location ID : ");
		lblAccLocationId.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAccLocationId = new GridBagConstraints();
		gbc_lblAccLocationId.anchor = GridBagConstraints.EAST;
		gbc_lblAccLocationId.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccLocationId.gridx = 1;
		gbc_lblAccLocationId.gridy = 3;
		panelAccLeft.add(lblAccLocationId, gbc_lblAccLocationId);
		
		textFieldAccLocId = new JTextField();
		textFieldAccLocId.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldAccLocId = new GridBagConstraints();
		gbc_textFieldAccLocId.ipady = 6;
		gbc_textFieldAccLocId.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAccLocId.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAccLocId.gridx = 3;
		gbc_textFieldAccLocId.gridy = 3;
		panelAccLeft.add(textFieldAccLocId, gbc_textFieldAccLocId);
		textFieldAccLocId.setColumns(10);
		
		JLabel lblAccStatus = new JLabel("Status : ");
		lblAccStatus.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblAccStatus = new GridBagConstraints();
		gbc_lblAccStatus.anchor = GridBagConstraints.EAST;
		gbc_lblAccStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblAccStatus.gridx = 1;
		gbc_lblAccStatus.gridy = 5;
		panelAccLeft.add(lblAccStatus, gbc_lblAccStatus);
		
		textFieldAccStatus = new JTextField();
		textFieldAccStatus.setEditable(false);
		textFieldAccStatus.setToolTipText("");
		textFieldAccStatus.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldAccStatus = new GridBagConstraints();
		gbc_textFieldAccStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAccStatus.ipady = 6;
		gbc_textFieldAccStatus.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAccStatus.gridx = 3;
		gbc_textFieldAccStatus.gridy = 5;
		panelAccLeft.add(textFieldAccStatus, gbc_textFieldAccStatus);
		textFieldAccStatus.setColumns(30);
		
		comboBoxAccStatus = new JComboBox();
		comboBoxAccStatus.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				textFieldAccStatus.setText((String) comboBoxAccStatus.getSelectedItem());
			}
		});
		
		comboBoxAccStatus.setModel(new DefaultComboBoxModel(new String[] {"IN", "OUT", "MAINTENANCE"}));
		
		comboBoxAccStatus.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_comboBoxAccStatus = new GridBagConstraints();
		gbc_comboBoxAccStatus.ipady = 6;
		gbc_comboBoxAccStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAccStatus.gridx = 3;
		gbc_comboBoxAccStatus.gridy = 6;
		panelAccLeft.add(comboBoxAccStatus, gbc_comboBoxAccStatus);
		
		JPanel panelAccTable = new JPanel();
		panelAccTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelAccession.add(panelAccTable);
		panelAccTable.setLayout(new BorderLayout(5, 5));
		
		JButton btnReload = new JButton("Reload");
		btnReload.setMnemonic('R');
		btnReload.setIcon(lpIconClass.reloadIcon);
		btnReload.setFont(MainFrame.smallBtnFont);
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				libProcessingReloadBtnHandler((byte) -1);
			}
		});
		panelAccTable.add(btnReload, BorderLayout.SOUTH);
		
		JScrollPane scrollPaneAcc = new JScrollPane();
		panelAccTable.add(scrollPaneAcc, BorderLayout.CENTER);
		
		tableAcc = new JTable();
		tableAcc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ResultSet rst = TableMethods.selectTable(tableAcc,  "accesion", "accno",1);
				try {
					while (rst.next()) {
						
						textFieldAccMatId.setText(rst.getString("matid"));
						textFieldAccAccNum.setText(rst.getString("accno"));
						textFieldAccLocId.setText(rst.getString("locid"));
						textFieldAccStatus.setText(rst.getString("status"));
					}
					rst.close();
					TableMethods.closeConnections();
				} catch (SQLException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Table selection failed");
				}
			}
		});
		scrollPaneAcc.setViewportView(tableAcc);
		
		JPanel panelAccSearch = new JPanel();
		panelAccSearch.setBorder(new EmptyBorder(3, 20, 3, 80));
		panelAccTable.add(panelAccSearch, BorderLayout.NORTH);
		GridBagLayout gbl_panelAccSearch = new GridBagLayout();
		gbl_panelAccSearch.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelAccSearch.rowHeights = new int[]{0, 0};
		gbl_panelAccSearch.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelAccSearch.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelAccSearch.setLayout(gbl_panelAccSearch);
		
		JLabel lblSearch = new JLabel("Search : ");
		lblSearch.setFont(MainFrame.levelFont);
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 0;
		panelAccSearch.add(lblSearch, gbc_lblSearch);
		
		textFieldAccSearch = new JTextField();
		textFieldAccSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				TableMethods.searchTable("accesion", tableAcc, textFieldAccSearch, "matid","accno","locid","status");
				tableAcc.getTableHeader().setFont(MainFrame.columnFont);
			}
		});
		textFieldAccSearch.setFont(MainFrame.fieldFont);
		GridBagConstraints gbc_textFieldAccSearch = new GridBagConstraints();
		gbc_textFieldAccSearch.ipady = 6;
		gbc_textFieldAccSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAccSearch.gridx = 2;
		gbc_textFieldAccSearch.gridy = 0;
		panelAccSearch.add(textFieldAccSearch, gbc_textFieldAccSearch);
		textFieldAccSearch.setColumns(10);

		
		TableMethods.loadTable(tableAcc, "*","accesion");
		TableMethods.loadTable(tableAcq, "*","acquisition");
		TableMethods.loadTable(tableAcqDet, "*","acqdetail");
		
	}

	
	public void libProcessingAddBtnHandler()
	{
		int s = tabbedPaneLP.getSelectedIndex();
		boolean isacq = false,ismatid = false;
		
		switch (s) {
		case 0:
			TableMethods.insertData("acquisition", "acqno", "acqdate", "refdetail",textFieldAcqNo.getText(), textFieldAcqDate.getText(), textFieldAcqRefDetail.getText());
			TableMethods.loadTable(tableAcq, "*","acquisition");
			libProcessingReloadBtnHandler((byte)0);
			break;
			
		case 1:
		
			isacq = TableMethods.isPresent("acquisition", "acqno", textFieldAcqDetAcqNum.getText(), "OR");
			ismatid = TableMethods.isPresent("materialinfo", "matid", textFieldAcqDetMatId.getText(), "OR");
			
			if(!isacq)
			{
				JOptionPane.showMessageDialog(null, "Acquisiton Number is not found !", "Data Not Found", JOptionPane.WARNING_MESSAGE);
			}
			else if(!ismatid)
			{
				JOptionPane.showMessageDialog(null, "Material ID  is not found !", "Data Not Found", JOptionPane.WARNING_MESSAGE);
			}
			else {
				TableMethods.insertData("acqdetail", "acqno", "matid" ,"qty", textFieldAcqDetAcqNum.getText(),textFieldAcqDetMatId.getText(), textFieldAcqDetQuantity.getText());
				TableMethods.loadTable(tableAcqDet, "*","acqdetail");
				libProcessingReloadBtnHandler((byte)1);
				
			}
			
			
			break;
			
		case 2:
				
			 ismatid = TableMethods.isPresent("materialinfo", "matid", textFieldAccMatId.getText(), "OR");
			
			if(!ismatid)
			{
				JOptionPane.showMessageDialog(null, "Material ID is not found !", "Data Not Found", JOptionPane.WARNING_MESSAGE);
			}
			else {
				TableMethods.insertData("accesion", "matid", "accno", "locid","status",textFieldAccMatId.getText(), textFieldAccAccNum.getText(), textFieldAccLocId.getText(), (String)comboBoxAccStatus.getSelectedItem());
				TableMethods.loadTable(tableAcc, "*","accesion");
				libProcessingReloadBtnHandler((byte)2);
			}
			
		}
	}
	
	
	public void  libProcessingReloadBtnHandler(byte s) {
		
		
		if(s<0)
			s = (byte) tabbedPaneLP.getSelectedIndex();
		
		switch (s) {
		case 0:
			
			textFieldAcqDate.setText("");
			textFieldAcqRefDetail.setText("");
			textFieldAcqNo.setText("");
			textFieldAcqSearch.setText("");
			
			TableMethods.loadTable(tableAcq, "*","acquisition");
			break;
			
		case 1:
			textFieldAcqDetAcqNum.setText("");
			textFieldAcqDetMatId.setText("");
			textFieldAcqDetQuantity.setText("");
			textFieldAcqDetSearch.setText("");
			TableMethods.loadTable(tableAcqDet, "*","acqdetail");
			break;
			
		case 2:
			
			textFieldAccAccNum.setText("");
			textFieldAccLocId.setText("");
			textFieldAccMatId.setText("");
			textFieldAccStatus.setText("");
			textFieldAccSearch.setText("");
			TableMethods.loadTable(tableAcc, "*","accesion");
		
		}
	}
	
	
	public void libProcessingmodifyBtnHandler()
	{
		if(MainFrame.isLibProcessingOn)
		{
			int s = tabbedPaneLP.getSelectedIndex();
			
			switch(s)
			{
				case 0:
						TableMethods.updateData("acquisition","acqdate", textFieldAcqDate.getText(),"refdetail",textFieldAcqRefDetail.getText(),"acqno", textFieldAcqNo.getText());
						TableMethods.loadTable(tableAcq, "*","acquisition");
						libProcessingReloadBtnHandler((byte)0);
						break;
				case 1:
					TableMethods.updateData("acqdetail","matid", textFieldAcqDetMatId.getText(),"qty", textFieldAcqDetMatId.getText(), "acqno", textFieldAcqDetAcqNum.getText());;
					TableMethods.loadTable(tableAcqDet, "*", "acqdetail");
					libProcessingReloadBtnHandler((byte)1);
					break;
				case 2:
					if(TableMethods.isPresent("accesion","accno", textFieldAccAccNum.getText()))
					{
						JOptionPane.showMessageDialog(null, "There exists another material with the same Accession number.\nTry another one. ","Duplicate values.", JOptionPane.WARNING_MESSAGE);
					}
					else {
						TableMethods.updateData("accesion", "accno", textFieldAccAccNum.getText(), "locid", textFieldAccLocId.getText(),"status", (String)comboBoxAccStatus.getSelectedItem(), "accno", textFieldAccAccNum.getText());
						TableMethods.loadTable(tableAcc,"*", "accesion");
						libProcessingReloadBtnHandler((byte)2);
					}
					break;
			}
			
		}
	}
	
	
	public void libProcessingDeleteBtnHandler()
	{
		if(MainFrame.isLibProcessingOn)
		{
			int s = tabbedPaneLP.getSelectedIndex();
			//JOptionPane.showMessageDialog(null, s);
			
			switch(s)
			{
				case 0:
						TableMethods.deleteData("acquisition", "acqno", textFieldAcqNo.getText());
						TableMethods.deleteData("acqdetail", "acqno", textFieldAcqNo.getText());
						TableMethods.loadTable(tableAcq, "*","acquisition");
						libProcessingReloadBtnHandler((byte)0);
						break;
				case 1:
					TableMethods.deleteData("acqdetail", "acqno", textFieldAcqDetAcqNum.getText());
					TableMethods.deleteData("acquisition", "acqno", textFieldAcqDetAcqNum.getText());
					TableMethods.loadTable(tableAcqDet, "*","acqdetail");
					libProcessingReloadBtnHandler((byte)1);
					break;
				case 2:
					
					JOptionPane.showMessageDialog(null, "You can not delete material data.\nYou can change its status if it is lost.");
					/*TableMethods.deleteData("accesion", "accno", textFieldAccAccNum.getText());
					TableMethods.loadTable(tableAcc, "*","authors");
					libProcessingReloadBtnHandler((byte)2);*/
					break;
			}
			
		}
	}
	
}
