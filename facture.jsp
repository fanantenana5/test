<%@ page import="database.*,jirama.*,general.*" %>
<%
	Prelevement p = new Prelevement();
	DBconnect connex=new DBconnect();
	Connect con=new Connect();
	Client cl=new Client();
	Tarif t=new Tarif();
	String index = request.getParameter("indexCompteurElec");
	String ancientIndex = request.getParameter("ancientIndexElec");
	String numCompt = request.getParameter("numCompt");
	int i = Integer.valueOf(index);
	int a = Integer.valueOf(ancientIndex);
	//int c = i-a;
	int c = connex.calculConsommation(i,a,numCompt,con);
	String n = request.getParameter("numCompt");
	String d = request.getParameter("datePrelevement");
	String autoinc=connex.autoIncrement("idPrelevement",con);
	p.setIdPrelevement(autoinc);
	p.setIndexCompteurElec(i);
	p.setAncientIndexElec(a);
	p.setDatePrelevement(d);
	p.setNumComptElec(n);
	p.setConsommationElec(c);
	//out.print(connex.insert(p));
	connex.executeInsert(p,con);
	Facture f=connex.createFacture(p.getIdPrelevement(),con);
	cl=connex.getClient(autoinc,con);
	t=connex.getTarif(autoinc,con);
	Generalisation gen = new Generalisation();
	//out.print("annee ="+f.getAnneeFacture()+"<br/>");
	try{
		connex.executeInsert(f, con);
	} catch(Exception e){
		out.print(e.getMessage());
		e.printStackTrace();
	}
	
	double conso1=(f.getMontantElec())/(t.getPrixUnitaireElec());
	double conso2=(f.getMontantElec2())/(t.getPrixUnitaireElec2());
	double conso3=(f.getMontantElec3())/(t.getPrixUnitaireElec3());
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Facture Jirama</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="style.css">
		<script src="js/jquery.min.js"></script> 
		<script src="js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-sm-12 col-xs-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Info Client</h3>
						</div>
						<div class="list-group">
							<p  class="list-group-item"><span class="badge badge-info"><% out.print(cl.getNom()+" "+cl.getPrenom()); %></span>Nom</p>
							<p  class="list-group-item"><span class="badge badge-info"><% out.print(cl.getAdresse());%></span>Adresse</p>
							<p  class="list-group-item"><span class="badge badge-info"><%out.print(cl.getNomTarif()); %></span>Tarif</p>
							<p  class="list-group-item"><span class="badge badge-info"><%out.print(cl.getCategorie()); %></span>Categorie</p>
					  </div>
					</div>
				</div>
				<div class="col-sm-12 col-xs-12">
					<table border="1" class="table table-hover table-expansed table-radius">
						<caption class="text-center"><h1>Facture</h1></caption>
							<tr>
								<th class="danger">date Facture</th>
								<th class="warning">agence</th>
								<th class="danger">consommation electricite</th>
								<th class="warning">1er tranche</th>
								<th class="danger">2 eme tranche</th>
								<th class="warning">3 eme tranche</th>
								<th class="danger">Montant total</th>
							</tr>
							<tr>
								<td><% out.print(f.getDateFacture()); %></td>
								<td><% out.print(f.getAgence()); %></td>
								<td><% out.print(f.getConsommationElec()); %></td>
								<td><% out.print(f.getMontantElec()); %></td>
								<td><% out.print(f.getMontantElec2()); %></td>
								<td><% out.print(f.getMontantElec3()); %></td>
								<td><% out.print(f.getMontantTotal()); %></td>
							</tr>
					</table>
				</div>
			</div>
			<div class="row"> 
			<table border="1" class="table table-hover table-expansed table-radius">
						<caption class="text-center"><h1>Info Tarif <% out.print(t.getNomTarif());%></h1></caption>
							<tr>
								<th></th>
								<th class="warning">Quantite (Kw)</th>
								<th class="danger">Prix unitaire (Ar)</th>
							</tr>
							<tr>
								<td>1 ere tranche de consommation</td>
								<td><%out.print(conso1); %> </td>
								<td><% out.print(t.getPrixUnitaireElec());%> </td>
							</tr>
							<tr>
								<td>2 eme tranche de consommation</td>
								<td><%out.print(conso2); %> </td>
								<td><% out.print(t.getPrixUnitaireElec2());%> </td>
							</tr>
							<tr>
								<td>3 eme tranche de consommation</td>
								<td><%out.print(conso3); %> </td>
								<td><%out.print(t.getPrixUnitaireElec3());%> </td>
							</tr>
			</table>
			</div>
		</div>
	</body>
</html>