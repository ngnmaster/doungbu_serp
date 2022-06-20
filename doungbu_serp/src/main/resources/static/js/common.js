// checkbox script=======================================

function selectAll(selectAll)  {
  const checkboxes 
     = document.querySelectorAll('input[type="checkbox"]');
  
  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked
  })
}

//include script=========================================
$(function(){
	include();
})

//include
function include(callback){
	var z, i, elmnt, file, xhr;
	/*loop through a collection of all HTML elements:*/
	z = document.getElementsByTagName("*");
	for (i = 0; i < z.length; i++){
		elmnt = z[i];
		/*search for elements with a certain atrribute:*/
		file = elmnt.getAttribute("data-html");
		//console.log(file);
		if (file){
			/*make an HTTP request using the attribute value as the file name:*/
			xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function(){
				if (this.readyState == 4){
					if (this.status == 200) {
						elmnt.innerHTML = this.responseText;
					}
					if (this.status == 404){
						elmnt.innerHTML = "Page not found.";
					}
					/*remove the attribute, and call this function once more:*/
					elmnt.removeAttribute("data-html");
					include(callback);
				}
			};
			xhr.open("GET", file, true);
			xhr.send();
			/*exit the function:*/
			return;
		}
	}
}