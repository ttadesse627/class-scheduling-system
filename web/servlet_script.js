

var myXbtn = document.getElementById("printPage");
    window.addEventListener("load", event =>{
        myXbtn.onclick = function() {
            myXbtn.style.display = "none";
            var printable = document.getElementById("schedule");
            var wind = window.open("title='Schedules'");
            wind.document.write(printable.innerHTML);
            wind.focus();
            wind.print();
            wind.close();
            location.reload(true);
        }
    });
