package pro.siper.adapterx.impl

import android.support.v7.widget.RecyclerView
import android.view.View


interface BaseItem  {
    fun getTag(): Int
    fun getLayout(): Int
    fun bindHolder(holder: RecyclerView.ViewHolder?)
    fun createView(itemView: View): RecyclerView.ViewHolder
}
