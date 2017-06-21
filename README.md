# AdapterX

This is a simple multiview adapter for RecyclerView written on Kotlin

# Usage

1. Create your ViewHolder:
```
class ViewHolder1(itemView: View?, item: Item1): ViewHolderX(itemView, item) {
    val title: TextView?
    init {
        title = itemView!!.findViewById(R.id.title) as TextView
    }
}
```
2. Create you own item:
```
class Item1(context: Context) : ItemX<ViewHolder1>() {
    val context: Context
    init {
        this.context = context
    }
    override fun onClick(item: BaseItem, position: Int) {
        Toast.makeText(context, "Item 1 clicked at position: $position", Toast.LENGTH_SHORT).show()
    }

    override fun onLongClick(item: BaseItem, position: Int) {
        Toast.makeText(context, "Item 1 long clicked at position: $position", Toast.LENGTH_SHORT).show()
    }

    override fun getLayout(): Int = R.layout.item1

    override fun bindView(holder: ViewHolder1) {
        holder.title!!.text = "Item 1 at position: ${holder.adapterPosition}"
    }

    override fun createView(parent: View): RecyclerView.ViewHolder = ViewHolder1(parent, this)
}
```
3. Init you adapter and add items
```
var adapter: AdapterX = AdapterX()
recyclerView.adapter = adapter
adapter.addItem(Item1(this))
```   
