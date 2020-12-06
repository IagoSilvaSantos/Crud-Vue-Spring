<template>
  <div class="q-pa-md">
    <q-layout>
      <q-header elevated class="info">
        <q-toolbar>
          <q-toolbar-title>Vendas-Consulta</q-toolbar-title>
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
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="filtro.id" label="Código" />
                  </div>

                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <select class="select-list" v-model="filtro.produtos">
                      <option>Escolha Produtos</option>
                      <option
                        class="select-option"
                        v-for="produto in listProdutos"
                        :key="produto.id"
                        v-bind:value="produto"
                      >
                        {{ produto.nome }}
                      </option>
                    </select>
                  </div>

                </div>
                <br />
                <div class="row">
                  <div
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="filtro.valor" label="Valor Venda" />
                  </div>
                  <div
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input
                      v-model="filtro.data"
                      type="date"
                      label="Data Venda"
                    />
                  </div>
                </div>
                <br />
              </q-card-section>

              <q-card-actions align="left">
                <q-btn push @click="handleFiltro" color="primary">Consultar</q-btn>

                <q-btn push @click="handleFiltroLimpar" color="primary">Limpar</q-btn>

                <q-btn push color="primary" to="/">Voltar</q-btn>

                <q-btn push color="primary" size="14px" to="/vendas-cadastro"
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
                  <td>{{ item.produtos.nome }}</td>
                  <td>{{ item.valor }}</td>
                  <td>{{ formatDate(item.data) }}</td>
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
        </template>
      </q-page>
    </q-layout>
  </div>
</template>
<script>
import api from '../../services/api'

export default {
  data () {
    return {
      model: null,
      text: '',
      lista: [],
      categoriaFiltro: '',
      listCategorias: [],
      listProdutos: [],
      filtro: {
        id: '',
        descricao: '',
        nome: '',
        produtos: 'Escolha Produtos',
        valor: '',
        data: ''
      },
      columns: [
        {
          name: 'id',
          label: '#ID'
        },
        {
          name: 'produtos.nome',
          label: 'Produto'
        },
        {
          name: 'valor',
          label: 'Valor Venda'
        },
        {
          name: 'data',
          label: 'Data'
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
        .delete(`vendas/${id}`)
        .then(() => {
          const index = this.lista.findIndex((item) => item.id === id)
          const auxList = [...this.lista]
          auxList.splice(index, 1)
          this.lista = [...auxList]

          this.$q.notify({
            type: 'positive',
            message: 'deletado com sucesso.'
          })
        })
        .catch(() => {
          this.$q.notify({
            type: 'negative',
            message: 'Ops, ocorreu um erro ao deletar.'
          })
        }
        )
    },
    formatDate (data) {
      return data.substr(0, 10).split('-').reverse().join('/')
    },
    handleUpdate (id) {
      this.$router.push(`vendas-cadastro/${id}`)
    },
    async handleFiltro () {
      await this.loadLista()

      if (this.filtro.id) {
        this.lista = this.lista.filter(
          (item) => item.id === parseInt(this.filtro.id)
        )
      }
      if (this.filtro.produtos && this.filtro.produtos !== 'Escolha Produtos') {
        this.lista = this.lista.filter((item) =>
          item.produtos.nome.includes(this.filtro.produtos.nome)
        )
      }

      if (this.filtro.valor && this.filtro.valor) {
        this.lista = this.lista.filter((item) =>
          item.valor === parseInt(this.filtro.valor)
        )
      }

      if (this.filtro.data && this.filtro.data) {
        this.lista = this.lista.filter((item) =>
          item.data.includes(this.filtro.data)
        )
      }
    },
    async handleFiltroLimpar () {
      this.filtro = {
        id: '',
        valor: '',
        data: '',
        produtos: 'Escolha Produtos'
      }
      await this.loadLista()
    },
    async loadLista () {
      const response = await api.get('vendas')
      if (response.data.length > 0) {
        const auxList = response.data.map((item) => {
          return { ...item, acoes: '' }
        })
        this.lista = [...auxList]
      }
    }
  },
  mounted () {
    const load = async () => {
      const responseCategorias = await api.get('categorias')
      if (responseCategorias?.data?.length > 0) {
        this.listCategorias = responseCategorias.data
      }

      const responseProdutos = await api.get('produtos')
      if (responseProdutos?.data?.length > 0) {
        this.listProdutos = responseProdutos.data
      }

      await this.loadLista()
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

.select-list {
  font-size: 18px;
  padding: 14px 14px;
  margin-top: 10px;
  border-radius: 4px;
  outline: 0;
  border: 1px solid lightgray;
  width: 100%;
}

.select-option {
  padding: 8px 14px;
}
</style>
