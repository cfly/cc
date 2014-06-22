package org.caofei.cc;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cell {
	private static int i = 0;
	private static final Log LOG = LogFactory.getLog(Cell.class);
	private String id;
	private String name = "guest" + i++;

	private Status status = Status.Disconnected;
	private Nexus nexus;

	private Queue<String> messageQueue = new ConcurrentLinkedQueue<String>();

	public void connect() {
		LOG.debug(getId() + ":" + getName() + " connecting");
		if (status == Status.Disconnected) {
			updateStatus(Status.Connecting);
			CellQueue.put(this);
		}
	}

	public void disconnect() {
		LOG.debug(getId() + ":" + getName() + " disconnect");
		if (status != Status.Disconnected) {
			updateStatus(Status.Disconnected);
			Nexus nexus = getNexus();
			if (nexus != null) {
				nexus.kickAll();
			}

		}
	}

	public String listen() {
		// LOG.debug("listen");
		String str = messageQueue.poll();
		return StringUtils.isBlank(str) ? "" : str;
	}

	public void say(String str) {
		LOG.debug(getId() + ":" + getName() + " say:" + str);
		Nexus nexus = getNexus();
		if (nexus != null) {
			nexus.push(getName() + ":" + str);
		} else {
			push(getName() + ":" + str);
		}
	}

	public void push(String message) {
		LOG.debug("push message:" + message);
		messageQueue.offer(message.trim());
	}

	synchronized Status updateStatus(Status status) {
		say("status is " + status);
		return this.status = status;
	}

	Status getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Nexus getNexus() {
		return nexus;
	}

	public void setNexus(Nexus nexus) {
		this.nexus = nexus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	enum Status {
		Disconnected, Connecting, Connected, ;
	}

	@Override
	protected void finalize() throws Throwable {
		disconnect();
	}

	public void rename(String name) {
		say(this.name + " rename to " + name);
		setName(name);
	}

	static {
		NexusDaemon.startup();
	}

}
