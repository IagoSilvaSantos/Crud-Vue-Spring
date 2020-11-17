<template>
  <div class="q-pa-md">
    <q-layout>
      <q-header elevated class="info">
        <q-toolbar>
          <q-toolbar-title>Produtos-Consulta</q-toolbar-title>
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
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="filtro.nome" label="Nome" />
                  </div>

                  <div
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="filtro.descricao" label="Descrição" />
                  </div>

                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <select class="select-list" v-model="filtro.categorias">
                      <option>Escolha Categoria</option>
                      <option
                        class="select-option"
                        v-for="categoria in listCategorias"
                        :key="categoria.id"
                        v-bind:value="categoria"
                      >
                        {{ categoria.nome }}
                      </option>
                    </select>
                  </div>

                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <select class="select-list" v-model="filtro.estoque">
                      <option>Escolha Estoque</option>
                      <option
                        class="select-option"
                        v-for="aux_estoque in listEstoques"
                        :key="aux_estoque.id"
                        v-bind:value="aux_estoque"
                      >
                        {{ aux_estoque.id }}
                      </option>
                    </select>
                  </div>

                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <select class="select-list" v-model="filtro.marcas">
                      <option>Escolha Marcas</option>
                      <option
                        class="select-option"
                        v-for="aux_marcas in listMarcas"
                        :key="aux_marcas.id"
                        v-bind:value="aux_marcas"
                      >
                        {{ aux_marcas.nome }}
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
                    <q-input v-model="filtro.valorVenda" label="Valor Venda" />
                  </div>
                  <div
                    class="col-sm-4 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input
                      v-model="filtro.valorCompra"
                      label="Valor Compra"
                    />
                  </div>
                </div>
                <br />
              </q-card-section>

              <q-card-actions align="left">
                <q-btn push @click="handleFiltro" color="primary">Consultar</q-btn>

                <q-btn push @click="handleFiltroLimpar" color="primary">Limpar</q-btn>

                <q-btn push color="primary" to="/">Voltar</q-btn>

                <q-btn push color="primary" size="14px" to="/produtos-cadastro"
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
                  <td>{{ item.categorias.nome }}</td>
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
      options: ['Todos', 'Google', 'Facebook', 'Twitter', 'Apple', 'Oracle'],
      lista: [],
      categoriaFiltro: '',
      listCategorias: [],
      listEstoques: [],
      listMarcas: [],
      filtro: {
        id: '',
        descricao: '',
        nome: '',
        categorias: 'Escolha Categoria',
        estoque: 'Escolha Estoque',
        marcas: 'Escolha Marcas',
        valorVenda: '',
        valorCompra: ''
      },
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
          name: 'categorias.nome',
          label: 'Categoria'
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
        .delete(`produtos/${id}`)
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
    handleUpdate (id) {
      this.$router.push(`produtos-cadastro/${id}`)
    },
    handleFiltro () {
      if (this.filtro.id) {
        this.lista = this.lista.filter(
          (item) => item.id === parseInt(this.filtro.id)
        )
      }
      if (this.filtro.nome) {
        this.lista = this.lista.filter((item) =>
          item.nome.includes(this.filtro.nome)
        )
      }
      if (this.filtro.descricao) {
        this.lista = this.lista.filter((item) =>
          item.descricao.includes(this.filtro.descricao)
        )
      }

      if (this.filtro.categorias && this.filtro.categorias !== 'Escolha Categoria') {
        this.lista = this.lista.filter((item) =>
          item.categorias.nome.includes(this.filtro.categorias.nome)
        )
      }

      if (this.filtro.estoque && this.filtro.estoque !== 'Escolha Estoque') {
        this.lista = this.lista.filter((item) =>
          item.estoque.id.includes(this.filtro.estoque.id)
        )
      }

      if (this.filtro.estoque && this.filtro.estoque !== 'Escolha Marcas') {
        this.lista = this.lista.filter((item) =>
          item.estoque.marcas.includes(this.filtro.estoque.marcas)
        )
      }

      if (this.filtro.valorVenda && this.filtro.valorVenda) {
        this.lista = this.lista.filter((item) =>
          item.valorVenda === parseInt(this.filtro.valorVenda)
        )
      }

      if (this.filtro.valorCompra && this.filtro.valorCompra) {
        this.lista = this.lista.filter((item) =>
          item.valorCompra === parseInt(this.filtro.valorCompra)
        )
      }
    },
    async handleFiltroLimpar () {
      this.filtro = {
        id: '',
        descricao: '',
        nome: '',
        categorias: 'Escolha Categoria',
        marcas: 'Escolha Marcas',
        estoque: 'Escolha Estoque'
      }
      await this.loadLista()
    },
    async loadLista () {
      const response = await api.get('produtos')
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
      const responseMarcas = await api.get('marcas')
      if (responseMarcas?.data?.length > 0) {
        this.listMarcas = responseMarcas.data
      }
      const responseEstoque = await api.get('estoque')
      if (responseEstoque?.data?.length > 0) {
        this.listEstoques = responseEstoque.data
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
