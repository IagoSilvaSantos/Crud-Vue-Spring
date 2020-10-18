<template>
<q-page class="container">

    <div class="row" style="padding: 5% 0% 0% 30%">
        <div class="col-sm-7 col-xs-12">
           <q-card class="my-card">
              <q-card-section class="bg-grey-1 text-black">
                <div class="text-primary"> <h6>Olá! Digite o usuário e senha para continuar</h6></div>
                  <div class="q-gutter-md">
                      <q-input v-model="text" label="Digite seu usuário" />
                  </div>
                  <br>
                 <div class="q-gutter-md">
                      <q-input v-model="text" label="Digite sua senha" />
                 </div>
                   <br>
              </q-card-section>

        <q-card-actions align="around">
            <q-btn push color="primary" class="full-width">Continuar</q-btn>
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
export default {
  data () {
    return {
      model: null,
      options: [
      ]
    }
  }
}
</script>
