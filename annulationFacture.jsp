<%@ page import="fonction.*,jirama.*,general.*,database.*,java.sql.Connection" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Annulation facture</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="style.css">
		<script src="js/jquery.min.js"></script> 
		<script src="js/bootstrap.min.js"></script>
	</head>
	<body>
		<%
			Generalisation gen = new Generalisation();
			Connect c = new Connect();
			Connection con = c.connexion();
			Formulaire fi = new Formulaire(new FactureAvoir());
			fi.getChamp("idFactureAvoir").setVisible(false);
			fi.getChamp("idFactureAvoir").setValue(gen.autoIncrement("idFactureAvoir", con));
			fi.getChamp("idFacture").setVisible(false);
			fi.getChamp("idFacture").setValue(request.getParameter("idFacture"));
			fi.getChamp("typeAnnulation").setVisible(false);
			fi.setAction("traitementAnnulation.jsp");
			out.print(fi.getHTML());
			con.close();
		%>
	</body>
</html>