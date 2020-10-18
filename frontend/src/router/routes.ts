import { RouteConfig } from 'vue-router'

const routes: RouteConfig[] = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '/', component: () => import('pages/inicio/inicio.vue') },
      { path: 'usuarios-login', component: () => import('pages/usuarios/UsuariosLogin.vue') },
      { path: 'usuarios-cadastro', component: () => import('pages/usuarios/UsuariosCadastro.vue') },
      { path: 'produtos-consulta', component: () => import('pages/produtos/ProdutosConsulta.vue') },
      { path: 'produtos-cadastro', component: () => import('pages/produtos/ProdutosCadastro.vue') },
      { path: 'vendas-consulta', component: () => import('pages/vendas/VendasConsulta.vue') },
      { path: 'vendas-cadastro', component: () => import('pages/vendas/VendasCadastro.vue') },
      { path: 'compras-consulta', component: () => import('pages/compras/ComprasConsulta.vue') },
      { path: 'compras-cadastro', component: () => import('pages/compras/ComprasCadastro.vue') },
      { path: 'estoque-consulta', component: () => import('pages/estoque/EstoqueConsulta.vue') },
      { path: 'financas-consulta', component: () => import('pages/financas/FinancasConsulta.vue') },
      { path: 'financas-cadastro', component: () => import('pages/financas/FinancasCadastro.vue') }
    ]
  }
]

// Always leave this as last one
if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '*',
    component: () => import('pages/Error404.vue')
  })
}

export default routes
