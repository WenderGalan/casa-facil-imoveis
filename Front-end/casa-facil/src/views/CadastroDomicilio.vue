<template>
  <div>
    <div style="padding: 15px">
      <b-card id="tile" title="Cadastro de domicílio">
        <div class="row">
          <div class="col-sm-12 col-md-4 col-lg-12">
            <p id="titulo" class="text-left">Localização do domicílio</p>
            <hr>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <div class="row">
              <div class="col-sm-12 col-md-4 col-lg-7">

                <div class="col-sm-12 col-md-4 col-lg-12">
                  <p class="text-left">CEP:</p>
                </div>

                <div class="col-sm-12 col-md-4 col-lg-12">
                  <input type="text" v-mask="'#####-###'" class="form-control"/>
                </div>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-5">
                <div class="col-sm-12 col-md-4 col-lg-12">
                  <p class="text-left">UF</p>
                </div>

                <div class="col-sm-12 col-md-4 col-lg-12">
                  <input type="text" class="form-control"/>
                </div>
              </div>
            </div>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-6">
            <div class="row">
              <div class="col-sm-12 col-md-4 col-lg-8">
                <div class="col-sm-12 col-md-4 col-lg-12">
                  <p class="text-left">Endereço:</p>
                </div>

                <div class="col-sm-12 col-md-4 col-lg-12">
                  <input type="text" class="form-control"/>
                </div>
              </div>
              <div class="col-sm-12 col-md-4 col-lg-4">

              </div>
            </div>
          </div>
        </div>
      </b-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CadastroDomicilio'
}
</script>

<style scoped>
  p#titulo {
    font-weight: bold;
  }

</style>