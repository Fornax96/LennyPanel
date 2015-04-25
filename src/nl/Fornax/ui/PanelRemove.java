package nl.Fornax.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import nl.Fornax.Config;

/**
 * @author Fornax
 */
public class PanelRemove extends JPanel implements ActionListener {

	private final JPanel emotePanel;
	private final JScrollPane scrollPane;
	private final Config cfg = new Config();
	private final Font fontLennyBtn = new Font("SansSerif", Font.PLAIN, 26);

	public PanelRemove(ActionListener parentListener) {
		setLayout(new MigLayout("", "[grow,fill]", "[][fill]"));

		JLabel lblExplanation = new JLabel("<html>Click on an emoticon to remove it "
			+ "from the application.</html>");

		add(lblExplanation, "growx");
		
		emotePanel = new JPanel();
		emotePanel.setLayout(new WrapLayout());
		emotePanel.setSize(new Dimension(300, 1));

		scrollPane = new JScrollPane(emotePanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
		
		add(scrollPane, "cell 0 1 1 0");
		
		setEmotePanel();
	}

	private void setEmotePanel() {
		emotePanel.removeAll();
		
		ArrayList<String> faces = cfg.getEmotes();

		int i = 0;
		for (String lenny : faces) {
			if(lenny.length() > 20){
				lenny = lenny.subSequence(0, 20).toString() + "…";
			}
			JButton btnLenny = new JButton(lenny);
			btnLenny.addActionListener(this);
			btnLenny.setFont(fontLennyBtn);
			btnLenny.setActionCommand("rmvLennyBtn" + i);

			emotePanel.add(btnLenny);
			i++;
		}

		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.contains("rmvLennyBtn")) {
			Config cfg = new Config();
			int lennyId = Integer.parseInt(action.replace("rmvLennyBtn", ""));

			cfg.removeEmote(lennyId);

			setEmotePanel();
		}
	}

}
