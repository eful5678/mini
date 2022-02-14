<template>
  <div>
    <div style="font-size: 20px">
      {{ boardsId }}
    </div>
    <div v-if="depth === 0">
      <input type="text" v-model="reply.class.content" />
      <button @click="createReply">댓글 등록</button>
    </div>
    <!-- <div v-if="reply.class.length > 0"> -->
    <div>
      <ul v-for="(reply, index) in replys" :key="index">
        <li v-show="reply.parentId === null && reply.depth === 0">
          <div>
            {{ index }}
            {{ reply.replyId }}
            {{ reply.content }}
            <button @click="createRe(index)">대 댓글</button>

            <div>
              <input type="text" v-model="subContent" />
              <button @click="createSubReply(reply.replyId)">등록</button>
            </div>
            <div v-if="reply.reReplys.length > 0">
              <div  v-for="(reReply, index) in reply.reReplys" :key="index">
                {{ reReply.replyId }}
                {{ reReply.content }}
              </div>
            </div>
          </div>
          <!-- <div v-show="reply.depth > 0 && reply.parentId > 0">
            <ul v-for="(reReply, index) in reply.reReplys" :key="index">
              <li>{{ reReply.replyId }}</li>
              <li>{{ reReply.content }}</li>
            </ul>
          </div> -->
        </li>
        <Reply v-if="replys.reReplys.length > 0" :board="board" :replys="replys" :boardParam="$route.params" :depth="reply.depth"></Reply>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Reply } from "@/resources/entity";

export default {
  name: "Reply",
  components: {},
  props: {
    board: {
      type: Object,
      required: true,
    },
    boardParam: {
      type: Object,
      required: true,
    },
    depth: {
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
    // boardParamId: this.boardId,
  }),
  beforeCreate() {
    console.log(this, "beforeCreate");
  },
  created() {
    console.log(this, "created");
    console.log(this.boardParam);
    this.reply.class.boardId = Number(this.boardParam.id);
  },
  mounted() {
    console.log(this, "mounted");
    this.search();
  },
  computed: {
    boardsId: function () {
      return this.boardId;
    },
  },
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
    createRe: function (id) {
      alert("아직....구현중인데요./.");
      var openReply =
        document.getElementById(id) !== null
          ? document.getElementById(id).style.display
          : null;
      if (openReply !== null) {
        document.getElementById(id).classList.add("show");
      }
    },
    createSubReply: function (replyId) {
      console.log(replyId);
      this.reply.class.replyId = replyId;
      this.reply.class.content = this.subContent;
      this.reply.class.parentId = replyId;
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
  },
};
</script>

<style>
</style>