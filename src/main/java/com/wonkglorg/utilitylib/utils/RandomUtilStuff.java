package com.wonkglorg.utilitylib.utils;

import java.util.List;

public final class RandomUtilStuff
{
	
	@SafeVarargs
	public static String[][] listsTo2DArray(List<String>... lists)
	{
		int numRows = lists.length;
		int numCols = lists[0].size();
		
		String[][] result = new String[numRows][numCols];
		
		for(int i = 0; i < numRows; i++)
		{
			List<String> currentList = lists[i];
			for(int j = 0; j < numCols; j++)
			{
				result[i][j] = currentList.get(j);
			}
		}
		
		return result;
	}
}
