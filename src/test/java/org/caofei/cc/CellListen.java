package org.caofei.cc;

public class CellListen extends Thread {
	public CellListen(Cell nexusUnit) {
		setName("NexusUnitlisten Thread:");
		this.nexusUnit = nexusUnit;
	}

	private Cell nexusUnit;

	public void run() {
		while (true) {
			String str = nexusUnit.listen();
			str = getName() + " listen:\"" + str + "\"";
			System.out.println(str);
		}
	}

}
