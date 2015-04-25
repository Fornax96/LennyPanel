package nl.Fornax.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import nl.Fornax.Clipboard;
import nl.Fornax.Config;
import nl.Fornax.LennyPanel;

/**
 * @author Fornax
 */
public class PanelLenny extends JPanel implements ActionListener {
	
	private final ArrayList<String> faces;

	public PanelLenny() {
		setLayout(new BorderLayout());

		Config cfg = new Config();
		faces = cfg.getEmotes();
		
		//Font fontLennyBtn = new Font("default", Font.PLAIN, 26);
		Font fontLennyBtn = new Font("SansSerif", Font.PLAIN, 26);
//		try {
//			fontLennyBtn = Font.createFont(0, new File("lib/Verdana.ttf")).deriveFont(26F);
//			
//		} catch (FontFormatException | IOException ex) {
//			fontLennyBtn = new Font("default", Font.PLAIN, 26);
//			System.out.println("Failed to load custom font");
//		}

		JPanel emotePanel = new JPanel();
		emotePanel.setLayout(new WrapLayout());
		emotePanel.setSize(new Dimension(300, 1));
		
		JScrollPane scrollPane = new JScrollPane(emotePanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		scrollPane.setViewportBorder(null);
		
		int i = 0;
		for (String lenny : faces) {
			if(lenny.length() > 20){
				lenny = lenny.subSequence(0, 20).toString() + "...";
			}
			JButton btnLenny = new JButton(lenny);
			btnLenny.addActionListener(this);
			btnLenny.setFont(fontLennyBtn);
			btnLenny.setActionCommand("lennyButton" + i);

			emotePanel.add(btnLenny);
			i++;
		}
		
		add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if (actionCommand.contains("lennyButton")) {
			int lennyId = Integer.parseInt(actionCommand.replace("lennyButton", ""));
			
			LennyPanel.close();
			
			Clipboard.copy(faces.get(lennyId));
		}
	}
}
