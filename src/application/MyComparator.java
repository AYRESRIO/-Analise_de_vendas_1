package application;

import java.util.Comparator;

import entities.Sale;

public class MyComparator implements Comparator<Sale> {

	@Override
	public int compare(Sale p1, Sale p2) {
		return (p2.averagePrice()).compareTo(p1.averagePrice());

	}

}
