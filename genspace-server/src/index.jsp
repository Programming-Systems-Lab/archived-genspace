<title>genSpace Administrator : Home</title>

<body bgcolor="#003366" link="white" vlink="white" alink="white">

<font face="Arial, Helvetica" color="white">

<h1>genSpace Administrator : Home</h1>


<p>
<hr>
<p>

Get information about a user:

<form method="POST" action="user.jsp">
<select name="user">
<% 
genspace.db.UserManager um = new genspace.db.UserManager();

String[] allUsers = um.getAllUsers();

for (String user : allUsers) out.println("<option>" + user + "</option>");

%>
</select>
<input type=submit value="Submit">
</form>


<p>
<hr>
<p>

Get information about a network:

<form method="POST" action="network.jsp">
<select name="network">
<% 
genspace.db.NetworkManager nm = new genspace.db.NetworkManager();

String[] allNetworks = nm.getAllNetworks();

for (String network : allNetworks) out.println("<option>" + network + "</option>");

%>
</select>
<input type=submit value="Submit">
</form>


<p>
<hr>
<p>

Get information about a tool:

<form method="POST" action="tool.jsp">
<select name="tool">
<% 

String[] allTools = nm.getAllAnalysisTools();

for (String tool : allTools) out.println("<option>" + tool + "</option>");

%>
</select>
<input type=submit value="Submit">
</form>
