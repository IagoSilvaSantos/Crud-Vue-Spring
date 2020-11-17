<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="leftDrawerOpen = !leftDrawerOpen"
        />

        <q-toolbar-title> Sistema de Controle de Estoque </q-toolbar-title>

        <div>
          <span>Versão 1.0</span>

          <q-btn v-if="validAuthenticated()" style="margin-left: 10px;" @click="handleLogout" round color="primary" icon="directions"
            >Deslogar</q-btn
          >
        </div>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
      content-class="bg-primary"
    >
      <q-list>
        <q-item-label class="text-grey-1 text-subtitle2">
          <q-toolbar>
            <q-btn
              flat
              dense
              icon="menu"
              aria-label="Menu"
              class="text-grey-1 text-subtitle2"
              @click="leftDrawerOpen = !leftDrawerOpen"
            />
            <q-toolbar-title> Menu </q-toolbar-title>
          </q-toolbar>
        </q-item-label>
        <EssentialLink
          v-for="link in essentialLinks"
          :key="link.title"
          v-bind="link"
          class="bg-blue-10 text-grey-1 shadow-3 rounded-borders"
        />
      </q-list>
    </q-drawer>
    <q-footer>
      <q-toolbar> SistemaDeControleDeEstoque Copyright © 2020 </q-toolbar>
    </q-footer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import EssentialLink from 'components/EssentialLink'
import { logout, isAuthenticated } from '../services/auth'
export default {
  name: 'MainLayout',

  components: {
    EssentialLink
  },

  data () {
    return {
      leftDrawerOpen: false,
      essentialLinks: [
        {
          title: 'Início',
          icon: 'home',
          link: '/'
        },
        {
          title: 'Usuários',
          icon: 'person',
          link: '/usuarios-login'
        },
        {
          title: 'Categorias',
          icon: 'category',
          link: '/categorias-consulta'
        },
        {
          title: 'Produtos',
          icon: 'shopping_basket',
          link: '/produtos-consulta'
        },
        {
          title: 'Vendas',
          icon: ' local_offer',
          link: '/vendas-consulta'
        },
        {
          title: 'Compras',
          icon: 'shopping_cart',
          link: '/compras-consulta'
        },
        {
          title: 'Estoque',
          icon: 'storage',
          link: '/estoque-consulta'
        },
        {
          title: 'Marcas',
          icon: 'store',
          link: '/marcas-consulta'
        },
        {
          title: 'Finanças',
          icon: 'monetization_on',
          link: '/financas-consulta'
        }
      ]
    }
  },
  methods: {
    handleLogout () {
      this.$q.notify({
        type: 'positive',
        message: 'Deslogado com sucesso.'
      })
      this.$router.push('/usuarios-login')
      logout()
    },
    validAuthenticated () {
      if (isAuthenticated()) {
        return true
      }
      return false
    }

  }
}
</script>
<style>
body {
  background-color: #d9dade;
}
</style>
