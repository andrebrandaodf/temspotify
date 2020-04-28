<!DOCTYPE html>
<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<jsp:useBean id="Usuario"
	type="br.com.professorisidro.temspotify.model.Usuario" scope="session" />
<jsp:useBean id="PlayList" type="br.com.professorisidro.temspotify.model.PlayList" scope="session"/>	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>.: TemSpotify by TemAula! :.</title>


<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<script type="text/javascript">
	
	var musics = new Array(); //lista de Musicas
	var repeat = false;       //se a playlist vi ficar em modo repeat
	var currentSong = 0;
	
	function setupPlayer(){
		var divMusicas = document.getElementById("playerContent");
	}
	
	function play(objetoMusica){
		console.log("Vai tocar a musica");
	}
	
</script>

</head>
<body onLoad="setupPlayer();">
	<div class="container-fluid">

		<div class="row">
			<div class="col-md-12">
				<a href="playlists"><img src="images/logoSpotify.jpg" class="rounded mx-auto d-block"
					width="15%" align="center"/></a>
			</div>
		</div>
	</div>
	
	<div id="playerContent">
		<c:forEach var="music" items="${PlayList.musicas} ">
			<div title="${music.linkMP3}" class="musica" onclick="play(this)">
				${music.titulo} (${music.artista}) 
			
			</div>
		</c:forEach>
	</div>
	
	<div>
		<audio id="musicplayer" controls controlsList="nodownload" src=""/>
	</div>
	
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
	
</body>
</html>