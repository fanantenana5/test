<%@ page import="fonction.*,connect.*,general.*,java.sql.*,jirama.*"%>
<html>
	<head>
		<title>java setter et getter</title>
	</head>
<body>
	<%
		Generalisation g = new Generalisation();
		out.print(g.getCodeClass(new PrelevementAnnulee()));
	%>
</body>
<html>