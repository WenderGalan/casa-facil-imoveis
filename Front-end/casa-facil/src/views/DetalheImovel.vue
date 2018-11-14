<template>
  <div style="margin-top: 45px; padding: 15px">
    <div class="col-sm-12 col-md-4 col-lg-12">
      <div class="row">
        <div class="col-sm-12 col-md-4 col-lg-8">
          <loader-modal :show-modal="showModal"></loader-modal>
          <b-card :title="anuncio.titulo">
            <i v-if="verificarSessao" :class="[alterarIcon, 'unfav']" aria-hidden="true" @click="verificarFavorito"></i>
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
                <textarea class="form-control" rows="6" v-model="email.mensagem" :disabled="disabled"></textarea>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12" style="margin-top: 15px">
                <input type="text" class="form-control" v-model="email.nome" placeholder="Nome" :disabled="disabled"/>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12" style="margin-top: 15px">
                <input type="email" class="form-control" v-model="email.email" placeholder="Email" :disabled="disabled"/>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12" style="margin-top: 15px">
                <b-button variant="info" @click="enviarEmail" :disabled="disabled">{{msgButton}}</b-button>
              </div>
            </div>
          </b-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {buscarAnuncio, enviarEmailAnuncio, addFav, removeFav, trazerFavoritos} from "../services/requestServices";
import loaderModal from '../templates/Loader'
import Swal from '../util/Swal'

export default {
  name: 'DetalheImovel',
  data () {
    return {
      id: '',
      idFav: '',
      showModal: true,
      disabled: false,
      msgButton: 'CONTATAR ANUNCIANTE',
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
      },
      isFavorito: false
    }
  },
  components: {
    loaderModal
  },
  methods: {
    verificarFavorito() {
      debugger;
      if (this.isFavorito) {
        this.isFavorito = false;
        removeFav(this.idFav).then(response => {
          console.log(response)
        }).catch(err => {
          console.log(err)
        })
      } else {
        this.adicionarFavorito()
      }
    },
    adicionarFavorito() {
      debugger;
      let idCliente = this.$store.state.sessao.id;
      addFav(idCliente, this.id).then(response => {
        debugger;
        console.log(response);
        this.isFavorito = true;
      }).catch(err => {
        debugger;
        console.log(err)
      })
    },
    procurarAnuncio () {
      this.showModal = true;
      buscarAnuncio(this.id).then((response) => {
        this.showModal = false;
        if (response.data) {
          this.anuncio = response.data;
          this.ajustarPlaceHolder()
        }
      }).catch((err) => {
        this.showModal = false;
        console.log(err.response)
      })
    },
    ajustarPlaceHolder () {
      this.email.mensagem = `Olá ${this.anuncio.anunciante.nome}, tenho interesse neste imóvel: ${this.anuncio.titulo} - ${this.anuncio.endereco.endereco} - ${this.anuncio.endereco.cidade} - ${this.anuncio.endereco.estado}.\n \n Aguardo o contato. Obrigado.`
    },
    enviarEmail () {
      this.showModal = true;
      enviarEmailAnuncio(this.email, this.anuncio.id).then((response) => {
        this.showModal = false;
        Swal.alertUmButton('Anunciante contatado com sucesso!', '', 'success');
        this.msgButton = 'ANUNCIANTE CONTATADO';
        this.disabled = true;
        console.log('enviado com sucesso')
      }).catch((err) => {
        this.showModal = false;
        console.log(err)
      })
    },
    verificarAnuncio () {
      if (this.$store.state.sessao) {
        let idUser = this.$store.state.sessao.id;
        trazerFavoritos(idUser).then(response => {
          for (let anuncio of response.data) {
            if (anuncio.anuncio.id === this.id) {
              this.isFavorito = true;
              this.idFav = anuncio.id;
            }
          }
        })
      }
    }
  },
  mounted () {
    this.id = this.$router.history.current.params.id;
    this.procurarAnuncio();
    this.verificarAnuncio();
  },
  computed: {
    verificarSessao() {
      return this.$store.state.sessao !== undefined && this.$store.state.sessao.cpf
    },
    alterarIcon() {
      if (this.isFavorito) {
        return 'fa fa-star fa-lg'
      }
      return 'fa fa-star-o fa-lg'
    }
  }

}
</script>

<style scoped>
  .unfav {
    position: absolute;
    right: 60px;
    top: 30px;
    color: yellow;
    cursor: pointer;
  }

</style>