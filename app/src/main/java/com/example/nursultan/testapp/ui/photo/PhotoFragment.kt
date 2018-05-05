package com.example.nursultan.testapp.ui.photo

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.nursultan.testapp.R
import com.example.nursultan.testapp.dummy.DummyContent
import com.example.nursultan.testapp.ui.base.BaseFragment
import com.example.nursultan.testapp.ui.detail.DetailFragment
import javax.inject.Inject

class PhotoFragment : BaseFragment(), PhotoAdapter.Callback, PhotoMvpView {

    @BindView(R.id.list)
    lateinit var recyclerView: RecyclerView
    @BindView(R.id.go_btn)
    lateinit var goBtn: Button
    @BindView(R.id.autoCompleteTextView)
    lateinit var _et: AutoCompleteTextView

    @Inject
    lateinit var presenter: PhotoMvpPresenter<PhotoMvpView>
    @Inject
    lateinit var adapter: PhotoAdapter
    @Inject
    lateinit var manager: GridLayoutManager
    @Inject
    lateinit var suggestionsAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_photo_list, container, false)
        val component = activityComponent
        if (component != null) {
            component.inject(this)
            setUnBinder(ButterKnife.bind(this, view))
            presenter.onAttach(this)
            adapter.setCallback(this)
        }
        return view
    }


    override fun setUp(view: View) {
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        _et.threshold = 1
        _et.setAdapter(suggestionsAdapter)
        presenter.onViewPrepared()
    }

    override fun updateData(photoList: List<DummyContent.DummyItem>) {
        adapter.swapItems(photoList)
    }

    override fun updateSuggestions(list: List<String>) {
        suggestionsAdapter.clear()
        suggestionsAdapter.addAll(list)
    }

    @OnClick(R.id.go_btn)
    fun click() {
        presenter.insertSearchQuery(_et.text.toString())
        presenter.requestSearchUpdate(_et.text.toString())
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    override fun showFull(path: String) {
        baseActivity!!.supportFragmentManager.beginTransaction().replace(R.id.frame_container, DetailFragment.newInstance(path))
                .addToBackStack(null)
                .commit()
    }

    override fun onPhotoClick(item: DummyContent.DummyItem) {
        presenter.goDetail(item.path)
    }

    companion object {

        private val TAG = "PhotoFragment"


        fun newInstance(): PhotoFragment {
            val fragment = PhotoFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}

