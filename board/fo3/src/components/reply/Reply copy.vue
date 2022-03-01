<template>
  <div class="reply">
    <div>dddd</div>
    <div
      :style="`margin-left:${reReply.depth * 5}px`"
      v-for="(reReply, index) in replys"
      :key="index"
    >
      <div>
        <div
          class="reply replyContent"
          style="display: flex; align-items: center"
        >
          <div>
            {{ reReply.depth }}
            <div style="font-weight: 800">{{ reReply.replyWriter }}<br /></div>
            <div style="font-size: 0.7rem">
              {{ reReply.createDateTime }}
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
                src="@/assets/icon/button/write.png"
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
        <Reply
          v-if="reReply.replys.length > 0"
          :reReplys="reReply.replys"
        ></Reply>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Reply } from "@/resources/entity";
// import Reply from "@/components/reply/Reply.vue";

export default {
  name: "Reply",
  components: {
    // Reply,
  },
  props: {
    boardId: {
      type: Number,
    },
    reReplys: {
      type: Array,
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
  mounted() {
    console.log(this, "mounted");
    console.log(typeof this.boardId);
    this.reply.class.boardId = this.boardId;
    this.search();
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
  },
};
</script>

<style>
</style>