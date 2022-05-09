let newReimBtn = document.getElementById("reimBtn");

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
    let type1 = document.querySelector('input[name="ReimType"]:checked').value;
    let reim = {
            amount: amount1,
            type: type1        
        }
    return reim;
}