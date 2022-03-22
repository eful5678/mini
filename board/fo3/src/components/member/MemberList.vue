<template>
  <div class="content">
    <div>
      <table>
        <tbody>
          <tr>
            <th>No.</th>
            <th>ID</th>
            <th>이름</th>
            <th>가입일</th>
          </tr>
          <tr v-for="(member, index) in members" :key="index">
            <td>{{ index + 1 }}</td>
            <td>{{ member.username }}</td>
            <td>{{ member.name }}</td>
            <td>{{ common.longToDate(member.createDateTime) }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { Member } from "@/resources/entity";
import axios from "axios";

export default {
  data: () => ({
    member: {
      class: new Member(),
    },
    members: [],
  }),
  created() {
    this.search();
  },
  methods: {
    search: function () {
      axios
        .get("http://localhost:8090/member/list")
        .then((result) => {
          console.log(result);
          // this.common.responseParse(this.member, result, this.member.class);
          console.log(this.member);
          this.members = result.data;
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