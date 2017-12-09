import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;


public class About extends JPanel {

	/**
	 * Create the panel.
	 */
	public About() {
		setBorder(new EmptyBorder(20, 20, 40, 20));
		setLayout(new BorderLayout(10, 10));
		
		JLabel lblAbout = new JLabel("About");
		lblAbout.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblAbout, BorderLayout.NORTH);
		
		JPanel panelAboutCenter = new JPanel();
		add(panelAboutCenter, BorderLayout.CENTER);
		panelAboutCenter.setLayout(new BorderLayout(0, 30));
		
		JTextPane txtpnDigitalLibraryAssitant = new JTextPane();
		txtpnDigitalLibraryAssitant.setEditable(false);
		txtpnDigitalLibraryAssitant.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnDigitalLibraryAssitant.setText("Digital Library Assitant (DLA) is a tool for managing library digitally. It is a tool for librarians so that they can keep track of materials and readers easily. Issue and Return of materials also can be done using this tool.");
		panelAboutCenter.add(txtpnDigitalLibraryAssitant, BorderLayout.NORTH);
		
		JPanel panelDevelopers = new JPanel();
		panelDevelopers.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelAboutCenter.add(panelDevelopers, BorderLayout.CENTER);
		panelDevelopers.setLayout(new BorderLayout(30, 30));
		
		JLabel lblDevelopers = new JLabel("Developers");
		lblDevelopers.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDevelopers.setHorizontalAlignment(SwingConstants.CENTER);
		panelDevelopers.add(lblDevelopers, BorderLayout.NORTH);
		
		JLabel lblThanks = new JLabel("Thanks");
		lblThanks.setVerticalAlignment(SwingConstants.TOP);
		lblThanks.setHorizontalAlignment(SwingConstants.CENTER);
		lblThanks.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelDevelopers.add(lblThanks, BorderLayout.SOUTH);
		
		JTextPane textPaneDevs = new JTextPane();
		textPaneDevs.setEditable(false);
		textPaneDevs.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPaneDevs.setText("\n\tMahmudul Hasan Bhuiyan\n\tDepartment of Computer Science & Engineering\n\tUniversity of Chittagong\n\tMail: mdmahmudulhasan4@gmail.com\n\n\tMoinul Hossain Khan\n\tDepartment of Computer Science & Engineering\n\tUniversity of Chittagong\n\tMail: moinulsifat@gmail.com");
		
		panelDevelopers.add(textPaneDevs, BorderLayout.CENTER);
		

	}

}
