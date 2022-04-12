package com.thingsflow.app.ui.list.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AutoBindViewHolder<T, V : HolderEvent>(
    containerView: View,
    holderEvent: HolderEvent
) :
    RecyclerView.ViewHolder(containerView) {

    @Suppress("UNCHECKED_CAST")
    val event = holderEvent as V
    val context: Context? = itemView.context

    abstract fun bind(item: T)

    open fun bind(item: T, payloads: MutableList<Any>) {
        bind(item)
    }

    @Suppress("UNCHECKED_CAST")
    internal fun bindInternal(item: Any) {
        try {
            bind(item as T)
        } catch (e: TypeCastException) {
            throw HolderItemTypeNotMatchedException()
        }
    }

    @Suppress("UNCHECKED_CAST")
    internal fun bindInternal(item: Any, payloads: MutableList<Any>) {
        try {
            bind(item as T, payloads)
        } catch (e: TypeCastException) {
            throw HolderItemTypeNotMatchedException()
        }
    }

    class HolderItemTypeNotMatchedException :
        Exception("AutoBindViewHolder item type is not matched Adapter's item type")
}
