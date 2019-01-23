<%@ page import="database.*,jirama.*" %>
<%
	DBconnect connex=new DBconnect();
	Prelevement p = new Prelevement();
	Client cl=new Client();
	Tarif t=new Tarif();
	Facture[] f=new Facture[2]; 
	String moisFacture= request.getParameter("moisFacture");
	String anneeFacture = request.getParameter("anneeFacture");
	String n = request.getParameter("numCompt");
	p=connex.getPrelevement2(n, null);
	String idPrel=p.getIdPrelevement();

	f=connex.executeSearch(idPrel, moisFacture, anneeFacture, null);
		
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
								<% for(int i =0 ; i<f.length;i++){%>
								<tr>
									<td><% out.print(f[i].getDateFacture()); %></td>
									<td><% out.print(f[i].getAgence()); %></td>
									<td><% out.print(f[i].getConsommationElec()); %></td>
									<td><% out.print(f[i].getMontantElec()); %></td>
									<td><% out.print(f[i].getMontantElec2()); %></td>
									<td><% out.print(f[i].getMontantElec3()); %></td>
									<td><% out.print(f[i].getMontantTotal()); %></td>
								</tr>
								<%} %>
						</table>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>