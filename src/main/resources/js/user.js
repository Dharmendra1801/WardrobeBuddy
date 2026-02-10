const API = "/api/user";

function register() {
  fetch(API + "/register", {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({
      username: username.value,
      name: name.value,
      password: password.value
    })
  }).then(r => r.text()).then(t => msg.innerText = t);
}

function login() {
  fetch(API + "/login", {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({
      username: username.value,
      password: password.value
    })
  }).then(r => r.text()).then(t => {
    localStorage.setItem("username", username.value);
    msg.innerText = t;
  });
}

function logout() {
  fetch(API + "/logout", {
    method: "POST",
    headers: {"Content-Type": "text/plain"},
    body: localStorage.getItem("username")
  }).then(r => r.text()).then(t => msg.innerText = t);
}

function updateName() {
  fetch(API + "/update/name?name=" + newName.value, {
    method: "PUT",
    headers: {"Content-Type": "text/plain"},
    body: localStorage.getItem("username")
  }).then(r => r.text()).then(t => msg.innerText = t);
}

function updatePassword() {
  fetch(API + "/update/password", {
    method: "PUT",
    headers: {
      "Content-Type": "text/plain",
      "oldPass": oldPass.value,
      "newPass": newPass.value
    },
    body: localStorage.getItem("username")
  }).then(r => r.text()).then(t => msg.innerText = t);
}

function deleteAccount() {
  fetch(API + "/delete", {
    method: "DELETE",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({
      username: localStorage.getItem("username"),
      password: oldPass.value
    })
  }).then(r => r.text()).then(t => msg.innerText = t);
}
