package com.thingsflow.app.ui.list.adapter

open class AutoBindViewPagerAdapter<S : Any>(
    bindHolderFactory: AutoBindHolderFactory<S>
) : AutoBindListAdapter<S>(bindHolderFactory) {
    var onCurrentListChangedListener: ((previousList: MutableList<S>, currentList: MutableList<S>) -> Unit)? =
        null

    override fun submitList(list: List<S>?) {
        super.submitList(
            list?.let {
                listOf(list.last()) + list + listOf(list.first())
            }
        )
    }

    override fun submitList(list: List<S>?, commitCallback: Runnable?) {
        super.submitList(
            list?.let {
                listOf(list.last()) + list + listOf(list.first())
            },
            commitCallback
        )
    }

    override fun onCurrentListChanged(previousList: MutableList<S>, currentList: MutableList<S>) {
        super.onCurrentListChanged(previousList, currentList)
        onCurrentListChangedListener?.invoke(previousList, currentList)
    }
}
