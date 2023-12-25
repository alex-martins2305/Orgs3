package br.com.alura.orgs.ui.recyclerview.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Insets.add
import android.icu.text.NumberFormat
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.text.Spannable.Factory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.ui.platform.LocalContext
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.model.Produto
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import java.util.Locale
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imageLoader = ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        fun vincula(produto: Produto) {
            val nome = binding.produtoItemNome
            nome.text = produto.nome
            val descricao = binding.produtoItemDescricao
            descricao.text = produto.descricao

            val valor = binding.produtoItemValor
            val formatador = NumberFormat
                .getCurrencyInstance(Locale("pt", "br"))
            val valorEmMoeda = formatador.format(produto.valor)
            valor.text = valorEmMoeda
            binding.imageView.load(produto.image) {
                error(R.drawable.no_image2)
                fallback(R.drawable.no_image2)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }
}

//changing something in file to test github
