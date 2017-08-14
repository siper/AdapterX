package pro.siper.adapterx

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Siper on 21.06.2017.
 */

abstract class ViewHolderX(itemView: View?, item: BaseItem) : RecyclerView.ViewHolder(itemView) {
    val item: BaseItem
    init {
        itemView!!.setOnClickListener{v -> item.onClick(item, adapterPosition)}
        itemView.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                item.onLongClick(item, adapterPosition)
                return true
            }
        })
        this.item = item
    }
}
