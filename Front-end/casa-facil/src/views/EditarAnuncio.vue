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
            <p class="text-left">Escolha 1 imagem por vez</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <b-form-file v-model="fotos" class="mt-3" unique plain></b-form-file>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <div class="row">
              <div class="col-sm-12 col-md-4 col-lg-3" v-for="img in anuncio.imagensAnuncios">
                <b-img alt="Thumbnail" id="imagem" :src="img.imagemUrl" class="col-sm-12 col-md-4 col-lg-12"/>
                <b-button variant="danger" @click="deletarImagemAnuncio(img.id)">
                  <!--<i class="fas fa-trash-alt" style="font-size:40px; color: white; margin: 5px"></i>-->
                  Excluir
                </b-button>
              </div>
            </div>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Escolha o tipo de imóvel</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <b-form-select id="file" v-model="anuncio.tipoImovel" :options="tiposDeDomicilio"
                           class="mb-3"></b-form-select>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <p class="text-left">Defina um título</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <p class="text-left">Preço</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <input type="text" v-model="anuncio.titulo" class="form-control"/>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <input type="number" v-model="anuncio.valor" class="form-control"/>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Descrição:</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <textarea v-model="anuncio.descricao" class="form-control"></textarea>
          </div>
        </div>
        <b-button style="margin-top: 10px"
                  variant="warning"
                  @click="editarAnuncio" @keyup.enter="editarAnuncio">Salvar alterações</b-button>
      </b-card>
    </div>
  </div>
</template>

<script>
  import Axios from 'axios'
  import {buscarAnuncio, alterarAnuncio, excluirImagemAnuncio, salvarImagensAnuncio} from '../services/requestServices'
  import loaderModal from '../templates/Loader'
  import Swal from '../util/Swal'

  export default {
    name: 'EditarAnuncio',
    components: {
      loaderModal
    },
    data () {
      return {
        fotos: null,
        anuncio: null,
        localizacao: {
          cep: '',
          logradouro: '',
          uf: '',
          numero: '',
          complemento: '',
          localidade: '',
          bairro: ''
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
      findAnuncio (id) {
        this.showModal = true
        buscarAnuncio(id).then((response) => {
          this.anuncio = response.data
          this.showModal = false
          this.atribuirInformacoes()
        }).catch((err) => {
          console.log(err.response)
          this.showModal = false
        })
      },
      atribuirInformacoes () {
        this.localizacao.bairro = this.anuncio.endereco.bairro
        this.localizacao.cep = this.anuncio.endereco.cep
        this.localizacao.localidade = this.anuncio.endereco.cidade
        this.localizacao.complemento = this.anuncio.endereco.complemento
        this.localizacao.logradouro = this.anuncio.endereco.endereco
        this.localizacao.numero = this.anuncio.endereco.numero
        this.localizacao.uf = this.anuncio.endereco.estado

        if (this.anuncio.tiposImovel === 'Casa') {
          this.anuncio.tiposImovel = this.tiposDeDomicilio[0].value

        } else if (this.anuncio.tiposImovel === 'Apartamento') {
          this.anuncio.tiposImovel = this.tiposDeDomicilio[1].value

        } else if (this.anuncio.tiposImovel === 'Loja') {
          this.anuncio.tiposImovel = this.tiposDeDomicilio[2].value

        } else if (this.anuncio.tiposImovel === 'Terreno') {
          this.anuncio.tiposImovel = this.tiposDeDomicilio[3].value

        } else if (this.anuncio.tiposImovel === 'Fazenda') {
          this.anuncio.tiposImovel = this.tiposDeDomicilio[4].value

        } else if (this.anuncio.tiposImovel === 'Imovel Comercial') {
          this.anuncio.tiposImovel = this.tiposDeDomicilio[5].value
        }
      },
      editarAnuncio () {
        this.showModal = true
        this.anuncio.endereco.bairro = this.localizacao.bairro
        this.anuncio.endereco.cep = this.localizacao.cep
        this.anuncio.endereco.cidade = this.localizacao.localidade
        this.anuncio.endereco.complemento = this.localizacao.complemento
        this.anuncio.endereco.endereco = this.localizacao.logradouro
        this.anuncio.endereco.numero = this.localizacao.numero
        this.anuncio.endereco.estado = this.localizacao.uf

        alterarAnuncio(this.anuncio).then((response) => {
          this.showModal = false
          this.$router.push({name: 'home'})
        }).catch((err) => {
          this.showModal = false
          console.log(err.response)
        })
      },
      deletarImagemAnuncio (id) {
        Swal.alertDoisButtons('Atenção!', 'Deseja realmente excluir esta foto?', 'warning')
          .then((value) => {
            switch (value) {
              case 'sim':
                this.showModal = true
                excluirImagemAnuncio(id).then((response) => {
                  console.log('imagens retornadas -> ',response.data)
                  this.anuncio.imagensAnuncios = response.data
                  this.showModal = false
                }).catch((err) => {
                  this.showModal = false
                  console.log(err.response)
                })
                break;
              case 'nao':
                break;
            }
          })
      }
    },
    mounted () {
      const id = this.$router.history.current.params.id
      this.findAnuncio (id)
    },
    watch: {
      fotos (fotos) {
        let formData = new FormData();
        formData.append('files', fotos)
        this.showModal = true
        salvarImagensAnuncio(this.anuncio.id, formData).then((response) => {
          this.anuncio.imagensAnuncios.push(response.data)
          const imagens = this.anuncio.imagensAnuncios
          this.anuncio.imagensAnuncios = null
          this.anuncio.imagensAnuncios = null
          this.anuncio.imagensAnuncios = null
          this.anuncio.imagensAnuncios = null
          this.anuncio.imagensAnuncios = null
          this.anuncio.imagensAnuncios = null
          this.anuncio.imagensAnuncios = null
          this.anuncio.imagensAnuncios = null
          this.anuncio.imagensAnuncios = null
          this.anuncio.imagensAnuncios = imagens
          this.showModal = false
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


  #imagem {
    padding: 15px;
    min-height: 197px;
    max-height: 197px
  }

  #imagem:hover {
  opacity: 0.3
  }

  #excluirImagem:hover {
    opacity: 1;
  }

  #excluirImagem {
    opacity: 0;
    top: 50%;
    position: absolute;
    left: 40%;
    margin-top: -30px
  }

</style>
