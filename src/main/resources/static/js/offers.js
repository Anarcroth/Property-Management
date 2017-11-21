function rentPrice(rent) {
    console.log(rent);
  if($(rent).is(":checked")){
    alert("Yes checked"); //when checked
   // $("#page-header-inner").addClass("sticky");
  }else{
    alert("Not checked"); //when not checked
  }
}

function display(){
	const form = document.getElementById("profileWrapper");

    const btnProfB = document.getElementById('btnProfB');
    btnProfB.onclick = function() {
        console.log("button was clicked");
        form.style.display = "block";
    }
}

