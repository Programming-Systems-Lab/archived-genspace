package org.geworkbench.components.genspace.server;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;

import org.geworkbench.components.genspace.entity.AnalysisEvent;
import org.geworkbench.components.genspace.entity.Tool;
import org.geworkbench.components.genspace.entity.Transaction;
import org.geworkbench.components.genspace.entity.User;
import org.geworkbench.components.genspace.entity.Workflow;

@Local
@RolesAllowed("user")
public interface UsageInformationLocal {


}
