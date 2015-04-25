package nl.Fornax.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Fornax
 */
public class PanelAbout extends JPanel {
	private final String aboutText = ""
		+ "LennyPanel is an easy emoticon clipboard to conform "
		+ "you on all your exciting internet adventures. <br>"
		+ "(∩ ͡° ͜ʖ ͡°)⊃━☆ﾟ. * ･ ｡ﾟ,<br><br>"
		+ "This software was written by Fornax<br>"
		+ "<br>"
		+ "http://fornax96.me";
	
	public PanelAbout(ActionListener parentListener) {
		setLayout(new FlowLayout());
		JLabel lblAbout = new JLabel("<html><div style='width:250px;'>" + aboutText + "</div></html>");

		add(lblAbout);
	}

}
