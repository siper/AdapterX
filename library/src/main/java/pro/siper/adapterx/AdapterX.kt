package pro.siper.adapterx

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class AdapterX(var dataset: MutableList<BaseItem> = mutableListOf(),
               val listener: OnClickListenerX? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var registeredViews: HashMap<Int, BaseItem> = HashMap()

    init {
        if(dataset.isNotEmpty()) {
            dataset.forEach { registerViewType(it) }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun getItemViewType(position: Int): Int {
        return dataset[position].getLayout()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        dataset[position].bindHolder(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(viewType, parent, false)
        val holder = registeredViews[viewType]!!.createView(view)
        listener?.let {
            holder.itemView.setOnClickListener {
                val position = holder.adapterPosition
                listener.onClick(dataset[position], position)
            }
            holder.itemView.setOnLongClickListener(object : View.OnLongClickListener {
                override fun onLongClick(p0: View?): Boolean {
                    val position = holder.adapterPosition
                    listener.onLongClick(dataset[position], position)
                    return true
                }
            })

        }
        return holder
    }

    fun addItem(item: BaseItem) {
        dataset.add(item)
        registerViewType(item)
    }

    fun addItems(items: List<BaseItem>) {
        for(item in items) {
            registerViewType(item)
            dataset.add(item)
        }
    }

    fun swapDataset(newDataset: List<BaseItem>) {
        unRegisterAllViewTypes()
        newDataset.forEach { registerViewType(it) }
        dataset = newDataset.toMutableList()
    }

    private fun registerViewType(item: BaseItem) {
        if(!registeredViews.containsKey(item.getLayout())) {
            registeredViews.put(item.getLayout(), item)
        }
    }

    private fun unRegisterViewType(item: BaseItem) {
        if(registeredViews.containsKey(item.getLayout())) {
            registeredViews.remove(item.getLayout())
        }
    }

    private fun unRegisterAllViewTypes() {
        registeredViews.clear()
    }

    fun removeItem(position: Int) {
        dataset.removeAt(position)
    }

    fun removeItem(item: BaseItem) {
        dataset.remove(item)
    }

    fun removeAllItems() {
        dataset.clear()
        unRegisterAllViewTypes()
    }
}