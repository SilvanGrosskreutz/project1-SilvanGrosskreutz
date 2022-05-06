let tbody = document.getElementById("reimBody");
let reimButton = document.getElementById("getReims");
let newReimBtn = document.getElementById("reimBtn");

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

newReimBtn.onclick=addReim;

async function addReim(){
    let reim = gatherReim();

    let response = await fetch("http://localhost:8080/project/reim", {
        method:"POST",
        body: JSON.stringify(reim) // Takes a JS Object and changes to JSON
    });

    if(response.status === 201){
        console.log("Reimbursement added successfully.")
    }else{
        console.log("Something went wrong adding the Reimbursement.");
        console.log(response);
    }
}

function gatherReim(){
    let amount1 = document.getElementById("amount").value;
    let reim = {
            amount: amount1        
        }
    return reim;
}