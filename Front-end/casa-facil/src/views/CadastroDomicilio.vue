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
              <div class="col-sm-12 col-md-4 col-lg-12">
                <p class="text-left">CEP:</p>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-10">
                <input type="text" id="cep" @keyup.enter="buscarCep" v-model="localizacao.cep" v-mask="'#####-###'" class="form-control"/>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-2">
                <b-button variant="info" @click="buscarCep">Buscar</b-button>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <div class="row">
                  <div class="col-sm-12 col-md-4 col-lg-6" style="margin-left: -15px">
                    <div class="col-sm-12 col-md-4 col-lg-12">
                      <p class="text-left">Estado:</p>
                    </div>

                    <div class="col-sm-12 col-md-4 col-lg-12">
                      <input type="text" v-model="localizacao.uf" class="form-control"/>
                    </div>
                  </div>

                  <div class="col-sm-12 col-md-4 col-lg-6" style="margin-right: -20px">
                    <div class="col-sm-12 col-md-4 col-lg-12">
                      <p class="text-left">Número:</p>
                    </div>

                    <div class="col-sm-12 col-md-4 col-lg-12">
                      <input type="number" v-model="localizacao.numero" class="form-control"/>
                    </div>
                  </div>
                </div>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <p class="text-left">Cidade:</p>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <input type="text" v-model="localizacao.localidade" class="form-control"/>
              </div>
            </div>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <div class="row">
              <div class="col-sm-12 col-md-4 col-lg-12">
                <p class="text-left">Endereço:</p>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <input type="text" v-model="localizacao.logradouro" class="form-control"/>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <p class="text-left">Complemento:</p>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <input type="text" v-model="localizacao.complemento" class="form-control"/>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <p class="text-left">Bairro:</p>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <input type="text" v-model="localizacao.bairro" class="form-control"/>
              </div>
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
                  @click="adicionarAnuncio" @keyup.enter="adicionarAnuncio">Inserir anúncio</b-button>
      </b-card>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'
import {salvarAnuncio, salvarImagensAnuncio} from '../services/requestServices'
import loaderModal from '../templates/Loader'
import Swal from '../util/Swal'

export default {
  name: 'CadastroDomicilio',
  components: {
    loaderModal
  },
  data () {
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
        tipoImovel: null
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
      showModal: false
    }
  },
  methods: {
    buscarCep () {
      Axios({
        method: 'GET',
        url: `http://viacep.com.br/ws/${this.localizacao.cep}/json/`
      }).then((response) => {
        this.localizacao = response.data
      }).catch((err) => {
        console.log(err.response)
      })
    },
    adicionarAnuncio () {
      this.showModal = true
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
        titulo: this.infoImovel.titulo,
        valor: this.infoImovel.valor
      }
      salvarAnuncio(this.$store.state.sessao.id, anuncio).then((response) => {
        if (response.data) {
          this.salvarImagens(response.data.id)
        }
      }).catch((err) => {
        console.log(err.response)
      })
    },
    salvarImagens (id) {
      let formData = new FormData()
      for (let i = 0, max = this.fotos.length; i < max; i++) {
        formData.append('files', this.fotos[i])
      }
      salvarImagensAnuncio(id, formData)
        .then((response) => {
          this.showModal = false
          console.log('ABAIXO FICA AS FOTOS')
          console.log('FOTOS ->', response.data)
          this.$router.push({name: 'home'})
          Swal.alertUmButton('Salvo com sucesso', '', 'success')
      }).catch((err) => {
        console.log(err.response)
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

</style>
