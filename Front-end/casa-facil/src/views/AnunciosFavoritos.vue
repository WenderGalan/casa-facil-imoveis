<template>
  <div class="home" style="margin-top: 45px">
    <div style="padding: 15px;">
      <div class="row">
        <loader :show-modal="showModal"/>
        <b-card
            style="margin-left: 10px"
            title="">

          <AnuncioComponent v-for="anuncio in anuncios" :anuncio="anuncio.anuncio"/>
        </b-card>
      </div>
    </div>
  </div>
</template>

<script>
  import loader from '../templates/Loader'
  import AnuncioComponent from '../components/AnuncioComponent'
  import {trazerFavoritos} from '../services/requestServices'
  export default {
    name: "AnunciosFavoritos",
    components: {loader, AnuncioComponent},
    data() {
      return {
        anuncios: [],
        showModal: false
      }
    },
    methods: {
      buscarFavoritos() {
        debugger;
        this.showModal = true;
        const id = this.$store.state.sessao.id;
        trazerFavoritos(id).then(response => {
          debugger
          this.showModal = false;
          this.anuncios = response.data
        }).catch(err => {
          debugger
          this.showModal = false;
          console.log(err)
        })
      }
    },
    beforeMount() {
      debugger;
      this.buscarFavoritos();
    }
  }
</script>

<style scoped>

</style>
