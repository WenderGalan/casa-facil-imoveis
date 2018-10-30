<template>
  <div style="padding: 15px; margin-top: 45px;">
    <loader-modal :show-modal="showModal"></loader-modal>
    <b-card title="Anuncios cadastrados">
      <div class="col-sm-12 col-md-4 col-lg-12">
        <div class="row" style="text-align: right; display: block">
          <!--<div class="col-sm-12 col-md-4 col-lg-3">-->

          <!--</div>-->
          <b-form-select v-model="selected" :options="tiposDeRelatorio" class="mb-3 col-sm-12 col-md-4 col-lg-3"
                         style="margin-top: 18px; margin-right: 15px"></b-form-select>
          <b-button variant="success" @click="gerarRelatorioVenda('VENDA')"><i class="fa fa-usd" aria-hidden="true"></i>
            Gerar relatório de venda
          </b-button>
          <b-button variant="info" style="margin-left: 15px" @click="gerarRelatorioVenda('ALUGUEL')"><i
              class="fa fa-usd" aria-hidden="true"></i> Gerar relatório de
            aluguel
          </b-button>
        </div>
      </div>
      <div v-for="anuncio in anuncios" class="col-sm-12 col-md-4 col-lg-12">
        <div class="row">
          <div class="col-sm-12 col-md-4 col-lg-12">
            <anuncio-component :anuncio="anuncio"/>
            <b-button variant="danger" id="excluir" @click="alertaAnuncio(anuncio.id)">
              <i class="fa fa-trash" aria-hidden="true"></i>
            </b-button>
            <b-button variant="warning" id="editar" @click="editarAnuncio(anuncio.id)">
              <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
            </b-button>
          </div>
        </div>
      </div>
      <b-button variant="info" :disabled="enableButton" @click="listarAnuncios">Carregar mais anúncios</b-button>
    </b-card>
  </div>
</template>

<script>
  import {buscarAnunciosUsuario, excluirAnuncio, gerarRelatorio} from "../services/requestServices";
  import loaderModal from '../templates/Loader'
  import AnuncioComponent from '../components/AnuncioComponent'
  import {tiposRelatorio} from "../models/Enums";
  import Swal from '../util/Swal'
  import InputSelectComponent from "../components/InputSelectComponent";

  export default {
    name: 'ListagemAnuncios',
    components: {
      InputSelectComponent,
      loaderModal,
      AnuncioComponent
    },
    data() {
      return {
        anuncios: null,
        showModal: false,
        page: 0,
        fazerBusca: true,
        enableButton: false,
        tiposDeRelatorio: tiposRelatorio,
        selected: null
      }
    },
    methods: {
      listarAnuncios() {
        if (this.fazerBusca) {
          this.showModal = true;
          buscarAnunciosUsuario(this.$store.state.sessao.id, this.page).then((response) => {
            if (response.data.length > 0) {
              this.page++;
              console.log('anuncios ->', response.data);
              this.anuncios = response.data;
              this.showModal = false
            } else {
              Swal.alertUmButton('Não existem mais anúncio', '', 'info');
              this.showModal = false;
              this.fazerBusca = false;
              this.enableButton = true
            }
          }).catch((err) => {
            console.log(err.response);
            Swal.alertUmButton('Atenção', 'Ocorreu um erro inesperado, favor atualize a página', 'error');
            this.showModal = false
          })
        }
      },
      alertaAnuncio(id) {
        Swal.alertDoisButtons('Atenção!', 'Deseja mesmo deletar este anuncio?', 'warning')
          .then((value) => {
            switch (value) {
              case 'sim':
                this.showModal = true;
                this.deletarAnuncio(id);
                break;
              case 'nao':
                break;
            }
          })
      },
      deletarAnuncio(id) {
        excluirAnuncio(id).then((response) => {
          console.log('response delete ->', response);
          this.listarAnuncios()
        }).catch((err) => {
          this.showModal = false;
          console.log(err.response)
        })
      },
      editarAnuncio(id) {
        this.$router.push({name: 'editarAnuncio', params: {id}})
      },
      gerarRelatorioVenda(tipoNegocio) {
        this.showModal = true;
        const id = this.$store.state.sessao.id;
        gerarRelatorio(tipoNegocio, id, this.selected).then(response => {
          this.showModal = false;
          Swal.alertUmButton('', 'Relatório gerado com sucesso, verifique seu email para mais informações', 'success')
        }).catch(err => {
          this.showModal = false;
          debugger;
          if (err.response.data.code === 1003) {
            Swal.alertUmButton('', err.response.data.message, 'error')
          }
          console.log(err)
        })
      }
    },
    mounted() {
      this.listarAnuncios()
    }
  }
</script>

<style scoped>

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
