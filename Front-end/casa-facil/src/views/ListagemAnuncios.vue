<template>
  <div style="padding: 15px; margin-top: 45px;">
    <loader-modal :show-modal="showModal"></loader-modal>
    <b-card title="Anuncios cadastrados">
      <b-card v-for="anuncio in anuncios" style="margin: 15px; padding-left: 0px; cursor: pointer; position: relative">
        <div class="row">
          <div class="col-sm-12 col-md-4 col-lg-5" @click="detalhesAnuncio(anuncio.id)" v-if="anuncio.imagensAnuncios.length > 0">
            <b-img alt="Thumbnail" :src="anuncio.imagensAnuncios[0].imagemUrl" style="width: 300px; height: 250px;"/>
          </div>
          <div class="col-sm-12 col-md-4 col-lg-6" @click="detalhesAnuncio(anuncio.id)">
            <h3>{{anuncio.titulo}}</h3>
            <p class="col-sm-12 col-md-4 col-lg-12" id="descricao">{{anuncio.descricao}}</p>
            <p class="text-right text-bottom" id="valor">Valor: {{anuncio.valor}}</p>
          </div>
          <div class="col-sm-12 col-md-4 col-lg-1">
            <b-button variant="danger" id="excluir" @click="alertaAnuncio(anuncio.id)">Excluir</b-button>
            <b-button variant="warning" id="editar" @click="editarAnuncio(anuncio.id)">Editar</b-button>
          </div>
        </div>
      </b-card>
    </b-card>
  </div>
</template>

<script>
import {buscarAnunciosUsuario, excluirAnuncio} from "../services/requestServices";
import loaderModal from '../templates/Loader'
import Swal from '../util/Swal'

export default {
  name: 'ListagemAnuncios',
  components: {
    loaderModal
  },
  data () {
    return {
      anuncios: null,
      showModal: false
    }
  },
  methods: {
    listarAnuncios () {
      this.showModal = true
      buscarAnunciosUsuario(this.$store.state.sessao.id).then((response) => {
        if (response) {
          console.log('anuncios ->', response.data)
          this.anuncios = response.data
          this.showModal = false
        }
      }).catch((err) => {
        console.log(err.response)
        this.showModal = false
      })
    },
    alertaAnuncio (id) {
      Swal.alertDoisButtons('Atenção!', 'Deseja mesmo deletar este anuncio?', 'warning')
        .then((value) => {
          switch (value) {
            case 'sim':
              this.showModal = true
              this.deletarAnuncio(id)
              break;
            case 'nao':
              break;
          }
        })
    },
    deletarAnuncio (id) {
      excluirAnuncio(id).then((response) => {
        console.log('response delete ->', response)
        this.listarAnuncios()
      }).catch((err) => {
        this.showModal = false
        console.log(err.response)
      })
    },
    detalhesAnuncio (id) {
      console.log('id ->', id)
      this.$router.push({name: 'detalheImovel', params: {id}})
    },
    editarAnuncio (id) {
      this.$router.push({name: 'editarAnuncio', params: {id}})
    }
  },
  mounted () {
    this.listarAnuncios()
  }
}
</script>

<style scoped>
  #valor {
    font-weight: bold;
    position: absolute;
    bottom: 0;
    right: 0;
    margin-right: 10px;
  }

  #descricao {
    text-overflow: ellipsis;
    overflow: hidden;
    max-height: 120px;
    width: 100%;
  }

  #excluir {
    position: absolute;
    top: 50%;
    margin-top: -25px;
    right: 0;
  }

  #editar {
    position: absolute;
    top: 50%;
    margin-top: -70px;
    right: 0;
  }
</style>
