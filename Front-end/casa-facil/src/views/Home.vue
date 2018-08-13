<template>
  <div class="home">
    <div style="padding: 15px;">
      <div class="row" >
        <div class="col-sm-12 col-md-4 col-lg-4">
          <b-card title="">
            <div class="row">
              <div class="col-sm-12 col-md-4 col-lg-12">
                <p class="text-left">Digite a cidade, bairro ou rua</p>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-12">
                <input type="text" placeholder="Ex: Campo Grande"
                       class="form-control col-sm-12 col-md-4 col-lg-12"/>
              </div>

              <div style="margin-top: 15px" class="col-sm-12 col-md-4 col-lg-6">
                <p class="text-left">Preço mínimo</p>
              </div>

              <div style="margin-top: 15px" class="col-sm-12 col-md-4 col-lg-6">
                <p class="text-left">Preço máximo</p>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-6">
                <input v-mask="'###.###.###,##'" type="text" placeholder="R$" class="form-control col-sm-12 col-md-4 col-lg-12"/>
              </div>

              <div class="col-sm-12 col-md-4 col-lg-6">
                <input v-mask="'###.###.###,##'" type="text" placeholder="R$" class="form-control col-sm-12 col-md-4 col-lg-12"/>
              </div>
            </div>
          </b-card>
        </div>
      <br>

        <div class="col-sm-12 col-md-4 col-lg-8">
          <b-card title="">
            <div class="row">
              <b-card>
                <div class="row">
                  <div class="col-sm-12 col-md-4 col-lg-4">
                    <img src="../assets/logo.png"/>
                  </div>
                  <div class="col-sm-12 col-md-4 col-lg-8">
                    <p>dokasopkdopskaodkosakdopksaokdposakdoksaopkdopksaokdospadopksaokdospkdopadopksaokdospkdopadopksaokdospkdopadopkpadopksaokdo</p>
                    <p>dokasopkdopskaodkosakdopksaokdposakdoksaopkdopksaokdospadopksaokdospkdopadopksaokdospkdopadopksaokdospkdopadopkpadopksaokdo</p>
                    <p>dokasopkdopskaodkosakdopksaokdposakdoksaopkdopksaokdospadopksaokdospkdopadopksaokdospkdopadopksaokdospkdopadopkpadopksaokdo</p>
                  </div>
                </div>
              </b-card>
              <br>
              <b-card>
                <div class="row">
                  <div class="col-sm-12 col-md-4 col-lg-4">
                    <img src="../assets/logo.png"/>
                  </div>
                  <div class="col-sm-12 col-md-4 col-lg-8">
                    <p>dokasopkdopskaodkosakdopksaokdposakdoksaopkdopksaokdospadopksaokdospkdopadopksaokdospkdopadopksaokdospkdopadopkpadopksaokdo</p>
                    <p>dokasopkdopskaodkosakdopksaokdposakdoksaopkdopksaokdospadopksaokdospkdopadopksaokdospkdopadopksaokdospkdopadopkpadopksaokdo</p>
                    <p>dokasopkdopskaodkosakdopksaokdposakdoksaopkdopksaokdospadopksaokdospkdopadopksaokdospkdopadopksaokdospkdopadopkpadopksaokdo</p>
                  </div>
                </div>
              </b-card>
              <br>
              <b-card>
                <div class="row">
                  <div class="col-sm-12 col-md-4 col-lg-4">
                    <img src="../assets/logo.png"/>
                  </div>
                  <div class="col-sm-12 col-md-4 col-lg-8">
                    <p>dokasopkdopskaodkosakdopksaokdposakdoksaopkdopksaokdospadopksaokdospkdopadopksaokdospkdopadopksaokdospkdopadopkpadopksaokdo</p>
                    <p>dokasopkdopskaodkosakdopksaokdposakdoksaopkdopksaokdospadopksaokdospkdopadopksaokdospkdopadopksaokdospkdopadopkpadopksaokdo</p>
                    <p>dokasopkdopskaodkosakdopksaokdposakdoksaopkdopksaokdospadopksaokdospkdopadopksaokdospkdopadopksaokdospkdopadopkpadopksaokdo</p>
                  </div>
                </div>
              </b-card>
            </div>
          </b-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'home'
}
</script>
