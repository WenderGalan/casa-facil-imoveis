<template>
  <div class="col-sm-12 col-md-4 col-lg-12">
    <div class="col-sm-12 col-md-4 col-lg-12" v-if="label.length > 0">
      <p class="text-left">{{label}}</p>
    </div>

    <div :class="classDiv">
      <input class="form-control col-sm-12 col-md-4 col-lg-12" v-model="text" :type="tipo" :id="id"
             :placeholder="holder" :disabled="disabledInput" :maxlength="max"/>
    </div>
  </div>
</template>

<script>
  export default {
    name: "inputTextButton",
    props: {
      tipo: {
        type: String,
        default: '',
        required: true
      },
      id: {
        type: String,
        default: '',
        required: true
      },
      holder: {
        type: String,
        default: '',
        required: true
      },
      label: {
        type: String,
        default: '',
        required: false
      },
      disabledInput: {
        type: Boolean,
        default: false
      },
      valueInput: {
        type: String,
        default: ''
      },
      classDiv: {
        type: String,
        default: 'col-sm-12 col-md-4 col-lg-12',
      },
      max: {
        type: String,
        default: '1000'
      }
    },
    data () {
      return {
        text: ''
      }
    },
    watch: {
      text(text) {
        const obj = {
          id: this.id,
          message: text
        };
        this.$emit('resultadoText', obj);
      },
      valueInput(valueInput) {
        this.text = valueInput;
      }
    }
  }
</script>

<style scoped>
  input {
    height: 30px;
  }

  p {
    margin-top: 15px;
    margin-bottom: 5px;
  }
</style>