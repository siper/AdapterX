package pro.siper.adapterx

import android.support.v7.widget.RecyclerView
import android.view.View


abstract class ItemX<in T : RecyclerView.ViewHolder> : BaseItem {
    abstract override fun getLayout(): Int
    abstract override fun getTag(): Int
    abstract fun bindView(holder: T)

    override fun bindHolder(holder: RecyclerView.ViewHolder?) {
        @Suppress("UNCHECKED_CAST")
        bindView(holder as T)
    }

    abstract override fun createView(parent: View): RecyclerView.ViewHolder
}