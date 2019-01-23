<%@ page import="fonction.*,jirama.*,database.*,java.sql.*,general.*" %>
<%
    Connect db = new Connect();
    Connection con = db.connexion();
    DBconnect fonc = new DBconnect();
    Generalisation g = new Generalisation();
    String idCompteur = g.autoIncrement("idCompteur",con);
    String numComptElec = request.getParameter("numComptElec");
    String idClient = request.getParameter("idClient");
    String idPrepaidCompter = request.getParameter("idPrepaidCompter");
    Compteur compteur = new Compteur(idCompteur, numComptElec, idClient, idPrepaidCompter);
    compteur.setDateExpiration(con);
    out.print(g.executeInsert(compteur,con));
    con.close();
%>