import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import PrivateTemplate from '../templates/PrivateTemplate'
import Cadastro from '../views/Cadastro'
import Login from '../views/Login'
import CadastroDomicilio from '../views/CadastroDomicilio'
import store from '../store/store'

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
        },
        {
          path: '/private/cadastro-domicilio',
          name: 'cadastroDomicilio',
          component: CadastroDomicilio,
          beforeEnter (to, from, next) {
            if (store.state.sessao) {
              next()
            } else {
              next({name: 'login'})
            }
          }
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
      beforeEnter (to, from, next) {
        if (store.state.sessao) {
          next(from.name)
        } else {
          next()
        }
      }
    },
    {
      path: '/cadastro',
      name: 'cadastro',
      component: Cadastro,
      beforeEnter (to, from, next) {
        if (store.state.sessao) {
          next(from.name)
        } else {
          next()
        }
      }
    },
    {
      path: '/',
      redirect: '/private/home'
    }
  ]
})
