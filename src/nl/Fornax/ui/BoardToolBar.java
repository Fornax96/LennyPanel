package nl.Fornax.ui;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * @author Fornax
 */
public class BoardToolBar extends JToolBar{
	public BoardToolBar(ActionListener listener){
		setOrientation(HORIZONTAL);
		setFloatable(false);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(listener);
		btnHome.setActionCommand("btnBack");
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(listener);
		btnAdd.setActionCommand("btnAdd");
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(listener);
		btnRemove.setActionCommand("btnRemove");
		
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(listener);
		btnAbout.setActionCommand("btnAbout");
		
		add(btnHome);
		add(btnAdd);
		add(btnRemove);
		add(btnAbout);
	}
}
