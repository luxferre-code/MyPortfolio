const icons = [
    "a.png",
    "l.png",
    "e.png",
    "n.png",
    "t.png",
    "i.png",
    "n.png",
    "v.png"
]

document.addEventListener("DOMContentLoaded", () => {
    let i = 0;
    setInterval(() => {
        document.querySelector("link[rel='icon']").href = `contents/icons/lettre-${icons[i]}`;
        i = (i + 1) % icons.length;
    }, 1000);
});