package com.slctick.slctick.Share


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.slctick.slctick.R
import com.slctick.slctick.utils.EventbusDataEvents
import com.slctick.slctick.utils.UniversalImageLoader
import kotlinx.android.synthetic.main.fragment_share_next.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ShareNextFragment : Fragment() {
    var secilenResimYolu: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view = inflater.inflate(R.layout.fragment_share_next, container, false)
        UniversalImageLoader.setImage(secilenResimYolu!!,view!!.tvBirinciFoto!!,null,"file://")


        return view
    }


    //////////////////////////// EVENTBUS /////////////////////////////////
    @Subscribe(sticky = true)
    internal fun onSecilenResimEvent(secilenResim: EventbusDataEvents.PaylasilacakResmiGonder) {

        secilenResimYolu = secilenResim!!.resimYolu!!



    }




    override fun onAttach(context: Context?) {
        super.onAttach(context)
        EventBus.getDefault().register(this)
    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }




}
