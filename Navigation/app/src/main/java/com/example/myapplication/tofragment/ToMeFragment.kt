package com.example.myapplication.tofragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_to_me.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ToMeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ToMeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_to_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_me_content.text = "$param1"
        btn_to_shop.setOnClickListener {
            val bundle = Bundle().apply {
                putString(ARG_PARAM1, "to_shop")
            }
            Navigation.findNavController(it)
                .navigate(R.id.action_toMeFragment_to_toShopFragment, bundle)
        }
    }


}