package org.caofei.cc.servlet;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.caofei.cc.Cell;

public class CcSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		Cell cell = (Cell) se.getSession().getAttribute("cell");
		if (cell == null) {
			cell = new Cell();
			cell.setId(se.getSession().getId());
			se.getSession().setAttribute("cell", cell);
		}
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		Cell cell = (Cell) se.getSession().getAttribute("cell");
		if (cell != null) {
			cell.disconnect();
		}
	}

}
