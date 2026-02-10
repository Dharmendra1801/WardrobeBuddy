const username = localStorage.getItem("username");
const BASE = `/api/${username}/wardrobe`;

function createWardrobe() {
  fetch(BASE + "/new", {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({
      wardrobeName: wardrobeName.value,
      note: wardrobeNote.value
    })
  })
  .then(r => r.text())
  .then(t => msg.innerText = t);
}

function loadWardrobes() {
  fetch(BASE + "/all")
    .then(r => r.json())
    .then(data => {
      list.innerHTML = "";
      data.forEach(w => {
        const li = document.createElement("li");
        li.innerHTML = `
          <b>${w.wardrobeName}</b><br>
          ${w.note || ""}
          <br>
          <button onclick="rename('${w.wardrobeName}')">Rename</button>
          <button onclick="changeNote('${w.wardrobeName}')">Change Note</button>
          <button onclick="remove('${w.wardrobeName}')">Delete</button>
          <hr>
        `;
        list.appendChild(li);
      });
    });
}

function rename(oldName) {
  const newName = prompt("New name:");
  if (!newName) return;

  fetch(`${BASE}/change_name/${oldName}?newName=${newName}`, {
    method: "PUT"
  })
  .then(r => r.text())
  .then(t => {
    msg.innerText = t;
    loadWardrobes();
  });
}

function changeNote(name) {
  const note = prompt("New note:");
  if (!note) return;

  fetch(`${BASE}/change_note/${name}?newNote=${note}`, {
    method: "PUT"
  })
  .then(r => r.text())
  .then(t => {
    msg.innerText = t;
    loadWardrobes();
  });
}

function remove(name) {
  if (!confirm("Delete wardrobe?")) return;

  fetch(`${BASE}/delete/${name}`, {
    method: "DELETE"
  })
  .then(r => r.text())
  .then(t => {
    msg.innerText = t;
    loadWardrobes();
  });
}
