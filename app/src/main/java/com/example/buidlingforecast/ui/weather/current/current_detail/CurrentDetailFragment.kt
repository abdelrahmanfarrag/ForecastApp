package com.example.buidlingforecast.ui.weather.current.current_detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.buidlingforecast.R

class CurrentDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentDetailFragment()
    }

    private lateinit var viewModel: CurrentDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
