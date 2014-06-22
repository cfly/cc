package org.caofei;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Cell0 {
	private String id;
	private String name;
	private Cell0 target;
	private Queue<String> messages = new LinkedList<String>();
	private List<Cell0> group = new LinkedList<Cell0>();
	private Status status = Status.Single;
	synchronized public boolean touch(Cell0 cell) {
		status = Status.Seeking;
		boolean r = false;
		if (validate(cell)) {
			target = cell;
			if (isNotCouple() && cell.onTouch(this)) {
				r = true;
			}else{
				target = null;
				status = Status.Single;
			}
		}
		return r;
	}

	synchronized public boolean onTouch(Cell0 cell) {
		boolean r = false;
		if (validate(cell)) {
			if (isNotCouple() && touch(cell)) {
				r = false;
			} else {
				r = true;
			}
		}
		return r;
	}

	public boolean isCouple() {
		return target != null && target.target == this;
	}

	public boolean isNotCouple() {
		return !isCouple();
	}

	public void say(String something) {
		for (Cell0 cell : group) {
			cell.messages.offer(something);
		}
	}

	private boolean validate(Cell0 cell) {
		return cell != null && this != cell
				&& (target == null || target == cell)
				&& (cell.target == null || cell.target == this)
				;
	}

	public void connect() {

	}

	public void disconnect() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	enum Status{Single,Seeking,Couple};
}
