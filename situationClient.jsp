<%@ page import="fonction.*,jirama.*,database.*,java.sql.*,general.*" %>
<%
	String idClient = request.getParameter("idClient");
	Connect c = new Connect();
	Generalise g = new Generalise();
	Generalisation gen = new Generalisation();
	Connection con = c.connexion();
	Object[] liste = g.select(con, new Facture(), "idclient = '"+idClient+"'");
	Object[] factureAvoirs = g.selectOpt(con, new FactureAvoir(), "idFacture IN (select idFacture from facture where idClient = '"+idClient+"')");
	double montantFactureAvoir = 0;
	if(factureAvoirs.length > 0){
		montantFactureAvoir = gen.somme(factureAvoirs, "montant");
	}
	double montantFacture = gen.somme(liste, "montantTotal");
	con.close();
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<title>Situation du client</title>
	</head>
	<body>
		<table border="1">
            <tr>
                <th>idFacture</th>
                <th>idPrelevement</th>
                <th>Date</th>
                <th>MontantTotal</th>
                <th>idClient</th>
            </tr>
            <% 
               /* for(int i=0;i<liste.length;i++){ %>
                    <tr>
                        <td><% out.print(((Facture)liste[i]).getIdFacture()); %></td>
                        <td><% out.print(((Facture)liste[i]).getIdPrelevement()); %></td>
                        <td><% out.print(((Facture)liste[i]).getDateFacture()); %></td>
                        <td><% out.print(((Facture)liste[i]).getMontantTotal()); %></td>
                        <td><% out.print(((Facture)liste[i]).getIdClient()); %></td>
                        
                    </tr>
                <%} */ 
            %>
        </table>
		
		<h1>Prix total du facture : <% out.print(montantFacture); %></h1>
		<h1>Prix total du facture annulee : <% out.print(montantFactureAvoir); %></h1>
		<h1>Prix total a payer : <% out.print(montantFacture+montantFactureAvoir); %></h1>
	
	</body>
</html>