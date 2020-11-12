<template>
  <div class="q-pa-md">

    <q-layout >
      <q-header elevated class="info">
        <q-toolbar>

          <q-toolbar-title>Produtos-Consulta</q-toolbar-title>

        </q-toolbar>
      </q-header>
      <br><br>
<q-page class="container q-pa-xs">
<br>
    <div class="row">
        <div class="col-sm-12 col-xs-12">
     <q-card class="my-card">
      <q-card-section class="bg-grey-1 text-black">
        <div class="row">
        <div class="col-sm-2 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-input v-model="text" label="Código" />
        </div>
        <div class="col-sm-5 col-xs-12" style="padding: 0px 10px 10px 10px">
               <q-select outlined v-model="model" :options="options" label="Categoria" />
        </div>
            <div class="col-sm-5 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-select outlined v-model="model" :options="options" label="Produto" />
            </div>
        </div>
        <br>
        <div class="row">
        <div class="col-sm-4 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-input v-model="text" label="Marca" />
        </div>
        <div class="col-sm-4 col-xs-12" style="padding: 0px 10px 10px 10px">
               <q-input v-model="text" label="Valor" />
        </div>
            <div class="col-sm-4 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-input v-model="text" label="Fornecedor" />
            </div>
        </div>
        <br>

      </q-card-section>

      <q-card-actions align="left">
        <q-btn push color="primary">Consultar</q-btn>

        <q-btn push color="primary" to="/">Limpar</q-btn>

        <q-btn push color="primary" to="/">Voltar</q-btn>

        <q-btn push color="primary" size="14px" to="/produtos-cadastro">Novo</q-btn>
        <button type="button" @click="handleAddCategory" >Novo category teste</button>

      </q-card-actions>
    </q-card>

        </div>
    </div>
    <br>
      <div class="row">
      <div class="col-sm-12 col-xs-12">
    <q-table
      title="Resultado"
      :data="lista"
      :columns="columns"
      row-key="name"
    />
  </div>
  </div>
  <br>
</q-page>
</q-layout >
 </div>
</template>
<script>

import api from '../../services/api'

export default {
  data () {
    return {
      model: null,
      options: [
        'Todos', 'Google', 'Facebook', 'Twitter', 'Apple', 'Oracle'
      ],
      lista: [],
      columns: [
        {
          descricao: 'descricao',
          required: true,
          label: 'Código',
          align: 'left',
          field: row => row.descricao,
          format: val => `${val}`,
          sortable: true
        },
        { name: 'id', align: 'center', label: 'Id', field: 'id', sortable: true },
        { name: 'nome', label: 'Nome', field: 'nome', sortable: true }
        // { name: 'valor_de_compra', label: 'Valor de compra', field: 'carbs' },
        // { name: 'valor_de_venda', label: 'Valor de venda', field: 'protein' },
        // { name: 'quantidade_no_estoque', label: 'Quantidade no estoque', field: 'sodium' },
        // { name: 'data_da_compra', label: 'Data da compra', field: 'calcium', sortable: true, sort: (a, b) => parseInt(a, 10) - parseInt(b, 10) },
        // { name: 'fornecedor', label: 'Fornecedor', field: 'iron', sortable: true, sort: (a, b) => parseInt(a, 10) - parseInt(b, 10) }
      ],
      data: [
        {
          name: '1',
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0,
          sodium: 87,
          calcium: '14%',
          iron: '1%'
        },
        {
          name: '2',
          calories: 237,
          fat: 9.0,
          carbs: 37,
          protein: 4.3,
          sodium: 129,
          calcium: '8%',
          iron: '1%'
        },
        {
          name: '3',
          calories: 262,
          fat: 16.0,
          carbs: 23,
          protein: 6.0,
          sodium: 337,
          calcium: '6%',
          iron: '7%'
        },
        {
          name: '4',
          calories: 305,
          fat: 3.7,
          carbs: 67,
          protein: 4.3,
          sodium: 413,
          calcium: '3%',
          iron: '8%'
        },
        {
          name: '5',
          calories: 356,
          fat: 16.0,
          carbs: 49,
          protein: 3.9,
          sodium: 327,
          calcium: '7%',
          iron: '16%'
        },
        {
          name: '6',
          calories: 375,
          fat: 0.0,
          carbs: 94,
          protein: 0.0,
          sodium: 50,
          calcium: '0%',
          iron: '0%'
        },
        {
          name: '7',
          calories: 392,
          fat: 0.2,
          carbs: 98,
          protein: 0,
          sodium: 38,
          calcium: '0%',
          iron: '2%'
        },
        {
          name: '8',
          calories: 408,
          fat: 3.2,
          carbs: 87,
          protein: 6.5,
          sodium: 562,
          calcium: '0%',
          iron: '45%'
        },
        {
          name: '9',
          calories: 452,
          fat: 25.0,
          carbs: 51,
          protein: 4.9,
          sodium: 326,
          calcium: '2%',
          iron: '22%'
        },
        {
          name: '10',
          calories: 518,
          fat: 26.0,
          carbs: 65,
          protein: 7,
          sodium: 54,
          calcium: '12%',
          iron: '6%'
        }
      ]
    }
  },
  methods: {
    async handleAddCategory () {
      console.log('clickou')
      await api.post('categorias', {
        nome: 'cat x',
        descricao: 'categoria x'
      }).then(response => {
        alert('cadastrado com sucesso')
        console.log('cadastro: ', response)
      }).catch(err => alert('erro ' + err))
    }
  },
  mounted () {
    const load = async () => {
      const response = await api.get('categorias')
      this.lista = response.data

      const responseDelete = await api.get(`categorias/${4}`)
      if (responseDelete.data) {
        await api.delete(`categorias/${4}`)
      }
    }
    load()
  }
}
</script>
