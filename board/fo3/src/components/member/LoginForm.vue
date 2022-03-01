<template>
  <div class="content">
    <div>ID: <input type="text" /></div>
    <div>PW: <input type="text" /></div>
    <div>
      <span>
        <button>로그인</button>
      </span>
      <span> <button @click="signUpBind">회원가입</button></span>
    </div>
  </div>
</template>

<script>
export default {
  name: "loginForm",
  methods: {
    signUpBind: function () {
      this.$router.push("/singup");
    },
  },
};
</script>

<style>
</style>