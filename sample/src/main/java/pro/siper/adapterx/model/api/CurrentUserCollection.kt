package pro.siper.adapterx.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrentUserCollection {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("published_at")
    @Expose
    var publishedAt: String? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
    @SerializedName("curated")
    @Expose
    var curated: Boolean? = null
    @SerializedName("cover_photo")
    @Expose
    var coverPhoto: CoverPhoto? = null
    @SerializedName("user")
    @Expose
    var user: User? = null
    @SerializedName("links")
    @Expose
    var links: Links? = null

}