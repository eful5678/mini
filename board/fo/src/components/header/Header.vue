<template>
  <div class="header">
    <h1 @click="mainBind">Main</h1>
    <h2>{{ $route.name }}</h2>
  </div>
</template>

<script>
export default {
  mounted() {
    console.log(this);
  },
  methods: {
    mainBind: function () {
      this.$router.push("/");
    },
  },
};
</script>

<style>
</style>