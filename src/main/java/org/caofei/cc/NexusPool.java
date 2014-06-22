package org.caofei.cc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

public class NexusPool {
	private static final Log LOG = LogFactory.getLog(NexusPool.class);
	private static final GenericObjectPool OBJECT_POOL = new GenericObjectPool(
			new NexusObjectFactory());
	static {
		OBJECT_POOL.setMaxIdle(99999);
		OBJECT_POOL.setMinIdle(9);
		OBJECT_POOL.setMaxActive(-1);
	}

	public static void returnObject(Nexus nexus) {
		LOG.debug("return " + nexus.getName() + " to pool");
		try {
			OBJECT_POOL.returnObject(nexus);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			Thread.currentThread().interrupt();
		}
	}

	public static String stat() {
		StringBuilder sb = new StringBuilder();
		sb.append("MaxActive:" + OBJECT_POOL.getMaxActive());
		sb.append("\r\n");
		sb.append("MaxIdle:" + OBJECT_POOL.getMaxIdle());
		sb.append("\r\n");
		sb.append("MaxWait:" + OBJECT_POOL.getMaxWait());
		sb.append("\r\n");
		sb.append("MinIdle:" + OBJECT_POOL.getMinIdle());
		sb.append("\r\n");
		sb.append("NumActive:" + OBJECT_POOL.getNumActive());
		sb.append("\r\n");
		sb.append("NumIdle:" + OBJECT_POOL.getNumIdle());
		sb.append("\r\n");
		return sb.toString();
	}

	public static Nexus borrowObject() {
		try {
			return (Nexus) OBJECT_POOL.borrowObject();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			Thread.currentThread().interrupt();
			return null;
		}
	}

}
