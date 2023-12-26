package br.com.alura.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityVisualizarProdutoBinding

class VisualizarProdutoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityVisualizarProdutoBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title="Cadastrar produto"
        setContentView(binding.root)
        setContentView(R.layout.activity_visualizar_produto)

    }
}
