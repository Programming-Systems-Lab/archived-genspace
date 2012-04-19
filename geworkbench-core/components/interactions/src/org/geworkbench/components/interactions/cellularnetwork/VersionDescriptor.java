package org.geworkbench.components.interactions.cellularnetwork;

/**
 * Created by Min You  
 */

/**
 * The  Version Descriptor of one Dataset.
 * @version $Id: VersionDescriptor.java 9234 2012-03-28 16:28:06Z zji $
 */
public class VersionDescriptor {
    private final String version;    
    private final boolean requiresAuthentication;
    private final String versionDesc;
    

    public VersionDescriptor(final String version, final boolean requiresAuthentication, final String versionDesc) {
        this.version = version;
        this.requiresAuthentication = requiresAuthentication;
        this.versionDesc = versionDesc;
    }

    public String getVersion() {
        return version;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public boolean getRequiresAuthentication() {
        return requiresAuthentication;
    }
   
}
