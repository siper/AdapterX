package pro.siper.adapterx.x

import pro.siper.adapterx.impl.BaseItem
import pro.siper.adapterx.impl.ItemLongClickListener

interface ItemLongClickListenerX<in T : BaseItem> : ItemLongClickListener {
    @Suppress("UNCHECKED_CAST")
    override fun onLongClick(item: BaseItem, position: Int) {
        onItemLongClick(item as T, position)
    }

    fun onItemLongClick(item: T, position: Int)
}