package pro.siper.adapterx.item

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import pro.siper.adapterx.x.ItemX
import pro.siper.adapterx.R

class Item1(val title: String) : ItemX<ViewHolder1>() {
    override fun getTag(): Int = 1

    override fun getLayout(): Int = R.layout.item1

    override fun bindView(holder: ViewHolder1) {
        holder.title.text = "Item 1 ($title) at position: ${holder.adapterPosition}"
    }

    override fun createView(parent: View): RecyclerView.ViewHolder = ViewHolder1(parent)
}

class ViewHolder1(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title) as TextView
}