package tablet.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import scri.commons.gui.*;

public class Tablet
{
	private static File prefsFile = new File(
		System.getProperty("user.home"), ".tablet.xml");
	private static Prefs prefs = new Prefs();

	public static WinMain winMain;

	public static void main(String[] args)
		throws Exception
	{
		Prefs.setDefaults();
		prefs.loadPreferences(prefsFile, Prefs.class);

		new Tablet(args[0]);
	}

	Tablet(String filename)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {}

		winMain = new WinMain(filename);

		winMain.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				if (winMain.okToExit() == false)
					return;

				shutdown();
			}
		});

		TaskDialog.initialize(winMain, "Tablet");

		winMain.setVisible(true);
	}

	private void shutdown()
	{
		Prefs.isFirstRun = false;
		prefs.savePreferences(prefsFile, Prefs.class);

		System.exit(0);
	}
}