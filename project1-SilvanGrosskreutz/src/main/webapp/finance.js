let tbody = document.getElementById("reimBody");
let reimButton = document.getElementById("getReims");
let updatebtn = document.getElementById("updateReimBtn");

reimButton.addEventListener("click",fetchReims);
updatebtn.addEventListener("click",updateReim);

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
        let id = document.createElement("td");
        let status = document.createElement("td");
        let authorName = document.createElement("td");
        let resolverName = document.createElement("td");
        let amount = document.createElement("td");
        let type = document.createElement("td");
    
        id.innerText = reim.id;
        status.innerText = reim.status;
        authorName.innerText = reim.author.username;
        if (reim.resolver == null) {
            resolverName.innerText = null;    
        } else{
            resolverName.innerText = reim.resolver.username;  
        }
        amount.innerText = reim.amount;
        type.innerText = reim.type;
    
        row.appendChild(id);
        row.appendChild(status);
        row.appendChild(authorName);
        row.appendChild(resolverName);
        row.appendChild(amount);
        row.appendChild(type);
        tbody.appendChild(row);
    }
}

async function updateReim(){
    let reim = {
      id:document.getElementById("updateReimId").value,
      author: null,
      resolver: null,
      status:document.getElementById("updateReimStatus").value,
      amount: 0,
      type: null
    }
  
    let response = await fetch("http://localhost:8080/project/reim", {
      method:"PUT",
      body:JSON.stringify(reim),
      credentials:"include"
    });
  
    if(response.status===200){
      console.log("Reimbursement Status successfully changed!");
    }else{
      console.log("Could not update Reimbursement!");
      console.log(response);
    }
  }