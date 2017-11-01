package pro.siper.adapterx.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Category {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("photo_count")
    @Expose
    var photoCount: Int? = null
    @SerializedName("links")
    @Expose
    var links: Links? = null

}