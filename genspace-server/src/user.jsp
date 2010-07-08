<%
String user = request.getParameter("user");
%>


<title>genSpace Administrator : User <%= user %></title>

<body bgcolor="#003366" link="white" vlink="white" alink="white">

<font face="Arial, Helvetica" color="white">

<h1>genSpace Administrator : User <%= user %></h1>

<a href="index.jsp">&lt;&lt; Home</a>


<!-- would be nice to have some "real" information here, like name, email, etc. -->

<p>
<hr>
<p>

<b>Social Networks</b>
<p>

<% 
genspace.db.NetworkManager nm = new genspace.db.NetworkManager();

String[] allNetworks = nm.getNetworksByUser(user);

if (allNetworks == null || allNetworks.length == 0) out.println("The user is not in any networks");
else 
{
%>

This user's networks:

<form method="POST" action="network.jsp">
<select name="network">

<%
for (String network : allNetworks) out.println("<option>" + network + "</option>");


%>
</select>
<input type=submit value="See more information">
</form>
<%
}
%>



<p>
<hr>
<p>

<b>genSpace Friends</b>
<p>

<% 
genspace.db.UserManager um = new genspace.db.UserManager();

String[] friends = um.getFriends(user);

if (friends == null || friends.length == 0) out.println("The user has no genSpace friends");
else 
{
%>

This user's friends:

<form method="POST" action="user.jsp">
<select name="user">

<%
for (String friend : friends) out.println("<option>" + friend + "</option>");


%>
</select>
<input type=submit value="See more information">
</form>
<%
}
%>



<p>
<hr>
<p>

<b>Analysis Tools</b>
<p>

<% 

java.util.HashMap<String, Double> tools = um.getAnalysisByUser(user);

if (tools == null || tools.size() == 0) out.println("The user has not used any analysis tools");
else 
{
%>

This user's analysis tools:

<form method="POST" action="tool.jsp">
<select name="tool">

<%
for (String tool : tools.keySet()) out.println("<option>" + tool + "</option>");


%>
</select>
<input type=submit value="See more information">
</form>
<%
}
%>


<% 
genspace.db.PopularToolManager ptm = new genspace.db.PopularToolManager();

String mostPopularTool = ptm.getMostPopularToolInUsersNetworks(user);

if (mostPopularTool != null)
{
%>
The most popular tool in this user's network(s) is <a href="tool.jsp?tool=<%=mostPopularTool%>"><%=mostPopularTool%></a>.
<%
} // end else
%>

<!-- what tool is this user an expert on? -->
<%
genspace.db.SuggestionManager sm = new genspace.db.SuggestionManager();

String[] allTools = sm.getAllAnalysisTools();

for (String tool : allTools)
{
	String expert = sm.suggestExpert(tool, "anonymous", false);
	
	if (expert != null && expert.equals(user)) 
	{
	%>
	    This user is an expert for the tool <a href="tool.jsp?tool=<%=tool%>"><%=tool%></a><br>
	<%
	} // end if

}
%>



<!-- here we should list all of the user's workflows -->

<!-- also maybe some stuff to add or remove from a network, same with friends -->

<!-- inclusions and exclusions, with ability to modify -->

<!-- opt in or out -->





