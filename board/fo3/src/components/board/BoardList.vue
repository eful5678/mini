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
          <span style="cursor: pointer" @click="updateBoard"
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
  components: {
    Reply,
  },
  data: () => ({
    board: {
      class: new Board(),
    },
    boards: [],
    openReplyId: 0,
  }),
  mounted() {
    this.search();
  },
  updated() {
    console.log("updated");
  },
  methods: {
    refresh: function () {
      this.search();
    },
    search: function () {
      axios
        .get("http://localhost:8090/board/list")
        .then((result) => {
          console.log(result);
          this.boards = result.data;
        })
        .catch((err) => {
          console.log(err);
        })
        .finally(() => {});
    },
    updateBoard: function () {
      alert("기능 구현중");
    },
    deleteBoard: function (boardId) {
      console.log(boardId);
      let deleteCheck = confirm("게시글을 삭제하시겠습니까?");
      if (deleteCheck) {
        alert("삭제");
        this.board.class.id = boardId;
        axios
          .post(
            "http://localhost:8090/board/deleteBoard",
            this.board.class.delete()
          )
          .then((result) => {
            console.log(result);
            this.search();
          })
          .catch((err) => {
            console.log(err);
          })
          .finally(() => {});
      }
    },
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