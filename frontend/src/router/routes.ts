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
      { path: 'produtos-cadastro/:id', component: () => import('pages/produtos/ProdutosCadastro.vue') },
      { path: 'vendas-consulta', component: () => import('pages/vendas/VendasConsulta.vue') },
      { path: 'vendas-cadastro', component: () => import('pages/vendas/VendasCadastro.vue') },
      { path: 'compras-consulta', component: () => import('pages/compras/ComprasConsulta.vue') },
      { path: 'compras-cadastro', component: () => import('pages/compras/ComprasCadastro.vue') },
      { path: 'categorias-consulta', component: () => import('pages/categorias/CategoriasConsulta.vue') },
      { path: 'categorias-cadastro', component: () => import('pages/categorias/CategoriasCadastro.vue') },
      { path: 'categorias-cadastro/:id', component: () => import('pages/categorias/CategoriasCadastro.vue') },
      // { path: 'estoque-consulta', component: () => import('pages/estoque/EstoqueConsulta.vue') },
      { path: 'financas-consulta', component: () => import('pages/financas/FinancasConsulta.vue') },
      { path: 'financas-cadastro', component: () => import('pages/financas/FinancasCadastro.vue') },
      { path: 'estoque-consulta', component: () => import('pages/estoques/EstoquesConsulta.vue') },
      { path: 'estoque-cadastro', component: () => import('pages/estoques/EstoquesCadastro.vue') },
      { path: 'estoque-cadastro/:id', component: () => import('pages/estoques/EstoquesCadastro.vue') },
      { path: 'marcas-consulta', component: () => import('pages/marcas/MarcasConsulta.vue') },
      { path: 'marcas-cadastro', component: () => import('pages/marcas/MarcasCadastro.vue') },
      { path: 'marcas-cadastro/:id', component: () => import('pages/marcas/MarcasCadastro.vue') }
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
