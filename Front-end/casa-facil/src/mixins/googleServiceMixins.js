export default {
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