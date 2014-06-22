package org.caofei.cc;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Nexus {

	private static final Log LOG = LogFactory.getLog(Nexus.class);

	private Queue<Cell> cellsGroup = new ConcurrentLinkedQueue<Cell>();
	private Status status = Status.Empty;
	private String name = "Nexus" + i++;

	private static int i = 0;

	synchronized Status updateStatus() {
		switch (cellsGroup.size()) {
		case 0:
			status = Status.Empty;
			NexusPool.returnObject(this);
			break;
		case 1:
			status = Status.Waiting;
			break;
		default:
			if (status != Status.Running) {
				status = Status.Running;
				String message = "";
				for (Cell cell : cellsGroup) {
					cell.updateStatus(Cell.Status.Connected);
					message += cell.getName();
					message += ",";
				}
				push(message + "Connected at " + getName());
			}
			break;
		}
		return status;
	}

	public Nexus() {
		setName("Nexus " + ++i);
	}

	void push(String message) {
		for (Cell cell : cellsGroup) {
			cell.push(message);
		}
	}

	enum Status {
		Empty, Waiting, Running,
	}

	synchronized public void kickAll() {
		for (Cell cell : cellsGroup) {
			cell.setNexus(null);
			cell.disconnect();
		}
		cellsGroup.clear();
		updateStatus();
	}

	// @Override
	// public void run() {
	// Nexus nexus = null;
	// while (true) {
	// Cell cell = null;
	// cell = CellQueue.take();
	// if (nexus == null || nexus.updateStatus() == Status.Running) {
	// nexus = NexusPool.borrowObject();
	// }
	// cell.setNexus(nexus);
	// nexus.cellsGroup.add(cell);
	// nexus.updateStatus();
	// }
	// }

	Queue<Cell> getCellsGroup() {
		return cellsGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
