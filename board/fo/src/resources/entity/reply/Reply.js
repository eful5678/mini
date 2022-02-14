export default class Reply {
    constructor(result) {
        const _data = result || {};
        this.content = _data.content || '';
        this.boardId = _data.boardId || 0;
        this.parentId = _data.parentId || 0;

        this.subContent = _data.subContent || '';
        
    }

    create(){
        return{
            content: this.content,
            boardId: this.boardId,
            replyId: this.replyId,
            parentId: this.parentId,
            
        }
    }
}