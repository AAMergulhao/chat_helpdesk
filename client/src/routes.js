import {createWebHistory, createRouter} from 'vue-router';
import Login from './components/Login.vue';
import Panel from './components/Panel.vue';

const routes = [
    {
        path: '/login',
        name: 'Login',
        component:Login
    },
    {
        path: '/panel',
        name: 'Panel',
        component:Panel
    }
];

const router = createRouter({
    history:createWebHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    if (to.name !== 'Login' && !sessionStorage.usuario){ next({ path: '/login' })}

    else if (to.name == 'Login' && sessionStorage.usuario){ next({ path: '/teste' })}
    
    else next();
  })

export default router;