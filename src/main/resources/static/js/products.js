const username = getUser();
const wardrobe = localStorage.getItem("wardrobe");

fetch(`/api/${username}/${wardrobe}/product/all`)
  .then(res => res.json())
  .then(products => {
    const ul = document.getElementById("productList");

    products.forEach(p => {
      const li = document.createElement("li");
      li.textContent = p.productName;

      li.onclick = () => {
        localStorage.setItem("productId", p.id);
        window.location.href = "/product-detail.html";
      };

      ul.appendChild(li);
    });
  });
