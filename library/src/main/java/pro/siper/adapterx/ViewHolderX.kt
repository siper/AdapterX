package pro.siper.adapterx

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Siper on 21.06.2017.
 */

abstract class ViewHolderX(itemView: View?, item: BaseItem) : RecyclerView.ViewHolder(itemView), View.OnClickListener,
        View.OnLongClickListener {
    val item: BaseItem
    init {
        itemView!!.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
        this.item = item
    }

    override fun onClick(v: View?) {
        item.onClick(item, adapterPosition)
    }

    override fun onLongClick(v: View?): Boolean {
        item.onClick(item, adapterPosition)
        return true
    }
}
