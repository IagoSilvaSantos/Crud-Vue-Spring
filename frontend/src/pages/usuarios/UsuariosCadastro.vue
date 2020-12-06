<template>
  <div class="q-pa-md">
    <q-layout>
      <q-header elevated class="info">
        <q-toolbar>
          <q-toolbar-title>Usuario-Cadastro</q-toolbar-title>
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
                    class="col-sm-12 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input v-model="usuarios.nome" label="Novo Usuário" />
                  </div>
                </div>
                <br />
                <div class="row">
                  <div
                    class="col-sm-12 col-xs-12"
                    style="padding: 0px 10px 10px 10px"
                  >
                    <q-input type="password" v-model="usuarios.senha" label="Senha" />
                  </div>
                </div>
                <br />
              </q-card-section>

              <q-card-actions align="left">
                <q-btn :disable="!this.usuarios.nome || !this.usuarios.senha || this.usuarios.nome.length < 3 || this.usuarios.senha.length < 8" push @click="handleSubmit" color="primary">Criar</q-btn>

                <q-btn push color="primary" to="/">Cancelar</q-btn>

                <q-btn push color="primary" to="/usuarios-login">Voltar</q-btn>
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
      usuarios: {
        id: undefined,
        nome: '',
        senha: ''
      },
      options: ['Todos', 'Google', 'Facebook', 'Twitter', 'Apple', 'Oracle']
    }
  },
  methods: {
    async handleSubmit () {
      try {
        await api.post('usuarios', this.usuarios)
        alert('cadastrado com sucesso')
        // this.$router.push('/usuarios-login')
        this.$router.push('/')
      } catch (err) {
        alert('erro ' + err)
      }
    }

  },
  mounted () {
    console.log('iniciou')
  }
}
</script>
