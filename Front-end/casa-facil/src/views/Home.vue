<template>
  <div class="home" style="margin-top: 45px">
    <div style="padding: 15px;">
      <div class="row">
        <div class="col-sm-12 col-md-4 col-lg-12">
          <b-card
              style="margin-left: 10px"
              title="">

            <div class="col-sm-12 col-md-4 col-lg-12">
              <p class="text-left">Pesquisar</p>
            </div>

            <div class="row">
              <div class="col-sm-12 col-md-4 col-lg-5">
                <vue-bootstrap-typeahead
                    style="margin-left: 15px"
                    v-model="buscar"
                    :data="resultadoPesquisa"/>
              </div>
              <b-button variant="info"><i class="fa fa-search" aria-hidden="true"></i></b-button>
            </div>
            <b-card v-for="anuncio in anuncios" @click="detalhesAnuncio(anuncio.titulo, anuncio.id)"
                    style="margin: 15px; padding-left: 0px; cursor: pointer">
              <div class="row">
                <div class="col-sm-12 col-md-4 col-lg-5" v-if="anuncio.imagensAnuncios.length > 0">
                  <b-img alt="Thumbnail" :src="anuncio.imagensAnuncios[0].imagemUrl"
                         style="width: 300px; height: 250px;"/>
                </div>
                <div class="col-sm-12 col-md-4 col-lg-7">
                  <h3>{{anuncio.titulo}}</h3>
                  <p class="col-sm-12 col-md-4 col-lg-12" id="descricao" style="width: 100%">{{anuncio.descricao}}</p>
                  <p id="valor">Valor: {{anuncio.valor}}</p>
                </div>
              </div>
            </b-card>
            <b-button variant="info" @click="buscarAnuncios" :disabled="disabledButton">Carregar mais anúncios</b-button>
          </b-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import VueBootstrapTypeahead from 'vue-bootstrap-typeahead'
import {buscarTodosAnuncios, buscarAnuncios} from '../services/requestServices'
import loader from '../templates/Loader'
import Swal from '../util/Swal'
export default {
  name: 'home',
  components: {
    loader,
    VueBootstrapTypeahead
  },
  props: {
    loader
  },
  data () {
    return {
      anuncios: [],
      showModal: false,
      requestAnuncio: true,
      buscar: '',
      resultadoPesquisa :[
        'Campo Grande',
        'São Paulo',
        'Rio de Janeiro',
        'Salvador',
        'Pernambuco',
        'Uberlandia',
        'Curitiba'
      ],
      disabledButton: false,
      page: 0
    }
  },
  methods: {
    buscarAnuncios () {
      if (this.requestAnuncio)  {
        let id = ''
        this.showModal = true
        buscarTodosAnuncios(id, this.page).then((response) => {
          this.showModal = false
          if (response.data.length > 0) {
            this.page++
            for (let i = 0; i < response.data.length; i++) {
              this.anuncios.push(response.data[i])
            }
          } else {
            this.disabledButton = true
            this.requestAnuncio = false
            Swal.alertUmButton('Não existem mais anúncios', '', 'info')
          }
        }).catch((err) => {
          Swal.alertUmButton('Atenção', 'Ocorreu um erro inesperado, favor atualize a página', 'error')
          this.showModal = false
          console.log(err.response)
        })
      }
    },
    detalhesAnuncio (title, id) {
      this.$router.push({name: 'detalheImovel', params: {title, id}})
    },
    procurarAnuncio () {
      this.showModal = true
      buscarAnuncios(this.buscar.rua, this.buscar.bairro, this.buscar.cidade).then((response) => {
        this.showModal = false
        this.anuncios = response.data
      }).catch((err) => {
        this.showModal = false
        Swal.alertUmButton('Atenção', 'Ocorreu um erro inesperado, favor atualize a página', 'error')
        console.log(err.response)
      })
    }
  },
  mounted () {
    this.buscarAnuncios()
  },
  watch: {
    anuncios(anuncios) {
      if (anuncios === undefined || anuncios === null) {
        console.log("ficou undefined")
      }
    }
  }
}
</script>

<style scoped>
  #descricao {
    width: 100%;
    text-overflow: ellipsis;
    overflow: hidden;
    max-height: 140px;
  }

  #valor {
    font-weight: bold;
    position: absolute;
    bottom: 0;
    right: 0;
    margin-right: 10px;
  }
</style>
