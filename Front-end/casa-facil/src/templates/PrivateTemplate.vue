<template>
  <div>
    <b-navbar type="dark" variant="primary" toggleable>
      <b-navbar-brand href="#">Casa fácil</b-navbar-brand>

      <b-collapse is-nav id="nav_dropdown_collapse">
        <b-navbar-nav>
          <b-nav-item style="margin-left: 50px" href="#">Início</b-nav-item>
          <b-nav-item href="#">Link</b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-nav-item href="#">Cadastre-se</b-nav-item>
          <b-nav-item href="#">Entrar</b-nav-item>
          <!-- Navbar dropdowns -->
          <b-nav-item-dropdown v-if="verificarSessao" text="User" right>
            <b-dropdown-item href="#" v-if="verificarSessao">Perfil</b-dropdown-item>
            <b-dropdown-item href="#" v-if="verificarSessao">Sair</b-dropdown-item>
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
    computed: {
      verificarSessao () {
        return this.$store.state.sessao !== undefined;
      }
    }
  }
</script>

<style scoped>

</style>