<template>
    <div id="loaderAqui">
      <b-modal ref="myModalRef" hide-footer title="Aguarde um instande"
               centered no-close-on-backdrop hide-header-close>
        <moon-loader :loading="true" :color="'#3F51B5'" :size="'100px'"></moon-loader>
      </b-modal>
    </div>
</template>

<script>
  import MoonLoader from 'vue-spinner/src/MoonLoader'
  export default {
    components: {
      MoonLoader
    },
    methods: {
      abrirModal() {
        this.$refs.myModalRef.show()
      }
    },
    mounted () {
      this.abrirModal()
    }
  }
</script>

<style scoped>

</style>