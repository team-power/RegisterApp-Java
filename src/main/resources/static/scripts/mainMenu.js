document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("startTransactionButton").addEventListener("click", startTransactionClick);
    document.getElementById("productListingButton").addEventListener("click", productListingClick);
    document.getElementById("employeeDetailsButton").addEventListener("click", employeeDetailsClick);
    document.getElementById("salesReportButton").addEventListener("click", salesReportClick);
    document.getElementById("cashierReportButton").addEventListener("click", cashierReportClick);
});

function startTransactionClick(event) {
    window.location.assign("/transaction/");
}

function productListingClick(event) {
    window.location.assign("/productListing/");
}

function employeeDetailsClick(event) {
    window.location.assign("/employeeDetail/");
}

function salesReportClick(event) {
    displayError("Functionality has not yet been implemented.");
}

function cashierReportClick(event) {
    displayError("Functionality has not yet been implemented.");
}