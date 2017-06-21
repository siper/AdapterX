package pro.siper.adapterx

/**
 * Created by Siper on 18.06.2017.
 */
interface OnClickListenerX {
    fun onClick(item: BaseItem, position: Int)
    fun onLongClick(item: BaseItem, position: Int)
}