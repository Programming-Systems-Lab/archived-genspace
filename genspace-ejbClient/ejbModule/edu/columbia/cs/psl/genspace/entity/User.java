package edu.columbia.cs.psl.genspace.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class User {
	
	private int id;
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
	private Set<Friend> friends = new HashSet<Friend>();
	private Set<Network> networks = new HashSet<Network>();
	
	private Set<Workflow> workflows = new HashSet<Workflow>();
	private Set<WorkflowFolder> folders = new HashSet<WorkflowFolder>();
	private Set<WorkflowComment> workflowComments = new HashSet<WorkflowComment>();
	private Set<ToolComment> toolComments = new HashSet<ToolComment>();
	private Set<Transaction> transactions = new HashSet<Transaction>();
	private Set<IncomingWorkflow> incomingWorkflows = new HashSet<IncomingWorkflow>();
	private Set<IncomingWorkflow> outgoingWorkflows = new HashSet<IncomingWorkflow>();
	private Set<UserWorkflow> myWorkflows = new HashSet<UserWorkflow>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getWorkTitle() {
		return workTitle;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLabAffiliation() {
		return labAffiliation;
	}
	public void setLabAffiliation(String labAffiliation) {
		this.labAffiliation = labAffiliation;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public int getOnlineStatus() {
		return onlineStatus;
	}
	public void setOnlineStatus(int onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
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
	public Set<Friend> getFriends() {
		return friends;
	}
	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}
	@ManyToMany(mappedBy="members")
	public Set<Network> getNetworks() {
		return networks;
	}
	public void setNetworks(Set<Network> networks) {
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
	public Set<IncomingWorkflow> getIncomingWorkflows() {
		return incomingWorkflows;
	}
	public void setIncomingWorkflows(Set<IncomingWorkflow> incomingWorkflows) {
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
}
