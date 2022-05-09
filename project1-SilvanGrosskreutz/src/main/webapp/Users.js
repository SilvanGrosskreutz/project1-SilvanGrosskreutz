let tbody = document.getElementById("userBody");
let userButton = document.getElementById("getUsers");
let newUserBtn = document.getElementById("userBtn");
let newFmBtn = document.getElementById("fmBtn");

userButton.addEventListener("click",fetchUsers);

async function fetchUsers(){
    let response = await fetch("http://localhost:8080/project/user");
    if(response.status === 200){
        let data = await response.json();
        populateUsers(data);
    } else{
        console.log("Could not find the users!");
        console.log(response);
    }
}

function populateUsers(users){
    tbody.innerHTML = "";

    for(let user of users){
        let row = document.createElement("tr");
        for(let cell in user){
            let td = document.createElement("td");
            td.innerText = user[cell];
            row.appendChild(td);
        }
        tbody.appendChild(row);
    }
}

newUserBtn.onclick=addUser;

async function addUser(){
    let user = gatherUser();

    let response = await fetch("http://localhost:8080/project/user", {
        method:"POST",
        body: JSON.stringify(user) // Takes a JS Object and changes to JSON
    });

    if(response.status === 201){
        console.log("User added successfully.")
    }else{
        console.log("Something went wrong adding the User.");
        console.log(response);
    }
}

function gatherUser(){
    let userName = document.getElementById("userName").value;
    let userPassword = document.getElementById("userPassword").value;
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let eMail = document.getElementById("eMail").value;
    let phoneNumber = document.getElementById("phoneNumber").value;
    let user = {
            username: userName,
            password: userPassword,
            role: "EMPLOYEE",
            firstName: firstname,
            lastName: lastname,
            eMail: eMail,
            phoneNumber: phoneNumber
        }
    return user;
}

newFmBtn.onclick=addFm;

async function addFm(){
    let fm = gatherFm();

    let response = await fetch("http://localhost:8080/project/user", {
        method:"POST",
        body: JSON.stringify(fm) // Takes a JS Object and changes to JSON
    });

    if(response.status === 201){
        console.log("User added successfully.")
    }else{
        console.log("Something went wrong adding the User.");
        console.log(response);
    }
}

function gatherFm(){
    let userName = document.getElementById("userName1").value;
    let userPassword = document.getElementById("userPassword1").value;
    let firstname = document.getElementById("firstname1").value;
    let lastname = document.getElementById("lastname1").value;
    let eMail = document.getElementById("eMail1").value;
    let phoneNumber = document.getElementById("phoneNumber1").value;
    let fm = {
            username: userName,
            password: userPassword,
            role: "FINANCE_MANAGER",
            firstName: firstname,
            lastName: lastname,
            eMail: eMail,
            phoneNumber: phoneNumber
        }
    return fm;
}