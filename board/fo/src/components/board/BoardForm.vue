<template>
  <div>
    <div>
      <table>
        <colgroup></colgroup>
        <tbody>
          <tr>
            <th>제목</th>
            <td><input type="text" v-model="board.class.title" /></td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea
                name=""
                id=""
                cols="30"
                rows="10"
                v-model="board.class.content"
              ></textarea>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div>
      <button @click="create">글 등록</button>
    </div>
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
    create: function () {
      axios
        .post("http://localhost:8090/board/create", this.board.class.create())
        .then((result) => {
          console.log(result);
          this.$router.push("/board");
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