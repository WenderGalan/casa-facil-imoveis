<template>
  <div class="col-sm-12 col-md-4 col-lg-12">
    <div class="col-sm-12 col-md-4 col-lg-12" v-if="label.length > 0">
      <p class="text-left">{{label}}</p>
    </div>

    <div class="col-sm-12 col-md-4 col-lg-12">
      <select v-model="value" :id="id" class="form-control col-sm-12 col-md-4 col-lg-12">
        <option v-for="option in options" :value="option.value">{{option.text}}</option>
      </select>
    </div>
  </div>
</template>

<script>
  export default {
    name: "InputSelectComponent",
    props: {
      options: {
        type: Array,
        required: true
      },
      label: {
        type: String,
        default: ''
      },
      id: {
        type: String,
        default: '',
        required: true
      },
      selectedValue: {
        default: ''
      }
    },
    data() {
      return {
        value: ''
      }
    },
    watch: {
      value(value) {
        const obj = {
          id: this.id,
          message: value
        };
        this.$emit('resultadoSelect', obj)
      }
    }
  }
</script>

<style scoped>
  select {
    height: 30px;
  }

  p {
    margin-top: 15px;
    margin-bottom: 5px;
  }
</style>