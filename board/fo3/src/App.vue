<template>
  <div id="app" class="app">
    <Header></Header>
    <LeftMenu></LeftMenu>
    <router-view></router-view>
    <Footer></Footer>
  </div>
</template>
<script>
import Header from "@/components/Header.vue";
import LeftMenu from "@/components/LeftMenu.vue";
import Footer from "@/components/Footer.vue";
export default {
  name: "App",
  components: {
    Header,
    LeftMenu,
    Footer,
  },
};
</script>
<style lang="scss">
@import "./assets/scss/layout.scss";
@import "./assets/scss/reset.scss";
@import "./assets/scss/leftmenu.scss";
@import "./assets/scss/header.scss";
@import "./assets/scss/main.scss";
@import "./assets/scss/footer.scss";
@import "./assets/scss/board/board.scss";
@import "./assets/scss/reply/reply.scss";
@import "./assets/scss/member/member.scss";
</style>
