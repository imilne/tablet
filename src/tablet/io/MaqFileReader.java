package tablet.io;

import java.io.*;
import java.util.*;

import tablet.analysis.*;
import tablet.data.*;
import tablet.data.cache.*;

public class MaqFileReader extends TrackableReader
{
	private IReadCache readCache;

	private ReferenceFileReader refReader;

	// The index of the Maq file in the files[] array
	private int maqIndex = -1;
	// The index of the reference file in the files[] array
	private int refIndex = -1;

	private HashMap<String, Contig> contigHash = new HashMap<String, Contig>();

	MaqFileReader(IReadCache readCache)
	{
		this.readCache = readCache;
	}

	boolean canRead() throws Exception
	{
		refReader = new ReferenceFileReader(assembly);

		boolean foundMaq = false;
		boolean foundRef  = false;

		// We need to check each file to see if it is readable
		for (int i = 0; i < 2; i++)
		{
			if (isMaqFile(i))
			{
				foundMaq = true;
				maqIndex = i;
			}
			else if (refReader.canRead(files[i]))
			{
				foundRef = true;
				refIndex = i;
			}
		}

		return (foundMaq && foundRef);
	}

	// Checks to see if this is a Maq file by assuming 16 columns of \t data
	private boolean isMaqFile(int fileIndex)
		throws Exception
	{
		in = new BufferedReader(new InputStreamReader(getInputStream(fileIndex)));
		str = readLine();

		boolean isMaqFile = (str != null && str.split("\t").length == 16);
		in.close();
		is.close();

		return isMaqFile;
	}

	public void runJob(int jobIndex) throws Exception
	{
		readReferenceFile();
		readMaqFile();
	}

	private void readReferenceFile()
			throws Exception
	{
		in = new BufferedReader(new InputStreamReader(getInputStream(refIndex), "ASCII"));

		refReader.readReferenceFile(this, files[refIndex]);
		contigHash = refReader.getContigHashMap();

		in.close();

		assembly.setName(files[maqIndex].getName());
	}

	private void readMaqFile()
			throws Exception
	{
		in = new BufferedReader(new InputStreamReader(getInputStream(maqIndex), "ASCII"));

		int readID = 0;

		while ((str = readLine()) != null && okToRun)
		{
			String[] tokens = str.split("\t");

			String name = new String(tokens[0]);
			String data = new String(tokens[14]);
			String chr  = tokens[1];
			boolean complemented = tokens[3].equals("-");
			int pos = Integer.parseInt(tokens[2]) - 1;

			Read read = new Read(readID, pos);

			Contig contigToAddTo = contigHash.get(chr);

			if (contigToAddTo != null)
			{
				contigToAddTo.getReads().add(read);

				ReadMetaData rmd = new ReadMetaData(name, complemented);
				rmd.setData(data.toString());
				rmd.calculateUnpaddedLength();
				read.setLength(rmd.length());

				// Do base-position comparison...
				BasePositionComparator.compare(contigToAddTo.getConsensus(), rmd,
					read.getStartPosition());

				readCache.setReadMetaData(rmd);

				readID++;
			}
		}

		in.close();
	}
}