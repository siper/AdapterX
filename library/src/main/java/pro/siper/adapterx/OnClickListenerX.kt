package pro.siper.adapterx

@Deprecated(message = "OnClickListener deprecated use OnItemClickListenerX instead")
interface OnClickListenerX {
    fun onClick(item: BaseItem, position: Int)
    fun onLongClick(item: BaseItem, position: Int)
}