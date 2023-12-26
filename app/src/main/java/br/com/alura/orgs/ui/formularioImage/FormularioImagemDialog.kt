package br.com.alura.orgs.ui.formularioImage

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.databinding.FormularioImagemBinding
import br.com.alura.orgs.extensions.tentaCarregarImagem

class FormularioImagemDialog (private val context: Context){

    fun mostra(
        urlPadrao: String? = null,
        quandoImagemCarregada: (imagem: String) -> Unit
    ) {
        val binding= FormularioImagemBinding
            .inflate(LayoutInflater.from(context)).apply {

        urlPadrao.let{
            activityFormularioImageImage.tentaCarregarImagem(it)
            activityFormularioImageUrl.setText(it)
        }

        formularioImagemButton.setOnClickListener{
            val url = activityFormularioImageUrl.text.toString()
            activityFormularioImageImage.tentaCarregarImagem(url)
        }

        AlertDialog.Builder(context)
            .setView(root)
            .setPositiveButton("Confirmar") {_,_->
                val url = activityFormularioImageUrl.text.toString()
                quandoImagemCarregada(url)
            }
            .setNegativeButton("Cancelar"){_,_->

            }
            .show()
    }
    }
}