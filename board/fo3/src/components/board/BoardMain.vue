<template>
  <div>
    <div class="main">
      <div>Board</div>
      <BoardForm @refresh="refresh"></BoardForm>
      <BoardList
        @refresh="refresh"
        :boards="boards"
        @updateDialogOpen="updateDialogOpen"
      ></BoardList>
      <BoardUpdate
        :isShow.sync="flag.boardUpdateFlag"
        @refresh="refresh"
        :boardId="boardId"
        @updateBoard="updateBoard"
      ></BoardUpdate>
    </div>
  </div>
</template>

<script>
import BoardUpdate from "@/components/board/BoardUpdate.vue";
import BoardForm from "@/components/board/BoardForm.vue";
import BoardList from "@/components/board/BoardList.vue";
import axios from "axios";

export default {
  name: "BoardMain",
  components: {
    BoardForm,
    BoardList,
    BoardUpdate,
  },
  data: () => ({
    boards: [],
    flag: {
      boardUpdateFlag: false,
    },
    boardId: 0,
  }),
  mounted() {
    console.log(this);
    console.log(this.$refs.BoardList);
    this.refresh();
  },
  updated() {
    console.log("updated, BoardMain");
  },
  methods: {
    // 글 목록 조회 용도 & 글작성/수정/삭제 후 List 재 조회 용도
    refresh: function () {
      console.log(";dd");
      axios
        .get("http://localhost:8091/board/list")
        .then((result) => {
          console.log(result);
          this.boards = result.data;
          // this.$refs.BoardList.search();
        })
        .catch((err) => {
          console.log(err);
        })
        .finally(() => {});
    },
    // 수정 팝업 open
    updateDialogOpen: function (boardId) {
      console.log(boardId);
      this.boardId = boardId;
      this.flag.boardUpdateFlag = true;
    },
    // 수정 request
    updateBoard: function (boardId) {
      this.board.class.id = boardId;
      console.log(this.board.class.update());
      axios
        .post(
          "http://localhost:8090/board/updateBoard",
          this.board.class.update()
        )
        .then((result) => {
          console.log(result);
          this.search();
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