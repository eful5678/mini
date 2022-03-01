import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios";
import { Reply } from "@/resources/entity";


Vue.use(Vuex); // use -> Vue의 plugin -> vue 전역에서 사용하기 위해 사용

export const store = new Vuex.Store({
    state: {
        headerText: 'Jin',
        replys: [], 
        reply: {
            class: new Reply(),
        },

    },
    mutations: {
        refreshReply: function (state, boardId) {
            console.log(boardId);
            axios
                .get("http://localhost:8090/reply/list", {
                    params: { boardId: boardId.boardId },
                })
                .then((result) => {
                    console.log(result);
                    this.replys = result.data;
                })
                .catch((err) => {
                    console.log(err);
                })
                .finally(() => { });
        }
    }
});