<template>
    <div class="autocomplete">
        <input type="text"
               class="form-control"
               v-model="search"
               placeholder="Pesquisar por endereço, bairro ou cidade"
               style="width: 550px"
               @input="onChange"/>
        <ul class="autocomplete-results"
            v-show="isOpen"
            style="width: 550px">
            <li class="autocomplete-result"
                v-for="(result, i) in results"
                @click="setResult(result)"
                :key="i">
                {{ result }}
            </li>
        </ul>
    </div>
</template>

<script>
  export default {
    name: "AutoComplete",
    data() {
      return {
        search: '',
        results: [],
        isOpen: false
      };
    },
    props: {
      items: {
        type: Array,
        required: false,
        default: () => [],
      },
      novoValor: {
        type: String,
        default: ''
      }
    },
    methods: {
      onChange() {
        this.isOpen = true;
        this.filterResults();
      },
      filterResults() {
        this.results = this.items.filter(item => item.toLowerCase().indexOf(this.search.toLowerCase()) > -1);
      },
      setResult(result) {
        this.search = result;
        this.isOpen = false;
      },
    },
    watch: {
      search(search) {
        this.$emit('recebeValor', search);
        debugger;
        if (search.length > 6) {
          this.$emit('alterarValor', search)
        } else if (search.length === 3 || search.length === 6) {
          this.$emit('buscarValores', search)
        }
      },
      novoValor(novoValor) {
        debugger;
        this.search = novoValor
      }
    }
  }
</script>

<style scoped>
    .autocomplete {
        position: relative;
        width: 130px;
    }

    .autocomplete-results {
        padding: 0;
        margin: 0;
        border: 1px solid #eeeeee;
        height: 120px;
        overflow: auto;
    }

    .autocomplete-result {
        list-style: none;
        text-align: left;
        padding: 4px 2px;
        cursor: pointer;
    }

    .autocomplete-result:hover {
        background-color: #4AAE9B;
        color: white;
    }
</style>