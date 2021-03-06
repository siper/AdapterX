package pro.siper.adapterx.x

import android.support.v7.widget.RecyclerView
import android.view.View
import pro.siper.adapterx.impl.BaseItem


abstract class ItemX<in T : RecyclerView.ViewHolder> : BaseItem {
    abstract override fun getLayout(): Int
    override fun getTag(): Int = getLayout()
    abstract fun bindView(holder: T)

    override fun bindHolder(holder: RecyclerView.ViewHolder?) {
        @Suppress("UNCHECKED_CAST")
        bindView(holder as T)
    }

    abstract override fun createView(itemView: View): RecyclerView.ViewHolder
}