function setUser(username) {
  localStorage.setItem("username", username);
}

function getUser() {
  return localStorage.getItem("username");
}

function logout() {
  localStorage.clear();
  window.location.href = "/login.html";
}
