#!/bin/sh
rm -rf src/org/geworkbench/components/genspace/server/stubs/

c:/Program\ Files/Java/jdk1.6.0_20/bin/wsimport.exe -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/WorkflowRepositoryService/WorkflowRepository?wsdl
c:/Program\ Files/Java/jdk1.6.0_20/bin/wsimport.exe -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/PublicFacadeService/PublicFacade?wsdl
c:/Program\ Files/Java/jdk1.6.0_20/bin/wsimport.exe -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/FriendFacadeService/FriendFacade?wsdl
c:/Program\ Files/Java/jdk1.6.0_20/bin/wsimport.exe -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/ToolUsageInformationService/ToolUsageInformation?wsdl
c:/Program\ Files/Java/jdk1.6.0_20/bin/wsimport.exe -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/UserFacadeService/UserFacade?wsdl
c:/Program\ Files/Java/jdk1.6.0_20/bin/wsimport.exe -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/UsageInformationService/UsageInformation?wsdl
c:/Program\ Files/Java/jdk1.6.0_20/bin/wsimport.exe -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/NetworkFacadeService/NetworkFacade?wsdl
c:/Program\ Files/Java/jdk1.6.0_20/bin/wsimport.exe -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/AlignmentFacadeService/AlignmentFacade?wsdl
c:/Program\ Files/Java/jdk1.6.0_20/bin/wsimport.exe -d bin/ -s src/ -p org.geworkbench.components.genspace.server.stubs http://$1:8080/AlignmentFacadeService/SequenceFacade?wsdl
