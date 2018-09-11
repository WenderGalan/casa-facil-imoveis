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

          <div style="margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Digite seu nome e sobrenome:*</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <input type="text" id="nome" placeholder="Nome" v-model="novoUsuario.nome"
                   class="form-control col-sm-12 col-md-4 col-lg-12"/>
          </div>

          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Digite seu número de telefone:</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <input type="text" placeholder="Número (opcional)" v-model="novoUsuario.numero" v-mask="'(##) #####-####'"
                   class="form-control col-sm-12 col-md-4 col-lg-12"/>
          </div>

          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Digite seu Email:*</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <input type="text" id="email" placeholder="exemplo@dominio.com" v-model="novoUsuario.email"
                   class="form-control col-sm-12 col-md-4 col-lg-12"/>
          </div>

          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Que tipo de usuário você é?</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <b-form-select id="tipoUsuario" v-model="novoUsuario.tipoUsuario" :options="tiposDeUsuario"
                           class="mb-3"></b-form-select>
          </div>

          <div style="margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Digite sua senha:*</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <input type="password" id="senha" placeholder="Senha (mínimo 8 caracteres)" v-model="novoUsuario.senha"
                   class="form-control col-sm-12 col-md-4 col-lg-12"/>
          </div>

          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Confirme sua senha:*</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <input type="password" id="confirmaSenha" placeholder="Digite sua senha novamente" v-model="confirmaSenha"
                   class="form-control col-sm-12 col-md-4 col-lg-12"/>
          </div>

          <div class="container" style="margin-top: 25px">
            <b-button class="form-control col-sm-12 col-md-4 col-lg-12" @click="validarEmail()" variant="info">Cadastrar</b-button>
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
import {enviarEmail, criarConta} from '../services/requestServices'
import Utils from '../util/Utils'
import Loader from '../templates/Loader'
export default {
  name: 'cadastro',
  components: {Loader},
  comments: {
    Loader
  },
  data () {
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
      tiposDeUsuario: [
        {
          value: 0,
          text: 'Sou pessoa física'
        },
        {
          value: 1,
          text: 'Sou corretor'
        },
        {
          value: 2,
          text: 'Sou uma empresa'
        }
      ],
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
    // DIRECIONA O USUARIO PARA A TELA DE LOGIN
    irLogin () {
      this.$router.push({name: 'login'})
    },
    // FAZ A VALIDAÇÃO DOS CAMPOS
    validarCampos () {
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
    validarEmail () {
      if (this.validarCampos()) {
        this.showModal = true
        enviarEmail(this.novoUsuario.nome, this.novoUsuario.email).then((response) => {
          this.responseEmail = response.data
          this.showModal()
        }).catch((err) => {
          console.log(err)
        })
      }
    },
    // APARECE A MODAL NA TELA
    showModal () {
      this.showModal = false
      this.$refs.myModalRef.show()
    },
    // ESCONDE A MODAL DA TELA
    hideModal () {
      if (this.inputValidacao === this.responseEmail.mensagem) {
        if (this.novoUsuario.numero.length > 0) {
          this.novoUsuario.numero = Utils.formatarNumero(this.novoUsuario.numero)
        }
        thia.showModal = true
        criarConta(this.novoUsuario).then((response) => {
          this.showModal = false
          if (response.data) {
            this.novoUsuario = response.data
            this.$refs.myModalRef.hide()
            this.$store.commit('alterarSessao', this.novoUsuario)
            this.$router.push({name: 'home'})
          }
        }).catch((err) => {
          console.log(err.response)
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

  input, b-form-select {
    height: 30px;
  }

  #tipoUsuario {
    height: 30px;
    padding-bottom: 5px;
  }
</style>
