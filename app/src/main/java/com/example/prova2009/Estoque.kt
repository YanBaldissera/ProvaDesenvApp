package com.example.prova2009

class Estoque {

    companion object{
        private val produtos = mutableListOf<Produto>()

        fun adicionarProduto(produto: Produto): Boolean{
            produtos.add(produto)
            return true
        }

        fun calcularValorEstoque(): Double {
            return produtos.sumOf{ it.preco * it.quantidade}
        }

        fun calcularQuantidadeProdutos(): Int {
            return produtos.sumOf { it.quantidade }
        }

        fun listarProduto(): List<Produto>{
            return produtos
        }
    }
}