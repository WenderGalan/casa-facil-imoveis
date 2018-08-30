<template>
  <div style="padding: 15px">
    <b-card title="Anuncios cadastrados">
      <b-card v-for="anuncio in anuncios" style="margin: 15px; padding-left: 0px">
        <div class="row">
          <div class="col-sm-12 col-md-4 col-lg-5">
            <b-img alt="Thumbnail" :src="anuncio.imagensAnuncios[0].imagemUrl" style="width: 300px; height: 250px;"/>
          </div>
          <div class="col-sm-12 col-md-4 col-lg-7">
            <h3>{{anuncio.titulo}}</h3>
            <p class="col-sm-12 col-md-4 col-lg-12" style="width: 100%">{{anuncio.descricao}}</p>
            <p class="text-right text-bottom" style="font-weight: bold">Valor: {{anuncio.valor}}</p>
          </div>
        </div>
      </b-card>
    </b-card>
  </div>
</template>

<script>
export default {
  name: 'ListagemAnuncios'
}
</script>

<style scoped>

</style>
