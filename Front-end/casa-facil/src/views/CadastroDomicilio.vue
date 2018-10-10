<template>
  <div>
    <div style="padding: 15px; margin-top: 45px">
      <loader-modal :show-modal="showModal"></loader-modal>
      <b-card id="tile" title="Cadastro de Imóvel" >
        <div class="row">

          <endereco-component @updateLogradouro="updateLogradouro"/>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <p id="titulo2" class="text-left">Informações do Imóvel</p>
            <hr>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Escolha no maximo 4 imagens</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <b-form-file v-model="fotos" class="mt-3" multiple plain></b-form-file>
          </div>

          <input-select-component :id="'tipoImovel'" :options="tiposDeDomicilio" :label="'Escolha o tipo de imóvel'"
                                  @resultadoSelect="atribuirResultado"/>

          <input-select-component :id="'tipoNegocio'" :options="tiposDeNegocio" :label="'Escolha o tipo de negócio'"
                                  @resultadoSelect="atribuirResultado"/>

          <div class="col-sm-12 col-md-4 col-lg-6" style="padding: 0">
            <input-component :holder="''" :tipo="'text'" :label="'Defina um título'" :id="'titulo'"
                             @resultadoText="atribuirResultado"/>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6" style="padding: 0">
            <input-component :holder="''" :tipo="'number'" :label="'Preço'" :id="'preco'"
                             @resultadoText="atribuirResultado"/>
          </div>

          <input-text-area-component :id="'descricao'" :label="'Descrição:'" @resultadoTextArea="atribuirResultado"/>

        </div>
        <b-button style="margin-top: 10px"
                  variant="success"
                  @click="adicionarAnuncio" @keyup.enter="adicionarAnuncio">Inserir anúncio
        </b-button>
      </b-card>
    </div>
  </div>
</template>

<script>
  import {salvarAnuncio, salvarImagensAnuncio} from '../services/requestServices'
  import loaderModal from '../templates/Loader'
  import Swal from '../util/Swal'
  import Utils from '../util/Utils'
  import inputComponent from '../components/inputTextComponent'
  import inputNumberComponent from '../components/InputNumberComponent'
  import inputSelectComponent from '../components/InputSelectComponent'
  import inputTextAreaComponent from '../components/TextAreaComponent'
  import enderecoComponent from '../components/groupComponents/EnderecoComponent'
  import {tiposDeDomicilio as tpDomicilios, tiposDeNegocio as tpNegocio} from "../models/Enums";

  export default {
    name: 'CadastroDomicilio',
    components: {
      loaderModal,
      inputComponent,
      inputNumberComponent,
      inputSelectComponent,
      inputTextAreaComponent,
      enderecoComponent
    },
    data() {
      return {
        localizacao: '',
        fotos: null,
        infoImovel: {
          titulo: '',
          descricao: '',
          valor: 0,
          tipoImovel: null,
          tipoNegocio: null
        },
        tiposDeDomicilio: tpDomicilios,
        tiposDeNegocio: tpNegocio,
        showModal: false
      }
    },
    methods: {
      updateLogradouro(result){
        this.localizacao = result;
        console.log(this.localizacao)
      },
      atribuirResultado(result) {
        if (result.id === 'tipoImovel') {
          this.infoImovel.tipoImovel = result.message
        } else if (result.id === 'tipoNegocio') {
          this.infoImovel.tipoNegocio = result.message
        } else if (result.id === 'titulo') {
          this.infoImovel.titulo = result.message
        } else if (result.id === 'preco') {
          this.infoImovel.valor = result.message
        } else if (result.id === 'descricao') {
          this.infoImovel.descricao = result.message
        }
      },
      validarCadastro() {
        let result = true;
        if (this.localizacao.cep === null || this.localizacao.cep === '') {
          Utils.alertInput('cep');
          result = false
        } else {
          Utils.alertInputValid('cep')
        }

        if (this.localizacao.logradouro === null || this.localizacao.logradouro === '') {
          Utils.alertInput('logradouro');
          result = false
        } else {
          Utils.alertInputValid('logradouro')
        }

        if (this.localizacao.uf === null || this.localizacao.uf === '') {
          Utils.alertInput('uf');
          result = false
        } else {
          Utils.alertInputValid('uf')
        }

        if (this.localizacao.localidade === null || this.localizacao.localidade === '') {
          Utils.alertInput('localidade');
          result = false
        } else {
          Utils.alertInputValid('localidade')
        }
        return result
      },
      adicionarAnuncio() {
        if (this.validarCadastro()) {
          this.showModal = true;
          const anuncio = {
            descricao: this.infoImovel.descricao,
            endereco: {
              bairro: this.localizacao.bairro,
              cep: this.localizacao.cep,
              cidade: this.localizacao.localidade,
              complemento: this.localizacao.complemento,
              endereco: this.localizacao.logradouro,
              estado: this.localizacao.uf,
              latitude: 0.0,
              longitude: 0.0,
              numero: this.localizacao.numero
            },
            tipoImovel: this.infoImovel.tipoImovel,
            tipoNegocio: this.infoImovel.tipoNegocio,
            titulo: this.infoImovel.titulo,
            valor: this.infoImovel.valor
          };
          salvarAnuncio(this.$store.state.sessao.id, anuncio).then((response) => {
            if (response.data) {
              this.salvarImagens(response.data.id)
            }
          }).catch((err) => {
            console.log(err.response);
            Swal.alertUmButton('Atenção', 'Ocorreu um erro inesperado, favor atualize a página', 'error')
          })
        }
      },
      salvarImagens(id) {
        let formData = new FormData();
        for (let i = 0, max = this.fotos.length; i < max; i++) {
          formData.append('files', this.fotos[i])
        }
        salvarImagensAnuncio(id, formData)
          .then((response) => {
            this.showModal = false;
            console.log('ABAIXO FICA AS FOTOS');
            console.log('FOTOS ->', response.data);
            this.$router.push({name: 'home'});
            Swal.alertUmButton('Salvo com sucesso', '', 'success')
          }).catch((err) => {
          Swal.alertUmButton('Atenção', 'Ocorreu um erro inesperado, favor atualize a página', 'error');
          console.log(err.response);
          this.showModal = false
        })
      }
    }
  }
</script>

<style scoped>
  #titulo2 {
    font-weight: bold;
  }

</style>
