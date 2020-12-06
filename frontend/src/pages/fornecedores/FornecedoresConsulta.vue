<template>
  <div class="q-pa-md">
    <q-layout>
      <q-header elevated class="info">
        <q-toolbar>
          <q-toolbar-title>Fornecedores-Consulta</q-toolbar-title>
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
                    <q-input v-model="filtro.id" label="Id" />
                  </div>
                </div>
                <br />
                <div class="row">
                  <div
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="filtro.razaoSocial" label="RazaoSocial" />
                  </div>
                  <div
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input
                      v-model="filtro.nomeFantasia"
                      label="Nome Fantasia"
                    />
                  </div>
                </div>
                <div class="row">
                  <div
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input
                      v-model="filtro.representante"
                      label="Representante"
                    />
                  </div>

                  <div
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="filtro.cnpj" label="CNPJ" />
                  </div>

                  <div
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="filtro.telefone" label="Telefone" />
                  </div>
                </div>
                <br />
              </q-card-section>

              <q-card-actions align="left">
                <q-btn push @click="handleFiltro" color="primary"
                  >Consultar</q-btn
                >

                <q-btn push @click="handleFiltroLimpar" color="primary"
                  >Limpar</q-btn
                >

                <q-btn push color="primary" to="/">Voltar</q-btn>

                <q-btn
                  push
                  color="primary"
                  size="14px"
                  to="/fornecedores-cadastro"
                  >Novo</q-btn
                >
              </q-card-actions>
            </q-card>
          </div>
        </div>
        <br />

        <template class="container q-pa-xs tabela-grid">
          <div class="row">
            <div class="col-sm-12 col-xs-12">
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
                      <td>{{ item.razaoSocial }}</td>
                      <td>{{ item.nomeFantasia }}</td>
                      <td>{{ item.representante }}</td>
                      <td>{{ item.cnpj }}</td>
                      <td>{{ item.telefone }}</td>
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
                    <tr v-if="lista.length == 0">
                      <p style="margin: 0 auto; padding: 40px">Sem elementos</p>
                    </tr>
                  </tbody>
                  <tfoot>
                    <p style="text-align: center; padding: 5px">
                      Total de elementos {{ lista.length }}.
                    </p>
                  </tfoot>
                </table>
              </div>
            </div>
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
      filtro: {
        id: '',
        descricao: '',
        nome: ''
      },
      columns: [
        {
          name: 'id',
          label: '#ID'
        },
        {
          name: 'razaoSocial',
          label: 'RazaoSocial'
        },
        {
          name: 'nomeFantasia',
          label: 'NomeFantasia'
        },
        {
          name: 'representante',
          label: 'Representante'
        },
        {
          name: 'cnpj',
          label: 'CNPJ'
        },
        {
          name: 'telefone',
          label: 'Telefone'
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
        .delete(`fornecedores/${id}`)
        .then(() => {
          const index = this.lista.findIndex((item) => item.id === id)
          const auxList = [...this.lista]
          auxList.splice(index, 1)
          this.lista = [...auxList]
          this.$q.notify({
            type: 'positive',
            message: 'Deletado com sucesso.'
          })
        })
        .catch(() => {
          this.$q.notify({
            type: 'negative',
            message: 'Ops, ocorreu um erro, esse dado pertence a outro registro.'
          })
        })
    },
    handleUpdate (id) {
      this.$router.push(`fornecedores-cadastro/${id}`)
    },
    async handleFiltro () {
      let newFilter = '?filter=filter'

      if (this.filtro.id) {
        newFilter += `&id=${this.filtro.id}`
      }
      if (this.filtro.nomeFantasia) {
        newFilter += `&nomeFantasia=${this.filtro.nomeFantasia}`
      }
      if (this.filtro.razaoSocial) {
        newFilter += `&razaoSocial=${this.filtro.razaoSocial}`
      }
      if (this.filtro.representante) {
        newFilter += `&representante=${this.filtro.representante}`
      }
      if (this.filtro.telefone) {
        newFilter += `&telefone=${this.filtro.telefone}`
      }
      if (this.filtro.cnpj) {
        newFilter += `&cnpj=${this.filtro.cnpj}`
      }

      await this.loadLista(newFilter)
    },
    async handleFiltroLimpar () {
      this.filtro = {
        id: '',
        nomeFantasia: '',
        cnpj: '',
        razaoSocial: '',
        telefone: '',
        representante: ''
      }
      await this.loadLista()
    },
    async loadLista (newFilter = '') {
      const response = await api.get('fornecedores/filtrado'.concat(newFilter))
      console.log('response', response)
      if (response.data.length > 0) {
        const auxList = response.data.map((item) => {
          return { ...item, acoes: '' }
        })
        this.lista = [...auxList]
      } else this.lista = []
    }
  },
  mounted () {
    const load = async () => {
      await this.loadLista()
    }
    load()
  }
}
</script>

<style lang="stylus" scoped>

.tabela-grid {
  max-width 100vw;
  overflow-x: scroll;
}

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

th {
  border-left: 1px solid lightgray;
}

th:child-first {
  border-left: 0;
}

tr:nth-child(even) {
  background-color: #dddddd;
}

th, tfoot {
  background-color: #1976d2;
  color: white;
  border-radius: 6px;
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

tfoot {
  margin-top: 10px;
  width: 100%;
  margin: 0 auto;
}

.jtable {
  padding: 20px;
  background: white;
  color: black;
  border-radius: 4px;
  box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
}
</style>
