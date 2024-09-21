package com.example.prova2009

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prova2009.ui.theme.Prova2009Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navegacao()
        }
    }
}

@Composable
fun Navegacao(){
    val navController = rememberNavController()
    val produtos = remember {
        mutableListOf<Produto>()
    }

    NavHost(navController = navController, startDestination = "tela1"){
        composable("tela1"){ cadastrarProdutos(navController, produtos) }
        composable("tela2") { ListaProdutos(navController, produtos)  }
        composable("tela3") { DetalhesProdutos(navController, produtos) }
    }
}

@Composable
fun cadastrarProdutos(navController: NavController, produtos: MutableList<Produto>){

    var nomeProduto by remember {
        mutableStateOf("")
    }

    var categoria by remember {
        mutableStateOf("")
    }

    var preco by remember {
        mutableStateOf("")
    }

    var quantidade by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        TextField(value = nomeProduto, onValueChange = { nomeProduto = it },
            label = { Text("Digite o nome do produto") },
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = categoria, onValueChange = { categoria = it },
            label = { Text("Digite a categoria do produto")},
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = preco,
            onValueChange = { preco = it },
            label = { Text("Preço") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade em Estoque") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        
        Button(onClick = {
            if (nomeProduto.isEmpty() || categoria.isEmpty() || preco.isEmpty() || quantidade.isEmpty()){
                Toast.makeText(context, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show()
            }else{
                val produto = Produto(
                    nomeProduto = nomeProduto,
                    categoria = categoria,
                    preco = preco.toDouble(),
                    quantidade = quantidade.toInt()
                )

                produtos.add(produto)
                Toast.makeText(context, "Produto adicionado", Toast.LENGTH_SHORT).show()
                nomeProduto = ""
                categoria = ""
                preco = ""
                quantidade = ""
                navController.navigate("tela2")
            }
        }) {
            Text(text = "Cadastrar")
        }

    }
}

@Composable
fun ListaProdutos(navController: NavController, produtos: List<Produto>){
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            items(produtos){ produto ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {

                    Text(text = "${produto.nomeProduto} (${produto.quantidade})")
                    
                    Button(onClick = { 
                        navController.navigate("tela3")
                    }) {
                        Text(text = "Detalhes")
                    }

                }
            }

        }
}


@Composable
fun DetalhesProdutos(navController: NavController, produtos: MutableList<Produto>){
    Text(text = "Hello world")
}

@Preview(showBackground = true)
@Composable
fun LayoutPreview(){
    Navegacao()
}