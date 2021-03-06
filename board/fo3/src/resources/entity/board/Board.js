export default class Board{
    constructor(result){
        const _data = result || {};
        this.username = _data.username || '';
        this.title = _data.title || '';
        this.content = _data.content || '';
        this.id = _data.id || 0;
    }

    create() {
        return {
            title: this.title,
            content: this.content,
            username: this.username,
        }
    }
    update(){
        return{
            boardId: this.id,
            content: this.content,
        }
    }
    delete(){
        return {
            boardId: this.id,
        }
    }
    like(){
        return{
            boardId: this.id,
        }
    }
}