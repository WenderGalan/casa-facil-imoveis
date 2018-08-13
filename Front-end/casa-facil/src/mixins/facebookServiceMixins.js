export default {
  data () {
    return {
      fbSignInParams: {
        scope: 'email, public_profile',
        return_scopes: true
      }
    }
  },
  methods: {
    onSignInSuccess (response) {
      FB.api('/me', dude => {
        console.log(`Good to see you, ${dude.name}.`)
        console.log(dude)
      })
      console.log(response)
    },
    onSignInError (error) {
      console.log('OH NOES', error)
    }
  }
}