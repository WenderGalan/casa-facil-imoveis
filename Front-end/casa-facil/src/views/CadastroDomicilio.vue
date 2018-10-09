<template>
  <div>
    <div style="padding: 15px; margin-top: 45px">
      <loader-modal :show-modal="showModal"></loader-modal>
      <b-card id="tile" title="Cadastro de Imóvel">
        <div class="row">
          <div class="col-sm-12 col-md-4 col-lg-12">
            <p id="titulo" class="text-left">Localização do Imóvel</p>
            <hr>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <div class="row">

              <div class="col-sm-12 col-md-4 col-lg-10">
                <input-number-component :id="'cep'" :holder="''" :mask="'#####-###'" :label="'CEP:'"
                                        @resultadoNumber="atribuirResultado"/>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-2">
                <b-button variant="info" class="botaoBuscar" @click="buscarCep">Buscar</b-button>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <div class="row">
                  <div class="col-sm-12 col-md-4 col-lg-6">
                    <input-component :holder="''" :tipo="'text'" :label="'Estado:'" :value-input="localizacao.uf"
                                     :id="'uf'" @resultadoText="atribuirResultado"/>
                  </div>

                  <div class="col-sm-12 col-md-4 col-lg-6">
                    <input-component :holder="''" :tipo="'text'" :label="'Número:'" :value-input="localizacao.numero"
                                     :id="'numero'" @resultadoText="atribuirResultado"/>
                  </div>
                </div>
              </div>

              <input-component style="margin-left: 15px" :holder="''" :tipo="'text'" :label="'Cidade:'"
                               :value-input="localizacao.localidade" :id="'cidade'" @resultadoText="atribuirResultado"/>
            </div>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <div class="row">
              <input-component :holder="''" :tipo="'text'" :label="'Endereço:'"
                               :value-input="localizacao.logradouro" :id="'logradouro'" @resultadoText="atribuirResultado"/>

              <input-component :holder="''" :tipo="'text'" :label="'Complemento:'"
                               :value-input="localizacao.complemento" :id="'complemento'" @resultadoText="atribuirResultado"/>

              <input-component :holder="''" :tipo="'text'" :label="'Bairro:'"
                               :value-input="localizacao.bairro" :id="'bairro'" @resultadoText="atribuirResultado"/>
            </div>
          </div>

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

          <div class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Escolha o tipo de imóvel</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <b-form-select id="file" v-model="infoImovel.tipoImovel" :options="tiposDeDomicilio"
                           class="mb-3"></b-form-select>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Escolha o tipo de negócio</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <b-form-select id="file" v-model="infoImovel.tipoNegocio" :options="tiposDeNegocio"
                           class="mb-3"></b-form-select>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <p class="text-left">Defina um título</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <p class="text-left">Preço</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <input type="text" v-model="infoImovel.titulo" class="form-control"/>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <input type="number" v-model="infoImovel.valor" class="form-control"/>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Descrição:</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <textarea v-model="infoImovel.descricao" class="form-control" rows="8"></textarea>
          </div>
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
  import Axios from 'axios'
  import {salvarAnuncio, salvarImagensAnuncio} from '../services/requestServices'
  import loaderModal from '../templates/Loader'
  import Swal from '../util/Swal'
  import Utils from '../util/Utils'
  import inputComponent from '../components/inputTextComponent'
  import inputNumberComponent from '../components/InputNumberComponent'

  export default {
    name: 'CadastroDomicilio',
    components: {
      loaderModal,
      inputComponent,
      inputNumberComponent
    },
    data() {
      return {
        localizacao: {
          cep: '',
          logradouro: '',
          uf: '',
          numero: '',
          complemento: '',
          localidade: '',
          bairro: ''
        },
        fotos: null,
        infoImovel: {
          titulo: '',
          descricao: '',
          valor: 0,
          tipoImovel: null,
          tipoNegocio: null
        },
        tiposDeDomicilio: [
          {
            value: 0,
            text: 'Casa'
          },
          {
            value: 1,
            text: 'Apartamento'
          },
          {
            value: 2,
            text: 'Loja'
          },
          {
            value: 3,
            text: 'Terreno'
          },
          {
            value: 4,
            text: 'Fazenda'
          },
          {
            value: 5,
            text: 'Imovel Comercial'
          }
        ],
        tiposDeNegocio: [
          {
            value: 0,
            text: 'Venda'
          },
          {
            value: 1,
            text: 'Aluguel'
          }
        ],
        showModal: false
      }
    },
    methods: {
      atribuirResultado(result) {
        if (result.id === 'cep') {
          this.localizacao.cep = result.message
        } else if (result.id === 'uf') {
          this.localizacao.uf = result.message
        } else if (result.id === 'numero') {
          this.localizacao.numero = result.message
        } else if (result.id === 'cidade') {
          this.localizacao.localidade = result.message
        } else if (result.id === 'logradouro') {
          this.localizacao.logradouro = result.message
        } else if (result.id === 'complemento') {
          this.localizacao.complemento = result.message
        } else if (result.id === 'bairro') {
          this.localizacao.bairro = result.message
        }
      },
      buscarCep() {
        Axios({
          method: 'GET',
          url: `http://viacep.com.br/ws/${this.localizacao.cep}/json/`
        }).then((response) => {
          this.localizacao = response.data
        }).catch((err) => {
          console.log(err.response)
        })
      },
      validarCadastro() {
        debugger;
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
        debugger;
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
  #titulo, #titulo2 {
    font-weight: bold;
  }

  p {
    margin-top: 15px;
    margin-bottom: -2px;
  }

  .botaoBuscar {
    position: absolute;
    bottom: 0;
    left: 0;
  }

</style>
