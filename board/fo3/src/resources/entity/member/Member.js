import { BaseEntity } from "../global";

export default class Member extends BaseEntity {
    constructor(result){
        super();
        const _data = result || {};

        this.username = _data.username || '';
        this.password = _data.password || '';
        this.passwordCheck = _data.passwordCheck || '';
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