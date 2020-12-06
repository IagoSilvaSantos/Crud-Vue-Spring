<template>
  <div class="q-pa-md">
    <q-layout>
      <q-header elevated class="info">
        <q-toolbar>
          <q-toolbar-title>Vendas-Cadastro</q-toolbar-title>
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
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input :disable="true" v-model="vendas.id" label="ID" />
                  </div>
                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input
                      step="1"
                      type="number"
                      v-model="vendas.quantidade"
                      label="Quantidade"
                    />
                  </div>
                </div>
                <div class="row">
                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input
                      step="1"
                      type="number"
                      v-model="vendas.valor"
                      label="Valor"
                    />
                  </div>
                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input
                      v-model="vendas.data"
                      label="Data da Compra"
                      type="date"
                    />
                  </div>
                </div>
                <br />

                <div class="row">
                  <div
                    class="col-sm-5 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <select class="select-list" v-model="vendas.produtos">
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
              </q-card-section>

              <q-card-actions align="left">
                <q-btn push @click="handleSubmit" color="primary">Salvar</q-btn>

                <q-btn push @click="handleClear" color="primary">Limpar</q-btn>

                <q-btn push color="primary" to="/vendas-consulta">Voltar</q-btn>
              </q-card-actions>
            </q-card>
          </div>
        </div>
        <br />
      </q-page>
    </q-layout>
  </div>
</template>
<script>
import api from '../../services/api'
import { getUser } from '../../services/auth'
export default {
  data () {
    return {
      model: null,
      listProdutos: [],
      vendas: {
        id: undefined,
        quantidade: '',
        valor: '',
        data: '',
        usuarios: getUser() ? getUser() : '',
        produtos: 'Escolha Produtos'
      }
    }
  },
  methods: {
    async handleSubmit () {
      const id = this.$route.params.id
      const method = id ? 'put' : 'post'
      const tipo = id ? `/${id}` : ''
      try {
        await api[method](`vendas${tipo}`, {
          ...this.vendas,
          usuarios: getUser(),
          valor: parseFloat(this.vendas.valor)
        })

        this.$q.notify({
          type: 'positive',
          message: 'cadastrado com sucesso.'
        })
        this.$router.push('/vendas-consulta')
      } catch (err) {
        this.$q.notify({
          type: 'negativo',
          message: 'Erro ao cadastrar. -> ' + err
        })
      }
    },
    async handleClear () {
      const id = this.$route.params.id
      if (id) {
        const response = await api.get(`vendas/${id}`)
        this.vendas = {
          ...response.data,
          data: this.formatDate(response.data.data)
        }
      } else {
        this.vendas = {
          id: undefined,
          quantidade: '',
          valor: '',
          data: '',
          usuarios: getUser() ? getUser() : '',
          produtos: 'Escolha Produtos'
        }
      }
    },
    formatDate (data) {
      const newDate = data.substr(0, 10).split('-').reverse().join('/')
      const day = newDate.split('/')[0]
      const month = newDate.split('/')[1]
      const ano = newDate.split('/')[2]
      return ano + '-' + month + '-' + day
    }
  },
  mounted () {
    const load = async () => {
      const responseProdutos = await api.get('produtos')
      if (responseProdutos?.data?.length > 0) {
        this.listProdutos = responseProdutos.data
      }

      const id = this.$route.params.id
      if (id) {
        const response = await api.get(`vendas/${id}`)
        this.vendas = {
          ...response.data,
          data: this.formatDate(response.data.data)
        }
      }

      await this.loadLista()
    }
    load()
  }
}
</script>

<style lang="stylus" scoped>
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
