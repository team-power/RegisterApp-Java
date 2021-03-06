document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("cancelButton").addEventListener("click", cancelTransaction);
    var listElements = document.getElementsByClassName("removeButton");
    for (let i = 0; i < listElements.length; i++) {
        listElements[i].addEventListener("click", removeProduct);
    }
});

function cancelTransaction(event) {
    window.location.assign("/mainMenu/");
}

function removeProduct(event) {
    var target = event.target;
    var parent = target.parentElement;
    var productLookupCode = parent.querySelector(".productLookupCodeDisplay")
    
    window.location.assign(removeProductFromUrl(productLookupCode.innerText ,window.location.href))
}


function removeProductFromUrl(key, sourceUrl) {
    var returnUrl = sourceUrl.split("?")[0];
    var params = sourceUrl.split("?")[1].split("&");
    var returnParams = []


    for (var i = 0; i < params.length; i++) {
        if (key !== params[i].split("=")[0]) {
            returnParams.push(params[i])
        }
    }

    if (returnParams.length > 0) {
        return returnUrl + "?" + returnParams.join("&");
    }
    else {
        return returnUrl;
    }
}