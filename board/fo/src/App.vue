<template>
  <div class="main">
    <Header></Header>
    <router-view></router-view>
  </div>
</template>

<script>
import Header from "@/components/header/Header.vue";
export default {
  name: "App",
  components: {
    Header,
  },
};
</script>

<style>
.main {
  /* text-align: center; */
  width: 100%;
  height: 100%;
}
.header {
  text-align: center;
  color: purple;
}
.boardList {
  text-align: center;
  position: relative;
  width: 100%;
  height: 100%;
}
.boardList .title {
  text-align: center;
}
.boardList .table {
  text-align: center;
  border: 1px solid;
  width: 70%;
  display: inline-table;

}
/* @import "./css/main.css"; */
</style>
