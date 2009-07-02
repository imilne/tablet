package tablet.gui.viewer;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import tablet.analysis.*;
import tablet.data.*;
import tablet.gui.*;
import tablet.gui.viewer.colors.*;

class ProteinCanvas extends JPanel
{
	private Contig contig;
	private Consensus consensus;
	private ScaleCanvas sCanvas;
	private ReadsCanvas rCanvas;

	private TranslationFractory factory;

	boolean[] enabled = new boolean[6];
	Vector<short[]> translations;

	// The LHS offset (difference) between the left-most read and the consensus
	private int offset;

	private Dimension dimension = new Dimension();

	// Menu items that appear on the popup menu for this canvas
	ProteinCanvasML mouseListener;

	ProteinCanvas()
	{
		setOpaque(false);

		String[] enabledStates = Prefs.visProteins.split("\\s+");
		for (int i = 0; i < enabledStates.length; i++)
			enabled[i] = enabledStates[i].equals("1");
	}

	void setAssemblyPanel(AssemblyPanel aPanel)
	{
		rCanvas = aPanel.readsCanvas;
		sCanvas = aPanel.scaleCanvas;

		mouseListener = new ProteinCanvasML(aPanel);
	}

	void setContig(Contig contig)
	{
		this.contig = contig;

		if (contig != null)
		{
			consensus = contig.getConsensus();
			offset = contig.getConsensusOffset();

			updateTranslations();
		}
	}

	void updateTranslations()
	{
		// Clear the existing translations (if any)
		translations = null;
		repaint();

		// Start a new thread to generate the translations in the background
		if (factory != null)
			factory.killMe = true;

		factory = new TranslationFractory(consensus);
		factory.start();
	}

	void setDimensions()
	{
		// How many tracks need to be shown?
		int count = 0;
		for (boolean b: enabled)
			if (b) count++;

		dimension = new Dimension(0, (rCanvas.ntH+1) * count);

		setPreferredSize(dimension);
		revalidate();
	}

	public Dimension getPreferredSize()
		{ return dimension; }

	private void setTranslations(Vector<short[]> translations)
	{
		this.translations = translations;
		repaint();
	}

	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;

		if (contig == null || translations == null)
			return;

		// Determine lhs and rhs of canvas
		int x1 = rCanvas.pX1;
		int x2 = rCanvas.pX2;
		int width = (x2-x1+1);

		// Clip to only draw what's needed (mainly ignoring what would appear
		// above the vertical scrollbar of the reads canvas)
		g.setClip(3, 0, width, getHeight());
		g.translate(3-x1, 0);


		int ntW = rCanvas.ntW;
		int ntH = rCanvas.ntH;
		int xS = rCanvas.xS;
		int xE = rCanvas.xE;

		ColorScheme colors = rCanvas.proteins;

		for (int t = 0, count = 0; t < translations.size(); t++)
		{
			// If this translation isn't needed, skip it...
			if (enabled[t] == false)
				continue;

			int y = (rCanvas.ntH+1) * count;
			count++;

			short[] data = getRegion(translations.get(t), xS-offset, xE-offset);

			for (int i = 0, x = (ntW*xS); i < data.length; i++, x += ntW)
				if (data[i] > 0)
					g.drawImage(colors.getImage(data[i]), x, y, null);
		}
	}

	// Strips out a region of a protein array to contain just the data needed
	// to draw that region to the screen
	private short[] getRegion(short[] translation, int start, int end)
	{
		short[] data = new short[end-start+1];

		int i = 0, d = 0;

		// Pre protein data (ie, before consensus starts)
		for (i = start; i < 0 && i <= end; i++, d++)
			data[d] = -1;

		// Protein data
		for (i = i; i <= end && i < translation.length; i++, d++)
			data[d] = translation[i];

		// Post protein data (ie, after consensus ends)
		for (i = i; i <= end; i++, d++)
			data[d] = -1;

		return data;
	}

	/**
	 * This class generates the DNA->Protein translations in real-time as they
	 * are needed by the canvas. Whenever a new consensus is displayed, the
	 * existing translations are thrown away and new ones are calculated.
	 */
	private class TranslationFractory extends Thread
	{
		private Consensus consensus;
		private Vector<short[]> translations = new Vector<short[]>(6);

		boolean killMe = false;

		TranslationFractory(Consensus consensus)
			{ this.consensus = consensus; }

		public void run()
		{
			setPriority(Thread.MIN_PRIORITY);
			try { Thread.sleep(250); } catch (Exception e) {}

			// Forward and reverse...
			for (int i = 0, t = 0; i < 2 && !killMe; i++)
			{
				ProteinTranslator.Direction direction = i == 0 ?
					ProteinTranslator.Direction.FORWARD :
					ProteinTranslator.Direction.REVERSE;

				// ...three reading frames in each direction
				for (int j = 1; j <= 3 && !killMe; j++)
				{
					// Only do the translation if it needs to be shown
					if (enabled[t++])
					{
						ProteinTranslator pt = new ProteinTranslator(
							consensus, direction, j);

						try
						{
							pt.runJob(0);
							translations.add(pt.getTranslation());
						}
						catch (Exception e) {}
					}
					else
						translations.add(null);
				}
			}

			if (killMe == false)
				setTranslations(translations);
		}
	}
}