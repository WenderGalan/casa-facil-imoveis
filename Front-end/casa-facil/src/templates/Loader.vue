<template>
    <div>
      <b-modal v-model="showModal" hide-footer title="Aguarde um instante"
               centered no-close-on-backdrop hide-header-close>
        <div class="center" id="loading">
            <moon-loader :loading="true" :color="'#3F51B5'" :size="'100px'"></moon-loader>
        </div>
      </b-modal>
    </div>
</template>

<script>
  import MoonLoader from 'vue-spinner/src/MoonLoader'
  export default {
    props:  {
      showModal: {type: Boolean, default: false, required: true}
    },
    components: {
      MoonLoader
    }
  }
</script>

<style scoped>
    #loading {
        margin-left: 38%;
    }

</style>