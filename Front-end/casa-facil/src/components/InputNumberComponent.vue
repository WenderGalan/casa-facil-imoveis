<template>
  <div :class="classDiv">
    <div class="col-sm-12 col-md-4 col-lg-12" v-if="label.length > 0">
      <p class="text-left">{{label}}</p>
    </div>

    <div class="col-sm-12 col-md-4 col-lg-12">
      <input class="form-control col-sm-12 col-md-4 col-lg-12" v-mask="mask" v-model="number" type="text" :id="id"
             :placeholder="holder" :disabled="disabledInput"/>
    </div>
  </div>
</template>

<script>
  export default {
    name: "InputNumberComponent",
    props: {
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
      },
      mask: {
        type: String,
        default: '',
      },
      disabledInput: {
        type: Boolean,
      },
      valueInput: {
        type: String,
        default: ''
      },
      classDiv: {
        type: String,
        default: 'col-sm-12 col-md-4 col-lg-12',
      }
    },
    data() {
      return {
        number: ''
      }
    },
    watch: {
      number(number) {
        const obj = {
          id: this.id,
          message: number
        };
        this.$emit('resultadoNumber', obj);
      },
      valueInput(valueInput) {
        this.number = valueInput;
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