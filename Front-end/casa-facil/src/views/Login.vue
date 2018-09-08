<template>
  <div>
    <loader-modal :show-modal="showModal"></loader-modal>
    <div class="col-sm-12 col-md-4 col-lg-4  center">
      <b-card title="Login">
        <div class="row">

          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <fb-signin-button
              :params="fbSignInParams"
              @success="onSignInSuccess"
              @error="onSignInError">
              <b>Entre com uma conta facebook</b>
            </fb-signin-button>
          </div>

          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <g-signin-button
              :params="googleSignInParams"
              @success="onSignInSuccessGoogle"
              @error="onSignInErrorGoogle">
              <b>Entre com uma conta google</b>
            </g-signin-button>
          </div>

          <div style="margin-top: 30px; margin-bottom: -20px" class="col-sm-12 col-md-4 col-lg-12">
            <p>ou</p>
          </div>

          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Digite seu Email:*</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <input type="text" id="email" v-model="email" placeholder="exemplo@dominio.com"
                   class="form-control col-sm-12 col-md-4 col-lg-12"/>
          </div>

          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Digite sua senha:*</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <input type="password" id="senha" v-model="senha" placeholder="Senha (mínimo 8 caracteres)"
                   @keyup.enter="logIn" class="form-control col-sm-12 col-md-4 col-lg-12"/>
          </div>

          <div class="container" style="margin-top: 25px">
            <b-button class="form-control col-sm-12 col-md-4 col-lg-12" @click="logIn" variant="info">Entrar</b-button>
          </div>

        </div>
      </b-card>
    </div>
  </div>
</template>
<script>
import mixinsGoogle from '../mixins/googleServiceMixins'
import mixinsFacebook from '../mixins/facebookServiceMixins'
import {login} from '../services/requestServices'
import Utils from '../util/Utils'
import loaderModal from '../templates/Loader'
export default {
  name: 'login',
  components: {
    loaderModal
  },
  data () {
    return {
      email: '',
      senha: '',
      showModal: false
    }
  },
  mixins: [
    mixinsGoogle,
    mixinsFacebook
  ],
  methods: {
    validarCampos () {
      let validacao = true
      if (Utils.validateEmail(this.email) === false) {
        Utils.alertInput('email')
        validacao = false
      } else {
        Utils.alertInputValid('email')
      }
      if (this.senha === null || this.senha.length < 8) {
        Utils.alertInput('senha')
        validacao = false
      } else {
        Utils.alertInputValid('senha')
      }
      return validacao
    },
    logIn () {
      if (this.validarCampos()) {
        this.showModal = true
        login(this.email, this.senha).then((response) => {
          if (response.data) {
            this.showModal = false
            this.$store.commit('alterarSessao', response.data)
            this.$router.push({name: 'home'})
          }
        }).catch((err) => {
          console.log(err.response)
          this.showModal = false
        })
      }
    }
  }
}
</script>

<style scoped>
  .center {
    width: 50%;
    padding: 10px;
    margin: auto;
  }

  input {
    height: 30px;
  }
</style>
