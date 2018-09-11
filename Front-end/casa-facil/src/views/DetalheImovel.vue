<template>
  <div style="margin-top: 45px; padding: 15px">
    <div class="col-sm-12 col-md-4 col-lg-12">
      <div class="row">
        <div class="col-sm-12 col-md-4 col-lg-8">
          <loader-modal :show-modal="showModal"></loader-modal>
          <b-card :title="anuncio.titulo">
            <div class="row">
              <div class="col-sm-12 col-md-4 col-lg-10">
                <div style="display:flex;justify-content:center;align-items:center;width:100%;">
                  <b-carousel id="carousel1"
                              style="text-shadow: 1px 1px 2px #333; max-width: 70%"
                              controls
                              indicators
                              background="#ababab"
                              :interval="10000"
                              img-width="500"
                              img-height="200"
                              class="center">
                    <!-- Text slides with image -->
                    <b-carousel-slide v-for="img in anuncio.imagensAnuncios" :img-src="img.imagemUrl">
                    </b-carousel-slide>
                  </b-carousel>
                </div>
              </div>
              <div class="col-sm-12 col-md-4 col-lg-2">
                <div class="col-sm-12 col-md-4 col-lg-12">
                  <h5 style="text-align: initial; margin-left: -14px">Valor de venda:</h5>
                </div>

                <div class="row col-sm-12 col-md-4 col-lg-12">
                  <p>R$: </p><h6 style="text-align: initial; margin-top: 2px">{{anuncio.valor}}</h6>
                </div>

                <div class="row col-sm-12 col-md-4 col-lg-12">
                  <h5 style="text-align: initial;">Tipo de imóvel:</h5>
                </div>

                <div class="row col-sm-12 col-md-4 col-lg-12">
                  <h6 style="text-align: initial; margin-top: 2px">{{anuncio.tipoImovel}}</h6>
                </div>
              </div>
              <div class="col-sm-12 col-md-4 col-lg-10">
                <div class="col-sm-12 col-md-4 col-lg-12">
                  <h4 style="text-align: initial; margin-top: 20px">Descrição:</h4>
                  <p style="text-align: initial">{{anuncio.descricao}}</p>
                  <hr>
                </div>

                <div class="col-sm-12 col-md-4 col-lg-12">
                  <h4 style="text-align: initial; margin-top: 20px">Endereço:</h4>

                  <div class="row">
                    <div class="col-sm-12 col-md-4 col-lg-6">
                      <p style="text-align: initial" v-if="anuncio.endereco.endereco">Endereço: {{anuncio.endereco.endereco}}, {{anuncio.endereco.numero}}</p>
                      <p style="text-align: initial" v-if="anuncio.endereco.bairro">Bairro: {{anuncio.endereco.bairro}}</p>
                      <p style="text-align: initial" v-if="anuncio.endereco.bairro">Complemento: {{anuncio.endereco.complemento}}</p>
                    </div>

                    <div class="col-sm-12 col-md-4 col-lg-6">
                      <p style="text-align: initial" v-if="anuncio.endereco.bairro">Cep: {{anuncio.endereco.cep}}</p>
                      <p style="text-align: initial" v-if="anuncio.endereco.bairro">Cidade: {{anuncio.endereco.cidade}}</p>
                      <p style="text-align: initial" v-if="anuncio.endereco.bairro">Estado: {{anuncio.endereco.estado}}</p>
                    </div>
                  </div>

                </div>
              </div>
            </div>
          </b-card>
        </div>
        <div class="col-sm-12 col-md-4 col-lg-4">
          <b-card>
            <h5>Contatar anunciante</h5>
            <div class="row">

              <div class="col-sm-12 col-md-4 col-lg-12">
                <h5>{{anuncio.anunciante.numero}}</h5>
                <hr>
              </div>

              <br/>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <h5>Ou envie um e-mail</h5>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <textarea class="form-control" rows="6" v-model="email.mensagem"></textarea>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12" style="margin-top: 15px">
                <input type="text" class="form-control" v-model="email.nome" placeholder="Nome"/>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12" style="margin-top: 15px">
                <input type="email" class="form-control" v-model="email.email" placeholder="Email"/>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12" style="margin-top: 15px">
                <b-button variant="info" @click="enviarEmail">CONTATAR ANUNCIANTE</b-button>
              </div>
            </div>
          </b-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {buscarAnuncio, enviarEmailAnuncio} from "../services/requestServices";
import loaderModal from '../templates/Loader'

export default {
  name: 'DetalheImovel',
  data () {
    return {
      showModal: true,
      anuncio: {
        anunciante: {
          numero: 0
        },
        endereco: {
          endereco: '',
          numero: 0,

        }
      },
      email: {
        nome: '',
        email: '',
        mensagem: ''
      }
    }
  },
  components: {
    loaderModal
  },
  methods: {
    procurarAnuncio () {
      this.showModal = true
      const id = this.$router.history.current.params.id
      buscarAnuncio(id).then((response) => {
        this.showModal = false
        if (response.data) {
          this.anuncio = response.data
          this.ajustarPlaceHolder()
        }
      }).catch((err) => {
        this.showModal = false
        console.log(err.response)
      })
    },
    ajustarPlaceHolder () {
      this.email.mensagem = `Olá ${this.anuncio.anunciante.nome}, tenho interesse neste imóvel: ${this.anuncio.titulo} - ${this.anuncio.endereco.endereco} - ${this.anuncio.endereco.cidade} - ${this.anuncio.endereco.estado}.\n \n Aguardo o contato. Obrigado.`
    },
    enviarEmail () {
      enviarEmailAnuncio(this.email, this.anuncio.id).then((response) => {
        console.log('enviado com sucesso')
      }).catch((err) => {
        console.log(err)
      })
    }
  },
  mounted () {
    this.procurarAnuncio()
  },
  // beforeRouteEnter (to, from, next) {
  //   next(vm => vm.procurarAnuncio())
  // }

}
</script>

<style scoped>

</style>