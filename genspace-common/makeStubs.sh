#!/bin/sh
rm -rf src/org/geworkbench/components/genspace/server/stubs/
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/WorkflowRepositoryService/WorkflowRepository?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/PublicFacadeService/PublicFacade?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/FriendFacadeService/FriendFacade?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/ToolUsageInformationService/ToolUsageInformation?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/UserFacadeService/UserFacade?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/UsageInformationService/UsageInformation?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/NetworkFacadeService/NetworkFacade?wsdl