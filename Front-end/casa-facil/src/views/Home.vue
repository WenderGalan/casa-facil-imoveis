<template>
  <div class="home" style="margin-top: 45px">
    <div style="padding: 15px;">
      <div class="row" >
        <div class="col-sm-12 col-md-4 col-lg-4">
          <loader :show-modal="showModal"></loader>
          <b-card
              class="col-sm-12 col-md-4 col-lg-4"
              title="" style="position: fixed">
            <div class="row">
              <div class="col-sm-12 col-md-4 col-lg-12">
                <p class="text-left">Digite a cidade, bairro ou rua</p>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <input type="text" placeholder="Ex: Campo Grande"
                       class="form-control col-sm-12 col-md-4 col-lg-12"/>
              </div>

              <div style="margin-top: 15px" class="col-sm-12 col-md-4 col-lg-6">
                <p class="text-left">Preço mínimo</p>
              </div>

              <div style="margin-top: 15px" class="col-sm-12 col-md-4 col-lg-6">
                <p class="text-left">Preço máximo</p>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-6">
                <input v-mask="'###.###.###,##'" type="text" placeholder="R$" class="form-control col-sm-12 col-md-4 col-lg-12"/>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-6">
                <input v-mask="'###.###.###,##'" type="text" placeholder="R$" class="form-control col-sm-12 col-md-4 col-lg-12"/>
              </div>
            </div>
          </b-card>
        </div>
      <br>

        <div class="col-sm-12 col-md-4 col-lg-8">
          <b-card
              style="margin-left: 10px"
              title="">
            <b-card v-for="anuncio in anuncios" @click="detalhesAnuncio(anuncio.id)" style="margin: 15px; padding-left: 0px; cursor: pointer">
              <div class="row">
                <div class="col-sm-12 col-md-4 col-lg-5" v-if="anuncio.imagensAnuncios.length > 0">
                  <b-img alt="Thumbnail" :src="anuncio.imagensAnuncios[0].imagemUrl" style="width: 300px; height: 250px;"/>
                </div>
                <div class="col-sm-12 col-md-4 col-lg-7">
                  <h3>{{anuncio.titulo}}</h3>
                  <p class="col-sm-12 col-md-4 col-lg-12" id="descricao" style="width: 100%">{{anuncio.descricao}}</p>
                  <p id="valor">Valor: {{anuncio.valor}}</p>
                </div>
              </div>
            </b-card>
          </b-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {buscarTodosAnuncios} from '../services/requestServices'
import loader from '../templates/Loader'
export default {
  name: 'home',
  components: {loader},
  props: {
    loader
  },
  data () {
    return {
      anuncios: [],
      showModal: false
    }
  },
  methods: {
    buscarAnuncios () {
      this.showModal = true
      buscarTodosAnuncios().then((response) => {
        this.showModal = false
        if (response.data) {
          this.anuncios = response.data
          console.log(response.data)
        }
      }).catch((err) => {
        this.showModal = false
        console.log(err.response)
      })
    },
    detalhesAnuncio (id) {
      console.log('id ->', id)
      this.$router.push({name: 'detalheImovel', params: {id}})
    }
  },
  mounted () {
    this.buscarAnuncios()
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
