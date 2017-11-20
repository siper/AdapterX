package pro.siper.adapterx.x

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pro.siper.adapterx.impl.BaseItem
import pro.siper.adapterx.impl.BaseItemClickListener
import pro.siper.adapterx.impl.ItemClickListener
import pro.siper.adapterx.impl.ItemLongClickListener
import kotlin.reflect.KClass


class AdapterX() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var dataset: MutableList<BaseItem> = mutableListOf()
    private var viewTypes: HashMap<Int, BaseItem> = HashMap()
    var itemClickListeners: HashMap<KClass<*>, ItemClickListener> = HashMap()
    var itemLongClickListeners: HashMap<KClass<*>, ItemLongClickListener> = HashMap()

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
        val view = LayoutInflater
                .from(parent?.context)
                .inflate(viewTypes[viewType]!!.getLayout(), parent, false)
        val holder = viewTypes[viewType]!!.createView(view)
        if (itemClickListener != null || itemClickListeners.isNotEmpty()
                || itemLongClickListeners.isNotEmpty()) {
            holder.itemView.setOnClickListener {
                val position = holder.adapterPosition
                val item = dataset[position]
                val itemClass = dataset[position]::class
                itemClickListener?.onItemClick(item, position)
                if (itemClickListeners.containsKey(itemClass)) {
                    itemClickListeners[itemClass]?.onClick(item, position)
                }
            }
            holder.itemView.setOnLongClickListener {
                val position = holder.adapterPosition
                val item = dataset[position]
                itemClickListener?.onItemLongClick(item, position)
                val itemClass = dataset[position]::class
                if (itemLongClickListeners.containsKey(itemClass)) {
                    itemLongClickListeners[itemClass]?.onLongClick(item, position)
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

    fun <T : BaseItem> addItem(item: T) {
        dataset.add(item)
        registerViewType(item)
    }

    fun <T : BaseItem> addItems(items: List<T>) {
        items.forEach {
            registerViewType(it)
            dataset.add(it)
        }
    }

    fun <T : BaseItem> addItems(index: Int, items: List<T>) {
        items.forEach {
            registerViewType(it)
        }
        dataset.addAll(index, items)
    }

    fun <T : BaseItem> swapDataset(newDataset: List<T>) {
        unRegisterAllViewTypes()
        newDataset.forEach { registerViewType(it) }
        dataset = newDataset.toMutableList()
    }

    fun <T : BaseItem> removeItems(items: List<T>) {
        items.forEach { removeItem(it) }
    }

    fun removeItem(position: Int) {
        dataset.removeAt(position)
    }

    fun <T : BaseItem> removeItem(item: T) {
        dataset.remove(item)
        if (dataset.filter { it.getTag() == item.getTag() }.isEmpty()) {
            unRegisterViewType(item)
        }
    }

    fun removeAllItems() {
        dataset.clear()
        unRegisterAllViewTypes()
    }

    fun setOnItemClickListener(listener: BaseItemClickListener?) {
        this.itemClickListener = listener
    }

    inline fun <reified T : BaseItem> addTypedOnItemClickListener(listener: ItemClickListenerX<T>) {
        if (!itemClickListeners.containsKey(T::class)) {
            itemClickListeners.put(T::class, listener)
        }
    }

    inline fun <reified T : BaseItem> addTypedOnItemClickListener(
            crossinline listener: (item: T, position: Int) -> Unit) {
        addTypedOnItemClickListener(object : ItemClickListenerX<T> {
            override fun onItemClick(item: T, position: Int) {
                listener(item, position)
            }
        })
    }

    inline fun <reified T : BaseItem> addTypedOnItemClickListener(
            crossinline listener: (item: T) -> Unit) {
        if (!itemClickListeners.containsKey(T::class)) {
            itemClickListeners.put(T::class, object : OnlyItemClickListenerX<T> {
                override fun onItemClick(item: T) { listener(item) }
            })
        }
    }

    fun <T : ItemClickListener> removeTypedOnItemClickListener(listener: T) {
        if (itemClickListeners.containsValue(listener)) {
            itemClickListeners.values.remove(listener)
        }
    }

    inline fun <reified T : BaseItem> removeTypedOnItemClickListener() {
        if (itemClickListeners.containsKey(T::class)) {
            itemClickListeners.remove(T::class)
        }
    }

    inline fun <reified T : BaseItem> addTypedOnItemLongClickListener(
            listener: ItemLongClickListenerX<T>){
        if (!itemLongClickListeners.containsKey(T::class)) {
            itemLongClickListeners.put(T::class, listener)
        }
    }

    inline fun <reified T : BaseItem> addTypedOnItemLongClickListener(
            crossinline listener: (item: T, position: Int) -> Unit) {
        addTypedOnItemLongClickListener(object : ItemLongClickListenerX<T> {
            override fun onItemLongClick(item: T, position: Int) {
                listener(item, position)
            }
        })
    }

    inline fun <reified T : BaseItem> addTypedOnItemLongClickListener(
            crossinline listener: (item: T) -> Unit) {
        if (!itemLongClickListeners.containsKey(T::class)) {
            itemLongClickListeners.put(T::class, object : OnlyItemLongClickListenerX<T> {
                override fun onItemLongClick(item: T) { listener(item) }
            }
            )
        }
    }

    fun <T : ItemLongClickListener> removeTypedOnItemLongClickListener(listener: T) {
        if (itemLongClickListeners.containsValue(listener)) {
            itemLongClickListeners.values.remove(listener)
        }
    }

    inline fun <reified T : BaseItem> removeTypedOnItemLongClickListener() {
        if (itemLongClickListeners.containsKey(T::class)) {
            itemLongClickListeners.remove(T::class)
        }
    }
}