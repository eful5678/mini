<template>
  <div class="boardList">
    <h1 class="boardList title">Board</h1>
    <table class="boardList table">
      <colgroup>
        <col width="10%">
        <col width="60%">
        <col width="30%">
      </colgroup>
      <thead>
        <tr>
          <th>순번</th>
          <th>제목</th>
          <th>작성자</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(board, index) in boards" :key="index">
          <td>{{ index }}</td>
          <td @click="boardBind(board.id)">{{ board.title }}</td>
          <td>{{ board.writer }}</td>
        </tr>
      </tbody>
    </table>
    <div>
      <button @click="BoardFormBind">등록</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Board } from "@/resources/entity";
export default {
  name: "BoardList",
  data: () => ({
    board: {
      class: new Board(),
    },
    boards: [],
  }),
  mounted() {
    console.log(this);
    this.search();
  },
  methods: {
    search: function () {
      axios
        .get("http://localhost:8090/board/list")
        .then((result) => {
          console.log(result);
          this.boards = result.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    BoardFormBind: function () {
      this.$router.push("/boardForm");
    },
    boardBind: function(id) {
      console.log('bid');
      this.$router.push({
        name: 'BoardInfo',
        params: {
          id: id
        }
      })
    }
  },
};
</script>

<style>
</style>