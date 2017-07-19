package pro.siper.adapterx

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import pro.siper.adapterx.item.Item1
import pro.siper.adapterx.item.Item2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.list) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter: AdapterX = AdapterX()
        recyclerView.adapter = adapter
        adapter.addItem(Item1(this))
        adapter.addItem(Item2(this))
        adapter.addItem(Item1(this))
        adapter.addItem(Item2(this))
        adapter.addItem(Item1(this))
        adapter.addItem(Item2(this))
        adapter.addItem(Item1(this))
        adapter.addItem(Item2(this))
        adapter.addItem(Item1(this))
        adapter.addItem(Item2(this))
        adapter.addItem(Item1(this))
        adapter.addItem(Item2(this))
        adapter.addItem(Item1(this))
        adapter.addItem(Item2(this))
        adapter.notifyDataSetChanged()
    }
}
