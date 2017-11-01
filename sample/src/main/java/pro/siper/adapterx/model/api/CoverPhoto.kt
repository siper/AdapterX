package pro.siper.adapterx.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import pro.siper.adapterx.model.api.Category

class CoverPhoto {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("color")
    @Expose
    var color: String? = null
    @SerializedName("likes")
    @Expose
    var likes: Int? = null
    @SerializedName("liked_by_user")
    @Expose
    var likedByUser: Boolean? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("user")
    @Expose
    var user: User? = null
    @SerializedName("urls")
    @Expose
    var urls: Urls? = null
    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null
    @SerializedName("links")
    @Expose
    var links: Links? = null

}