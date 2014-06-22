package org.caofei.cc;

import org.apache.commons.lang3.RandomStringUtils;

public class CellSay extends Cell implements Runnable {
	public CellSay() {
		super();
		setId("ID_" + RandomStringUtils.randomAscii(5));
		setName("name_" + RandomStringUtils.randomAscii(5));
		new Thread(this, "NexusUnitsay Thread:").start();
	}

	public void run() {
		int ic = 0;
		while (true) {
			long millis = 1000 * Integer.valueOf(RandomStringUtils
					.randomNumeric(1));
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int i = Integer.valueOf(RandomStringUtils.randomNumeric(1));
			if (i > 3) {
//				String something = null;
				// something = RandomStringUtils.random(10);
				String str = getId() + "::" + ic++ ;
				say(str);
				System.out.println(str);
			}
		}
	}

}
