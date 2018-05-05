package com.example.nursultan.testapp.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.nursultan.testapp.R
import com.example.nursultan.testapp.ui.base.BaseFragment
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailFragment : BaseFragment(), DetailMvpView {
    private var mPathParam: String? = null

    @BindView(R.id.iv)
    lateinit var iv: ImageView

    @Inject
    lateinit var picasso: Picasso
    @Inject
    lateinit var presenter: DetailPresenter<DetailMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mPathParam = arguments!!.getString(PATH_PARAM)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_photo_detail, container, false)
        val component = activityComponent
        if (component != null) {
            component.inject(this)
            setUnBinder(ButterKnife.bind(this, view))
            presenter.onAttach(this)
        }
        return view
    }

    override fun loadImage() {
        picasso.load(mPathParam).into(iv)
    }

    override fun setUp(view: View) {
        baseActivity!!.hideKeyboard()
        presenter.onViewPrepared()
    }

    companion object {

        private val PATH_PARAM = "path_param"


        fun newInstance(path: String): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putString(PATH_PARAM, path)
            fragment.arguments = args
            return fragment
        }
    }

}
