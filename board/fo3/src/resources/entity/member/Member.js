export default class Member {
    constructor(result){
        const _data = result || {};

        this.username = _data.username || '';
        this.password = _data.password || '';
        this.name = _data.name || ''
    };

    create(){
        return{
            username: this.username,
            password: this.password,
            name: this.name,
        }
    }
    login(){
        return {
            username: this.username,
            password: this.password,
        }
    }
}