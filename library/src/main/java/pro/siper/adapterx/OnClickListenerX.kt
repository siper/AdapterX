package pro.siper.adapterx

@Deprecated(message = "Use OnItemClickListenerX instead")
interface OnClickListenerX {
    fun onClick(item: BaseItem, position: Int)
    fun onLongClick(item: BaseItem, position: Int)
}