<template>
  <div class="q-pa-md">
    <q-layout>
      <q-header elevated class="info">
        <q-toolbar>
          <q-toolbar-title>Categoria-Consulta</q-toolbar-title>
        </q-toolbar>
      </q-header>
      <br /><br />
      <q-page class="container q-pa-xs">
        <br />
        <div class="row">
          <div class="col-sm-12 col-xs-12">
            <q-card class="my-card">
              <q-card-section class="bg-grey-1 text-black">
                <div class="row">
                  <div
                    class="col-sm-2 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="text" label="Código" />
                  </div>
                  <div
                    class="col-sm-5 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-select
                      outlined
                      v-model="model"
                      :options="options"
                      label="Categoria"
                    />
                  </div>
                  <div
                    class="col-sm-5 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-select
                      outlined
                      v-model="model"
                      :options="options"
                      label="Produto"
                    />
                  </div>
                </div>
                <br />
                <div class="row">
                  <div
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="text" label="Marca" />
                  </div>
                  <div
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="text" label="Valor" />
                  </div>
                  <div
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="text" label="Fornecedor" />
                  </div>
                </div>
                <br />
              </q-card-section>

              <q-card-actions align="left">
                <q-btn push color="primary">Consultar</q-btn>

                <q-btn push color="primary" to="/">Limpar</q-btn>

                <q-btn push color="primary" to="/">Voltar</q-btn>

                <q-btn
                  push
                  color="primary"
                  size="14px"
                  to="/categorias-cadastro"
                  >Novo</q-btn
                >
              </q-card-actions>
            </q-card>
          </div>
        </div>
        <br />
        <template>
          <div class="q-pa-md jtable">
            <table>
              <thead>
                <tr :key="col" v-for="col in columns">
                  <th>{{ col.label }}</th>
                </tr>
              </thead>
              <tbody>
                <tr :key="item" v-for="item in lista">
                  <td>{{ item.id }}</td>
                  <td>{{ item.nome }}</td>
                  <td>{{ item.descricao }}</td>
                  <td>
                    <q-btn
                      size="sm"
                      color="red"
                      round
                      dense
                      @click="handleDelete(item.id)"
                      icon="remove"
                      style="margin-right: 10px"
                    />
                    <q-btn
                      size="sm"
                      color="orange"
                      round
                      dense
                      @click="handleUpdate(item.id)"
                      icon="edit"
                    />
                  </td>
                </tr>
              </tbody>
              <tfoot>
                <p style="text-align: center; padding: 5px;">
                  Total de elementos {{ lista.length }}.
                </p>
              </tfoot>
            </table>
          </div>
        </template></q-page
      ></q-layout
    >
  </div>
</template>

<script>
import api from '../../services/api'

export default {
  data () {
    return {
      model: null,
      text: '',
      options: ['Todos', 'Google', 'Facebook', 'Twitter', 'Apple', 'Oracle'],
      lista: [],
      columns: [
        {
          name: 'id',
          label: '#ID'
        },
        {
          name: 'nome',
          label: 'Nome'
        },
        {
          name: 'descricao',
          label: 'Descrição'
        },
        {
          name: 'acoes',
          label: 'Ações'
        }
      ]
    }
  },
  methods: {
    async handleDelete (id) {
      await api
        .delete(`categorias/${id}`)
        .then(() => {
          const index = this.lista.findIndex((item) => item.id === id)
          const auxList = [...this.lista]
          auxList.splice(index, 1)
          this.lista = [...auxList]
          alert('deletado com sucesso.')
        })
        .catch(() => alert('Ops, ocorreu um erro ao deletar.'))
    },
    handleUpdate (id) {
      this.$router.push(`categorias-cadastro/${id}`)
    }
  },
  mounted () {
    const load = async () => {
      const response = await api.get('categorias')
      if (response.data.length > 0) {
        const auxList = response.data.map((item) => {
          return { ...item, acoes: '' }
        })
        this.lista = [...auxList]
      }
    }
    load()
  }
}
</script>

<style lang="stylus" scoped>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  text-align: left;
  padding: 8px;
  flex: 1;
}

tr {
  display: flex;
  width: 100%;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

th, tfoot {
  background-color: #1976d2;
  color: white;
}

thead {
  display: flex;
  width: 100%;
  align-items: 'center';
  justify-content: 'space-between';
}

tbody {
  display: flex;
  width: 100% !important;
  flex-direction: column;
}

tfooter {
  margin-top: 10px;
  padding: 20px;
  display: flex;
  width: 100%;
  align-items: 'center';
  justify-content: 'center';
}

.jtable {
  padding: 20px;
  background: white;
  color: black;
  border-radius: 4px;
  box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
}
</style>
