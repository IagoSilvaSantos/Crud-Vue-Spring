<template>
  <div class="q-pa-md">
    <q-layout>
      <q-header elevated class="info">
        <q-toolbar>
          <q-toolbar-title>Produtos-Cadastro</q-toolbar-title>
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
                    <q-input :disable="true" v-model="produtos.id" label="ID" />
                  </div>
                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="produtos.nome" label="Nome" />
                  </div>
                </div>
                <div class="row">
                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="produtos.descricao" label="Descrição" />
                  </div>
                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input
                      v-model="produtos.valorCompra"
                      label="Valor de Compra"
                      type="number"
                    />
                  </div>
                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input
                      v-model="produtos.valorVenda"
                      label="Valor de Venda"
                    />
                  </div>
                </div>
                <br />

                <div class="row">
                  <div
                    class="col-sm-5 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <select class="select-list" v-model="produtos.marcas">
                      <option>Escolha marcas</option>
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

                  <div
                    class="col-sm-5 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <select class="select-list" v-model="produtos.categorias">
                      <option>Escolha categorias</option>
                      <option
                        class="select-option"
                        v-for="aux_categoria in listCategorias"
                        :key="aux_categoria.id"
                        v-bind:value="aux_categoria"
                      >
                        {{ aux_categoria.nome }}
                      </option>
                    </select>
                  </div>

                  <div
                    class="col-sm-5 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <select class="select-list" v-model="produtos.estoque">
                      <option>Escolha estoque</option>
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

                </div>
              </q-card-section>

              <q-card-actions align="left">
                <q-btn push @click="handleSubmit" color="primary">Salvar</q-btn>

                <q-btn push color="primary">Limpar</q-btn>

                <q-btn push color="primary" to="/produtos-consulta"
                  >Voltar</q-btn
                >
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
export default {
  data () {
    return {
      model: null,
      listCategorias: [],
      listEstoques: [],
      listMarcas: [],
      produtos: {
        id: undefined,
        descricao: '',
        nome: '',
        valorCompra: '',
        valorVenda: '',
        estoque: 'Escolha estoque',
        categorias: 'Escolha categorias',
        marcas: 'Escolha marcas'
      },
      options: ['Todos', 'Google', 'Facebook', 'Twitter', 'Apple', 'Oracle']
    }
  },
  methods: {
    async handleSubmit () {
      const id = this.$route.params.id
      const method = id ? 'put' : 'post'
      const tipo = id ? `/${id}` : ''
      try {
        await api[method](`produtos${tipo}`, {
          ...this.produtos,
          valorCompra: parseFloat(this.produtos.valorCompra),
          valorVenda: parseFloat(this.produtos.valorVenda)
        })

        this.$q.notify({
          type: 'positive',
          message: 'cadastrado com sucesso.'
        })
        this.$router.push('/produtos-consulta')
      } catch (err) {
        this.$q.notify({
          type: 'negativo',
          message: 'Erro ao cadastrar. -> ' + err
        })
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

      const id = this.$route.params.id
      if (id) {
        const response = await api.get(`produtos/${id}`)
        this.produtos = response.data
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
