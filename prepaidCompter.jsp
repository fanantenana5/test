<%@ page import="fonction.*,jirama.*,database.*,java.sql.*,general.*" %>
<%
    String idClient = request.getParameter("idClient");
    String numComptElec = request.getParameter("numComptElec");
    Connect db = new Connect();
    Connection con = db.connexion();
    Generalise gen = new Generalise();
    Object[] liste = gen.select(con,new PrepaidCompter(),null);
    con.close();
%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>liste de offre prepayer</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>

        <table border="1">
            <tr>
                <th>Offre</th>
                <th>Prix</th>
                <th>Quantite</th>
                <th>Duree</th>
                <th></th>
            </tr>
            <%
                for(int i=0;i<liste.length;i++){ %>
                    <tr>
                    <td><% out.print(((PrepaidCompter)liste[i]).getIdPrepaidCompter()); %></td>
                    <td><% out.print(((PrepaidCompter)liste[i]).getPrix()); %></td>
                    <td><% out.print(((PrepaidCompter)liste[i]).getQuantite()); %></td>
                    <td><% out.print(((PrepaidCompter)liste[i]).getDuree()); %> jours</td>
                    <td><a href="traitementOffre.jsp?idClient=<% out.print(idClient); %>&idPrepaidCompter=<% out.print(((PrepaidCompter)liste[i]).getIdPrepaidCompter()); %>&numComptElec=<% out.print(numComptElec); %>">Choisir</a></td>
                </tr>
                <% }

            %>
        </table>

        <script src="js/jquery.min.js"></script> 
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>