<template>
  <div class="col-sm-12 col-md-4 col-lg-12" style="padding: 0">
    <div class="row">
      <div class="col-sm-12 col-md-4 col-lg-12">
        <p class="text-left titulo">Localização do Imóvel</p>
        <hr>
      </div>

      <div class="col-sm-12 col-md-4 col-lg-6">
        <div class="row">

          <div class="col-sm-12 col-md-4 col-lg-10">
            <input-number-component :id="'cep'" :holder="''" :mask="'#####-###'" :label="'CEP:'"
                                    :value-input="localizacao.cep" @resultadoNumber="atribuirResultadoEndereco"/>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-2">
            <b-button variant="info" class="botaoBuscar" @click="buscarCep">Buscar</b-button>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <div class="row">
              <div class="col-sm-12 col-md-4 col-lg-6">

                <input-component :holder="''" :tipo="'text'" :label="'Estado:'" :value-input="localizacao.uf"
                                 :id="'uf'" :max="'2'" @resultadoText="atribuirResultadoEndereco"/>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-6">
                <input-component :holder="''" :tipo="'text'" :label="'Número:'" :value-input="localizacao.numero"
                                 :id="'numero'" @resultadoText="atribuirResultadoEndereco"/>
              </div>
            </div>
          </div>

          <input-component style="margin-left: 15px" :holder="''" :tipo="'text'" :label="'Cidade:'"
                           :value-input="localizacao.localidade" :id="'localidade'" :max="'100'"
                           @resultadoText="atribuirResultadoEndereco"/>
        </div>
      </div>

      <div class="col-sm-12 col-md-4 col-lg-6">
        <div class="row">
          <input-component :holder="''" :tipo="'text'" :label="'Endereço:'" :value-input="localizacao.logradouro"
                           :id="'logradouro'" @resultadoText="atribuirResultadoEndereco" :max="'200'"/>

          <input-component :holder="''" :tipo="'text'" :label="'Complemento:'" :value-input="localizacao.complemento"
                           :id="'complemento'" :max="'100'" @resultadoText="atribuirResultadoEndereco"/>

          <input-component :holder="''" :tipo="'text'" :max="'100'" :label="'Bairro:'"
                           :value-input="localizacao.bairro" :id="'bairro'" @resultadoText="atribuirResultadoEndereco"/>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import inputComponent from '../inputTextComponent'
  import inputNumberComponent from '../InputNumberComponent'
  import Axios from 'axios'

  export default {
    name: "EnderecoComponent",
    components: {
      inputComponent,
      inputNumberComponent
    },
    props: {
      localizacaoProp: {
        type: Object
      }
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
        alteracao: '',
      }
    },
    methods: {
      atribuirResultadoEndereco(result) {
        if (result.id === 'cep') {
          this.alteracao = result.message;
          this.localizacao.cep = result.message
        } else if (result.id === 'uf') {
          this.alteracao = result.message;
          this.localizacao.uf = result.message
        } else if (result.id === 'numero') {
          this.alteracao = result.message;
          this.localizacao.numero = result.message
        } else if (result.id === 'localidade') {
          this.alteracao = result.message;
          this.localizacao.localidade = result.message
        } else if (result.id === 'logradouro') {
          this.alteracao = result.message;
          this.localizacao.logradouro = result.message
        } else if (result.id === 'complemento') {
          this.alteracao = result.message;
          this.localizacao.complemento = result.message
        } else if (result.id === 'bairro') {
          this.alteracao = result.message;
          this.localizacao.bairro = result.message
        }
      },
      buscarCep() {
        Axios({
          method: 'GET',
          url: `http://viacep.com.br/ws/${this.localizacao.cep}/json/`
        }).then((response) => {
          this.localizacao.cep = response.data.cep;
          this.localizacao.logradouro = response.data.logradouro;
          this.localizacao.uf = response.data.uf;
          this.localizacao.numero = response.data.numero;
          this.localizacao.complemento = response.data.complemento;
          this.localizacao.localidade = response.data.localidade;
          this.localizacao.bairro = response.data.bairro;
        }).catch((err) => {
          console.log(err.response)
        })
      },
    },
    watch: {
      localizacaoProp(localizacaoProp) {
        this.localizacao = localizacaoProp
      },
      alteracao() {
        this.$emit('updateLogradouro', this.localizacao)
      }
    }
  }
</script>

<style scoped>
  .botaoBuscar {
    position: absolute;
    bottom: 0;
    left: 0;
  }

  .titulo {
    font-weight: bold;
  }

</style>