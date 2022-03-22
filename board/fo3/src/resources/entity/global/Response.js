import DataParser from '@/resources/entity/global/DataParser';

export default class Response extends DataParser {
    constructor(result) {
        super(result.data || {})
        this.status = result.status;
        this.headers = result.headers || {};

        const _data = result.data || {};

        this.startTime = _data.startTime || 0;
    }

    parse(_class) {
        let rtnObject = this.dataParse(_class);
        rtnObject.config = this;
        return rtnObject;
    }
}