<template>
  <div class="home" style="margin-top: 45px">
    <div style="padding: 15px;">
      <div class="row">
          <loader :show-modal="showModal"/>
        <b-card
            style="margin-left: 10px"
            title="">

          <div class="row">
            <div class="col-sm-11 col-md-11 col-lg-11">
              <auto-complete
                  @buscarValores="receberValor"
                  @alterarValor="alterarParametro"
                  @recebeValor="receberValorAtual"
                  :items="resultadoPesquisa"
                  :novo-valor="buscar"/>
            </div>

            <div class="col-sm-1 col-md-1 col-lg-1">
              <b-button variant="info" @click="procurarAnuncio"><i class="fa fa-search" aria-hidden="true"></i></b-button>
            </div>
          </div>
          <AnuncioComponent v-for="anuncio in anuncios" :anuncio="anuncio"/>
          <b-button variant="info" @click="definirChamadaBusca" :disabled="disabledButton">Carregar mais anúncios
          </b-button>
        </b-card>
      </div>
    </div>
  </div>
</template>

<script>
  import VueBootstrapTypeahead from 'vue-bootstrap-typeahead'
  import {autoComplete, buscarAnuncios} from '../services/requestServices'
  import AnuncioComponent from '../components/AnuncioComponent'
  import loader from '../templates/Loader'
  import Swal from '../util/Swal'
  import AutoComplete from '../components/AutoComplete'

  export default {
    name: 'home',
    components: {
      loader,
      VueBootstrapTypeahead,
      AutoComplete,
      AnuncioComponent
    },
    props: {
      loader
    },
    data() {
      return {
        anuncios: [],
        showModal: false,
        requestAnuncio: true,
        buscar: '',
        resultadoPesquisa: [],
        disabledButton: false,
        pageBuscaTotal: 0,
        pageBusca: 0,
        resultadoAgrupado: [],
        valorAntigo: ''
      }
    },
    methods: {
      receberValorAtual(value) {
        this.buscar = value;
      },
      alterarParametro(param) {
        for (let i = 0,  max = this.resultadoPesquisa.length; i < max; i++) {
          if (param === this.resultadoAgrupado[i].concatenacao)  {
            this.buscar = this.resultadoAgrupado[i].pesquisa;
            break;
          }
        }
      },
      receberValor(value) {
        autoComplete(value).then(response => {
          let auxResultadoAgrupado = this.resultadoAgrupado;
          if (auxResultadoAgrupado.length === 0) {
            this.resultadoAgrupado = response.data;

          } else if (auxResultadoAgrupado.length > 0) {

            for (let j = 0, max = response.data.length; j < max; j++) {
              let igual = false;

              for (let i = 0, max2 = auxResultadoAgrupado.length; i < max2; i++) {

                if ((response.data[j].concatenacao === auxResultadoAgrupado[i].concatenacao)
                  && (response.data[j].pesquisa === auxResultadoAgrupado[i].pesquisa)) {
                  igual = true;
                  break;
                }
              }

              if (!igual) {
                this.resultadoAgrupado.push(response.data[j]);
              }
            }
          }
          let auxResultadoPesquisa = this.resultadoPesquisa;

          if (auxResultadoPesquisa.length === 0) {
            for (let i = 0, max = response.data.length; i < max; i++) {
              this.resultadoPesquisa.push(response.data[i].concatenacao);
            }
          } else if (auxResultadoPesquisa.length > 0) {
            for (let i = 0, max = response.data.length; i < max; i++) {
              let igual = false;
              for (let j = 0, max2 = auxResultadoPesquisa.length; j < max2; j++) {
                if (response.data[i].concatenacao === auxResultadoPesquisa[j]) {
                  igual = true;
                  break;
                }
              }
              if (!igual) {
                this.resultadoPesquisa.push(response.data[i].concatenacao);
              }
            }
          }
        }).catch(err => {
          console.log(err)
        });
      },
      buscarAnuncios() {
        if (this.requestAnuncio) {
          this.showModal = true;
          buscarAnuncios(`/anuncios/v1/search?page=${this.pageBuscaTotal}&size=20`).then((response) => {
            this.pageBusca = 0;
            this.showModal = false;
            if (response.data.length > 0) {
              this.pageBuscaTotal++;
              for (let i = 0; i < response.data.length; i++) {
                this.anuncios.push(response.data[i])
              }
            } else {
              this.disabledButton = true;
              this.requestAnuncio = false;
              Swal.alertUmButton('Não existem mais anúncios', ' ', 'info')
            }
          }).catch((err) => {
            this.showModal = false;
            Swal.alertUmButton('Atenção', 'Ocorreu um erro inesperado, favor atualize a página', 'error');
            console.log(err.response);
          })
        }
      },
      procurarAnuncio() {
        this.showModal = true;
        if (this.buscar !== this.valorAntigo) {
          this.pageBusca = 0
        }
        buscarAnuncios(`/anuncios/v1/search?page=${this.pageBusca}&pesquisa=${this.buscar}&size=20`).then((response) => {
          this.valorAntigo = this.buscar;
          this.disabledButton = false;
          this.pageBuscaTotal = 0;
          this.showModal = false;
          if (this.pageBusca > 0) {
            if (response.data.length > 0) {
              for (let i = 0, max = response.data.length; i < max; i++) {
                this.anuncios.push(response.data[i]);
              }
            }
          } else {
            this.anuncios = response.data;
          }
          this.pageBusca++;
        }).catch((err) => {
          this.showModal = false;
          Swal.alertUmButton('Atenção', 'Ocorreu um erro inesperado, favor atualize a página', 'error');
          console.log(err.response);
        })
      },
      definirChamadaBusca() {
        if (this.buscar.length > 0) {
          console.log(this.buscar)
          this.procurarAnuncio();
        } else {
          this.buscarAnuncios();
        }
      }
    },
    mounted() {
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
</style>
