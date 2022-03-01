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
    createBoard: function () {
      if (this.board.class.content === "") {
        return alert("글을 입력해주세요");
      }
      axios
        .post("http://localhost:8090/board/create", this.board.class.create())
        .then((result) => {
          console.log(result);
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