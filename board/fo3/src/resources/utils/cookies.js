function saveUserToCookie(value) {
    document.cookie = `user=${value}`;
}

function getUserFromCookie() {
    return document.cookie.replace(
        /(?:(?:^|.*;\s*)user\s*=\s*([^;]*).*$)|^.*$/,
        "$1"
      );
}

function deleteCookie(value) {
    console.log(document.cookie)
    console.log(value);
    document.cookie = `${value}=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
    console.log(document.cookie)

}

export { saveUserToCookie, getUserFromCookie, deleteCookie }