package org.caofei.cc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CellQueue {
	private static final BlockingQueue<Cell> CELLS_QUEUE = new LinkedBlockingQueue<Cell>();

	public static Cell take() {
		try {
			return CELLS_QUEUE.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void put(Cell cell) {
		try {
			CELLS_QUEUE.put(cell);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
