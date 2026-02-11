const username = getUser();
const params = new URLSearchParams(window.location.search);
const productId = params.get("id");
const wardrobe = params.get("wardrobe");

if (!username || !productId || !wardrobe) {
  alert("Invalid page access");
  window.history.back();
}

async function loadProduct() {
  const res = await fetch(
    `/api/${username}/${wardrobe}/product/id/${productId}`
  );

  if (!res.ok) {
    alert("Failed to load product");
    return;
  }

  const p = await res.json();

  document.getElementById("name").innerText = p.productName;
  document.getElementById("category").innerText = p.category;
  document.getElementById("brand").innerText = p.brand;
  document.getElementById("price").innerText = p.price;
  document.getElementById("quantity").innerText = p.quantity;
  document.getElementById("note").innerText = p.note || "";

  document.getElementById("image").src = p.image
    ? "data:image/jpeg;base64," + p.image
    : "https://via.placeholder.com/300";
}

async function uploadImage() {
  const fileInput = document.getElementById("imageFile");
  if (!fileInput.files.length) {
    alert("Select an image first");
    return;
  }

  const fd = new FormData();
  fd.append("image", fileInput.files[0]);

  const res = await fetch(
    `/api/${username}/${wardrobe}/product/add/image/${productId}`,
    {
      method: "PUT",
      body: fd
    }
  );

  if (res.ok) {
    alert("Image uploaded");
    loadProduct();
  } else {
    alert(res.text());
  }
}

async function deleteProduct() {
  if (!confirm("Delete this product?")) return;

  const res = await fetch(
    `/api/${username}/${wardrobe}/product/delete/${productId}`,
    { method: "DELETE" }
  );

  if (res.ok) {
    alert("Product deleted");
    window.history.back();
  } else {
    alert(await res.text());
  }
}

function goBack() {
  window.history.back();
}

loadProduct();
