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

          <input-component :tipo="'text'" :id="'email'" :label="'Digite seu Email:*'" :holder="'exemplo@dominio.com'"
                           @resultadoText="atribuirInformacao"/>

          <input-component :tipo="'password'" :id="'senha'" :label="'Digite sua senha:*'" :holder="'Senha (mínimo 8 caracteres)'"
                           @resultadoText="atribuirInformacao"/>

          <div class="container" style="margin-top: 25px">
            <b-button class="form-control col-sm-12 col-md-4 col-lg-12" @click="logIn" variant="info">Entrar</b-button>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <p>Não possui uma conta? <a href="#" @click="irCriarConta">cadastre-se aqui</a></p>
          </div>
        </div>
      </b-card>
    </div>
  </div>
</template>
<script>
  import mixinsGoogle from '../mixins/googleServiceMixins'
  import mixinsFacebook from '../mixins/facebookServiceMixins'
  import inputComponent from '../components/inputTextComponent'
  import {login} from '../services/requestServices'
  import Utils from '../util/Utils'
  import Swal from '../util/Swal'
  import loaderModal from '../templates/Loader'

  export default {
    name: 'login',
    components: {
      loaderModal,
      inputComponent
    },
    data() {
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
      irCriarConta(){
        this.$router.push({name: 'cadastro'})
      },
      atribuirInformacao(result) {
        if (result.id === 'email') {
          this.email = result.message
        } else if (result.id === 'senha') {
          this.senha = result.message
        }
      },
      validarCampos() {
        let validacao = true;
        if (Utils.validateEmail(this.email) === false) {
          Utils.alertInput('email');
          validacao = false
        } else {
          Utils.alertInputValid('email')
        }
        if (this.senha === null || this.senha.length < 8) {
          Utils.alertInput('senha');
          validacao = false
        } else {
          Utils.alertInputValid('senha')
        }
        return validacao
      },
      logIn() {
        if (this.validarCampos()) {
          this.showModal = true;
          login(this.email, this.senha).then((response) => {
            if (response.data) {
              this.showModal = false;
              this.$store.commit('alterarSessao', response.data);
              this.$router.push({name: 'home'})
            }
          }).catch((err) => {
            Swal.alertUmButton('Atenção', 'Usuário ou senha inválidos', 'error');
            console.log(err.response);
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
