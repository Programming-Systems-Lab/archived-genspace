package org.geworkbench.components.genspace.server.wrapper;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;


import org.geworkbench.components.genspace.GenSpaceServerFactory;
import org.geworkbench.components.genspace.RuntimeEnvironmentSettings;
import org.geworkbench.components.genspace.server.stubs.Friend;
import org.geworkbench.components.genspace.server.stubs.IncomingWorkflow;
import org.geworkbench.components.genspace.server.stubs.Network;
import org.geworkbench.components.genspace.server.stubs.ToolComment;
import org.geworkbench.components.genspace.server.stubs.Transaction;
import org.geworkbench.components.genspace.server.stubs.User;
import org.geworkbench.components.genspace.server.stubs.UserNetwork;
import org.geworkbench.components.genspace.server.stubs.Workflow;
import org.geworkbench.components.genspace.server.stubs.WorkflowComment;
import org.geworkbench.components.genspace.server.stubs.WorkflowFolder;

public class UserWrapper {
	private User delegate;
	public User getDelegate() {
		return delegate;
	}
	public UserWrapper(User delegate) {
		this.delegate = delegate;
	}
	public String getAddr1() {
		return delegate.getAddr1();
	}
	public void setAddr1(String addr1) {
		delegate.setAddr1(addr1);
	}
	public String getAddr2() {
		return delegate.getAddr2();
	}
	public void setAddr2(String addr2) {
		delegate.setAddr2(addr2);
	}
	public String getCity() {
		return delegate.getCity();
	}
	public void setCity(String city) {
		delegate.setCity(city);
	}
	public Calendar getCreatedAt() {
		return delegate.getCreatedAt();
	}
	public void setCreatedAt(Calendar createdAt) {
		delegate.setCreatedAt(createdAt);
	}
	public int getDataVisibility() {
		return delegate.getDataVisibility();
	}
	public void setDataVisibility(int dataVisibility) {
		delegate.setDataVisibility(dataVisibility);
	}
	public String getEmail() {
		return delegate.getEmail();
	}
	public void setEmail(String email) {
		delegate.setEmail(email);
	}
	public String getFirstName() {
		return delegate.getFirstName();
	}
	public void setFirstName(String firstName) {
		delegate.setFirstName(firstName);
	}
	public List<WorkflowFolder> getFolders() {
		return Arrays.asList(delegate.getFolders());
	}
	public void setFolders(WorkflowFolder[] folders) {
		delegate.setFolders(folders);
	}
	public WorkflowFolder getFolders(int i) {
		return delegate.getFolders(i);
	}
	public void setFolders(int i, WorkflowFolder _value) {
		delegate.setFolders(i, _value);
	}

	public int getId() {
		return delegate.getId();
	}
	public void setId(int id) {
		delegate.setId(id);
	}
	public IncomingWorkflow[] getIncomingWorkflows() {
		return delegate.getIncomingWorkflows();
	}
	public void setIncomingWorkflows(IncomingWorkflow[] incomingWorkflows) {
		delegate.setIncomingWorkflows(incomingWorkflows);
	}
	public IncomingWorkflow getIncomingWorkflows(int i) {
		return delegate.getIncomingWorkflows(i);
	}
	public void setIncomingWorkflows(int i, IncomingWorkflow _value) {
		delegate.setIncomingWorkflows(i, _value);
	}
	public String getInterests() {
		return delegate.getInterests();
	}
	public void setInterests(String interests) {
		delegate.setInterests(interests);
	}
	public String getLabAffiliation() {
		return delegate.getLabAffiliation();
	}
	public void setLabAffiliation(String labAffiliation) {
		delegate.setLabAffiliation(labAffiliation);
	}
	public String getLastName() {
		return delegate.getLastName();
	}
	public void setLastName(String lastName) {
		delegate.setLastName(lastName);
	}
	public int getLogData() {
		return delegate.getLogData();
	}
	public void setLogData(int logData) {
		delegate.setLogData(logData);
	}
	public Network[] getMyOwnedNetworks() {
		return delegate.getMyOwnedNetworks();
	}
	public void setMyOwnedNetworks(Network[] myOwnedNetworks) {
		delegate.setMyOwnedNetworks(myOwnedNetworks);
	}
	public Network getMyOwnedNetworks(int i) {
		return delegate.getMyOwnedNetworks(i);
	}
	public void setMyOwnedNetworks(int i, Network _value) {
		delegate.setMyOwnedNetworks(i, _value);
	}
	public UserNetwork[] getNetworks() {
		return delegate.getNetworks();
	}
	public void setNetworks(UserNetwork[] networks) {
		delegate.setNetworks(networks);
	}
	public UserNetwork getNetworks(int i) {
		return delegate.getNetworks(i);
	}
	public void setNetworks(int i, UserNetwork _value) {
		delegate.setNetworks(i, _value);
	}
	public int getOnlineStatus() {
		return delegate.getOnlineStatus();
	}
	public void setOnlineStatus(int onlineStatus) {
		delegate.setOnlineStatus(onlineStatus);
	}
	public IncomingWorkflow[] getOutgoingWorkflows() {
		return delegate.getOutgoingWorkflows();
	}
	public void setOutgoingWorkflows(IncomingWorkflow[] outgoingWorkflows) {
		delegate.setOutgoingWorkflows(outgoingWorkflows);
	}
	public IncomingWorkflow getOutgoingWorkflows(int i) {
		return delegate.getOutgoingWorkflows(i);
	}
	public void setOutgoingWorkflows(int i, IncomingWorkflow _value) {
		delegate.setOutgoingWorkflows(i, _value);
	}
	public String getPhone() {
		return delegate.getPhone();
	}
	public void setPhone(String phone) {
		delegate.setPhone(phone);
	}
	public WorkflowFolder getRootFolder() {
		return delegate.getRootFolder();
	}
	public void setRootFolder(WorkflowFolder rootFolder) {
		delegate.setRootFolder(rootFolder);
	}
	public String getState() {
		return delegate.getState();
	}
	public void setState(String state) {
		delegate.setState(state);
	}
	public ToolComment[] getToolComments() {
		return delegate.getToolComments();
	}
	public void setToolComments(ToolComment[] toolComments) {
		delegate.setToolComments(toolComments);
	}
	public ToolComment getToolComments(int i) {
		return delegate.getToolComments(i);
	}
	public void setToolComments(int i, ToolComment _value) {
		delegate.setToolComments(i, _value);
	}
	public Transaction[] getTransactions() {
		return delegate.getTransactions();
	}
	public void setTransactions(Transaction[] transactions) {
		delegate.setTransactions(transactions);
	}
	public Transaction getTransactions(int i) {
		return delegate.getTransactions(i);
	}
	public void setTransactions(int i, Transaction _value) {
		delegate.setTransactions(i, _value);
	}
	public String getUsername() {
		return delegate.getUsername();
	}
	public void setUsername(String username) {
		delegate.setUsername(username);
	}
	public boolean isVisible() {
		return delegate.isVisible();
	}
	public void setVisible(boolean visible) {
		delegate.setVisible(visible);
	}
	public String getWorkTitle() {
		return delegate.getWorkTitle();
	}
	public void setWorkTitle(String workTitle) {
		delegate.setWorkTitle(workTitle);
	}
	public WorkflowComment[] getWorkflowComments() {
		return delegate.getWorkflowComments();
	}
	public void setWorkflowComments(WorkflowComment[] workflowComments) {
		delegate.setWorkflowComments(workflowComments);
	}
	public WorkflowComment getWorkflowComments(int i) {
		return delegate.getWorkflowComments(i);
	}
	public void setWorkflowComments(int i, WorkflowComment _value) {
		delegate.setWorkflowComments(i, _value);
	}
	public Workflow[] getWorkflows() {
		return delegate.getWorkflows();
	}
	public void setWorkflows(Workflow[] workflows) {
		delegate.setWorkflows(workflows);
	}
	public Workflow getWorkflows(int i) {
		return delegate.getWorkflows(i);
	}
	public void setWorkflows(int i, Workflow _value) {
		delegate.setWorkflows(i, _value);
	}
	public String getZipcode() {
		return delegate.getZipcode();
	}
	public void setZipcode(String zipcode) {
		delegate.setZipcode(zipcode);
	}

	
	public String getFullName() {
		return this.getFirstName() + " " + this.getLastName();
	}
	public boolean isFriends()
	{
		return isFriendsWith(GenSpaceServerFactory.getUser()) != null;
	}
	public Friend isFriendsWith(User u) {
		for(Friend f: getFriends())
		{
			if(f.getRightUser().equals(u))
			{
				setFriendsWith(true);
				return f;
			}
		}
		setFriendsWith(false);
		return null;
	}
	
	public UserNetwork isInNetwork(Network n) {
		for(UserNetwork un : getNetworks())
		{
			if(un.getNetwork().equals(n))
				return un;
		}
		return null;
	}
	private boolean isVisibleTo(User other)
	{
		Friend f = isFriendsWith(other);
		if(f != null && f.isVisible())
		{
			return true;
		}
		//Check the networks
		for(UserNetwork u1 : this.getNetworks())
		{
			if(u1.isVisible())
				for(UserNetwork u2 : other.getNetworks())
				{
					if(u2.getNetwork().equals(u1.getNetwork()))
						return true;
				}
		}
		return false;
	}
	private String na(String s)
	{
		return (s == null || s.length() == 0 ? "N/A" : s);
	}
	public String getFullNameWUsername()
	{
		if(getFirstName().length() == 0 && getLastName().length() ==0)
			return getUsername();
		else
			return getFirstName() + " "
			+ getLastName() + " (" + getUsername() + ")";
	}
	
	public String toHTML() {
		String r = "<html><body><b>" +getFullNameWUsername() + "</b><br>";
		if (GenSpaceServerFactory.isVisible(delegate)) {
			r += "<i>"
					+ (getWorkTitle() != null
							&& getWorkTitle() != "" ? getWorkTitle() + " at " : "")
					+ (getLabAffiliation() != null ? getLabAffiliation():  " (affiliation not disclosed)") + "</i><br><br>";
			r += "<b>Research Interests:</b><br />"
					+(getInterests() == null ? "(not disclosed)" : getInterests()) + "<br><br>";
			r += "<b>Contact information:</b><br /><br>Phone: "
					+ na(getPhone()) + "<br>Email: "
					+ na(getEmail()) + "<br><br>Mailing Address:<br>"
					+ (getAddr1().length() == 0 && getAddr2().length() == 00 && getCity().length() == 0 && getState().length() == 0 && getZipcode().length() == 0 ? "not provided" : 
						 na(getAddr1()) + "<br>" + na(getAddr2())
					+ "<br>" + na(getCity()) + ", "
					+ na(getState()) + ", " + na(getZipcode()));
		} else {
			r += "This user is not visible to you. Please add them as a friend or join one of their networks to see their profile.";
		}
		r += "</body>";
		r += "</html>";
		return r;
	}
	public String getShortName() {
		if(getFirstName() != null && !getFirstName().equals(""))
			return getFirstName();
		return getUsername();
	}
	public List<User> getFriendsProfiles() {
		ArrayList<User> ret = new ArrayList<User>();
		
		for(Friend f: delegate.getFriends())
			ret.add(f.getRightUser());
		return ret;
	}
	public boolean containsFolderByName(String folderName) {
		for(WorkflowFolder f : getFolders())
			if(f.getName().equals(folderName))
				return true;
		return false;
	}
	public void setPasswordClearText(String password)
	{
		delegate.setPassword(this.getEncryptedPassword(password.toCharArray()));

	}
	protected final static String HEX_DIGITS = "0123456789abcdef";

	private String getEncryptedPassword(char[] c_password) {
		String plaintext = new String(c_password);
		
		java.security.MessageDigest d =null;
				try {
					d = java.security.MessageDigest.getInstance("SHA-1");
				} catch (NoSuchAlgorithmException e) {
//					GenSpaceServerFactory.logger.error("Error",e);
				}
				d.reset();
				d.update(plaintext.getBytes());
				byte[] hashedBytes =  d.digest();
				StringBuffer sb = new StringBuffer(hashedBytes.length * 2);
		        for (int i = 0; i < hashedBytes.length; i++) {
		             int b = hashedBytes[i] & 0xFF;
		             sb.append(HEX_DIGITS.charAt(b >>> 4)).append(HEX_DIGITS.charAt(b & 0xF));
		        }
		        return sb.toString();	
	}
	public User loadVisibility(User from) {
		setVisible(isVisibleTo(from));
		return delegate;
	}
	public Friend[] getFriends() {
		return delegate.getFriends();
	}
	public void setFriends(Friend[] friends) {
		delegate.setFriends(friends);
	}
	public Friend getFriends(int i) {
		return delegate.getFriends(i);
	}
	public void setFriends(int i, Friend _value) {
		delegate.setFriends(i, _value);
	}
	public boolean isFriendsWith() {
		return delegate.isFriendsWith();
	}
	
	public void setFriendsWith(boolean friendsWith) {
		delegate.setFriendsWith(friendsWith);
	}
	@Override
	public int hashCode() {
		return this.getId();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User)
		{
			return ((User) obj).getId() == this.getId();
		}
		return false;
	}
	public int compareTo(UserWrapper o) {
		int r = this.getLastName().compareTo(o.getLastName());
		if(r == 0)
			return this.getFirstName().compareTo(o.getFirstName());
		return r;
	}
}
