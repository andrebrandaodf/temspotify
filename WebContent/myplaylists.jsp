<!DOCTYPE html>
<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<jsp:useBean id="Usuario"
	type="br.com.professorisidro.temspotify.model.Usuario" scope="session" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>.: TemSpotify by TemAula! :.</title>


<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

</head>
<body>
	<div class="container-fluid">

		<div class="row">
			<div class="col-md-12">
				<img src="images/logoSpotify.jpg" class="rounded mx-auto d-block"
					width="15%" align="center" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h3 class="text-center">Tem Spotify - Sua playlist na Web!</h3>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<h4 class="text-center">Minhas Playlists!</h4>
			</div>
		</div>
		<div class="row" id="menu">
			<div class="col-md-2">
				&nbsp;
			</div>
			<div class="col-md-2 botao">
					<span class="text-center"> <a class="botaospt" href="novamusica">Upload</a> </span>
				</div>
				
				<div class="col-md-2 botao">
					<span class="text-center"> <a class="botaospt" href="playlists">Minhas Playlists</a> </span>
				</div>
				
				<div class="col-md-2 botao">
					<span class="text-center"> <a class="botaospt" href="novaplaylist">Add Playlist</a> </span>
				</div>
				
				<div class="col-md-2 botao">
					<span class="text-center"><a class="botaospt" href="logout"> Logout</a> </span>
				</div>		
			</div>
			<div class="col-md-2">
				&nbsp;
			</div>
			
		<c:forEach var="playlist" items="${Usuario.playlists}">
		<!-- varias iteracoes -->
			<div class="row">
				<div class="col-md-2"> &nbsp; </div>
				<div class="col-md-8"> 
					<strong><a href="playlistdetails?id=${playlist.id}">${playlist.titulo}</a></strong><br/>
					
				 </div>
				<div class="col-md-2"> &nbsp; </div>
			</div>	
		</c:forEach>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>