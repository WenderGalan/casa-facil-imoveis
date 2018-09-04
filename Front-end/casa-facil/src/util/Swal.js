import swal from 'sweetalert'

export default class Swal {
  static alertUmButton (titulo, descricao, icon) {
    return swal({
      title: titulo,
      text: descricao,
      icon: icon,
      closeOnClickOutside: false,
      buttons: {
        sim: {
          text: "Ok",
          value: "ok",
          visible: true,
          className: "",
          closeModal: true
        }
      },
    });
  }

  static alertDoisButtons (titulo, descricao, icon) {
    return swal({
      title: titulo,
      text: descricao,
      icon: icon,
      closeOnClickOutside: false,
      buttons: {
        sim: {
          text: "Sim",
          value: "sim",
          visible: true,
          className: "",
          closeModal: true
        },
        nao: {
          text: "Não",
          value: "nao",
          visible: true,
          className: "",
          closeModal: true,
        }
      },
    });
  }
}