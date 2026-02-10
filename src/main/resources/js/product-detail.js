const username = getUser();
const params = new URLSearchParams(window.location.search);
const productID = params.get("id");
const wardrobe = params.get("wardrobe");

async function loadProduct() {
  const res = await fetch(`/api/${username}/x/product/id/${productID}`);
  const p = await res.json();

  document.getElementById("product").innerHTML = `
    <h3>${p.productName}</h3>
    ${p.image ? `<img src="data:image/jpeg;base64,${p.image}">` : ``}
    <p>Category: ${p.category}</p>
    <p>Price: ₹${p.price}</p>
    <p>Quantity: ${p.quantity}</p>
  `;
}

async function uploadImage() {
  const file = document.getElementById("image").files[0];
  const fd = new FormData();
  fd.append("image", file);

  await fetch(`/api/${username}/${wardrobe}/product/add/image/${productID}`, {
    method: "PUT",
    body: fd
  });

  loadProduct();
}

loadProduct();
