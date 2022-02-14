import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        // {
        //     path: '/',
        //     component: () => import("@/components/Main.vue")
        // },
        {
            path: '/',
            redirect: '/board'
        },
        {
            path: '/board',
            name: 'BoardList',
            component: () => import("@/components/board/BoardList.vue")
        },
        {
            path: '/boardForm',
            name: 'BoardForm',
            component: () => import("@/components/board/BoardForm.vue")
        },
        {
            path: '/boardInfo/:id',
            name: 'BoardInfo',
            component: () => import("@/components/board/BoardInfo.vue")
        },
        {
            path: '/replyList',
            name: 'Reply',
            component: () => import("@/components/reply/Reply.vue")
        }
    ]
});

export default router;