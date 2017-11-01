package pro.siper.adapterx.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Links {

    @SerializedName("self")
    @Expose
    var self: String? = null
    @SerializedName("html")
    @Expose
    var html: String? = null
    @SerializedName("photos")
    @Expose
    var photos: String? = null
    @SerializedName("likes")
    @Expose
    var likes: String? = null
    @SerializedName("portfolio")
    @Expose
    var portfolio: String? = null
    @SerializedName("download")
    @Expose
    var download: String? = null
    @SerializedName("download_location")
    @Expose
    var downloadLocation: String? = null
}