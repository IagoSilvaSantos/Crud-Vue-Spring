<template>
  <div class="q-pa-md">
    <q-layout>
      <q-header elevated class="info">
        <q-toolbar>
          <q-toolbar-title>Fornecedores-Cadastro</q-toolbar-title>
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
                      v-model="fornecedores.id"
                      label="ID"
                    />
                  </div>
                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="fornecedores.razaoSocial" label="Razao Social" />
                  </div>
                </div>
                <div class="row">
                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="fornecedores.nomeFantasia" label="Nome Fantasia" />
                  </div>
                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="fornecedores.representante" label="Representante" />
                  </div>
                </div>

                <div class="row">
                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="fornecedores.cnpj" label="CNPJ" />
                  </div>
                  <div
                    class="col-sm-6 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="fornecedores.telefone" label="Telefone" />
                  </div>
                </div>
              </q-card-section>

              <q-card-actions align="left">
                <q-btn push @click="handleSubmit" color="primary">Salvar</q-btn>

                <q-btn push color="primary">Limpar</q-btn>

                <q-btn push color="primary" to="/fornecedores-consulta"
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
      fornecedores: {
        razaoSocial: '',
        telefone: '',
        representante: '',
        cnpj: '',
        nomeFantasia: '',
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
        await api[method](`fornecedores${tipo}`, this.fornecedores)

        this.$q.notify({
          type: 'positive',
          message: 'cadastrado com sucesso.'
        })
        this.$router.push('/fornecedores-consulta')
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
      const id = this.$route.params.id
      if (id) {
        const response = await api.get(`fornecedores/${id}`)
        this.fornecedores = response.data
      }
    }
    load()
  }
}
</script>
