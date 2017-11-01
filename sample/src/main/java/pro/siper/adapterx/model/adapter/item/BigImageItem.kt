package pro.siper.adapterx.model.adapter.item

import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import pro.siper.adapterx.R
import pro.siper.adapterx.model.adapter.viewholder.BigImageViewHolder
import pro.siper.adapterx.model.api.UnsplashItem
import pro.siper.adapterx.x.ItemX

class BigImageItem(val picasso: Picasso, val unsplashItem: UnsplashItem) : ItemX<BigImageViewHolder>() {
    override fun getLayout(): Int = R.layout.big_image_item

    override fun bindView(holder: BigImageViewHolder) {
        with(unsplashItem) {
            picasso.load(urls?.regular).into(holder.image)
            picasso.load(user?.profileImage?.medium).into(holder.authorAvatar)
            holder.authorName.text = "${user?.firstName} ${user?.lastName}"
        }
    }

    override fun createView(itemView: View): RecyclerView.ViewHolder = BigImageViewHolder(itemView)
}