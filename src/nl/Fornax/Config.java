package nl.Fornax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Fornax
 */
public class Config {

	private final String configDir;
	private final File configFile;

	public Config() {

		if (LennyPanel.system.contains("Linux")) {
			System.out.println("It's a Unix system!");
			configDir = System.getProperty("user.home") + "/.config/";
		} else if (LennyPanel.system.contains("Windows")) {
			System.out.println("So Windows it is.");
			configDir = System.getProperty("user.home") + "\\AppData\\Roaming\\";
		} else {
			System.out.println("Unknown operating system!");
			configDir = System.getProperty("user.home") + "/";
		}

		configFile = new File(configDir + "LennyPanel.properties");

		if (!configFile.exists()) {
			createConfig();
		}

		System.out.println(configDir);
	}

	private void createConfig() {
		try (OutputStreamWriter writer = new OutputStreamWriter(
			new FileOutputStream(configFile), "UTF-8")) {
			configFile.createNewFile();

			Properties props = new Properties();

			int i = 0;
			for (String text : getDefaultEmotes()) {
				props.setProperty("lenny" + i, text);
				i++;
			}

			props.store(writer, "Hello Lenny");
			writer.close();
		} catch (IOException ex) {
			Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void addEmote(String emote) {
		ArrayList<String> emotes = getEmotes();

		emotes.add(emote);

		try (OutputStreamWriter writer = new OutputStreamWriter(
			new FileOutputStream(configFile), "UTF-8")) {
			Properties props = new Properties();

			int i = 0;
			for (String text : emotes) {
				props.setProperty("lenny" + i, text);
				i++;
			}

			props.store(writer, "Config File");
			writer.close();
		} catch (IOException ex) {
			Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void removeEmote(int index) {
		ArrayList<String> emotes = getEmotes();

		emotes.remove(index);
		emotes.trimToSize();

		try (OutputStreamWriter writer = new OutputStreamWriter(
			new FileOutputStream(configFile), "UTF-8")) {
			Properties props = new Properties();

			props.clear();

			int i = 0;
			for (String text : emotes) {
				props.setProperty("lenny" + i, text);
				i++;
			}

			props.store(writer, "Config File");
			writer.close();
		} catch (IOException ex) {
			Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public ArrayList<String> getEmotes() {
		try (InputStreamReader reader = new InputStreamReader(new FileInputStream(configFile), "UTF-8")) {
			Properties props = new Properties();
			props.load(reader);

			ArrayList<String> faces = new ArrayList();
			int i = 0;
			while (props.getProperty("lenny" + i) != null) {
				faces.add(props.getProperty("lenny" + i));
				i++;
			}

			return faces;
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
		}

		ArrayList<String> returnArray = new ArrayList();
		returnArray.add("ERROR: Unknown");
		return returnArray;
	}

	public String[] getDefaultEmotes() {
		String[] faces = {"( ͡° ͜ʖ ͡°)", "( ͠° ͟ʖ ͡°)", "ᕦ( ͡° ͜ʖ ͡°)ᕤ", "( ͡~ ͜ʖ ͡°)", "( ͡o ͜ʖ ͡o)",
			"( ͡͡ ° ͜ ʖ ͡ °)﻿", "(ง ͠° ͟ل͜ ͡°)ง", "ヽ༼ຈل͜ຈ༽ﾉ", "{ ͡• ͜ʖ ͡•}", "( ͡° ͜V ͡°)",
			"( ͡^ ͜ʖ ͡^)", "( ͡°╭͜ʖ╮͡° )", "ᕦ( ͡°╭͜ʖ╮͡° )ᕤ", "( ‾ʖ̫‾)", "( ͡ᵔ ͜ʖ ͡ᵔ )",
			"♪♬ヽ(⌐■_■)ノ♪♬", "ಥ_ಥ", "༼ つ ◕_◕ ༽つ", "ᶘ ᵒᴥᵒᶅ", "(◕‿◕✿)", "ヽ༼◥▶ل͜◀◤༽ﾉ"};
		return faces;
	}
}
