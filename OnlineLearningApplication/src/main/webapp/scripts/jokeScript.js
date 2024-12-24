function tellMeAJoke(){
	var link= "https://v2.jokeapi.dev/joke/Programming?blacklistFlags=nsfw,religious,political,racist,sexist,explicit";   //the API link
	responseData(link, "jokeContainer");   
}

function fetchRequestData() {
	if (window.XMLHttpRequest) {
		return (new XMLHttpRequest());
	} 
	else if (window.ActiveXObject) {
		return (new ActiveXObject("Microsoft.XMLHTTP"));
	} 
	else {
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
		var response = JSON.parse(rqst.responseText);     //handle the API responses using the JSON format
		let theJoke = "";
		if(response.type === "twopart"){       //if the joke type is "twopart", combine them to be displayed together
			 theJoke ='<div class="joke">' + response.setup + "<br> <br>" + response.delivery + "</div>"
		} else {                    //otherwise display the joke as it is
			 theJoke ='<div class="joke">' + response.joke  + "</div>"
		}
		embedJoke(container, theJoke);
	}
}

function embedJoke(id, joke) {        //insert the joke in the HTML element with the specified id
	document.getElementById(id).innerHTML += joke;
}



