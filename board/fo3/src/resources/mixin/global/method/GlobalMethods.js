export default class GlobalMethods {
    constructor() {

    }

    longToDate(val) {
        let inputDate = new Date(val);
        inputDate = inputDate.getFullYear().toString() + '-' + (inputDate.getMonth() + 1).toString() + '-' + inputDate.getDate().toString()

        return inputDate;
    }
}