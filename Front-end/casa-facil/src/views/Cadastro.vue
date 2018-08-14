<template>
  <div class="container">
    <div class="col-sm-12 col-md-4 col-lg-5  center">
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
              <b>Entre com uma conta google</b>
            </g-signin-button>
          </div>

          <div style="margin-bottom: -12px; margin-top: 15px" class="col-sm-12 col-md-4 col-lg-12">
            <p>ou</p>
          </div>

          <div style="margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Digite seu nome e sobrenome:*</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <input type="text" placeholder="Nome" v-model="novoUsuario.nome"
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
            <input type="text" placeholder="exemplo@dominio.com" v-model="novoUsuario.email"
                   class="form-control col-sm-12 col-md-4 col-lg-12"/>
          </div>

          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Que tipo de usuário você é?</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <b-form-select v-model="novoUsuario.tipoUsuario" :options="tiposDeUsuario" class="mb-3" ></b-form-select>
          </div>

          <div style="margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Digite sua senha:*</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <input type="password" placeholder="Senha (mínimo 8 caracteres)" v-model="novoUsuario.senha"
                   class="form-control col-sm-12 col-md-4 col-lg-12"/>
          </div>

          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Confirme sua senha:*</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <input type="password" placeholder="Digite sua senha novamente" v-model="confirmaSenha"
                   class="form-control col-sm-12 col-md-4 col-lg-12"/>
          </div>

          <div class="container" style="margin-top: 25px">
            <b-button class="form-control col-sm-12 col-md-4 col-lg-12" variant="info">Cadastrar</b-button>
          </div>

          <div class="container">
            <div style="width: 80%; margin: auto" class="col-sm-12 col-md-4 col-lg-12">
              <p>Ao se cadastrar no Casa Fácil você concorda com os
                <a href="https://github.com/WenderGalan/casa-facil-imoveis/blob/master/LICENSE" target="_blank">termos de uso</a>.</p>
            </div>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <p>Possui uma conta? <a href="#" @click="irLogin">Entre aqui</a> </p>
          </div>
        </div>
      </b-card>
    </div>
  </div>
</template>

<script>
import googleMixins from '../mixins/googleServiceMixins'
import mixinsFacebook from '../mixins/facebookServiceMixins'
export default {
  name: 'cadastro',
  data () {
    return {
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
      ]
    }
  },
  mixins: [
    googleMixins,
    mixinsFacebook
  ],
  methods: {
    irLogin () {
      this.$router.push({name: 'login'})
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
</style>
