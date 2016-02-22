package org.htl.adtausfallsystem.restserverlet;

import org.htl.adtausfallsystem.Interfaces.RestServer;

public class RestFactory {

	public RestServer getServer(String typeOfServer){
		if(typeOfServer == null){
			return null;
		}
		if(typeOfServer.equalsIgnoreCase("TestServer")){
			return new TestFHIRRestServlet();
		}
		
		else if(typeOfServer.equalsIgnoreCase("Server")){
			return new FHIRRestServlet();
		}
		
		return null;
	}
}
