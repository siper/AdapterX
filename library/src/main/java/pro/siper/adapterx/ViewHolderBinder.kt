package pro.siper.adapterx

import android.support.v7.widget.RecyclerView


interface ViewHolderBinder<out T : RecyclerView.ViewHolder> {
    fun bindView(holder: RecyclerView.ViewHolder): T
}