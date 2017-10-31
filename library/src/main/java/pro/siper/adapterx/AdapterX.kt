package pro.siper.adapterx

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


@Suppress("DEPRECATION")
class AdapterX() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var dataset: MutableList<BaseItem> = mutableListOf()
    private var registeredViews: HashMap<Int, BaseItem> = HashMap()

    private var mOnClickListener: OnClickListenerX? = null
    private var mOnItemClickListener: OnItemClickListenerX? = null

    constructor(dataset: MutableList<BaseItem>) : this() {
        this.dataset = dataset
        if(dataset.isNotEmpty()) {
            dataset.forEach { registerViewType(it) }
        }
    }

    constructor(dataset: MutableList<BaseItem>,
                listener: OnItemClickListenerX? = null) : this(dataset) {
        this.mOnItemClickListener = listener
    }

    @Deprecated(message = "OnClickListener deprecated use OnItemClickListenerX instead")
    constructor(dataset: MutableList<BaseItem>,
                listener: OnClickListenerX? = null) : this(dataset) {
        this.mOnClickListener = listener
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
        if (mOnItemClickListener == null) {
            mOnClickListener?.let {
                holder.itemView.setOnClickListener {
                    val position = holder.adapterPosition
                    mOnClickListener?.onClick(dataset[position], position)
                }
                holder.itemView.setOnLongClickListener {
                    val position = holder.adapterPosition
                    mOnClickListener?.onLongClick(dataset[position], position)
                    true
                }
            }
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