<template>
  <div style="padding: 15px" class="container">
    <div class="row">
      <div class="col-sm-12 col-md-4 col-lg-4">
        <div class="row">
          <div class="col-sm-12 col-md-4 col-lg-12">
            <b-img rounded src="https://picsum.photos/1024/400/?image=41" width="130" height="130" blank-color="#777"
                   alt="img" class="m-1"></b-img>
            <input type="file" class="upload"/>
          </div>
        </div>
      </div>
      <div class="col-sm-12 col-md-4 col-lg-8">
        <b-card title="Dados do usuário">
          <div class="row">
            <div class="col-sm-12 col-md-4 col-lg-12">
              <p class="text-left">Nome:</p>
            </div>

            <div class="col-sm-12 col-md-4 col-lg-12">
              <input type="text" class="form-control" v-model="perfilUsuario.nome" :disabled="disabled"/>
            </div>

            <div class="col-sm-12 col-md-4 col-lg-12">
              <p class="text-left">Email:</p>
            </div>

            <div class="col-sm-12 col-md-4 col-lg-12">
              <input type="text" class="form-control" v-model="perfilUsuario.email" disabled/>
            </div>

            <div class="col-sm-12 col-md-4 col-lg-12">
              <p class="text-left">Número:</p>
            </div>

            <div class="col-sm-12 col-md-4 col-lg-12" v-mask="'(##) #####-####'">
              <input type="text" class="form-control" v-model="perfilUsuario.numero" :disabled="disabled"/>
            </div>

            <div class="col-sm-12 col-md-4 col-lg-12">
              <p class="text-left">Tipo de pessoa:</p>
            </div>

            <div class="col-sm-12 col-md-4 col-lg-12">
              <input type="text" class="form-control" v-model="perfilUsuario.tipoUsuario" disabled/>
            </div>

            <div class="col-sm-12 col-md-4 col-lg-12">
              <div class="row" style="padding: 16px">
                <b-form-checkbox id="checkbox1"
                                 v-model="permEditar">
                  Editar informações
                </b-form-checkbox>
              </div>
            </div>

            <div class="col-sm-12 col-md-4 col-lg-12">
              <b-btn :disabled="disabled" variant="success" @click="salvarAlteracoes">Salvar</b-btn>
            </div>
          </div>
        </b-card>
      </div>
    </div>
  </div>
</template>

<script>
import {alterarUsuario} from '../services/requestServices'
import Utils from '../util/Utils'
export default {
  name: 'perfil',
  data () {
    return {
      disabled: true,
      perfilUsuario: {
        email: '',
        id: '',
        nome: '',
        numero: '',
        tipoUsuario: '',
        senha: ''
      },
      permEditar: false
    }
  },
  methods: {
    atribuirInformações () {
      const user = this.$store.state.sessao
      this.perfilUsuario = user
    },
    salvarAlteracoes () {
      this.perfilUsuario.numero = Utils.formatarNumero(this.perfilUsuario.numero)
      console.log(this.perfilUsuario.numero)
      alterarUsuario(this.perfilUsuario).then((response) => {
        if (response.data) {
          this.perfilUsuario = response.data
          this.$store.commit('alterarSessao', this.perfilUsuario)
          alert('Seu perfil foi alterado com sucesso!')
        }
      }).catch((err) => {
        console.log(err.response)
      })
    }
  },
  mounted () {
    this.atribuirInformações()
  },
  watch: {
    permEditar (val) {
      this.disabled = !val
    }
  }
}
</script>

<style scoped>
  p {
    margin-top: 15px;
    margin-bottom: 0;
  }
</style>
