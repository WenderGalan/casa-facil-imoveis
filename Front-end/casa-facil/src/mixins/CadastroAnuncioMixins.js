import Utils from '../util/Utils'
export default {
  data() {
    return {
      localizacao: ''
    }
  },
  methods: {
    updateLogradouro(result) {
      this.localizacao = result;
      console.log(this.localizacao)
    },
    atribuirResultado(result) {
      if (result.id === 'tipoImovel') {
        this.infoImovel.tipoImovel = result.message
      } else if (result.id === 'tipoNegocio') {
        this.infoImovel.tipoNegocio = result.message
      } else if (result.id === 'titulo') {
        this.infoImovel.titulo = result.message
      } else if (result.id === 'preco') {
        this.infoImovel.valor = result.message
      } else if (result.id === 'descricao') {
        this.infoImovel.descricao = result.message
      }
    },
    validarCadastro() {
      let result = true;
      if (this.localizacao.cep === null || this.localizacao.cep === '') {
        Utils.alertInput('cep');
        result = false
      } else {
        Utils.alertInputValid('cep')
      }
      if (this.localizacao.logradouro === null || this.localizacao.logradouro === '') {
        Utils.alertInput('logradouro');
        result = false
      } else {
        Utils.alertInputValid('logradouro')
      }
      if (this.localizacao.bairro === null || this.localizacao.bairro === '') {
        Utils.alertInput('bairro');
        result = false
      } else {
        Utils.alertInputValid('bairro')
      }
      if (this.localizacao.uf === null || this.localizacao.uf === '') {
        Utils.alertInput('uf');
        result = false
      } else {
        Utils.alertInputValid('uf')
      }
      if (this.localizacao.localidade === null || this.localizacao.localidade === '') {
        Utils.alertInput('localidade');
        result = false
      } else {
        Utils.alertInputValid('localidade')
      }
      if (this.localizacao.complemento === null || this.localizacao.complemento === '') {
        Utils.alertInput('complemento');
        result = false
      } else {
        Utils.alertInputValid('complemento')
      }
      if (this.infoImovel.titulo === null || this.infoImovel.titulo === '') {
        Utils.alertInput('titulo');
        result = false
      } else {
        Utils.alertInputValid('titulo')
      }
      if (this.infoImovel.descricao === null || this.infoImovel.descricao === '') {
        Utils.alertInput('descricao');
        result = false
      } else {
        Utils.alertInputValid('descricao')
      }
      if (this.infoImovel.valor === null || this.infoImovel.valor === 0) {
        Utils.alertInput('preco');
        result = false
      } else {
        Utils.alertInputValid('preco')
      }
      if (this.infoImovel.tipoNegocio === null || this.infoImovel.tipoNegocio === '') {
        Utils.alertInput('tipoNegocio');
        result = false
      } else {
        Utils.alertInputValid('tipoNegocio')
      }
      if (this.infoImovel.tipoImovel === null || this.infoImovel.tipoImovel === '') {
        Utils.alertInput('tipoImovel');
        result = false
      } else {
        Utils.alertInputValid('tipoImovel')
      }
      return result
    },
    atribuirInformacoes() {
      this.localizacao.bairro = this.anuncio.endereco.bairro;
      this.localizacao.cep = this.anuncio.endereco.cep;
      this.localizacao.localidade = this.anuncio.endereco.cidade;
      this.localizacao.complemento = this.anuncio.endereco.complemento;
      this.localizacao.logradouro = this.anuncio.endereco.endereco;
      this.localizacao.numero = this.anuncio.endereco.numero;
      this.localizacao.uf = this.anuncio.endereco.estado;

      if (this.anuncio.tiposImovel === 'Casa') {
        this.anuncio.tiposImovel = this.tiposDeDomicilio[0].value

      } else if (this.anuncio.tiposImovel === 'Apartamento') {
        this.anuncio.tiposImovel = this.tiposDeDomicilio[1].value

      } else if (this.anuncio.tiposImovel === 'Loja') {
        this.anuncio.tiposImovel = this.tiposDeDomicilio[2].value

      } else if (this.anuncio.tiposImovel === 'Terreno') {
        this.anuncio.tiposImovel = this.tiposDeDomicilio[3].value

      } else if (this.anuncio.tiposImovel === 'Fazenda') {
        this.anuncio.tiposImovel = this.tiposDeDomicilio[4].value

      } else if (this.anuncio.tiposImovel === 'Imovel Comercial') {
        this.anuncio.tiposImovel = this.tiposDeDomicilio[5].value
      }
    },
  }
}