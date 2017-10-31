package pro.siper.adapterx.x

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pro.siper.adapterx.impl.BaseItem
import pro.siper.adapterx.impl.BaseItemClickListener
import pro.siper.adapterx.impl.ItemClickListener
import pro.siper.adapterx.impl.ItemLongClickListener


class AdapterX() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var dataset: MutableList<BaseItem> = mutableListOf()
    private var viewTypes: HashMap<Int, BaseItem> = HashMap()
    private var itemClickListeners: HashMap<Int, ItemClickListener> = HashMap()
    private var itemLongClickListeners: HashMap<Int, ItemLongClickListener> = HashMap()

    private var itemClickListener: BaseItemClickListener? = null

    constructor(dataset: MutableList<BaseItem> = mutableListOf()) : this() {
        this.dataset = dataset
        if(dataset.isNotEmpty()) {
            dataset.forEach { registerViewType(it) }
        }
    }

    constructor(listener: BaseItemClickListener?) : this() {
        this.itemClickListener = listener
    }

    constructor(dataset: MutableList<BaseItem>, listener: BaseItemClickListener?) : this(dataset) {
        this.itemClickListener = listener
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
                this.viewTypes[viewType]!!.getLayout(), parent, false)
        val holder = this.viewTypes[viewType]!!.createView(view)
        if (itemClickListener != null || itemClickListeners.isNotEmpty()
                || itemLongClickListeners.isNotEmpty()) {
            holder.itemView.setOnClickListener {
                val position = holder.adapterPosition
                val item = dataset[position]
                itemClickListener?.onItemClick(item, position)
                if (itemClickListeners.containsKey(viewType)) {
                    itemClickListeners[viewType]?.onClick(item, position)
                }
            }
            holder.itemView.setOnLongClickListener {
                val position = holder.adapterPosition
                val item = dataset[position]
                itemClickListener?.onItemLongClick(item, position)
                if (itemLongClickListeners.containsKey(viewType)) {
                    itemLongClickListeners[viewType]?.onLongClick(item, position)
                }
                true
            }
        }
        return holder
    }

    private fun <T : BaseItem> registerViewType(item: T) {
        if(!viewTypes.containsKey(item.getTag())) {
            viewTypes.put(item.getTag(), item)
        }
    }

    private fun <T : BaseItem> unRegisterViewType(item: T) {
        if(viewTypes.containsKey(item.getTag())) {
            viewTypes.remove(item.getTag())
        }
    }

    private fun unRegisterAllViewTypes() {
        viewTypes.clear()
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun <T : BaseItem> addItem(item: T) {
        dataset.add(item)
        registerViewType(item)
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun <T : BaseItem> addItems(items: List<T>) {
        items.forEach {
            registerViewType(it)
            dataset.add(it)
        }
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun <T : BaseItem> addItems(index: Int, items: List<T>) {
        items.forEach {
            registerViewType(it)
        }
        dataset.addAll(index, items)
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun <T : BaseItem> swapDataset(newDataset: List<T>) {
        unRegisterAllViewTypes()
        newDataset.forEach { registerViewType(it) }
        dataset = newDataset.toMutableList()
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun <T : BaseItem> removeItems(items: List<T>) {
        items.forEach { removeItem(it) }
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun removeItem(position: Int) {
        dataset.removeAt(position)
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun <T : BaseItem> removeItem(item: T) {
        dataset.remove(item)
        if (dataset.filter { it.getTag() == item.getTag() }.isEmpty()) {
            unRegisterViewType(item)
        }
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun removeAllItems() {
        dataset.clear()
        unRegisterAllViewTypes()
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun setOnItemClickListener(listener: BaseItemClickListener?) {
        this.itemClickListener = listener
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun <T : BaseItem> addTypedOnItemClickListener(itemTag: Int,
                                                                           listener: ItemClickListenerX<T>) {
        if (!itemClickListeners.containsKey(itemTag)) {
            itemClickListeners.put(itemTag, listener)
        }
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun <T : BaseItem> addTypedOnItemLongClickListener(itemTag: Int,
                                                                               listener: ItemLongClickListenerX<T>) {
        if (!itemLongClickListeners.containsKey(itemTag)) {
            itemLongClickListeners.put(itemTag, listener)
        }
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun <T : BaseItem> addTypedOnItemClickListener(itemTag: Int,
                                                                           listener: (item: T, position: Int) -> Unit) {
        addTypedOnItemClickListener(itemTag, object : ItemClickListenerX<T> {
            override fun onItemClick(item: T, position: Int) {
                listener(item, position)
            }
        })
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun <T : BaseItem> addTypedOnItemLongClickListener(itemTag: Int,
                                                                               listener: (item: T, position: Int) -> Unit) {
        addTypedOnItemLongClickListener(itemTag, object : ItemLongClickListenerX<T> {
            override fun onItemLongClick(item: T, position: Int) {
                listener(item, position)
            }
        })
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun <T : ItemClickListener> removeTypedOnItemClickListener(listener: T) {
        if (itemClickListeners.containsValue(listener)) {
            itemClickListeners.values.remove(listener)
        }
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun <T : ItemLongClickListener> removeTypedOnItemLongClickListener(listener: T) {
        if (itemLongClickListeners.containsValue(listener)) {
            itemLongClickListeners.values.remove(listener)
        }
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun removeTypedOnItemClickListener(itemTag: Int) {
        if (itemClickListeners.containsKey(itemTag)) {
            itemClickListeners.remove(itemTag)
        }
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun removeTypedOnItemLongClickListener(itemTag: Int) {
        if (itemLongClickListeners.containsKey(itemTag)) {
            itemLongClickListeners.remove(itemTag)
        }
    }
}