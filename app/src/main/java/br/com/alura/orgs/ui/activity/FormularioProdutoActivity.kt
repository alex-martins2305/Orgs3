package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.dao.ProdutosDao
import br.com.alura.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.orgs.databinding.FormularioImagemBinding
import br.com.alura.orgs.model.Produto
import coil.load
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImage.setOnClickListener{
            val bindingFormularioImagem=FormularioImagemBinding.inflate(layoutInflater)
            bindingFormularioImagem.formularioImagemButton.setOnClickListener{
                val url = bindingFormularioImagem.activityFormularioImageUrl.text.toString()
                bindingFormularioImagem.activityFormularioImageImage.load(url){
                    placeholder(R.drawable.loading)
                }
            }

            AlertDialog.Builder(this)
                .setView(bindingFormularioImagem.root)
                .setPositiveButton("Confirmar") {_,_->
                    url = bindingFormularioImagem.activityFormularioImageUrl.text.toString()
                    binding.activityFormularioProdutoImage.load(url){
                        placeholder(R.drawable.loading)
                    }
                }
                .setNegativeButton("Cancelar"){_,_->

                }
                .show()
        }

    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
        val dao = ProdutosDao()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.adiciona(produtoNovo)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor,
            image=url
        )
    }

}