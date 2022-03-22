import { Response } from '@/resources/entity';

export default class GlobalMethods {
    constructor() {

    }

    longToDate(val) {
        let inputDate = new Date(val);
        inputDate = inputDate.getFullYear().toString() + '-' + (inputDate.getMonth() + 1).toString() + '-' + inputDate.getDate().toString()

        return inputDate;
    }

    responseParse(dataArea, _data, _class) {
        Object.assign(dataArea, new Response(_data).parse(_class));
    }
}