package pro.siper.adapterx.impl


interface BaseItemClickListener : ItemClickListener, ItemLongClickListener {
    override fun onClick(item: BaseItem, position: Int) { onItemClick(item, position) }
    override fun onLongClick(item: BaseItem, position: Int) { onItemLongClick(item, position) }

    fun onItemClick(item: BaseItem, position: Int)
    fun onItemLongClick(item: BaseItem, position: Int)
}