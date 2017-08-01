package pro.siper.adapterx

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Siper on 19.06.2017.
 */
abstract class ItemX<in T: ViewHolderX>: BaseItem {
    abstract override fun onClick(item: BaseItem, position: Int)
    abstract override fun onLongClick(item: BaseItem, position: Int)
    abstract override fun getLayout(): Int
    abstract fun bindView(holder: T)

    override fun bindHolder(holder: RecyclerView.ViewHolder?) {
        bindView(holder as T)
    }

    abstract override fun createView(parent: View): RecyclerView.ViewHolder
}