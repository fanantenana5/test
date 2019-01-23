<%@ page import="database.*,jirama.*,general.*,java.sql.Connection" %>
<%
	Connect c = new Connect();
	Connection con = c.connexion();
	Generalise g = new Generalise();
		
	Object[] liste = g.select(con, new Facture(), "idFacture NOT IN (select idFacture from factureAvoir)");
	out.print("liste "+liste.length);
	con.close();
%>

<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>listes des factures</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="style.css">
    <head>
    <body>
        <table border="1">
            <tr>
                <th>idFacture</th>
                <th>idPrelevement</th>
                <th>Date</th>
                <th>MontantTotal</th>
                <th>IdClient</th>
				<th></th>
            </tr>
            <% 
                for(int i=0;i<liste.length;i++){ %>
                    <tr>
                        <td><% out.print(((Facture)liste[i]).getIdFacture()); %></td>
                        <td><% out.print(((Facture)liste[i]).getIdPrelevement()); %></td>
                        <td><% out.print(((Facture)liste[i]).getDateFacture()); %></td>
                        <td><% out.print(((Facture)liste[i]).getMontantTotal()); %></td>
                        <td><% out.print(((Facture)liste[i]).getIdClient()); %></td>
                        <td><a href="annulationFacture.jsp?idFacture=<% out.print(((Facture)liste[i]).getIdFacture()); %>">Annulee</a></td>
                    </tr>
                <%} 
            %>
        </table>
        <script src="js/jquery.min.js"></script> 
	    <script src="js/bootstrap.min.js"></script>
    </body>
</html>