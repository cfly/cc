package org.caofei.cc;


public class NexusDaemon extends Thread {
	public NexusDaemon() {
		setName("NexusDaemon");
	}

	private static NexusDaemon nexusDaemon;

	public static void startup() {
		if (nexusDaemon == null) {
			synchronized (NexusDaemon.class) {
				if (nexusDaemon == null) {
					nexusDaemon = new NexusDaemon();
					nexusDaemon.start();
				}
			}
		}
	}

	@Override
	public void run() {
        while (true) {
            Cell cell1 = null;
            Cell cell2 = null;
            while (true) {
                cell1 = cell1 != null
                        && cell1.getStatus() == Cell.Status.Connecting ? cell1
                        : CellQueue.take();
                cell2 = cell2 != null
                        && cell1.getStatus() == Cell.Status.Connecting ? cell2
                        : CellQueue.take();
                if (cell1.getStatus() == Cell.Status.Connecting
                        && cell2.getStatus() == Cell.Status.Connecting) {
                    break;
                }
            }
            Nexus nexus = NexusPool.borrowObject();
            cell1.setNexus(nexus);
            cell2.setNexus(nexus);
            nexus.getCellsGroup().add(cell1);
            nexus.getCellsGroup().add(cell2);
            nexus.updateStatus();
        }
    }
}
