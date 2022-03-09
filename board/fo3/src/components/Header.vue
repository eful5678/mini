<template>
  <div>
    <header class="header">
      <div class="skip">
        <a href="/">
          <img
            src="@/assets/icon/home.png"
            style="width: 40px; height: 40px"
            alt=""
        /></a>
      </div>
      <div>{{ this.$store.state.headerText }}</div>
      <div v-if="isLogin">
        <span>{{ $store.state.username }}님 안녕하세요</span><br />
        <a style="cursor: pointer" @click="logout">Log Out</a>
      </div>
      <div v-else>
        <a style="cursor: pointer" @click="signUpBind">Sign In</a>&nbsp;
        <a style="cursor: pointer">Sign Up</a>
      </div>
    </header>
  </div>
</template>

<script>
import { deleteCookie } from "@/resources/utils/cookies";
export default {
  data: () => ({
    username: "",
  }),
  created() {
    console.log(this.$store.state.username);
    this.username = this.$store.state.username;
  },
  computed: {
    isLogin: function () {
      return this.$store.getters.isLogin;
    },
  },
  methods: {
    signUpBind: function () {
      console.log("singUp");
      this.$router.push("/login");
    },
    logout: function () {
      this.$store.commit("clearUsername");
      deleteCookie("user");
      // this.$store.state.username = "";
      this.$router.push("/");
    },
  },
};
</script>

<style></style>
