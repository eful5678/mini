<template>
  <!-- <div v-if="replys.length > 0"> -->
  <div v-if="replys.length > 0">
    <div
      :style="`margin-left:${reReply.depth * 5}px`"
      v-for="(reReply, index) in replys"
      :key="index"
    >
      <div class="reply list">
        <div class="reply list" style="display: flex; align-items: center; border-top: 1px solid">
          <div class="reply list writer">
            {{ reReply.depth }}
            <div style="font-weight: 800">{{ reReply.replyWriter }}<br /></div>
            <div style="font-size: 0.7rem">
              {{ common.longToDate(reReply.createDateTime) }}
            </div>
          </div>
          <div>
            {{ reReply.content }}
          </div>
          <div>
            <button
              class="button_write"
              @click="openInput(reReply.replyId, reReply.boardId)"
            >
              <img
                src="@/assets/icon/button/add.png"
                style="width: 20px; height: 20px"
                alt=""
              /></button
            >&nbsp;&nbsp;
            <button class="button_write" @click="deleteReply(reReply.replyId)">
              <img
                src="@/assets/icon/button/delete.png"
                style="width: 20px; height: 20px"
                alt=""
              />
            </button>
          </div>
        </div>
        <div v-show="reReply.replyId === flagId">
          <input
            style="width: 50px"
            type="text"
            v-model="replyWriter"
            placeholder="작성자"
          />&nbsp;
          <input
            style="width: 300px"
            type="text"
            v-model="subContent"
            placeholder="댓글을 입력해주세요"
          />
          <button @click="createSubReply(reReply.replyId)">등록</button>
        </div>
        <reReply
          ref="reReply"
          v-if="reReply.replys.length > 0"
          :replys="reReply.replys"
        ></reReply>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Reply } from "@/resources/entity";

export default {
  name: "reReply",
  props: {
    replys: {
      type: Array,
    },
    // boardId: {
    //   type: Number,
    //   required: true,
    // },
  },
  data: () => ({
    subContent: "",
    // boardParamId: this.boardId,
    flagId: 0,
    reply: {
      class: new Reply(),
    },
    replyWriter: "",
    replies: [],
  }),
  created() {
    console.log(this.replys);
    console.log(this.boardId);
    // this.reply.class.boardId = Number(this.boardParam.id);
  },
  mounted() {
    console.log(typeof this.boardId);
    console.log(this);
    this.reply.class.boardId = this.boardId;
  },
  // updated(){
  //   this.searchReply();
  // },
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
    searchReply: function () {
      axios
        .get("http://localhost:8090/reply/list", {
          params: { replyId: this.reply.class.replyId },
        })
        .then((result) => {
          console.log(result);
        })
        .catch((err) => {
          console.log(err);
        })
        .finally(() => {});
    },
    openInput: function (id, boardId) {
      // this.flagId = id;
      this.flagId === 0 ? (this.flagId = id) : (this.flagId = 0);
      this.reply.class.boardId = boardId;
    },
    createRe: function (id) {
      this.flagId = id;
    },
    // createSubReply: function (id) {
    //   console.log("emit");
    //   this.$emit("createSubReply", id);
    // },
    createSubReply: function (replyId) {
      console.log("글 작성");
      console.log(replyId);
      this.reply.class.replyId = replyId;
      this.reply.class.content = this.subContent;
      this.reply.class.parentId = replyId;
      this.reply.class.replyWriter = this.replyWriter;
      this.reply.class.boardId = this.boardId;
      axios
        .post("http://localhost:8090/reply/create", this.reply.class.create())
        .then((result) => {
          console.log(result);
          console.log(this);
          // this.searchReply();
          // this.$emit("refresh");
          // this.$refs.reReply.searchReply();
          this.$store.commit("refreshReply", {
            boardId: result.data.boardId
          });
        })
        .catch((err) => {
          console.log(err);
        })
        .finally(() => {
          this.flagId = 0;
        });
    },
    deleteReply: function (id) {
      this.reply.class.replyId = id;
      axios
        .post(
          "http://localhost:8090/reply/deleteReply",
          this.reply.class.delete()
        )
        .then((result) => {
          console.log(result);
          this.searchReply();
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