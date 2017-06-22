package pro.siper.adapterx

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Siper on 16.06.2017.
 */
class AdapterX : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    var dataset: MutableList<BaseItem> = arrayListOf()
    var registredViews: HashMap<Int, BaseItem> = HashMap()

    constructor(dataset: MutableList<BaseItem> = arrayListOf()) {
        this.dataset = dataset
        if(dataset.isNotEmpty()) {
            for (item in dataset) {
                registerViewType(item)
            }
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
        return registredViews[viewType]!!.createView(view)
    }

    fun addItem(item: BaseItem) {
        dataset.add(item)
        registerViewType(item)
    }

    fun registerViewType(item: BaseItem) {
        if(!registredViews.containsKey(item.getLayout())) {
            registredViews.put(item.getLayout(), item)
        }
    }

    fun unRegisterViewType(item: BaseItem) {
        if(registredViews.containsKey(item.getLayout())) {
            registredViews.remove(item.getLayout())
        }
    }

    fun unRegiterAllViewTypes() {
        registredViews.clear()
    }
    
}