package com.example.wechatmoment.adapter.adapter

interface AdapterInterface {
    /**
     * 设置数据
     *
     * @param data
     */
    fun setData(data: List<Any?>?)

    /**
     * 获取数据
     */
    fun getData(): List<Any?>?

    /**
     * 添加数据
     *
     * @param data
     */
    fun addData(data: Any?)

    /**
     * 添加数据
     *
     * @param data
     */
    fun addDataList(data: List<Any?>?)

    /**
     * 清除数据
     */
    fun clear()

    /**
     * 刷新数据
     */
    fun updateChange()

    /**
     * 移除Item
     */
    fun removeItem(position: Int)
}