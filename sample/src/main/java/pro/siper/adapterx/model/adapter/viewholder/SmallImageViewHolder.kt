package pro.siper.adapterx.model.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import pro.siper.adapterx.R

class SmallImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.image) as ImageView
    val createdAt: TextView = itemView.findViewById(R.id.created_at) as TextView
    val widthHeight: TextView = itemView.findViewById(R.id.width_height) as TextView
}
