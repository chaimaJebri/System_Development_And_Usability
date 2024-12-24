function tellMeAJoke(){
	var link= "https://v2.jokeapi.dev/joke/Programming?blacklistFlags=nsfw,religious,political,racist,sexist,explicit";
	responseData(link, "jokeContainer");
}

function fetchRequestData() 
{
	if (window.XMLHttpRequest) 
	{
		return (new XMLHttpRequest());
	} 
	else if (window.ActiveXObject) 
	{
		return (new ActiveXObject("Microsoft.XMLHTTP"));
	} 
	else 
	{
		return (null);
	}
}

function responseData(link, container) {
	var rqst = fetchRequestData();
	rqst.onreadystatechange =
		function() {
			displayJoke(rqst,
				container);
		};
	rqst.open("GET", link, true);
	rqst.send(null);
}

function displayJoke(rqst, container) {
	if ((rqst.readyState == 4) && (rqst.status == 200)) {
		
		var response = JSON.parse(rqst.responseText);
		
		let theJoke = "";
		
		if(response.type === "twopart"){
			 theJoke ='<div class="joke">' + response.setup + "<br> <br>" + response.delivery + "</div>"
		} else {
			 theJoke ='<div class="joke">' + response.joke  + "</div>"
		}
		
		
		embedJoke(container, theJoke);
	}
}

function embedJoke(id, joke) {
	document.getElementById(id).innerHTML += joke;
}