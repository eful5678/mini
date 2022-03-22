<template>
  <div class="boardList">
    <div class="boardList main" v-for="(board, index) in boards" :key="index">
      <div class="boardList title">
        <strong class="boardList title writer">
          {{ board.writer }}
        </strong>
        <span class="boardList title date">
          {{ common.longToDate(board.createDateTime) }}
        </span>
      </div>
      <div class="boardList content">
        <span>{{ board.content }}</span>
      </div>
      <div class="boardList editMenu">
        <div>
          <span style="cursor: pointer" @click="likeBoard(board.id)">
            <img
              src="@/assets/icon/button/like.png"
              style="width: 20px; height: 20px"
              alt=""
            />
          </span>
          <span> {{ board.like }} </span>
        </div>
        <div>
          <span style="cursor: pointer" @click="updateBoardDialog(board.id)"
            ><img
              src="@/assets/icon/button/write.png"
              style="width: 20px; height: 20px"
              alt="" /></span
          >&nbsp;
          <span style="cursor: pointer" @click="deleteBoard(board.id)">
            <img
              src="@/assets/icon/button/delete.png"
              style="width: 20px; height: 20px"
              alt=""
            />
          </span>
        </div>
      </div>
      <div>
        <div @click="openReply(board.id)">
          <a>댓글열기</a>
        </div>
        <div v-if="openReplyId === board.id">
          <Reply :boardId="board.id"></Reply>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Reply from "@/components/reply/Reply.vue";
import { Board } from "@/resources/entity";
export default {
  name: "BoardList",
  props: {
    boards: {
      type: Array,
      required: true,
    },
  },
  components: {
    Reply,
  },
  data: () => ({
    board: {
      class: new Board(),
    },
    openReplyId: 0,
  }),
  methods: {
    // 조회용도
    search: function () {
      this.$emit("refresh");
    },
    updateBoardDialog: function (boardId) {
      console.log("update");
      // this.flag.boardUpdateFlag = true;
      this.$emit("updateDialogOpen", boardId);
    },
    // 글삭제 request
    deleteBoard: function (boardId) {
      console.log(boardId);
      let deleteCheck = confirm("게시글을 삭제하시겠습니까?");
      if (deleteCheck) {
        this.board.class.id = boardId;
        axios
          .post(
            "http://localhost:8091/board/deleteBoard",
            this.board.class.delete()
          )
          .then((result) => {
            console.log(result);
            this.$emit("refresh");
          })
          .catch((err) => {
            console.log(err);
          })
          .finally(() => {});
      }
    },
    // 글 좋아요 기능
    likeBoard: function (boardId) {
      console.log(boardId);
      this.board.class.id = boardId;
      axios
        .post("http://localhost:8090/board/likeBoard", this.board.class.like())
        .then((result) => {
          console.log(result);
          this.search();
        })
        .catch((err) => {
          console.log(err);
        })
        .finally(() => {});
    },
    // 댓글 목록 열기
    openReply: function (boardId) {
      console.log(boardId);
      this.openReplyId === 0
        ? (this.openReplyId = boardId)
        : (this.openReplyId = 0);
    },
  },
};
</script>

<style>
</style>