<template>
  <div>
    <div style="padding: 15px">
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
                <input type="text" id="cep" v-model="localizacao.cep" v-mask="'#####-###'" class="form-control"/>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-2">
                <b-button variant="info" @click="buscarCep">Buscar</b-button>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <div class="row">
                  <div class="col-sm-12 col-md-4 col-lg-6" style="margin-left: -15px">
                    <div class="col-sm-12 col-md-4 col-lg-12">
                      <p class="text-left">UF:</p>
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
                <p class="text-left">Localidade:</p>
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

          <div class="col-sm-12 col-md-4 col-lg-6">
            <p class="text-left">Defina um título</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <p class="text-left">Preço</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <input type="text" class="form-control"/>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <input type="text" class="form-control"/>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Descrição:</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <textarea class="form-control"/>
          </div>

        </div>
      </b-card>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  name: 'CadastroDomicilio',
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
      fotos: [],
      infoImovel: {
        titulo: '',
        descricao: '',
        valor: 0,
        tipoImovel: ''
      }
    }
  },
  methods: {
    buscarCep () {
      Axios({
        method: 'GET',
        url: `http://viacep.com.br/ws/${this.localizacao.cep}/json/`
      }).then((response) => {
        console.log(response.data)
        this.localizacao = response.data
      }).catch((err) => {
        console.log(err.response)
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
