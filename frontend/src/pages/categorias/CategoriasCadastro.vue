<template>
  <div class="q-pa-md">
    <q-layout>
      <q-header elevated class="info">
        <q-toolbar>
          <q-toolbar-title>Categorias-Cadastro</q-toolbar-title>
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
                    <q-input
                      :disable="true"
                      v-model="categorias.id"
                      label="ID"
                    />
                  </div>
                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="categorias.nome" label="Nome" />
                  </div>
                </div>
                <div class="row">
                  <div
                    class="col-sm-12 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="categorias.descricao" label="Descrição" />
                  </div>
                </div>
              </q-card-section>

              <q-card-actions align="left">
                <q-btn push @click="handleSubmit" color="primary">Salvar</q-btn>

                <q-btn push color="primary">Limpar</q-btn>

                <q-btn push color="primary" to="/categorias-consulta"
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
      categorias: {
        nome: '',
        descricao: '',
        id: undefined
      }
    }
  },
  methods: {
    async handleSubmit () {
      const id = this.$route.params.id
      const method = id ? 'put' : 'post'
      const tipo = id ? `/${id}` : ''
      try {
        await api[method](`categorias${tipo}`, this.categorias)
        alert('cadastrado com sucesso')
        this.$router.push('/categorias-consulta')
      } catch (err) {
        alert('erro ' + err)
      }
    }
  },
  mounted () {
    const load = async () => {
      const id = this.$route.params.id
      if (id) {
        const response = await api.get(`categorias/${id}`)
        this.categorias = response.data
      }
    }
    load()
  }
}
</script>
