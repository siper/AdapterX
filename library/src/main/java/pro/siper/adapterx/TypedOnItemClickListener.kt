package pro.siper.adapterx


interface TypedOnItemClickListener<in T : BaseItem> {
    fun onItemClick(item: T, position: Int)
    fun onItemLongClick(item: T, position: Int)
}