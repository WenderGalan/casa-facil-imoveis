<template>
  <div class="container">
    <div class="col-sm-12 col-md-4 col-lg-5  center">
      <b-card title="Login">
        <div class="row">


          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Digite seu Email:*</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <input type="text" placeholder="exemplo@dominio.com"
                   class="form-control col-sm-12 col-md-4 col-lg-12"/>
          </div>

          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <p class="text-left">Digite sua senha:*</p>
          </div>

          <div class="col-sm-12 col-md-4 col-lg-12">
            <input type="password" placeholder="Senha (mínimo 8 caracteres)"
                   class="form-control col-sm-12 col-md-4 col-lg-12"/>
          </div>

          <div class="container" style="margin-top: 25px">
            <b-button class="form-control col-sm-12 col-md-4 col-lg-12" variant="info">Cadastrar</b-button>
          </div>

        </div>
      </b-card>
    </div>
  </div>
</template>
<script>
export default {
  name: 'login'
}
</script>

<style scoped>
  .center {
    width: 50%;
    padding: 10px;
    margin: auto;
  }
</style>
