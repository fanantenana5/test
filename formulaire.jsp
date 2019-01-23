<%@ page import="fonction.*,jirama.*" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Formulaire</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="style.css">
		<script src="js/jquery.min.js"></script> 
		<script src="js/bootstrap.min.js"></script>
	</head>
	<body>
	<% 
		Formulaire f = new Formulaire();
		f.setChamp(new Client());
		f.getChamp(0).setVisible(false);
		f.getChamp(1).setVisible(true);
		f.getChamp(2).setVisible(true);
		
	%>
	<div class="row">
			<div align="center">
				<form action ="index.jp" method = "post">
					<%
						out.print(f.getHtml());
					%>
					<button type="submit" class="btn  btn-primary">Valider</button>
				</form>
			</div>
	</div>
	</body>
</html>