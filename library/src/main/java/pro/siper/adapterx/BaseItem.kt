package pro.siper.adapterx

import android.support.v7.widget.RecyclerView
import android.view.View


interface BaseItem  {
    fun getLayout(): Int
    fun bindHolder(holder: RecyclerView.ViewHolder?)
    fun createView(parent: View): RecyclerView.ViewHolder
}
