package pro.siper.adapterx


interface OnClickListenerX {
    fun onClick(item: BaseItem, position: Int)
    fun onLongClick(item: BaseItem, position: Int)
}