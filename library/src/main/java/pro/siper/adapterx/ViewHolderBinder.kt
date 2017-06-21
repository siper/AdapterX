package pro.siper.adapterx

import android.support.v7.widget.RecyclerView

/**
 * Created by Siper on 19.06.2017.
 */
interface ViewHolderBinder<out T : RecyclerView.ViewHolder> {
    fun bindView(holder: RecyclerView.ViewHolder): T
}