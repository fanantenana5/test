<%@ page import="jirama.*,java.sql.Connection,java.sql.Date,general.*,database.*" %>
<%
	String idFactureAvoir = request.getParameter("idFactureAvoir");
	String idFacture = request.getParameter("idFacture");
	String montant = request.getParameter("montant");
	String dateFactureAvoir = request.getParameter("dateFactureAvoir");
	Database db = new Database();
	Generalisation gen = new Generalisation();
	Connect c = new Connect();
	Connection con = c.connexion();
	FactureAvoir fa = new FactureAvoir();
	try{
		fa.setIdFactureAvoir(idFactureAvoir);
		fa.setIdFacture(idFacture);
		//fa.setMontant(montant);
		fa.setNegativeMontant(montant);
		fa.setDateFactureAvoir(dateFactureAvoir);
		fa.save();
		gen.executeInsert(fa, con);

		if(db.annulationFacture(fa) == 0){
			response.sendRedirect("index.jsp");
		}
	} catch(Exception e){
		out.print(e.getMessage()+"<br/>");
	} finally{
		con.close();
	}
%>
