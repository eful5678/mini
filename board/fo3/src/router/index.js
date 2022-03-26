import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "BoardMain",
    component: () => import("@/components/board/BoardMain.vue"),
  },
  {
    path: '/login',
    name: 'LoginForm',
    component: () => import('@/components/member/LoginForm.vue')
  },
  {
    path: '/singup',
    name: 'SingUpForm',
    component: () => import('@/components/member/SignUpForm.vue')
  },
  {
    path: '/memberList',
    name: 'MemberList',
    component: () => import('@/components/member/MemberList.vue')
  },
  {
    path: '/todoList',
    name: 'TodoList',
    component: () => import('@/components/todo/TodoList.vue')
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
