const username = getUser();
if (!username) window.location.href = "/login.html";

fetch(`/api/${username}/wardrobe/all`)
  .then(res => res.json())
  .then(data => {
    const ul = document.getElementById("wardrobeList");

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
