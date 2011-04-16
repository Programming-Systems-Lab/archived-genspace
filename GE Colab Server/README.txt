AXIS2 web service setup for geWorkbench Collaboration 

1. Environment
Java:   jdk1.6.0_22
Ant:    apache-ant-1.7.0
Axis2:  axis2-1.5.4
Tomcat: apache-tomcat-7.0.6

2. Source check out
Axis2: check out axis2-1.5.4(dev/collaboration) from cvs
         server: login.c2b2.columbia.edu
	 CVSROOT: /cvs/magnet/geworkbench
	 module:  dev/collaboration
Tomcat: download apache-tomcat-7.0.6 from tomcat website

3. Configuration and setup
export AXIS2_HOME=your_cvs_checkout_dir/dev/collaboration
export CATALINA_HOME=your_tomcat_installation_dir/apache-tomcat-7.0.6

cp $AXIS2_HOME/war/axis2.war $CATALINA_HOME/webapps
start tomcat
cp $AXIS2_HOME/war/mysql-connector-java-5.1.14-bin.jar $CATALINA_HOME/webapps/axis2/WEB-INF/lib

Edit $CATALINA_HOME/webapps/axis2/WEB-INF/conf/axis2.xml:
change lines 33-35:
       <!--parameter name="cacheAttachments">true</parameter>
       <parameter name="attachmentDIR"></parameter>
       <parameter name="sizeThreshold">4000</parameter-->
into:
	<parameter name="cacheAttachments">true</parameter>
	<parameter name="attachmentDIR">axis2cache</parameter>
	<parameter name="sizeThreshold">4000</parameter>

4. If you are going to create and use workspace_db on your own server:
   use dev/collaboration/createdb.sql to create workspace_db;
   change database server/dbname/user/password information in 
   	  $AXIS2_HOME/samples/mtom/src/sample/mtom/service/MTOMSampleSkeleton.java, and
  	  $AXIS2_HOME/samples/testdld/src/sample/service/WorkspaceService.java, and
  	  wspRoot directory name for storing workspace files in MTOMSampleSkeleton.java.

5. Build and deploy
cd $AXIS2_HOME/samples/mtom
ant generate.service
cp build/service/sample-mtom.aar $CATALINA_HOME/webapps/axis2/WEB-INF/services

cd $AXIS2_HOME/samples/testdld
ant generate.service
cp build/download-workspace-service.aar $CATALINA_HOME/webapps/axis2/WEB-INF/services

6. Check Service
If tomcat is not started, start tomcat.
Go to service url:
http://servername:8080/axis2/services/listServices
Under "Available services", there should be
"WorkspaceService", "MTOMSample" and "Version".
