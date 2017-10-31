package pro.siper.adapterx.impl


interface AbstractTypedOnItemClickListener {
    fun onClick(item: BaseItem, position: Int)
    fun onLongClick(item: BaseItem, position: Int)
}