import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import PrivateTemplate from '../templates/PrivateTemplate'
import Cadastro from '../views/Cadastro'
import Login from '../views/Login'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/private/*',
      name: 'privateTemplate',
      redirect: '/private/home',
      component: PrivateTemplate,
      children: [
        {
          path: '/private/home',
          name: 'home',
          component: Home
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/cadastro',
      name: 'cadastro',
      component: Cadastro
    },
    {
      path: '/',
      redirect: '/private/home'
    }
  ]
})
