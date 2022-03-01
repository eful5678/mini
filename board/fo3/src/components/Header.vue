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
      <div>
        <a style="cursor: pointer" @click="signUpBind">Sign In</a>
      </div>
    </header>
  </div>
</template>

<script>
export default {
  methods: {
    signUpBind: function () {
      console.log("singUp");
      this.$router.push("/login");
    },
  },
};
</script>

<style></style>
