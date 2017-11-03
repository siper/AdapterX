package pro.siper.adapterx

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import pro.siper.adapterx.model.adapter.item.BigImageItem
import pro.siper.adapterx.model.adapter.item.SmallImageLeftItem
import pro.siper.adapterx.model.adapter.item.SmallImageRightItem
import pro.siper.adapterx.model.api.UnsplashApi
import pro.siper.adapterx.model.api.UnsplashItem
import pro.siper.adapterx.x.AdapterX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val call: Call<List<UnsplashItem>>
            = getUnsplashApi(getRetrofit(Gson())).listPhotos(getString(R.string.unsplash_api_key))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.list) as RecyclerView
        val progress: ProgressBar = findViewById(R.id.progress) as ProgressBar
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AdapterX()
        recyclerView.adapter = adapter
        call.enqueue(object : Callback<List<UnsplashItem>> {
            override fun onFailure(call: Call<List<UnsplashItem>>?, t: Throwable?) {}

            override fun onResponse(call: Call<List<UnsplashItem>>?,
                                    response: Response<List<UnsplashItem>>?) {
                if (response!!.isSuccessful) {
                    val list = response.body()!!
                    for (i in list.indices) {
                        if (i % 3 == 0) {
                            adapter.addItem(BigImageItem(getPicasso(), list[i]))
                        } else if (i % 2 == 0) {
                            adapter.addItem(SmallImageLeftItem(getPicasso(), list[i]))
                        } else {
                            adapter.addItem(SmallImageRightItem(getPicasso(), list[i]))
                        }
                    }
                    recyclerView.visibility = View.VISIBLE
                    progress.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
            }
        })
        adapter.addTypedOnItemClickListener {
            item: BigImageItem, position: Int ->  toast(message = "Big item clicked")
        }
        adapter.addTypedOnItemLongClickListener {
            item: SmallImageLeftItem, position: Int ->
            toast(message = "SmallImageLeftItem item long clicked")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!call.isCanceled) {
            call.cancel()
        }
    }

    private fun getRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.unsplash.com/")
                .build()
    }

    private fun getUnsplashApi(retrofit: Retrofit): UnsplashApi {
        return retrofit.create(UnsplashApi::class.java)
    }

    private fun getPicasso(context: Context = this): Picasso {
        return Picasso.with(context)
    }

    private fun toast(context: Context = this, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
