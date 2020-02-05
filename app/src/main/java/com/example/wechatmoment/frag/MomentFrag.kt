package com.example.wechatmoment.frag

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.example.wechatmoment.R
import com.example.wechatmoment.adapter.adapter.MomentAdapter
import com.example.wechatmoment.adapter.adapter.RecyclerAdapterWithHF
import com.example.wechatmoment.bean.ItemDataBean
import com.example.wechatmoment.bean.MomentBean
import com.example.wechatmoment.bean.UserBean
import com.example.wechatmoment.net.ApiAction
import com.example.wechatmoment.net.NetCallBack
import com.example.wechatmoment.net.OkHttpUtil.Companion.instance
import com.example.wechatmoment.util.UIUtils
import com.example.wechatmoment.widget.AutoRelativeLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MomentFrag : Fragment() {
    private val gson = Gson()
    private var rv_frag_moment: RecyclerView? = null
    private var contentView: View? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var recyclerAdapterWithHF: RecyclerAdapterWithHF? = null
    private var momentAdapter: MomentAdapter? = null
    private var userBean: UserBean? = null
    private var momentBeanList: List<MomentBean>? = null
    private val userName = "jsmith"
    private var loadMorePos = 0
    private var page = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contentView = inflater.inflate(R.layout.frag_moment, container, false)
        return contentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_frag_moment = contentView!!.findViewById(R.id.rv_frag_moment)
        rv_frag_moment?.visibility = View.INVISIBLE
        linearLayoutManager = LinearLayoutManager(activity)
        rv_frag_moment?.layoutManager = linearLayoutManager
        momentAdapter = MomentAdapter()
        recyclerAdapterWithHF = RecyclerAdapterWithHF(momentAdapter)
        recyclerAdapterWithHF!!.addFooter(
            LayoutInflater.from(activity).inflate(R.layout.item_load_more, null, false)
        )
        rv_frag_moment?.adapter = recyclerAdapterWithHF
        val rl_frag_comment: AutoRelativeLayout = contentView!!.findViewById(R.id.rl_frag_comment)
        rl_frag_comment.recyclerView = rv_frag_moment
        rl_frag_comment.onRefreshListener = object :
            AutoRelativeLayout.OnRefreshListener {
            override fun onRefresh() {
                setData(false)
            }

            override fun onLoadMore() {
                setData(true)
            }
        }
        momentAdapter!!.notifyDataSetChanged()
        initData()
    }

    fun initData() {
        instance!!.Get(ApiAction.GET_USER + userName, object : NetCallBack() {
            override fun onSuccess(response: String?) {
                userBean = gson.fromJson(response, UserBean::class.java)
                setData(false)
            }
        })
        instance!!.Get(
            ApiAction.GET_USER + userName + ApiAction.TWEETS,
            object : NetCallBack() {
                override fun onSuccess(response: String?) {
                    momentBeanList = gson.fromJson<List<MomentBean>>(
                        response,
                        object : TypeToken<List<MomentBean?>?>() {}.type
                    )
                    setData(false)
                }
            })
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun setData(isLoadMore: Boolean) {
        if (isLoadMore) {
            page++
            if (momentBeanList != null) {
                for (i in loadMorePos until momentBeanList!!.size) {
                    loadMorePos++
                    if (momentBeanList!![i].isLegalMoment()) {
                        momentAdapter!!.addData(
                            ItemDataBean(
                                MomentAdapter.TYPE_CONTENT,
                                momentBeanList!![i]
                            )
                        )
                    }
                    if (momentAdapter!!.itemCount == 5 * page + 1) {
                        break
                    }
                }
            }
            momentAdapter!!.updateChange()
        } else {
            Log.e("fffff","fffffff")
            momentAdapter!!.clear()
            page = 1
            loadMorePos = 0
            if (userBean != null && momentBeanList != null) {
                momentAdapter!!.addData(ItemDataBean(MomentAdapter.TYPE_USER, userBean!!))
                for (i in momentBeanList!!.indices) {
                    loadMorePos++
                    if (momentBeanList!![i].isLegalMoment()) {
                        momentAdapter!!.addData(
                            ItemDataBean(
                                MomentAdapter.TYPE_CONTENT,
                                momentBeanList!![i]
                            )
                        )
                    }
                    if (momentAdapter!!.itemCount == 5 * page + 1) {
                        break
                    }
                }
                momentAdapter!!.updateChange()
                rv_frag_moment?.visibility = View.VISIBLE
                rv_frag_moment?.viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
                    override fun onGlobalLayout() {
                        rv_frag_moment?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
                        rv_frag_moment?.smoothScrollBy(
                            0,
                            UIUtils.width * 7 / 16
                        )
                    }
                })
            }
        }
    }
}