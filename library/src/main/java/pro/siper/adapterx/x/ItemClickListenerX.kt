package pro.siper.adapterx.x

import pro.siper.adapterx.impl.BaseItem
import pro.siper.adapterx.impl.ItemClickListener

interface ItemClickListenerX<in T : BaseItem> : ItemClickListener {
    @Suppress("UNCHECKED_CAST")
    override fun onClick(item: BaseItem, position: Int) {
        onItemClick(item as T, position)
    }

    fun onItemClick(item: T, position: Int)
}