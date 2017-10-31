package pro.siper.adapterx.impl


interface TypedOnItemClickListener<in T : BaseItem> : AbstractTypedOnItemClickListener {
    @Suppress("UNCHECKED_CAST")
    override fun onClick(item: BaseItem, position: Int) {
        onItemClick(item as T, position)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onLongClick(item: BaseItem, position: Int) {
        onItemLongClick(item as T, position)
    }

    fun onItemClick(item: T, position: Int)
    fun onItemLongClick(item: T, position: Int)
}