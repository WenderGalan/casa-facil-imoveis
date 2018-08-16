export default class Utils {
  // COLOCA EM DESTAQUE OS INPUTS QUE PRECISAM SER PREENCHIDOS CORRETAMENTE
  static alertInput (id) {
    document.getElementById(id).style.boxShadow = '0 0 0 0.2rem rgba(255, 0, 0, 0.25)'
    document.getElementById(id).style.borderColor = '#ff0000'
  }

  // COLOCA EM DESTAQUE OS INPUTS QUE FORAM PREENCHIDOS CORRETAMENTE
  static alertInputValid (id) {
    document.getElementById(id).style.boxShadow = '0 0 0 0.2rem rgb(220,237,200)'
    document.getElementById(id).style.borderColor = '#DCEDC8'
  }

  // FAZ A VALIDAÇÃO DO EMAIL
  static validateEmail (email) {
    let re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    return re.test(email)
  }
  // FAZ A FORMATAÇÃO DO NUMERO PARA RETIRAR A MÁSCARA DO VUE THE MASK
  static formatarNumero (numero) {
    let valor = numero
    valor = valor.replace('(', '')
    valor = valor.replace(')', '')
    valor = valor.replace(' ', '')
    valor = valor.replace('-', '')
    return valor
  }
}
