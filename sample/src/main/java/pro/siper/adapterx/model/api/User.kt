package pro.siper.adapterx.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("twitter_username")
    @Expose
    var twitterUsername: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("first_name")
    @Expose
    var firstName: String? = null
    @SerializedName("last_name")
    @Expose
    var lastName: String? = null
    @SerializedName("portfolio_url")
    @Expose
    var portfolioUrl: String? = null
    @SerializedName("bio")
    @Expose
    var bio: String? = null
    @SerializedName("location")
    @Expose
    var location: String? = null
    @SerializedName("total_likes")
    @Expose
    var totalLikes: Int? = null
    @SerializedName("total_photos")
    @Expose
    var totalPhotos: Int? = null
    @SerializedName("total_collections")
    @Expose
    var totalCollections: Int? = null
    @SerializedName("profile_image")
    @Expose
    var profileImage: ProfileImage? = null
    @SerializedName("links")
    @Expose
    var links: Links? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
}