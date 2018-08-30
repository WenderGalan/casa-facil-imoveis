<template>
    <div class="container">
        <pulse-loader :loading="loading" :color="color" :size="size"></pulse-loader>
        <ring-loader :loading="loading" :color="color1" :size="size"></ring-loader>
        <bounce-loader :loading="loading" :color="color" :size="size"></bounce-loader>
    </div>
</template>

<script>
  import PulseLoader from 'vue-spinner/src/PulseLoader.vue'
  import RingLoader from 'vue-spinner/src/RingLoader.vue'
  import BounceLoader from 'vue-spinner/src/BounceLoader.vue'
  export default {
    name: 'Loader',
    data () {
      return {
        color: '#cc181e',
        color1: '#5bc0de',
        size: '45px',
        margin: '2px',
        radius: '2px'
      }
    },
    components: {
      PulseLoader,
      RingLoader,










      BounceLoader
    }
  }
</script>

<style scoped>

</style>