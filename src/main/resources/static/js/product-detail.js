const username = getUser();
const productId = localStorage.getItem("productId");

fetch(`/api/${username}/any/product/id/${productId}`)
  .then(res => res.json())
  .then(p => {
    name.innerText = p.productName;
    note.innerText = p.note;
  });

async function upload() {
  const file = document.getElementById("image").files[0];
  const fd = new FormData();
  fd.append("image", file);

  const res = await fetch(
    `/api/${username}/any/product/add/image/${productId}`,
    { method: "PUT", body: fd }
  );

  alert(await res.text());
}
