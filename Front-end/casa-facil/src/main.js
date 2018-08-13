import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import store from './store/store'
import BootstrapVue from 'bootstrap-vue'
import GSignInButton from 'vue-google-signin-button'
import FBSignInButton from 'vue-facebook-signin-button'
import VueTheMask from 'vue-the-mask'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)
Vue.use(GSignInButton)
Vue.use(FBSignInButton)
Vue.use(VueTheMask)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
