package nl.Fornax.ui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Fornax
 */
public class Board extends JFrame implements ActionListener{
	private JPanel mainPanel;
	
	public Board(){
		setAlwaysOnTop(true);
		setAutoRequestFocus(false);
		setTitle("LennyPanel");
		setResizable(false);
		setLayout(new BorderLayout());
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		Point mousePos = MouseInfo.getPointerInfo().getLocation();
		setBounds(mousePos.x-200, mousePos.y-100, 400, 300);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addComponents();
	}
	
	private void addComponents(){
		//Add menu bar
		BoardToolBar menu = new BoardToolBar(this);
		
		add(menu, BorderLayout.PAGE_START);
		
		mainPanel = new PanelLenny();
		add(mainPanel);
	}
	
	protected void updatePanel(JPanel panel){
		remove(mainPanel);
		mainPanel = panel;
		add(mainPanel);
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		switch(actionCommand){
			case "btnBack":
				updatePanel(new PanelLenny());
				break;
			case "btnAdd":
				updatePanel(new PanelAdd(this));
				break;
			case "btnRemove":
				updatePanel(new PanelRemove(this));
				break;
			case "btnAbout":
				updatePanel(new PanelAbout(this));
		}
	}
}
