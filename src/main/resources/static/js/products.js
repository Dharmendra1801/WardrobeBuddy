const username = getUser();
const wardrobe = new URLSearchParams(window.location.search).get("wardrobe");

async function loadProducts() {
  const res = await fetch(`/api/${username}/${wardrobe}/product/all`);
  const data = await res.json();

  const list = document.getElementById("productList");
  list.innerHTML = "";

  data.forEach(p => {
    const li = document.createElement("li");

    li.innerHTML = `
      <div style="display:flex; gap:10px;">
        ${p.image ? `<img src="data:image/jpeg;base64,${p.image}" style="width:60px;height:60px;object-fit:cover;">` : ``}
        <div>
          <b>${p.productName}</b><br>
          ${p.category} | Qty: ${p.quantity}
        </div>
      </div>
    `;

    li.onclick = () => {
      window.location.href = `/product-detail.html?id=${p.id}&wardrobe=${wardrobe}`;
    };

    list.appendChild(li);
  });
}

async function addProduct() {
  const product = {
    productName: productName.value,
    category: category.value,
    price: price.value,
    quantity: quantity.value
  };

  await fetch(`/api/${username}/${wardrobe}/product/add`, {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(product)
  });

  loadProducts();
}

loadProducts();
