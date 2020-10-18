<template>
  <div class="q-pa-md">

    <q-layout >
      <q-header elevated class="info">
        <q-toolbar>

          <q-toolbar-title>Finanças-Consulta</q-toolbar-title>

        </q-toolbar>
      </q-header>
      <br><br>
<q-page class="container q-pa-xs">
<br>
    <div class="row">
        <div class="col-sm-12 col-xs-12">
     <q-card class="my-card">
      <q-card-section class="bg-grey-1 text-black">
        <div class="row">
        <div class="col-sm-3 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-input v-model="text" label="Código" />
        </div>
                <div class="col-sm-3 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-input v-model="text" label="Fornecedor" />
        </div>
        <div class="col-sm-3 col-xs-12" style="padding: 0px 10px 10px 10px">
               <q-input v-model="text" label="Valor do documento" />
        </div>
            <div class="col-sm-3 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-input v-model="text" label="Data de emissão" />
            </div>
        </div>
        <br>
        <div class="row">

                    <div class="col-sm-3 col-xs-12" style="padding: 0px 10px 10px 10px">
               <q-input v-model="text" label="Data de recebimento" />
        </div>
                            <div class="col-sm-3 col-xs-12" style="padding: 0px 10px 10px 10px">
               <q-input v-model="text" label="Data de vencimento" />
        </div>
        </div>
        <br>
      </q-card-section>

 <q-card-actions align="left">
        <q-btn push color="primary">Consultar</q-btn>

        <q-btn push color="primary">Limpar</q-btn>

        <q-btn push color="primary" to="/">Voltar</q-btn>

        <q-btn push color="primary" to="/financas-cadastro">Cadastrar Nova Conta</q-btn>
      </q-card-actions>
    </q-card>

        </div>
    </div>
    <br>
</q-page>
    </q-layout>
    </div>
</template>
<script>
export default {
  data () {
    return {
      model: null,
      options: [
        'Todos', 'Google', 'Facebook', 'Twitter', 'Apple', 'Oracle'
      ]
    }
  }
}
</script>
