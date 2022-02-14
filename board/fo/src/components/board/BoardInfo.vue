<template>
  <div>
    <div>
      <table>
        <tr>
          <th>Title {{ board.class.id }}</th>
          <td>{{ board.class.title }}</td>
        </tr>
        <tr>
          <th>Content</th>
          <td>{{ board.class.content }}</td>
        </tr>
      </table>
    </div>
    <div>
      <Reply :board="board" :boardParam="$route.params" :depth=0></Reply>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Board } from "@/resources/entity";
import Reply from "@/components/reply/Reply.vue";

export default {
  components: {
    Reply,
  },
  data: () => ({
    id: 0,
    board: {
      class: new Board(),
    },
  }),
  created() {
    this.id = this.$route.params.id;
  },
  mounted() {
    this.search();
    console.log(this);
    console.log(this.$route.params);
    console.log(typeof(this.$route.params));
  },
  methods: {
    search: function () {
      axios
        .get("http://localhost:8090/board/boardInfo", {
          params: { id: this.id },
        })
        .then((result) => {
          console.log(result);
          this.board.class.title = result.data.title;
          this.board.class.content = result.data.content;
          this.board.class.id = result.data.id;
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