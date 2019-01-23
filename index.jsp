<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Prelevement</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="style.css">
		<script src="js/jquery.min.js"></script> 
		<script src="js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="row">
			<div align="center">
				<h1><span class="label label-primary">JIRAMA</span></h1>
				<p class="text-warning">Jiro sy Rano Malagasy </p>
					<form action="facture.jsp" method="get"/>
						<table>
						
							<tr>
								<td><label for="indexCompteurElec" class="text-warning">Index compteur</label></td>
								<td><input type="text" name="indexCompteurElec"/></td>
							</tr>
							<tr>
								<td><label for="ancientIndexElec" class="text-primary">Ancient index compteur</label></td>
								<td><input type="text" name="ancientIndexElec"/></td>
							</tr>
							<tr>
								<td><label for="datePrelevement" class="text-warning">Date prelevement</label></td>
								<td><input type="text" name="datePrelevement"/></td>
							</tr>
							<tr>
								<td><label for="numCompt" class="text-primary">Numero compteur</label></td>
								<td><input type="text" name="numCompt"/></td>
							</tr>
						</table>
						<button type="submit" class="btn btn-lg btn-warning">valider</button></td>
					</form>
					
					<h1><span class="label label-primary">Recherche de facture</span></h1>
					<form action="facture2.jsp" method="post"/>
						<table>
							<tr>
								<td><label for="moisFacture" class="text-warning">Mois </label></td>
								<td><input type="text" name="moisFacture"/></td>
							</tr>
							<tr>
								<td><label for="anneeFacture" class="text-primary">Annee </label></td>
								<td><input type="text" name="anneeFacture"/></td>
							</tr>
							<tr>
								<td><label for="numCompt" class="text-warning">Numero compteur</label></td>
								<td><input type="text" name="numCompt"/></td>
							</tr>
						</table>
						<button type="submit" class="btn btn-lg btn-warning">valider</button></td>
					</form>	
			</div>
		</div>
		<div class='row'>
			<div align="center">
				<h1><span class="label label-primary">Prelevement</span></h1>
					<form action="prelevement.jsp" method="post"/>
						<table>
							<tr>
								<td><label for="numCompt" class="text-warning">Numero compteur</label></td>
								<td><input type="text" name="numCompt"/></td>
							</tr>
							<tr>
								<td><label for="numCompt" class="text-primary">Valeur index</label></td>
								<td><input type="text" name="indexCompteurElec"/></td>
							</tr>
							<tr>
								<td><label for="moisFacture" class="text-warning">Date prelevement</label></td>
								<td><input type="text" name="datePrelevement"/></td>
							</tr>
							<tr>
								<td><label for="moisFacture" class="text-primary">Mois </label></td>
								<td><input type="text" name="moisFacture"/></td>
							</tr>
							<tr>
								<td><label for="anneeFacture" class="text-warning">Annee </label></td>
								<td><input type="text" name="anneeFacture"/></td>
							</tr>
						</table>
						<td><button type="submit" class="btn btn-lg btn-warning">valider</button></td>
					</form>	
			</div>
		</div>
		<div class="row">
			<div align="center">
				<form action="formulaire.jsp" method="post">
					 <button type="submit" class="btn  btn-primary">Remplir formulaire</button>
				</form>
				<a href="clients.jsp"><button class="btn  btn-primary">Liste clients</button></a>
				<a href="listeFacture.jsp"><button class="btn  btn-primary">Liste facture</button></a>
			</div>
		</div>
	</body>
</html>