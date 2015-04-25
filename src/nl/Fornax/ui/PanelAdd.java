package nl.Fornax.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import nl.Fornax.Config;

/**
 * @author Fornax
 */
public class PanelAdd extends JPanel implements ActionListener {

	private final JTextField txtField;

	public PanelAdd(ActionListener parentListener) {
		setLayout(new FlowLayout());

		final JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(this);
		btnAdd.setActionCommand("btnAdd");
		
		txtField = new JTextField(20);
		txtField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "TransferFocus");
		txtField.getActionMap().put("TransferFocus", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAdd.doClick();
			}
		});

		add(txtField);
		add(btnAdd);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "btnAdd":
				if(txtField.getText().length() < 1){
					return;
				}
				
				Config cfg = new Config();
				cfg.addEmote(txtField.getText());
				txtField.setText("");
		}
	}
}
