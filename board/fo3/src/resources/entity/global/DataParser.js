export default class DataParser {

    #rtnData;
    #inData;
    constructor(result) {
        const _data = result || {};
        // this.#inData = result.data;
        let data = result;
        console.log(this.#inData);

    }

    dataParse(_class) {
        this.#rtnData = this.dataConvert(this.data, _class);
        let rtn = {
            data: this.#rtnData,
        }

        if (!Array.isArray(this.data)) {
            rtn.class = this.#rtnData;
        }
        return rtn;
    }

    dataConvert(_inData, _inClass) {
        if (Array.isArray(_inData)) {
            let _data = [];
            _inData.map(d => {
                const obj = Object.getPrototypeOf(Object.create(_inClass)).constructor(d);

                obj['masking'] = _inClass['masking'] || false;
                _data.push(obj);
            })
            return _data;
        } else {
            const obj = Object.getPrototypeOf(Object.create(_inClass)).constructor(_inData);
            obj['masking'] = _inClass['masking'] || false;
            return obj
        }
    }
}