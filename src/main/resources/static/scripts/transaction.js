document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("cancelButton").addEventListener("click", cancelTransaction);
});

function cancelTransaction(event) {
    window.location.assign("/MainMenu/");
}