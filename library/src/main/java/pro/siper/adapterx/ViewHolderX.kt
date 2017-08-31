package pro.siper.adapterx

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Siper on 21.06.2017.
 */

abstract class ViewHolderX(itemView: View, var item: BaseItem) : RecyclerView.ViewHolder(itemView) {
    init {
//        itemView.setOnClickListener {
//            item.onClick(item.adapter!!.dataset[adapterPosition], adapterPosition)
//        }
//        itemView.setOnLongClickListener ( object : View.OnLongClickListener{
//            override fun onLongClick(p0: View?): Boolean {
//                item.onLongClick(item.adapter!!.dataset[adapterPosition], adapterPosition)
//                return true
//            }
//        })
    }
}
