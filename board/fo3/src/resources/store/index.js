import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios";
import { Reply } from "@/resources/entity";
import { saveUserToCookie, getUserFromCookie } from '@/resources/utils/cookies'


Vue.use(Vuex); // use -> Vue의 plugin -> vue 전역에서 사용하기 위해 사용

export const store = new Vuex.Store({
    state: {
        headerText: 'Jin',
        username: getUserFromCookie() || '',

    },
    getters:{
        isLogin: function(state){
            return state.username !== '';
        }
    },
    mutations: {
        setUsername: function (state, username) {
            state.username = username;
            // document.cookie = username;
            saveUserToCookie(username);
        },
        clearUsername: function(state){
            state.username = '';
        }
    },
    actions: {
    },
});