<template>
  <div>
    <div class="col-sm-12 col-md-4 col-lg-4 center">
      <loader :show-modal="showModal"></loader>
      <b-card title="Cadastre-se">
        <div class="row">
          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <fb-signin-button
                :params="fbSignInParams"
                @success="onSignInSuccess"
                @error="onSignInError">
              <b>Entre com uma conta facebook</b>
            </fb-signin-button>
          </div>

          <div style="margin-top: 15px" class="col-sm-12 col-md-4 col-lg-12">
            <g-signin-button
                :params="googleSignInParams"
                @success="onSignInSuccessGoogle"
                @error="onSignInErrorGoogle">
              <b>Cadastre-se com uma conta google</b>
            </g-signin-button>
          </div>

          <div style="margin-bottom: -12px; margin-top: 15px" class="col-sm-12 col-md-4 col-lg-12">
            <p>ou</p>
          </div>

          <input-component :tipo="'text'" :holder="'Nome'" :id="'nome'" :label="'Digite seu nome e sobrenome:*'"
                           @resultadoText="atribuirResultado"/>

          <input-number-component :holder="'Número (opcional)'" :id="'numero'" :tipo="'text'"
                                  :label="'Digite seu número de telefone:'"
                                  :mask="'(##) #####-####'"
                                  @resultadoNumber="atribuirResultado"/>

          <input-component :holder="'exemplo@dominio.com'" :id="'email'" :tipo="'text'" :label="'Digite seu Email:*'"
                           @resultadoText="atribuirResultado"/>

          <input-select-component :id="'tipoUsuario'" :options="tiposDeUsuario" :label="'Que tipo de usuário você é?'"
                                  @resultadoSelect="atribuirResultado"/>

          <input-component :holder="'Senha (mínimo 8 caracteres)'" :id="'senha'" :tipo="'password'"
                           :label="'Digite sua senha:*'"
                           @resultadoText="atribuirResultado"/>

          <input-component :holder="'Digite sua senha novamente'" :id="'confirmaSenha'" :tipo="'password'"
                           :label="'Confirme sua senha:*'"
                           @resultadoText="atribuirResultado"/>

          <div class="container" style="margin-top: 25px">
            <b-button class="form-control col-sm-12 col-md-4 col-lg-12" @click="validarEmail" variant="info">
              Cadastrar
            </b-button>
          </div>

          <div class="container">
            <div style="width: 80%; margin: auto" class="col-sm-12 col-md-4 col-lg-12">
              <p>Ao se cadastrar no Casa Fácil você concorda com os
                <a href="https://github.com/WenderGalan/casa-facil-imoveis/blob/master/LICENSE" target="_blank">termos
                  de uso</a>.</p>
            </div>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <p>Possui uma conta? <a href="#" @click="irLogin">Entre aqui</a></p>
          </div>
        </div>
      </b-card>
    </div>
    <div>
      <b-modal ref="myModalRef" hide-footer title="Código de validação da conta"
               centered no-close-on-backdrop hide-header-close>
        <div class="d-block text-center">
          <p class="text-left">Enviamos o código de validação para seu email, por favor, digite o código abaixo</p>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <input type="password" placeholder="Digite seu código de confirmação" v-model="inputValidacao"
                   class="form-control col-sm-12 col-md-4 col-lg-12"/>
          </div>
        </div>
        <b-btn class="mt-3" variant="outline-info" block @click="hideModal">Validar</b-btn>
      </b-modal>
    </div>
  </div>
</template>

<script>
  import googleMixins from '../mixins/googleServiceMixins'
  import mixinsFacebook from '../mixins/facebookServiceMixins'
  import inputComponent from '../components/inputTextComponent'
  import inputNumberComponent from '../components/InputNumberComponent'
  import inputSelectComponent from '../components/InputSelectComponent'
  import {tiposDeUsuario as usuarios} from "../models/Enums";
  import {enviarEmail, criarConta} from '../services/requestServices'
  import Swal from '../util/Swal'
  import Utils from '../util/Utils'
  import Loader from '../templates/Loader'

  export default {
    name: 'cadastro',
    components: {
      Loader,
      inputComponent,
      inputNumberComponent,
      inputSelectComponent
    },
    data() {
      return {
        showModal: false,
        novoUsuario: {
          nome: '',
          numero: '',
          email: '',
          senha: '',
          tipoUsuario: null
        },
        confirmaSenha: '',
        tiposDeUsuario: usuarios,
        responseEmail: {
          campo: '',
          mensagem: ''
        },
        inputValidacao: ''
      }
    },
    mixins: [
      googleMixins,
      mixinsFacebook
    ],
    methods: {
      atribuirResultado(result) {
        if (result.id === 'nome') {
          this.novoUsuario.nome = result.message;
        } else if (result.id === 'email') {
          this.novoUsuario.email = result.message;
        } else if (result.id === 'numero') {
          this.novoUsuario.numero = result.message;
        } else if (result.id === 'senha') {
          this.novoUsuario.senha = result.message;
        } else if (result.id === 'confirmaSenha') {
          this.confirmaSenha = result.message;
        } else if(result.id === 'tipoUsuario') {
          this.novoUsuario.tipoUsuario = result.message;
        }
      },
      // DIRECIONA O USUARIO PARA A TELA DE LOGIN
      irLogin() {
        this.$router.push({name: 'login'})
      },
      // FAZ A VALIDAÇÃO DOS CAMPOS
      validarCampos() {
        let validacao = true
        if (this.novoUsuario.nome === null || this.novoUsuario.nome.length < 4) {
          Utils.alertInput('nome')
          validacao = false
        } else {
          Utils.alertInputValid('nome')
        }
        if (Utils.validateEmail(this.novoUsuario.email) === false) {
          Utils.alertInput('email')
          validacao = false
        } else {
          Utils.alertInputValid('email')
        }
        if (this.novoUsuario.senha === null || this.novoUsuario.senha.length < 8) {
          Utils.alertInput('senha')
          validacao = false
        } else {
          Utils.alertInputValid('senha')
        }
        if (this.novoUsuario.tipoUsuario === null) {
          Utils.alertInput('tipoUsuario')
          validacao = false
        } else {
          Utils.alertInputValid('tipoUsuario')
        }
        if (this.novoUsuario.senha.length < 8 || this.novoUsuario.senha !== this.confirmaSenha) {
          Utils.alertInput('senha')
          Utils.alertInput('confirmaSenha')
          validacao = false
        } else {
          Utils.alertInputValid('senha')
          Utils.alertInputValid('confirmaSenha')
        }
        return validacao
      },
      // ENVIA UM EMAIL DE VALIDAÇÃO PARA O EMAIL DA PESSOA
      validarEmail() {
        if (this.validarCampos()) {
          this.showModal = true;
          enviarEmail(this.novoUsuario.nome, this.novoUsuario.email).then((response) => {
            this.responseEmail = response.data;
            this.mostrarModal()
          }).catch((err) => {
            console.log(err)
          })
        }
      },
      // APARECE A MODAL NA TELA
      mostrarModal() {
        this.showModal = false;
        this.$refs.myModalRef.show()
      },
      // ESCONDE A MODAL DA TELA
      hideModal() {
        if (this.inputValidacao === this.responseEmail.mensagem) {
          if (this.novoUsuario.numero.length > 0) {
            this.novoUsuario.numero = Utils.formatarNumero(this.novoUsuario.numero)
          }
          this.showModal = true;
          criarConta(this.novoUsuario).then((response) => {
            this.showModal = false;
            if (response.data) {
              this.novoUsuario = response.data;
              this.$refs.myModalRef.hide();
              this.$store.commit('alterarSessao', this.novoUsuario);
              this.$router.push({name: 'home'})
            }
          }).catch((err) => {
            debugger;
            this.showModal = false;
            Swal.alertUmButton('Atenção', err.response.data[0].mensagem, 'error');
            console.log(err.response)
          })
        }
      }
    },
  }
</script>

<style scoped>
  .center {
    width: 50%;
    padding: 10px;
    margin: auto;
  }

  input, b-form-select {
    height: 30px;
  }

  #tipoUsuario {
    height: 30px;
    padding-bottom: 5px;
  }
</style>
