<%
String tool = request.getParameter("tool");
%>


<title>genSpace Administrator : Tool <%= tool %></title>

<body bgcolor="#003366" link="white" vlink="white" alink="white">

<font face="Arial, Helvetica" color="white">

<h1>genSpace Administrator : Tool <%= tool %></h1>

<a href="index.jsp">&lt;&lt; Home</a>


<p>
<hr>
<p>

<b>Users</b>
<p>

<% 
genspace.db.DatabaseManager dm = new genspace.db.DatabaseManager();

String[] users = dm.getUsersByAnalysis(tool);

if (users == null || users.length == 0) out.println("There are no users of this tool");
else 
{
%>

This tool's users:

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

<b>Expert</b>
<p>

<% 
genspace.db.SuggestionManager sm = new genspace.db.SuggestionManager();

// anonymous means to exclude that user... doesn't actually matter what we use here, as long as it's not a real user
// false means don't search in that user's networks... duh
String expert = sm.suggestExpert(tool, "anonymous", false);

if (expert == null) out.println("There is no expert for this tool");
else
{
%>
The expert for this tool is <a href="user.jsp?user=<%=expert%>"><%=expert%></a>.
<%
} // end else
%>


<p>
<hr>
<p>

<b>Workflows</b>
<p>

<% 
genspace.db.WorkflowManager wm = new genspace.db.WorkflowManager();

// anonymous means to exclude that user... doesn't actually matter what we use here, as long as it's not a real user
// false means don't search in that user's networks... duh
java.util.HashMap<String, Double> workflows = wm.getAllWorkflows(tool, "anonymous", false);

if (workflows == null || workflows.size() == 0) out.println("There are no workflows for this tool");
else
{
   out.println("The workflows including this tool are as follows:<br>");
   for (String workflow : workflows.keySet()) out.println("<li>" + workflow + "<br>");
   out.println("<p>");
   
   String mostCommonContaining = wm.getMostRepeatedWorkflowContaining(tool, "anonymous", false);
   if (mostCommonContaining != null) out.println("The most common workflow containing this tool is:<br>" + mostCommonContaining);
   out.println("<p>");
   
   String mostCommonStartingWith = wm.getMostRepeatedWorkflowStartingWith(tool, "anonymous", false);
   if (mostCommonStartingWith != null) out.println("The most common workflow starting with this tool is:<br>" + mostCommonStartingWith);
   out.println("<p>");
   
   
} // end else
%>

