<template>
  <div>
    <div class="main">
      <div>Board</div>
      <BoardForm @refresh="refresh"></BoardForm>
      <BoardList ref="BoardList"></BoardList>
    </div>
  </div>
</template>

<script>
import BoardForm from "@/components/board/BoardForm.vue";
import BoardList from "@/components/board/BoardList.vue";
import axios from "axios";

export default {
  name: "BoardMain",
  components: {
    BoardForm,
    BoardList,
  },
  data: () => ({
    boards: [],
  }),
  mounted(){
    console.log(this)
    console.log(this.$refs.BoardList);
  },
  updated(){
    console.log('updated, BoardMain')
  },
  methods: {
    refresh: function () {
      axios
        .get("http://localhost:8090/board/list")
        .then((result) => {
          console.log(result);
          this.boards = result.data;
          this.$refs.BoardList.search();
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