<template>
  <div>
    <aside class="leftMenu">
      <div>LeftMenu</div>
      <div @click="memberListBind">회원목록</div>
      <div @click="todoListBind">할 일</div>
    </aside>
  </div>
</template>

<script>
export default {
  methods: {
    memberListBind: function () {
      this.$router.push("/memberList");
    },
    todoListBind: function(){
      this.$router.push('/todoList');
    }
  },
};
</script>

<style></style>
