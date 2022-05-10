let tbody = document.getElementById("reimBody");
let reimButton = document.getElementById("getReims");

reimButton.addEventListener("click",fetchReims);

async function fetchReims(){
    let response = await fetch("http://localhost:8080/project/reim");
    if(response.status === 200){
        let data = await response.json();
        populateReims(data);
    } else{
        console.log("Could not find the Reimbursements!");
        console.log(response);
    }
}

function populateReims(reims){
    tbody.innerHTML = "";

    for(let reim of reims){
        let row = document.createElement("tr");
        for(let cell in reim){
            let td = document.createElement("td");       
            td.innerText = reim[cell];
           
            row.appendChild(td);
        }
        tbody.appendChild(row);
    }
}