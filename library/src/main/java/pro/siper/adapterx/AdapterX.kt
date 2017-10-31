package pro.siper.adapterx

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


class AdapterX() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var dataset: MutableList<BaseItem> = mutableListOf()
    private var registeredViews: HashMap<Int, BaseItem> = HashMap()
    private var registeredOnItemClickListeners: HashMap<Int, TypedOnItemClickListener<BaseItem>> = HashMap()

    private var mOnItemClickListener: OnItemClickListenerX? = null

    constructor(dataset: MutableList<BaseItem> = mutableListOf()) : this() {
        this.dataset = dataset
        if(dataset.isNotEmpty()) {
            dataset.forEach { registerViewType(it) }
        }
    }

    constructor(listener: OnItemClickListenerX?) : this() {
        this.mOnItemClickListener = listener
    }

    constructor(dataset: MutableList<BaseItem>, listener: OnItemClickListenerX?) : this(dataset) {
        this.mOnItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun getItemViewType(position: Int): Int {
        return dataset[position].getTag()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        dataset[position].bindHolder(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(
                registeredViews[viewType]!!.getLayout(), parent, false)
        val holder = registeredViews[viewType]!!.createView(view)
        mOnItemClickListener?.let {
            holder.itemView.setOnClickListener {
                val position = holder.adapterPosition
                mOnItemClickListener?.onItemClick(dataset[position], position)
            }
            holder.itemView.setOnLongClickListener {
                val position = holder.adapterPosition
                mOnItemClickListener?.onItemLongClick(dataset[position], position)
                true
            }
        }
        if (registeredOnItemClickListeners.containsKey(viewType)) {
            holder.itemView.setOnClickListener {
                val position = holder.adapterPosition
                val item = dataset[position]
                registeredOnItemClickListeners[viewType]!!.onItemClick(item, position)
            }
            holder.itemView.setOnLongClickListener {
                val position = holder.adapterPosition
                registeredOnItemClickListeners[viewType]!!.onItemLongClick(dataset[position], position)
                true
            }
        }
        return holder
    }

    fun <T : BaseItem> addItem(item: T) {
        dataset.add(item)
        registerViewType(item)
    }

    fun <T : BaseItem> addItems(items: List<T>) {
        for(item in items) {
            registerViewType(item)
            dataset.add(item)
        }
    }

    fun <T : BaseItem> swapDataset(newDataset: List<T>) {
        unRegisterAllViewTypes()
        newDataset.forEach { registerViewType(it) }
        dataset = newDataset.toMutableList()
    }

    private fun registerViewType(item: BaseItem) {
        if(!registeredViews.containsKey(item.getTag())) {
            registeredViews.put(item.getTag(), item)
        }
    }

    private fun unRegisterViewType(item: BaseItem) {
        if(registeredViews.containsKey(item.getTag())) {
            registeredViews.remove(item.getTag())
        }
    }

    private fun unRegisterAllViewTypes() {
        registeredViews.clear()
    }

    fun addTypedOnItemClickListener(item: BaseItem, listener: TypedOnItemClickListener<BaseItem>) {
        if (!registeredOnItemClickListeners.containsKey(item.getTag())) {
            registeredOnItemClickListeners.put(item.getTag(), listener)
        }
    }

    fun <T : BaseItem> removeTypedOnItemClickListener(item: T) {
        if (registeredOnItemClickListeners.containsKey(item.getTag())) {
            registeredOnItemClickListeners.remove(item.getTag())
        }
    }

    fun removeItem(position: Int) {
        dataset.removeAt(position)
    }

    fun <T : BaseItem> removeItem(item: T) {
        dataset.remove(item)
    }

    fun removeAllItems() {
        dataset.clear()
        unRegisterAllViewTypes()
    }
}