<template>
  <div class="col-sm-12 col-md-4 col-lg-12">
    <b-card class="card" @click="detalhesAnuncio(anuncio.titulo, anuncio.id)">
      <div class="row">
        <div class="col-sm-12 col-md-4 col-lg-3" v-if="anuncio.imagensAnuncios.length > 0">
          <b-img alt="Thumbnail" :src="anuncio.imagensAnuncios[0].imagemUrl" class="img"></b-img>
        </div>

        <div class="col-sm-12 col-md-4 col-lg-9" style="padding-left: 30px">
          <h3>{{anuncio.titulo}}</h3>
          <p class="col-sm-12 col-md-4 col-lg-12 descricao"  style="width: 100%">{{anuncio.descricao}}</p>

          <div class="row" style="margin-left: 20px">
            <div class="col-sm-12 col-md-4 col-lg-4">
              <div class="row">
                <b-button variant="success">Tipo do imóvel: {{anuncio.tipoImovel}}</b-button>
              </div>
            </div>

            <div class="col-sm-12 col-md-4 col-lg-4">
              <b-button variant="warning">Tipo de negócio: {{anuncio.tipoNegocio}}</b-button>
            </div>

            <div class="col-sm-12 col-md-4 col-lg-4">
              <p class="valor">Valor: R${{anuncio.valor}}</p>
            </div>
          </div>
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
  export default {
    name: "AnuncioComponent",
    props: {
      anuncio: {
        type: Object,
        required: true
      }
    },
    methods: {
      detalhesAnuncio(title, id) {
        this.$router.push({name: 'detalheImovel', params: {title, id}})
      }
    }
  }
</script>

<style scoped>
  .card {
    margin: 15px;
    padding-left: 0;
    cursor: pointer
  }

  .img {
    width: 300px;
    height: 250px;
  }

  .descricao {
    width: 100%;
    text-overflow: ellipsis;
    text-align: justify;
    overflow: hidden;
    max-height: 140px;
  }

  .valor {
    font-weight: bold;
    margin-top: 10px;
  }
</style>