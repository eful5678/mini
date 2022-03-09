<template>
  <div class="content">
    <div>ID: <input type="text" v-model="member.class.username" /></div>
    <div>PW: <input type="password" v-model="member.class.password" /></div>
    <div>
      <span>
        <button @click="login">로그인</button>
      </span>
      <span> <button @click="signUpBind">회원가입</button></span>
    </div>
  </div>
</template>

<script>
import { Member } from "@/resources/entity";
import axios from "axios";

export default {
  name: "loginForm",
  data: () => ({
    member: {
      class: new Member(),
    },
  }),
  methods: {
    signUpBind: function () {
      this.$router.push("/singup");
    },
    login: function () {
      if (
        this.member.class.username === "" ||
        this.member.class.password === ""
      ) {
        alert("아이디와 비밀번호를 입력해주세요");
        return;
      }
      // this.member.class.username = this.$store.state.username;
      axios
        .post("http://localhost:8090/member/login", this.member.class.login())
        .then((result) => {
          console.log(result);
          this.$store.commit("setUsername", this.member.class.username);
          this.$router.push("/");
        })
        .catch((err) => {
          console.log(err.response);
          console.log(err.error);
          alert(err.response.data.message);
        })
        .finally(() => {});
    },
  },
};
</script>

<style>
</style>