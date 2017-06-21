package pro.siper.adapterx.item

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import pro.siper.adapterx.BaseItem
import pro.siper.adapterx.ItemX
import pro.siper.adapterx.R
import pro.siper.adapterx.ViewHolderX

/**
 * Created by Siper on 18.06.2017.
 */
class Item2(context: Context) : ItemX<ViewHolder2>() {
    val context: Context
    init {
        this.context = context
    }
    override fun onClick(item: BaseItem, position: Int) {
        Toast.makeText(context, "Item 2 clicked at position: $position", Toast.LENGTH_SHORT).show()
    }

    override fun onLongClick(item: BaseItem, position: Int) {
        Toast.makeText(context, "Item 2 long clicked at position: $position", Toast.LENGTH_SHORT).show()
    }

    override fun getLayout(): Int = R.layout.item2

    override fun bindView(holder: ViewHolder2) {
        holder.title!!.text = "Item 2 at position: ${holder.adapterPosition}"
    }
    override fun createView(parent: View): RecyclerView.ViewHolder = ViewHolder2(parent, this)
}

class ViewHolder2(itemView: View?, item: Item2): ViewHolderX(itemView, item) {
    val title: TextView?
    init {
        title = itemView!!.findViewById(R.id.title) as TextView
    }
}