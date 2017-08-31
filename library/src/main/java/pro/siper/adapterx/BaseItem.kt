package pro.siper.adapterx

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Siper on 16.06.2017.
 */
interface BaseItem  {
    fun getLayout(): Int
    fun bindHolder(holder: RecyclerView.ViewHolder?)
    fun createView(parent: View): RecyclerView.ViewHolder
}
