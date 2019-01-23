<%@ page import="fonction.*,jirama.*,database.*,java.sql.*,general.*" %>
<%
    Connect db = new Connect();
    Connection con = db.connexion();
    Generalise gen = new Generalise();
    Object[] liste = gen.select(con, new Client(), null);

    con.close();
%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <title>listes des clients</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="style.css">
    <head>
    <body>
        <table border="1">
            <tr>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Numero compteur</th>
				<th>Situation client</th>
            </tr>
            <% 
                for(int i=0;i<liste.length;i++){ %>
                    <tr>
                        <td><% out.print(((Client)liste[i]).getNom()); %></td>
                        <td><% out.print(((Client)liste[i]).getPrenom()); %></td>
                        <td><a href="prepaidCompter.jsp?idClient=<% out.print(((Client)liste[i]).getIdClient()); %>&numComptElec=<% out.print(((Client)liste[i]).getNumComptElec()); %>"><% out.print(((Client)liste[i]).getNumComptElec()); %></a></td>
						<td><a href="situationClient.jsp?idClient=<% out.print(((Client)liste[i]).getIdClient()); %>">Voir</a></td>
					</tr>
                <%} 
            %>
        </table>
        <script src="js/jquery.min.js"></script> 
	    <script src="js/bootstrap.min.js"></script>
    </body>
</html>