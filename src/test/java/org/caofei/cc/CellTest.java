package org.caofei.cc;

import org.caofei.Cell0;

public class CellTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cell0 a,b,c;
		a = new Cell0();a.setId("1");a.setName("a");
		b = new Cell0();b.setId("2");b.setName("b");
		c = new Cell0();c.setId("3");c.setName("c");
		a.touch(b);
		c.touch(b);
	}

}
