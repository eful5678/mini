export default class Reply {
    constructor(result) {
        const _data = result || {};
        this.content = _data.content || '';
        this.boardId = _data.boardId || 0;
        this.parentId = _data.parentId || 0;
        this.replyWriter = _data.replyWriter || '';

        this.subContent = _data.subContent || '';
        
    }

    create(){
        return{
            content: this.content,
            replyWriter: this.replyWriter,
            boardId: this.boardId,
            replyId: this.replyId,
            parentId: this.parentId,
            
        }
    }
    update(){
        return{
            replyId: this.replyId,
            content: this.content,
        }
    }
    delete(){
        return {
            replyId: this.replyId,
        }
    }
}