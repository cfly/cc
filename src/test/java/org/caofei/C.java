package org.caofei;

import org.caofei.cc.CellListen;
import org.caofei.cc.CellSay;

public class C extends Thread {
	public static void main(String[] args) {
//		new NexusPanel().;
//		plus();
//		plus();
//		plus();
//		plus();
//		plus();
//		plus();
//		plus();
//		plus();
//		plus();
//		plus();
//		Nexus.startup();
//		Nexus.startup();
	}
	
	@Override
	public void run() {
//		NexusDaemon.startup();
	}

	public static void plus (){
		CellSay cellSay = new CellSay();
		new CellListen(cellSay).start();
	}
	// @Override
	// public void run() {
	// try {
	// Thread.sleep(1000*10);
	// } catch (InterruptedException e1) {
	// e1.printStackTrace();
	// }
	// while (true) {
	// NexusUnitsay nexusUnitTest = new NexusUnitsay();
	// Nexus.addNexusUnit(nexusUnitTest);
	// try {
	// Thread.sleep(1000*10);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }
	// }
}
