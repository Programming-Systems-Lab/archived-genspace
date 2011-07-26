#!/bin/sh
rm -rf src/org/geworkbench/components/genspace/server/stubs/
<<<<<<< HEAD
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/WorkflowRepositoryService/WorkflowRepository?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/PublicFacadeService/PublicFacade?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/FriendFacadeService/FriendFacade?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/ToolUsageInformationService/ToolUsageInformation?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/UserFacadeService/UserFacade?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/UsageInformationService/UsageInformation?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://127.0.0.1:8080/NetworkFacadeService/NetworkFacade?wsdl
=======
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/WorkflowRepositoryService/WorkflowRepository?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/PublicFacadeService/PublicFacade?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/FriendFacadeService/FriendFacade?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/ToolUsageInformationService/ToolUsageInformation?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/UserFacadeService/UserFacade?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/UsageInformationService/UsageInformation?wsdl
/Library/Java/Home/bin/wsimport -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/NetworkFacadeService/NetworkFacade?wsdl
>>>>>>> e1a70a72cf85e3e9d95495858ed1d194be4798d8
