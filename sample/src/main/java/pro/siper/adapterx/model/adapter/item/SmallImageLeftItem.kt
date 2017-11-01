package pro.siper.adapterx.model.adapter.item

import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import pro.siper.adapterx.R
import pro.siper.adapterx.model.adapter.viewholder.SmallImageViewHolder
import pro.siper.adapterx.model.api.UnsplashItem
import pro.siper.adapterx.x.ItemX

class SmallImageLeftItem(private val picasso: Picasso,
                         val unsplashItem: UnsplashItem) : ItemX<SmallImageViewHolder>() {
    override fun getLayout(): Int = R.layout.small_image_left_item

    override fun bindView(holder: SmallImageViewHolder) {
        with(unsplashItem) {
            picasso.load(urls?.regular).into(holder.image)
            holder.description.text = "$description"
            holder.widthHeight.text = "${width}x${height}"
        }
    }

    override fun createView(itemView: View): RecyclerView.ViewHolder = SmallImageViewHolder(itemView)
}