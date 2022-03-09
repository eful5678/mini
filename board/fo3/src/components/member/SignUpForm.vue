<template>
  <div class="content">
    <div>ID: <input type="text" v-model="member.class.username" /></div>
    <div>PW: <input type="password" v-model="member.class.password" /></div>
    <div>Name: <input type="text" v-model="member.class.name" /></div>
    <div>
      <button @click="signUp">회원가입</button>
    </div>
  </div>
</template>

<script>
import { Member } from "@/resources/entity";
import axios from "axios";

export default {
  name: "SignUpForm",
  data: () => ({
    member: {
      class: new Member(),
    },
  }),
  methods: {
    signUp: function () {
      axios
        .post("http://localhost:8090/member/signup", this.member.class.create())
        .then((result) => {
          console.log(result);
          this.$router.push("/login");
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