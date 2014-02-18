package com.theladders.solid.srp;

import java.util.HashMap;

public class HashCodeProvider
{
	private static Integer lastPrime = 1;
	private static HashMap<String, Integer> primeForType = new HashMap<String, Integer>();
	
	public static int hashCodeFor(Object object, Object ...objects)
	{
		String typename = object.getClass().toString();
		
		if( ! primeForType.containsKey(typename) )
		{
			primeForType.put(typename, getNextPrime());
		}

		return primeForType.get(typename) * hashCodeAlgorithm(objects);
	}
	
	//this would be a better algorithm
	private static int hashCodeAlgorithm(Object ...objects)
	{
		int ret = 0;
		for( Object object : objects )
		{
			if( object != null )
			{
				ret ^= object.hashCode();
			}
		}
		return ret;
	}

	//this would be a miller-rabin or something in real code
	private static int getNextPrime()
	{
		lastPrime += 2;
		return lastPrime;
	}
}
