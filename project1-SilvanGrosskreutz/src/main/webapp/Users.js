let tbody = document.getElementById("userBody");
let userButton = document.getElementById("getUsers");
let newUserBtn = document.getElementById("userBtn");

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
    let userRole = document.querySelector('input[name="userRole"]:checked').value;
    console.log(userRole);
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let eMail = document.getElementById("eMail").value;
    let phoneNumber = document.getElementById("phoneNumber").value;
    let user = {
            username: userName,
            password: userPassword,
            role: userRole,
            firstName: firstname,
            lastName: lastname,
            eMail: eMail,
            phoneNumber: phoneNumber
        }
    return user;
}