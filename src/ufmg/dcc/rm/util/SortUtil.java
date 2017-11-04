package ufmg.dcc.rm.util;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class SortUtil {

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
	    return map.entrySet()
	              .stream()
	              .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
	              .collect(Collectors.toMap(
	                Map.Entry::getKey, 
	                Map.Entry::getValue, 
	                (e1, e2) -> e1, 
	                LinkedHashMap::new
	              ));
	}
	
	/**
	 * Reverse sorts the specified array in descending order based on the
	 * evaluation value.
	 *
	 * @param ranking
	 *            the ranking evaluation
	 * @return the sorted array
	 */
	public static Double[] reverseSortDesc(Double[] ranking) {
		Double[] reverseSorted = new Double[ranking.length];
		Arrays.sort(ranking);
		int arrLen = ranking.length;
		for (int i = arrLen - 1; i >= 0; i--) {
			int pos = arrLen - 1 - i;
			reverseSorted[pos] = ranking[i];
		}
		return reverseSorted;
	}


}
