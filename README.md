# AdapterX [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AdapterX-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5918) [![Build Status](https://travis-ci.org/siper/AdapterX.svg?branch=master)](https://travis-ci.org/siper/AdapterX)

Simple multiview adapter for RecyclerView written on Kotlin

# Installation

### 1. Add the JitPack repository to your build file
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
### 2. Add the dependency
```
dependencies {
	compile 'com.github.siper:AdapterX:1.0'
}
```

# Usage

### 1. Create your ViewHolder
```
class ViewHolder1(itemView: View): ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.title) as TextView
}
```
### 2. Create you own item
```
class Item1(val context: Context) : ItemX<ViewHolder1>() {

    override fun getLayout(): Int = R.layout.item1

    override fun bindView(holder: ViewHolder1) {
        holder.title.text = "Item 1 at position: ${holder.adapterPosition}"
    }

    override fun createView(parent: View): RecyclerView.ViewHolder = ViewHolder1(parent)
}
```
### 3. Init you adapter and add items
```
var adapter: AdapterX = AdapterX()
recyclerView.adapter = adapter
adapter.addItem(Item1(this))
```   

### 4. Or init it with listener
```
val adapter = AdapterX(listener = object : OnClickListenerX {
            override fun onClick(item: BaseItem, position: Int) {
                when (item) {
                    is Item1 -> Toast.makeText(applicationContext, "Item 1 (${item.title}) at position: $position clicked",
                            Toast.LENGTH_SHORT).show()
                }
            }
            override fun onLongClick(item: BaseItem, position: Int) {
                when(item) {
                    is Item1 -> Toast.makeText(applicationContext, "Item 1 (${item.title}) at position: $position long clicked",
                            Toast.LENGTH_SHORT).show()
                }
            }
        })
```
# License

```
MIT License

Copyright (c) 2017 Siper

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
