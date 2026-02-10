const username = getUser();
if (!username) window.location.href = "/login.html";

function loadWardrobes() {
  fetch(`/api/${username}/wardrobe/all`)
    .then(res => res.json())
    .then(data => {
      const ul = document.getElementById("wardrobeList");
      ul.innerHTML = "";

      data.forEach(w => {
        const li = document.createElement("li");
        li.textContent = w.wardrobeName;

        li.onclick = () => {
          localStorage.setItem("wardrobe", w.wardrobeName);
          window.location.href = "/products.html";
        };

        ul.appendChild(li);
      });
    });
}

async function addWardrobe() {
  const name = document.getElementById("wardrobeName").value;
  const note = document.getElementById("wardrobeNote").value;

  if (!name) {
    alert("Wardrobe name required");
    return;
  }

  const res = await fetch(`/api/${username}/wardrobe/new`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      wardrobeName: name,
      note: note
    })
  });

  alert(await res.text());
  if (res.ok) loadWardrobes();
}

loadWardrobes();
