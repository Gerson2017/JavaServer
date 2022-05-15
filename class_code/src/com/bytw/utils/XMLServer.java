package com.bytw.utils;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Element;


@Root(name = "server")
public class XMLServer {
	
	@Element(name = "serverId",required = true)
	public int serverId;
	@Element(required = true)
	public int serverPort;
	@Element(required = true)
	public int adminPort;
	@Element(required = true)
	public String whiteIps;
	@Element(required = true)
	public String matchUrl;
	@Element(required = true)
	public boolean fight;
	@Element(required = true)
	public int crossPort;
	@Element(required = true)
	public String redisUrl;
	
}
