<template>
  <div style="margin-top: 45px; padding: 15px">
    <b-card>
      <div class="row">
        <div class="col-sm-12 col-md-4 col-lg-12">
          <b-carousel id="carousel1"
                      style="text-shadow: 1px 1px 2px #333;"
                      controls
                      indicators
                      background="#ababab"
                      :interval="10000"
                      img-width="500"
                      img-height="200">

            <!-- Text slides with image -->
            <b-carousel-slide img-width="500"
                              img-height="200"
                              img-src="https://picsum.photos/1024/480/?image=52"
            ></b-carousel-slide>
          </b-carousel>
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
export default {
  name: 'DetalheImovel'
}
</script>

<style scoped>

</style>