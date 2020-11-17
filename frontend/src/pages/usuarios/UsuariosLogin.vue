<template>
<q-page class="container">

    <div class="row" style="padding: 5% 0% 0% 30%">
        <div class="col-sm-7 col-xs-12">
           <q-card class="my-card">
              <q-card-section class="bg-grey-1 text-black">
                <div class="text-primary"> <h6>Olá! Digite o usuário e senha para continuar</h6></div>
                  <div class="q-gutter-md">
                      <q-input v-model="usuarios.nome" label="Digite seu usuário" />
                  </div>
                  <br>
                 <div class="q-gutter-md">
                      <q-input v-model="usuarios.senha" label="Digite sua senha" />
                 </div>
                   <br>
              </q-card-section>

        <q-card-actions align="around">
            <q-btn push color="primary" @click="handleSubmit" class="full-width">Continuar</q-btn>
        </q-card-actions>
      <q-card-actions align="around">
           <q-btn push color="primary" class="full-width" to="usuarios-cadastro">Criar Conta</q-btn>
      </q-card-actions>
    <br><br>
  </q-card>

        </div>
    </div>
</q-page>
</template>
<script>
import api from '../../services/api'
import { login } from '../../services/auth'
export default {
  data () {
    return {
      model: null,
      usuarios: {
        id: undefined,
        nome: '',
        senha: ''
      }
    }
  },
  methods: {
    async handleSubmit () {
      try {
        await api.post('usuarios/logar', this.usuarios)
          .then(response => {
            console.log('response: ', response)
            if (!response.data) {
              this.$q.notify({
                type: 'negative',
                message: 'Dados de login invalido.'
              })
            } else {
              login('@tcc-user', response.data)

              this.$q.notify({
                type: 'positive',
                message: 'Logado com suceso.'
              })
              this.$router.push('/')
            }
          })
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
