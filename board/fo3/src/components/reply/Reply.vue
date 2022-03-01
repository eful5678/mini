<template>
  <div class="reply">
    <div class="reply form">
      <input
        style="width: 20%"
        type="text"
        v-model="reply.class.replyWriter"
        placeholder="작성자"
      />&nbsp;
      <input
        style="width: 70%"
        type="text"
        v-model="reply.class.content"
        placeholder="댓글을 입력해주세요"
      />
      <button class="button_write" @click="createReply">
        <img
          src="@/assets/icon/button/add.png"
          style="width: 20px; height: 20px"
          alt=""
        />
      </button>
    </div>
    <div class="reply list" v-for="(reply, index) in replys" :key="index">
      <div v-if="reply.depth === 0">
        <div class="reply list" style="display: flex; align-items: center; border-top: 1px solid">
          <div class="reply list writer">
            <div style="font-weight: 800">{{ reply.replyWriter }}<br /></div>
            <div style="font-size: 0.7rem">
              {{ common.longToDate(reply.createDateTime) }}
            </div>
          </div>
          <div>
            {{ reply.content }}
          </div>
          <div>
            <button @click="openInput(reply.replyId)">
              <img
                src="@/assets/icon/button/add.png"
                style="width: 20px; height: 20px"
                alt=""
              /></button
            >&nbsp;&nbsp;
            <button @click="deleteReply(reply.replyId)">
              <img
                src="@/assets/icon/button/delete.png"
                style="width: 20px; height: 20px"
                alt=""
              />
            </button>
          </div>
        </div>
        <div>
          <div v-show="reply.replyId === flagId">
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
            <button @click="createSubReply(reply.replyId)">등록</button>
          </div>
        </div>
        <reReply :replys="reply.replys" @refresh="refresh"></reReply>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Reply } from "@/resources/entity";
import reReply from "@/components/reply/reReply.vue";

export default {
  name: "Reply",
  components: {
    reReply,
  },
  props: {
    boardId: {
      type: Number,
      required: true,
    },
  },
  data: () => ({
    reply: {
      class: new Reply(),
    },
    replys: [],
    subContent: "",
    replyWriter: "",
    // boardParamId: this.boardId,
    flagId: 0,
  }),
  beforeCreate() {
    console.log(this, "beforeCreate");
  },
  created() {
    console.log(this, "created");
    // this.reply.class.boardId = Number(this.boardParam.id);
    this.$store.commit("refreshReply", {
      boardId: this.boardId,
    });
    this.replys = this.$store.state.replys;
  },
  mounted() {
    console.log(this, "mounted");
    console.log(typeof this.boardId);
    this.reply.class.boardId = this.boardId;
    this.search();
  },
  computed: {},
  methods: {
    search: function () {
      console.log(this.reply.class.boardId);
      axios
        .get("http://localhost:8090/reply/list", {
          params: { boardId: this.reply.class.boardId },
        })
        .then((result) => {
          console.log(result);
          this.replys = result.data;
        })
        .catch((err) => {
          console.log(err);
        })
        .finally(() => {});
    },
    createReply: function () {
      this.reply.class.parentId = null;
      this.reply.class.replyId = null;
      axios
        .post("http://localhost:8090/reply/create", this.reply.class.create())
        .then((result) => {
          console.log(result);
          this.search();
        })
        .catch((err) => {
          console.log(err);
        })
        .finally(() => {});
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
          // this.searchReply();
          this.search();
        })
        .catch((err) => {
          console.log(err);
        })
        .finally(() => {});
    },
    openInput: function (id) {
      // this.flagId = id;
      this.flagId === 0 ? (this.flagId = id) : (this.flagId = 0);
    },
    createSubReply: function (replyId) {
      console.log(replyId);
      this.reply.class.replyId = replyId;
      this.reply.class.content = this.subContent;
      this.reply.class.parentId = replyId;
      this.reply.class.replyWriter = this.replyWriter;
      axios
        .post("http://localhost:8090/reply/create", this.reply.class.create())
        .then((result) => {
          console.log(result);
          this.search();
          this.flagId = 0;
        })
        .catch((err) => {
          console.log(err);
        })
        .finally(() => {
          this.flagId = 0;
        });
    },
    refresh: function () {
      console.log("refresh");
      this.$emit("refresh");
      this.search();
    },
  },
};
</script>

<style>
</style>