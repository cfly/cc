package org.caofei.cc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.BasePoolableObjectFactory;


public class NexusObjectFactory extends BasePoolableObjectFactory {
	private static final Log log = LogFactory.getLog(NexusObjectFactory.class);

	@Override
	public Object makeObject() throws Exception {
		log.debug("make a Nexus");
		return new Nexus();
	}
	
}
