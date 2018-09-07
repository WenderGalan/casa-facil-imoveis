<template>
  <div style="margin-top: 45px; padding: 15px">
    <b-card>
      <div style="display:flex;justify-content:center;align-items:center;width:100%;">
        <b-carousel id="carousel1"
                    style="text-shadow: 1px 1px 2px #333; max-width: 70%"
                    controls
                    indicators
                    background="#ababab"
                    :interval="10000"
                    img-width="500"
                    img-height="200"
                    class="center">

          <!-- Text slides with image -->
          <b-carousel-slide v-for="img in anuncio.imagensAnuncios" :img-src="img.imagemUrl"
          ></b-carousel-slide>
        </b-carousel>
      </div>
      <div class="row">
      </div>
    </b-card>
  </div>
</template>

<script>
import {buscarAnuncio} from "../services/requestServices";

export default {
  name: 'DetalheImovel',
  data () {
    return {
      anuncio: null
    }
  },
  methods: {
    procurarAnuncio (id) {
      buscarAnuncio(id).then((response) => {
        if (response.data) {
          this.anuncio = response.data
        }
      }).catch((err) => {
        console.log(err.response)
      })
    }
  },
  mounted () {
    const id = this.$router.history.current.params.id
    this.procurarAnuncio(id)
  }

}
</script>

<style scoped>

</style>