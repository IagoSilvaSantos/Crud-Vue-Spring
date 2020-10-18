<template>
  <div class="q-pa-md">

    <q-layout >
      <q-header elevated class="info">
        <q-toolbar>

          <q-toolbar-title>Produtos-Cadastro</q-toolbar-title>

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
        <div class="col-sm-5 col-xs-12" style="padding: 0px 10px 10px 10px">
               <q-select outlined v-model="model" :options="options" label="Categoria" />
        </div>
            <div class="col-sm-5 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-select outlined v-model="model" :options="options" label="Produto" />
            </div>
        </div>
        <br>
        <div class="row">
                  <div class="col-sm-3 col-xs-12" style="padding: 0px 10px 10px 10px">
               <q-input v-model="text" label="Marca" />
        </div>
                  <div class="col-sm-3 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-input v-model="text" label="Fornecedor" />
        </div>
        <div class="col-sm-3 col-xs-12" style="padding: 0px 10px 10px 10px">
              <q-input v-model="text" label="Preço de custo" />
        </div>
        <div class="col-sm-3 col-xs-12" style="padding: 0px 10px 10px 10px">
               <q-input v-model="text" label="Valor de venda" />
        </div>
        </div>
        <br>
        <div class="row">
        <div class="col-sm-12 col-xs-12" style="padding: 0px 10px 10px 10px">
        <q-input v-model="text" label="Descrição" />
        </div>

        </div>
      </q-card-section>

      <q-card-actions align="left">
        <q-btn push color="primary">Salvar</q-btn>

        <q-btn push color="primary">Limpar</q-btn>

        <q-btn push color="primary" to="/produtos-consulta">Voltar</q-btn>
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
