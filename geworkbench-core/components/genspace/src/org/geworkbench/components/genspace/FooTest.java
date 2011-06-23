package org.geworkbench.components.genspace;

import org.geworkbench.components.genspace.server.stubs.PublicFacade;
import org.geworkbench.components.genspace.server.stubs.PublicFacadeService;
import org.geworkbench.components.genspace.server.stubs.Tool;
import org.geworkbench.components.genspace.server.stubs.User;

public class FooTest {
public static void main(String[] args) {

	PublicFacadeService svcsvc = new PublicFacadeService();
	
	PublicFacade svc = svcsvc.getPublicFacadePort();
	for(Tool t : svc.getAllTools())
	{
		System.out.println(t.getName());	
	}
	User u = svc.getExpertUserFor(22);
	System.out.println(u.getUsername());
}
}
