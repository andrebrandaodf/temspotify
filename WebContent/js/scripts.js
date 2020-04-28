	function adicionar(idPlaylist, idMusica){
		var xmlhttp = new XMLH
    	xmlhttp.open("GET", "http://localhost:8080/temspotify/incluirnaplaylist?idplaylist="+idPlaylist+"&idmusica="+idMusica);
    	xmlhttp.onreadystatechange = function(){
        	if (xmlhttp.status === 200 && xmlhttp.readyState === 4){
            	alert(xmlhttp.responseText);
        }  
    };
    	xmlhttp.send();
}