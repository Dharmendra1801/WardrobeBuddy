const username = getUser();

async function loadWardrobes() {
  const res = await fetch(`/api/${username}/wardrobe/all`);
  const data = await res.json();

  const list = document.getElementById("wardrobeList");
  list.innerHTML = "";

  data.forEach(w => {
    // ✅ USE DIV, NOT LI
    const div = document.createElement("div");
    div.className = "wardrobe";

    div.innerHTML = `
      <h3>${w.wardrobeName}</h3>
      ${w.note ? `<p>${w.note}</p>` : ""}
    `;

    div.onclick = () => {
      window.location.href =
        `/products.html?wardrobe=${encodeURIComponent(w.wardrobeName)}`;
    };

    list.appendChild(div);
  });
}

async function addWardrobe() {
  const input = document.getElementById("wardrobeName");
  const name = input.value.trim();
  if (!name) return alert("Enter wardrobe name");

  await fetch(`/api/${username}/wardrobe/new`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ wardrobeName: name })
  });

  input.value = "";
  loadWardrobes();
}

loadWardrobes();
