const username = localStorage.getItem("username");
const params = new URLSearchParams(window.location.search);
const wardrobeName = params.get("wardrobe");

if (!username || !wardrobeName) {
  alert("Login and wardrobe selection required");
}

const BASE = `/api/${username}/${wardrobeName}/product`;

/* ---------------- ADD PRODUCT ---------------- */

function addProduct() {
  fetch(BASE + "/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      productName: productName.value,
      brand: brand.value,
      category: category.value,
      price: price.value,
      quantity: quantity.value,
      note: note.value
    })
  })
  .then(r => r.text())
  .then(t => {
    msg.innerText = t;
    loadAll();
  });
}

/* ---------------- LOAD PRODUCTS ---------------- */

function loadAll() {
  fetch(BASE + "/all")
    .then(r => r.json())
    .then(showProducts);
}

function loadByCategory() {
  fetch(`${BASE}/${filterCategory.value}`)
    .then(r => r.json())
    .then(showProducts);
}

/* ---------------- RENDER ---------------- */

function showProducts(products) {
  list.innerHTML = "";

  products.forEach(p => {
    const li = document.createElement("li");

    li.innerHTML = `
      <b>${p.productName}</b> (${p.category})<br>
      Brand: ${p.brand || "-"}<br>
      Price: ${p.price || 0}<br>
      Quantity: ${p.quantity || 0}<br>
      ${p.note || ""}<br><br>

      <input type="file" id="img-${p.id}" accept="image/*">
      <button onclick="uploadImage(${p.id})">Upload Image</button>

      <br><br>

      <button onclick="updateProduct(${p.id})">Update</button>
      <button onclick="deleteProduct(${p.id})">Delete</button>

      <hr>
    `;

    list.appendChild(li);
  });
}

/* ---------------- UPDATE ---------------- */

function updateProduct(id) {
  const name = prompt("Product name:");
  const category = prompt("Category:");
  const price = prompt("Price:");
  const quantity = prompt("Quantity:");

  fetch(`${BASE}/update/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      productName: name,
      category: category,
      price: price,
      quantity: quantity
    })
  })
  .then(r => r.text())
  .then(t => {
    msg.innerText = t;
    loadAll();
  });
}

/* ---------------- DELETE ---------------- */

function deleteProduct(id) {
  if (!confirm("Delete product?")) return;

  fetch(`${BASE}/delete/${id}`, {
    method: "DELETE"
  })
  .then(r => r.text())
  .then(t => {
    msg.innerText = t;
    loadAll();
  });
}

/* ---------------- IMAGE UPLOAD ---------------- */

function uploadImage(productID) {
  const fileInput = document.getElementById(`img-${productID}`);
  const file = fileInput.files[0];

  if (!file) {
    alert("Please select an image");
    return;
  }

  const formData = new FormData();
  formData.append("image", file);

  fetch(`${BASE}/add/image/${productID}`, {
    method: "PUT",
    body: formData
  })
  .then(r => r.text())
  .then(t => msg.innerText = t);
}
