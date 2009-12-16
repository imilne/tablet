// Copyright 2009 Plant Bioinformatics Group, SCRI. All rights reserved.
// Use is subject to the accompanying licence terms.

package tablet.data;

import java.util.*;

public class PackSet implements Iterable<Pack>, IReadManager
{
	private ArrayList<Pack> packs = new ArrayList<Pack>();

	private Contig contig;

	public PackSet(Contig contig)
	{
		this.contig = contig;
	}

	public Iterator<Pack> iterator()
		{ return packs.iterator(); }

	public int size()
		{ return packs.size(); }

	public void addPack(Pack pack)
		{ packs.add(pack); }

	public void trimToSize()
		{ packs.trimToSize(); }

	/**
	 * Returns a byte array containing sequence information (or -1 for no data)
	 * for the given line between the points start and end.
	 */
	public byte[] getValues(int line, int start, int end)
	{
		Pack pack = packs.get(line);

		start += contig.getVisualS();
		end += contig.getVisualS();

		return pack.getValues(start, end);
	}

	public Read getReadAt(int line, int nucleotidePosition)
	{
		if (line < 0 || line >= packs.size())
			return null;

		Pack pack = packs.get(line);

		return pack.getReadAt(nucleotidePosition);
	}

	public int getLineForRead(Read read)
	{
		for(Pack pack : packs)
		{
			Read found = pack.getReadAt(read.getStartPosition());
			if(found != null && found.getID() == read.getID())
			{
				return packs.indexOf(pack);
			}
			else
			{
				continue;
			}
		}
		return -1;
	}
}