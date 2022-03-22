<template>
  <div class="boardForm">
    <input type="text" v-model="board.class.content" />
    <span class="boardForm btn" @click="createBoard">등록</span>
  </div>
</template>

<script>
import { Board } from "@/resources/entity";
import axios from "axios";
export default {
  data: () => ({
    board: {
      class: new Board(),
    },
  }),
  methods: {
    // 글작성 request
    createBoard: function () {
      if (this.$store.state.username === "") {
        return alert("로그인 필요");
      }
      if (this.board.class.content === "") {
        return alert("글을 입력해주세요");
      }
      this.board.class.username = this.$store.state.username;
      axios
        .post("http://localhost:8091/board/create", this.board.class.create())
        .then((result) => {
          console.log(result);
          this.board.class.content = "";
          this.$emit("refresh");
        })
        .catch((err) => {
          console.log(err);
        })
        .finally(() => {});
    },
  },
};
</script>

<style>
</style>