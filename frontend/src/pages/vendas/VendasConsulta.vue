<template>
  <div class="q-pa-md">

    <q-layout >
      <q-header elevated class="info">
        <q-toolbar>

          <q-toolbar-title>Vendas-Consulta</q-toolbar-title>

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
        <div class="col-sm-2 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-input v-model="text" label="Código" />
        </div>
        <div class="col-sm-5 col-xs-12" style="padding: 0px 10px 10px 10px">
               <q-select outlined v-model="model" :options="options" label="Categoria" />
        </div>
            <div class="col-sm-5 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-select outlined v-model="model" :options="options" label="Produto" />
            </div>
        </div>
        <br>
        <div class="row">
                  <div class="col-sm-4 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-input v-model="text" label="Marca" />
        </div>
        <div class="col-sm-4 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-input v-model="text" label="Valor" />
        </div>
                    <div class="col-sm-4 col-xs-12" style="padding: 0px 10px 10px 10px">
               <q-input v-model="text" label="Data da Venda" />
        </div>
        </div>
        <br>

      </q-card-section>

      <q-card-actions align="left">
        <q-btn push color="primary">Consultar</q-btn>

        <q-btn push color="primary">Limpar</q-btn>

        <q-btn push color="primary" to="/">Voltar</q-btn>

        <q-btn push color="primary" to="/vendas-cadastro">Cadastrar Nova Venda</q-btn>
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
