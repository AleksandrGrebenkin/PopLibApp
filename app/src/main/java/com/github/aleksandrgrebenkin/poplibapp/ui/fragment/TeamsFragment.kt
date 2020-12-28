package com.github.aleksandrgrebenkin.poplibapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aleksandrgrebenkin.poplibapp.databinding.FragmentTeamsBinding
import com.github.aleksandrgrebenkin.poplibapp.mvp.presenter.TeamsPresenter
import com.github.aleksandrgrebenkin.poplibapp.mvp.view.TeamsView
import com.github.aleksandrgrebenkin.poplibapp.ui.App
import com.github.aleksandrgrebenkin.poplibapp.ui.BackButtonListener
import com.github.aleksandrgrebenkin.poplibapp.ui.adapter.TeamsRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class TeamsFragment : MvpAppCompatFragment(), TeamsView, BackButtonListener {

    companion object {
        fun newInstance() = TeamsFragment()
    }

    private var _binding: FragmentTeamsBinding? = null
    private val binding
        get() = _binding!!

    private val presenter by moxyPresenter {
        TeamsPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var adapter: TeamsRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvTeams.layoutManager = LinearLayoutManager(context)
        adapter = TeamsRVAdapter(presenter.teamsListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
        binding.rvTeams.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(text: String) {
        Log.e("TEAMS_FRAGMENT_ERROR", text)
    }

    override fun backPressed() = presenter.backPressed()
}