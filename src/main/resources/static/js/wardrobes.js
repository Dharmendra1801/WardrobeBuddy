const username = getUser();

async function loadWardrobes() {
  const res = await fetch(`/api/${username}/wardrobe/all`);
  const data = await res.json();

  const list = document.getElementById("wardrobeList");
  list.innerHTML = "";

  data.forEach(w => {
    const li = document.createElement("li");
    li.textContent = w.wardrobeName;
    li.onclick = () => {
      window.location.href = `/products.html?wardrobe=${w.wardrobeName}&wardrobe=${wardrobe}`;
    };
    list.appendChild(li);
  });
}

async function addWardrobe() {
  const name = document.getElementById("wardrobeName").value;

  await fetch(`/api/${username}/wardrobe/new`, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({ wardrobeName: name })
  });

  loadWardrobes();
}

loadWardrobes();
