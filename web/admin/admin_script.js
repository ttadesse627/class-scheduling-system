/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var modal = document.getElementById('id01');
var moda2 = document.getElementById('id02');
var links = document.getElementById('navLinks');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if ((event.target === modal)||(event.target === links)||(event.target === modal2)) {
        modal.style.display = "none";
    }
}
function myFunction(){
    var links = document.getElementById("navLinks");
    var clbtn = document.querySelector(".close_btn");
    if(links.style.display === "block"){
        if(win)
        links.style.display = "none";
    }
    else {
        links.style.display = "block";
        clbtn.style.display ="block";
    }
}

            var modal = document.getElementById('id01');
            var modal2 = document.getElementById('id02');
            var modal3 = document.getElementById('id03');

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
                if (event.target == modal2) {
                    modal2.style.display = "none";
                }
                if (event.target == modal3) {
                    modal3.style.display = "none";
                }
            }
            
function spinner() {
    myVar = document.getElementById("loadSpin");
    myVar.style.display = "block";
    setTimeout(showPage, 5000);
}

function showPage() {
  myVar.style.display = "none";
  document.getElementById("backtoHome").setAttribute("href","../index.jsp");
}

var myXbtn = document.getElementById("closeBtn");
window.addEventListener("load", event =>{
    myXbtn.onclick = function() {
        location.reload(true);
    }
});

//Script for profile
/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunc() {
  document.getElementById("myDropdown").classList.toggle("show");
  }

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}

// profile picture

function setPicture(event){
    var placeholder = document.getElementById("imgHolder");
    var src = URL.createObjectURL(event.target.files[0]);
    placeholder.setAttribute("src",src);
}