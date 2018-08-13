<template>
  <div class="container">
    <div class="col-sm-12 col-md-4 col-lg-5  center">
      <b-card title="Login">
        <div class="row">

          <div style="margin-top: 15px; margin-bottom: -12px" class="col-sm-12 col-md-4 col-lg-12">
            <g-signin-button
              :params="googleSignInParams"
              @success="onSignInSuccessGoogle"
              @error="onSignInErrorGoogle">
              Entre com uma conta google
            </g-signin-button>
          </div>

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
  name: 'login',
  data () {
    return {
      /**
       * The Auth2 parameters, as seen on
       * https://developers.google.com/identity/sign-in/web/reference#gapiauth2initparams.
       * As the very least, a valid client_id must present.
       * @type {Object}
       */
      googleSignInParams: {
        client_id: '158526899286-0mbplsvrgtfe9qo80jd276m3h4i3nkg3.apps.googleusercontent.com'
      }
    }
  },
  methods: {
    onSignInSuccessGoogle (googleUser) {
      // `googleUser` is the GoogleUser object that represents the just-signed-in user.
      // See https://developers.google.com/identity/sign-in/web/reference#users
      const profile = googleUser.getBasicProfile() // etc etc
      console.log(profile)
    },
    onSignInErrorGoogle (error) {
      // `error` contains any error occurred.
      console.log('OH NOES', error)
    }
  }
}
</script>

<style scoped>
  .center {
    width: 50%;
    padding: 10px;
    margin: auto;
  }

  .g-signin-button {
    /* This is where you control how the button looks. Be creative! */
    display: inline-block;
    padding: 4px 8px;
    border-radius: 3px;
    background-color: #3c82f7;
    color: #fff;
    box-shadow: 0 3px 0 #0f69ff;
    cursor: pointer;
  }
</style>
