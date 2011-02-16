package org.geworkbench.components.genspace.entity;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.tree.DefaultMutableTreeNode;

import org.geworkbench.components.genspace.GenSpace;
import org.geworkbench.components.genspace.LoginManager;
import org.geworkbench.components.genspace.workflowRepository.WorkflowNode;

@Entity
@Table(name="registration")
public class User implements Serializable{
	
	private static final long serialVersionUID = 2972043173442132575L;
	private int id;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String workTitle;
	private String phone;
	private String labAffiliation;
	private String addr1;
	private String addr2;
	private String city;
	private String state;
	private String zipcode;
	private int onlineStatus;
	private String interests;
	private int logData;
	private int dataVisibility;
	private java.util.Date createdAt;
	private List<Friend> friends = new ArrayList<Friend>(); 
	private List<UserNetwork> networks = new ArrayList<UserNetwork>();
	
	private Set<Workflow> workflows = new HashSet<Workflow>();
	private Set<WorkflowFolder> folders = new HashSet<WorkflowFolder>();
	private Set<WorkflowComment> workflowComments = new HashSet<WorkflowComment>();
	private Set<ToolComment> toolComments = new HashSet<ToolComment>();
	private Set<Transaction> transactions = new HashSet<Transaction>();
	private List<IncomingWorkflow> incomingWorkflows = new ArrayList<IncomingWorkflow>();
	private Set<IncomingWorkflow> outgoingWorkflows = new HashSet<IncomingWorkflow>();
	private Set<UserWorkflow> myWorkflows = new HashSet<UserWorkflow>();
	private Set<Network> myOwnedNetworks = new HashSet<Network>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(length=50, nullable = false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(length=50, nullable = false)
	private String getPassword() {
		return password;
	}
	private void setPassword(String password) {
		this.password = password;
	}
	
	@Column(length=50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="first_name", length=50, nullable = false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="last_name", length=50, nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="work_title", length=50)
	public String getWorkTitle() {
		return workTitle;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	
	@Column(length=50)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="lab_affiliation", length=100, nullable = false)
	public String getLabAffiliation() {
		return labAffiliation;
	}
	public void setLabAffiliation(String labAffiliation) {
		this.labAffiliation = labAffiliation;
	}
	
	@Column(length=50)
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	
	@Column(length=50)
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	
	@Column(length=50)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(length=50)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(length=5)
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@Column(name="online_status")
	public int getOnlineStatus() {
		return onlineStatus;
	}
	public void setOnlineStatus(int onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	
	@Lob
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	
	public int getLogData() {
		return logData;
	}
	public void setLogData(int logData) {
		this.logData = logData;
	}
	public int getDataVisibility() {
		return dataVisibility;
	}
	public void setDataVisibility(int dataVisibility) {
		this.dataVisibility = dataVisibility;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public java.util.Date getCreatedAt() { 
		return createdAt;
	}
	public void setCreatedAt(java.util.Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@OneToMany(mappedBy="leftUser")
	public List<Friend> getFriends() { 
		return friends;
	}
	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}
	@OneToMany(mappedBy="user")
	public List<UserNetwork> getNetworks() {
		return networks;
	}
	public void setNetworks(List<UserNetwork> networks) {
		this.networks = networks;
	}
	
	@OneToMany(mappedBy="creator")
	public Set<Workflow> getWorkflows() {
		return workflows;
	}
	public void setWorkflows(Set<Workflow> workflows) {
		this.workflows = workflows;
	}
	
	@OneToMany(mappedBy="owner")
	public Set<WorkflowFolder> getFolders() {
		return folders;
	}
	public void setFolders(Set<WorkflowFolder> folders) {
		this.folders = folders;
	}
	
	@OneToMany(mappedBy="creator")
	public Set<WorkflowComment> getWorkflowComments() {
		return workflowComments;
	}
	public void setWorkflowComments(Set<WorkflowComment> workflowComments) {
		this.workflowComments = workflowComments;
	}
	
	@OneToMany(mappedBy="creator")
	public Set<ToolComment> getToolComments() {
		return toolComments;
	}
	public void setToolComments(Set<ToolComment> toolComments) {
		this.toolComments = toolComments;
	}
	
	@OneToMany(mappedBy="user")
	public Set<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	@OneToMany(mappedBy="receiver")
	public List<IncomingWorkflow> getIncomingWorkflows() {
		return incomingWorkflows;
	}
	public void setIncomingWorkflows(List<IncomingWorkflow> incomingWorkflows) {
		this.incomingWorkflows = incomingWorkflows;
	}
	
	@OneToMany(mappedBy="sender")
	public Set<IncomingWorkflow> getOutgoingWorkflows() {
		return outgoingWorkflows;
	}
	public void setOutgoingWorkflows(Set<IncomingWorkflow> outgoingWorkflows) {
		this.outgoingWorkflows = outgoingWorkflows;
	}
	
	@OneToMany(mappedBy="owner")
	public Set<UserWorkflow> getMyWorkflows() {
		return myWorkflows;
	}
	public void setMyWorkflows(Set<UserWorkflow> myWorkflows) {
		this.myWorkflows = myWorkflows;
	}
	
	@OneToMany(mappedBy="owner")
	public Set<Network> getMyOwnedNetworks() {
		return myOwnedNetworks;
	}
	public void setMyOwnedNetworks(Set<Network> myOwnedNetworks) {
		this.myOwnedNetworks = myOwnedNetworks;
	}
	
	@Override
	public String toString() {
		String result = "User - username: " + username + "\n";
		result += "UserWorkflows: " + workflows.size() + "\n";
		for (UserWorkflow uw : myWorkflows) {
			result += uw.toString() + "\n";
		}

		result += "Inbox: " + incomingWorkflows.size() + "\n";
		for (IncomingWorkflow wi : incomingWorkflows) {
			result += wi.toString() + "\n";
		}
		return result;
	}

	public void setPasswordClearText(String password)
	{
		this.setPassword(this.getEncryptedPassword(password.toCharArray()));

	}
	
	public boolean passwordMatches(String password)
	{
		return this.getEncryptedPassword(password.toCharArray()).equals(this.getPassword());
	}


	public void addUserWorkflowTree(WorkflowNode rootNode) {

		HashMap<String, DefaultMutableTreeNode> folders = new HashMap<String, DefaultMutableTreeNode>();
		// first add all folders
		// Whenever a folder was added in the ADD function the list of folders
		// is ordered by name
		// so we don't worry about it here.Å
		for (WorkflowFolder f : this.getFolders()) {
			DefaultMutableTreeNode fnode = new DefaultMutableTreeNode(f);
			folders.put(f.getName(), fnode);
			rootNode.add(fnode);
		}
		// add workflows to folders
		for (UserWorkflow w : myWorkflows) {
			if (w.getFolder() == null) {
				rootNode.add(new WorkflowNode(w));
			} else {
				DefaultMutableTreeNode folderNode = folders.get(w.getFolder());
				if (folderNode == null) {
					folderNode = new DefaultMutableTreeNode(w.getFolder());
					folders.put(w.getFolder().getName(), folderNode);
					rootNode.add(folderNode);
				}
				folderNode.add(new WorkflowNode(w));
			}
		}
	}
	
	protected final static String HEX_DIGITS = "0123456789abcdef";

	/**
	 * This method is used to encrypt the password using SHA encryption.
	 * 
	 * @param password
	 *            represents the password to be encrypted
	 * @return returns password in encrypted form
	 */
	private String getEncryptedPassword(char[] c_password) {
		String plaintext = new String(c_password);
		
		java.security.MessageDigest d =null;
				try {
					d = java.security.MessageDigest.getInstance("SHA-1");
				} catch (NoSuchAlgorithmException e) {
					GenSpace.logger.error("Error",e);
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
	public String getFullName() {
		return this.getFirstName() + " " + this.getLastName();
	}
	public Friend isFriendsWith(User u) {
		for(Friend f: getFriends())
		{
			if(f.getRightUser().equals(u))
				return f;
		}
		return null;
	}
	public String toHTML() {
		String r = "<html><body><b>" + getFirstName() + " "
		+ getLastName() + " (" + getUsername() + ")</b><br>";
		if (LoginManager.isVisible(this)) {
			r += "<i>"
					+ (getWorkTitle() != null
							&& getWorkTitle() != "" ? getWorkTitle() + " at " : "")
					+ (getLabAffiliation() != null ? getLabAffiliation():  " (affiliation not disclosed)") + "</i><br><br>";
			r += "<b>Research Interests:</b><br />"
					+getInterests() + "<br><br>";
			r += "<b>Contact information:</b><br /><br>Phone: "
					+ getPhone() + "<br>Email: "
					+getEmail() + "<br><br>Mailing Address:<br>"
					+ getAddr1() + "<br>" +getAddr2()
					+ "<br>" + getCity() + ", "
					+ getState() + ", " + getZipcode();
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
		for(Friend f: getFriends())
			ret.add(f.getRightUser());
		return ret;
	}
}
