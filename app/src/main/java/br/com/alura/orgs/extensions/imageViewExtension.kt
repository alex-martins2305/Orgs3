package br.com.alura.orgs.extensions

import android.widget.ImageView
import br.com.alura.orgs.R
import coil.load

fun ImageView.tentaCarregarImagem(url:String?=null) {
    load(url) {
        error(R.drawable.no_image2)
        fallback(R.drawable.no_image2)
        placeholder(R.drawable.loading)
    }
}