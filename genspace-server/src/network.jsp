<%
String network = request.getParameter("network");
%>


<title>genSpace Administrator : Network <%= network %></title>

<body bgcolor="#003366" link="white" vlink="white" alink="white">

<font face="Arial, Helvetica" color="white">

<h1>genSpace Administrator : Network <%= network %></h1>

<a href="index.jsp">&lt;&lt; Home</a>



<p>
<hr>
<p>

<b>Users</b>
<p>

<% 
genspace.db.NetworkManager nm = new genspace.db.NetworkManager();

String[] users = nm.getUsersByNetwork(network);

if (users == null || users.length == 0) out.println("There are no users in this network");
else 
{
%>

This network's users:

<form method="POST" action="user.jsp">
<select name="user">

<%
for (String user : users) out.println("<option>" + user + "</option>");


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

<b>Most Popular Tool</b>
<p>

<% 
genspace.db.PopularToolManager ptm = new genspace.db.PopularToolManager();

String mostPopularTool = ptm.getMostPopularToolInNetwork(network);

if (mostPopularTool == null) out.println("There are no tools used in this network");
else
{
%>
The most popular tool in this network is <a href="tool.jsp?tool=<%=mostPopularTool%>"><%=mostPopularTool%></a>.
<%
} // end else
%>

<!-- something about creating a new network -->

<!-- tools used by people in this network -->