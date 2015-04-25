package nl.Fornax;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

/**
 * @author Fornax
 */
public class Clipboard {
	
	public static void copy(String text) {
		StringSelection stringSelection = new StringSelection(text);
		java.awt.datatransfer.Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}
}
