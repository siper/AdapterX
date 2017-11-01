package pro.siper.adapterx.model.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import pro.siper.adapterx.R

class BigImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView = itemView.findViewById(R.id.image) as ImageView
    val authorAvatar: ImageView = itemView.findViewById(R.id.author_avatar) as ImageView
    val authorName: TextView = itemView.findViewById(R.id.author) as TextView
}