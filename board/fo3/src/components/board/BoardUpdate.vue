<template>
  <!-- <div :class="{ show: isShow, hide: !isShow }"> -->
  <div v-show="isShow">
    <div class="layer-wrap is-active">
      <div class="layer layer--is-active" style="width: 500px">
        <div class="layer__tit">
          <div>
            <div>글 수정</div>
            <div>
              <textarea
                name=""
                id=""
                cols="30"
                rows="10"
                v-model="board.class.content"
              ></textarea>
            </div>
            <div>
              <button @click="updateBoard(boardId)">수정</button>
              <button @click="closeDialog">닫기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Board } from "@/resources/entity";
export default {
  props: {
    isShow: {
      type: Boolean,
      required: true,
      default: false,
    },
    boardId: {
      type: Number,
      default: 0,
    },
  },
  data: () => ({
    board: {
      class: new Board(),
    },
  }),
  created() {
    console.log(this.isShow);
    console.log(this.boardId);
  },
  updated() {
    console.log(this.isShow);
  },
  methods: {
    closeDialog: function () {
      this.$emit("update:isShow", false);
    },
    updateBoard: function (boardId) {
      // this.$emit("updateBoard", boardId);
    },
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
          this.$emit("update:isShow", false);
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

<style lang="scss" scoped>
.layer-body {
  max-height: 400px;
  overflow: auto;
  overflow-x: hidden;
}
.layer-wrap {
  position: fixed;
  display: flex;
  top: 0;
  left: 0;
  justify-content: center;
  align-items: center;
  -webkit-box-pack: center;
}
.layer-wrap:before {
  content: "";
  position: absolute;
  transition: background-color 0.2s;
}
.layer-wrap.is-active {
  z-index: 10010;
  right: 0;
  bottom: 0;
  height: auto;
}
.layer-wrap.is-active:before {
  background-color: black;
}
.layer {
  display: none;
  position: relative;
  background-color: antiquewhite;
  transition: top 0.2s 0.1s;
}
.layer.layer--is-active {
  display: block;
}
.layer__tit {
  display: block;
  color: black;
}
</style>