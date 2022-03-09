<template>
  <div>
    <aside class="leftMenu">
      <div>LeftMenu</div>
      <div @click="memberListBind">회원목록</div>
    </aside>
  </div>
</template>

<script>
export default {
  methods: {
    memberListBind: function () {
      this.$router.push("/memberList");
    },
  },
};
</script>

<style></style>
