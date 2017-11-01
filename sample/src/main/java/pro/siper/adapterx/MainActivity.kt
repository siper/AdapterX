package pro.siper.adapterx

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import pro.siper.adapterx.impl.BaseItem
import pro.siper.adapterx.impl.BaseItemClickListener
import pro.siper.adapterx.item.Item1
import pro.siper.adapterx.item.Item2
import pro.siper.adapterx.x.AdapterX

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.list) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AdapterX()
        adapter.addTypedOnItemLongClickListener(1, {
            item: Item1, position: Int -> Toast.makeText(applicationContext,
                "Item 1 (${item.title}) at position: $position long clicked", Toast.LENGTH_SHORT).show()
        })
        adapter.setOnItemClickListener(object : BaseItemClickListener {
            override fun onItemClick(item: BaseItem, position: Int) {
                when (item) {
                    is Item1 -> Toast.makeText(applicationContext,
                            "Item 1 (${item.title}) at position: $position clicked", Toast.LENGTH_SHORT).show()
                    is Item2 -> Toast.makeText(applicationContext,
                            "Item 2 (${item.title}) at position: $position clicked", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onItemLongClick(item: BaseItem, position: Int) {}
        })
        recyclerView.adapter = adapter
        adapter.addItem(Item1("Monkey"))
        adapter.addItem(Item2("Elephant"))
        adapter.addItem(Item1("Giraffe"))
        adapter.addItem(Item2("Mice"))
        adapter.addItem(Item1("Cat"))
        adapter.addItem(Item2("Dog"))
        adapter.addItem(Item1("Hamster"))
        adapter.notifyDataSetChanged()
    }
}
