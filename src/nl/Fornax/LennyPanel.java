package nl.Fornax;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import nl.Fornax.ui.Board;

/**
 * @author Fornax
 */
public class LennyPanel {
	
	public static final String system = System.getProperty("os.name");
	public static final String java_version = System.getProperty("java.version");

	private static String configDir;
	private static boolean daemonMode = false;
	
	private static Board board;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		System.setProperty("Xmx", "8M");
		
		//Set the look and feel to GTK when it's available
		try {

			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(info.getClassName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				} else {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
			}
		} catch (ClassNotFoundException | 
			InstantiationException | 
			IllegalAccessException | 
			UnsupportedLookAndFeelException ex) {
			Logger.getLogger(LennyPanel.class.getName())
				.log(Level.SEVERE, null, ex);
		}

		for (String param : args) {
			if (param.equals("-d")) {
				daemonMode = true;
				Keyboard.registerKeyBinds();
			}
		}
		
		if(!daemonMode){
			board = new Board();
			board.setVisible(true);
		}
	}
	
	public static void close(){
		board.setVisible(false);
		board.dispose();
		board = null;
		
		Timer timer = new Timer("ShutdownTimer");
	
		timer.schedule(new TimerTask(){
			@Override
			public void run(){
				System.exit(0);
			}
		}, 300000);
	}
}
