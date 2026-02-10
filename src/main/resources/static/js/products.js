const username = getUser();
const wardrobe = localStorage.getItem("wardrobe");

if (!username || !wardrobe) {
  window.location.href = "/wardrobes.html";
}

function loadProducts() {
  fetch(`/api/${username}/${wardrobe}/product/all`)
    .then(res => res.json())
    .then(products => {
      const ul = document.getElementById("productList");
      ul.innerHTML = "";

      products.forEach(p => {
        const li = document.createElement("li");
        li.textContent = `${p.productName} (${p.category})`;

        li.onclick = () => {
          localStorage.setItem("productId", p.id);
          window.location.href = "/product-detail.html";
        };

        ul.appendChild(li);
      });
    });
}

async function addProduct() {
  const product = {
    productName: productName.value,
    category: category.value,
    price: price.value,
    quantity: quantity.value,
    note: note.value
  };

  if (!product.productName || !product.category) {
    alert("Name and category required");
    return;
  }

  const res = await fetch(
    `/api/${username}/${wardrobe}/product/add`,
    {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(product)
    }
  );

  alert(await res.text());
  if (res.ok) loadProducts();
}

loadProducts();
