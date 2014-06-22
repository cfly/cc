package org.caofei.cc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.caofei.cc.Cell;
import org.caofei.cc.NexusPool;

public class CcServlet extends HttpServlet {
	private static final Log LOG = LogFactory.getLog(CcServlet.class);
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4008514032465970549L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) {
		Cell cell = (Cell) req.getSession().getAttribute("cell");
		if (cell == null) {
			cell = new Cell();
			cell.setId(req.getSession().getId());
			req.getSession().setAttribute("cell", cell);
		}
		String action = req.getParameter("action");
		if ("listen".equals(action)) {
			try {
				String str = cell.listen();
				if (StringUtils.isNotBlank(str)) {
					resp.setContentType("text/html");
					resp.getWriter().println(str);
				}
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		} else if ("say".equals(action)) {
			String say = req.getParameter("say");
			cell.say(say);
		} else if ("rename".equals(action)) {
			String name = req.getParameter("rename");
			cell.rename(name);
		} else if ("connect".equals(action)) {
			cell.connect();
		} else if ("disconnect".equals(action)) {
			cell.disconnect();
		} else if ("stat".equals(action)) {
			try {
				resp.getWriter().print(NexusPool.stat());
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}
}
