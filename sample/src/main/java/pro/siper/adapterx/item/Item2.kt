package pro.siper.adapterx.item

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import pro.siper.adapterx.R
import pro.siper.adapterx.x.ItemX

class Item2(val title: String) : ItemX<ViewHolder2>() {
    override fun getTag(): Int = 2

    override fun getLayout(): Int = R.layout.item2

    override fun bindView(holder: ViewHolder2) {
        holder.title.text = "Item 2 ($title) at position: ${holder.adapterPosition}"
    }
    override fun createView(parent: View): RecyclerView.ViewHolder = ViewHolder2(parent)
}

class ViewHolder2(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title) as TextView
}