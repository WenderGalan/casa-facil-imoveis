<template>
  <div style="padding: 15px; margin-top: 45px" class="container">
    <loader-perfil :show-modal="showModal"></loader-perfil>
    <div class="row">
      <div class="col-sm-12 col-md-4 col-lg-4">
        <div class="row">
          <div class="col-sm-12 col-md-4 col-lg-12">
            <b-img rounded="circle" :src="perfilUsuario.urlImagem" width="200" height="200" blank-color="#777"
                   alt="img" class="m-1"></b-img>
            <b-form-file v-if="verificarUrl" v-model="novaFoto" id="file" placeholder="Escolha uma foto"></b-form-file>
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
              <b-btn :disabled="disabled" variant="danger" style="margin-left: 15px" @click="excluirUsuario">Excluir usuário</b-btn>
            </div>
          </div>
        </b-card>
      </div>
    </div>
  </div>
</template>

<script>
import {alterarUsuario, salvarImagemUsuario, deletarUsuario} from '../services/requestServices'
import Utils from '../util/Utils'
import constantes from '../util/constantes'
import loaderPerfil from '../templates/Loader'
import Swal from '../util/Swal'

export default {
  name: 'perfil',
  components: {
    loaderPerfil
  },
  data () {
    return {
      disabled: true,
      perfilUsuario: {
        email: '',
        id: '',
        nome: '',
        numero: '',
        tipoUsuario: '',
        senha: '',
        urlImagem: ''
      },
      permEditar: false,
      novaFoto: null,
      showModal: false
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
    },
    excluirUsuario () {
      Swal.alertDoisButtons('Atenção!', 'Deseja mesmo deletar sua conta?', 'warning')
        .then((value) => {
          switch (value) {
            case 'sim':
              this.showModal = true
              this.deleteUser()
              break;
            case 'nao':
              break;
          }
        })
    },
    deleteUser () {
      deletarUsuario(this.perfilUsuario.id).then((response) => {
        this.showModal = false
        Swal.alertUmButton('Conta excluida com sucesso!', '', 'success')
          .then((value) => {
            switch (value) {
              case 'ok':
                this.$store.commit('alterarSessao', undefined)
                this.$router.push({name: 'home'})
                break;
            }
          })
      }).catch((err) => {
        this.showModal = false
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
    },
    novaFoto (foto) {
      const formData = new FormData()
      formData.append('file', foto)
      salvarImagemUsuario(this.perfilUsuario.id, formData).then((response) => {
        if (response.data) {
          this.perfilUsuario.urlImagem = response.data
        }
      }).catch((err) => {
        console.log(err.response)
      })
    }
  },
  computed: {
    verificarUrl () {
      return constantes.URL_IMG_DEFAULT === this.perfilUsuario.urlImagem
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
