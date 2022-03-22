<template>
  <div class="content">
    <div class="signUp">
      <table>
        <thead>
          <colgroup>
            <col width="30%" />
            <col width="20%" />
            <col width="50%" />
          </colgroup>
        </thead>
        <tbody>
          <tr>
            <th>ddddddd</th>
            <td>ddd</td>
            <td>ddd</td>
          </tr>
          <tr>
            <th>ddd</th>
            <td>dfdf</td>
          </tr>
        </tbody>
      </table>
      <div>ID: <input type="text" v-model="member.class.username" /></div>
      <div>
        PW: <input type="password" v-model="member.class.password" />
        <span
          v-show="passwordCheck !== ''"
          :style="
            passwordCheck === '비밀번호 일치' ? 'color: blue' : 'color: red'
          "
        >
          {{ passwordCheck }}</span
        >
      </div>
      <div>
        PWCheck: <input type="password" v-model="member.class.passwordCheck" />
      </div>
      <div>Name: <input type="text" v-model="member.class.name" /></div>
      <div>
        <button @click="signUp">회원가입</button>
      </div>
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
  computed: {
    passwordCheck: function () {
      let check;
      console.log(check);
      if (
        this.member.class.password === "" &&
        this.member.class.passwordCheck === ""
      ) {
        check = "";
      } else if (
        this.member.class.password === this.member.class.passwordCheck
      ) {
        check = "비밀번호 일치";
      } else {
        check = "비밀번호가 다릅니다";
      }
      return check;
    },
  },
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