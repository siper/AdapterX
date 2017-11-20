package pro.siper.adapterx.x

import pro.siper.adapterx.impl.BaseItem
import pro.siper.adapterx.impl.ItemClickListener

interface OnlyItemClickListenerX<in T : BaseItem> : ItemClickListener {
    @Suppress("UNCHECKED_CAST")
    override fun onClick(item: BaseItem, position: Int) { onItemClick(item as T) }

    fun onItemClick(item: T)
}
