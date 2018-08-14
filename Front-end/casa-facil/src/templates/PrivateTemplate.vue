<template>
  <div>
    <b-navbar type="dark" variant="primary" toggleable>
      <b-navbar-brand href="#" @click="irHome">Casa fácil</b-navbar-brand>

      <b-collapse is-nav id="nav_dropdown_collapse">
        <b-navbar-nav>
          <b-nav-item style="margin-left: 50px" href="#" @click="irHome">Início</b-nav-item>
          <b-nav-item href="#" @click="adicionarAnuncio">Adicionar Anúncio</b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-nav-item href="#" v-if="!verificarSessao" @click="irCadastro">Cadastre-se</b-nav-item>
          <b-nav-item href="#" v-if="!verificarSessao" @click="irLogin">Entrar</b-nav-item>
          <!-- Navbar dropdowns -->
          <b-nav-item-dropdown v-if="verificarSessao" text="User" right>
            <b-dropdown-item href="#" @click="irPerfil" v-if="verificarSessao">Perfil</b-dropdown-item>
            <b-dropdown-item href="#" @click="sair" v-if="verificarSessao">Sair</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'PrivateTemplate',
  methods: {
    irCadastro () {
      this.$router.push({name: 'cadastro'})
    },
    irLogin () {
      this.$router.push({name: 'login'})
    },
    irHome () {
      this.$router.push({name: 'home'})
    },
    sair () {
      this.$store.commit('alterarSessao', undefined)
    },
    adicionarAnuncio () {
      this.$router.push({name: 'cadastroDomicilio'})
    },
    irPerfil () {
      const id = this.$store.state.sessao.id
      this.$router.push(
        {
          name: 'perfil',
          params: {id}
        })
    }
  },
  computed: {
    verificarSessao () {
      return this.$store.state.sessao !== undefined
    }
  }
}
</script>

<style scoped>

</style>
