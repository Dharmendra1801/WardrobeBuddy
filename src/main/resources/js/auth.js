function setUser(username) {
  localStorage.setItem("username", username);
}

function getUser() {
  return localStorage.getItem("username");
}

async function logout() {
  const username = getUser();
  if (!username) return;

  await fetch("/api/user/logout", {
    method: "POST",
    headers: { "Content-Type": "text/plain" },
    body: username
  });

  localStorage.removeItem("username");
  window.location.href = "/login.html";
}
