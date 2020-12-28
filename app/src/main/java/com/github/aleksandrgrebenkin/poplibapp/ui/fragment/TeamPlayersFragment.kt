package com.github.aleksandrgrebenkin.poplibapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aleksandrgrebenkin.poplibapp.databinding.FragmentTeamPlayersBinding
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.Team
import com.github.aleksandrgrebenkin.poplibapp.mvp.presenter.TeamPlayersPresenter
import com.github.aleksandrgrebenkin.poplibapp.mvp.view.TeamPlayersView
import com.github.aleksandrgrebenkin.poplibapp.ui.App
import com.github.aleksandrgrebenkin.poplibapp.ui.BackButtonListener
import com.github.aleksandrgrebenkin.poplibapp.ui.adapter.TeamPlayersRVAdapter
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class TeamPlayersFragment : MvpAppCompatFragment(), TeamPlayersView, BackButtonListener {

    companion object {
        fun newInstance(team: Team) = TeamPlayersFragment().apply {
            arguments = Bundle().apply {
                this.putParcelable(TEAM_KEY, team)
            }
        }

        const val TEAM_KEY = "TEAM"
    }

    private var _binding: FragmentTeamPlayersBinding? = null
    private val binding
        get() = _binding!!

    private val presenter by moxyPresenter {
        val team: Team =
            arguments?.getParcelable<Team>(TEAM_KEY) as Team
        TeamPlayersPresenter(team).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var adapter: TeamPlayersRVAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamPlayersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onStart() {
        super.onStart()
        (requireActivity() as MvpAppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as MvpAppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvPlayers.layoutManager = LinearLayoutManager(context)
        adapter = TeamPlayersRVAdapter(presenter.teamPlayersListPresenter)
        binding.rvPlayers.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        Log.e("TEAM_PLAYER_ERROR", text)
    }

    override fun backPressed() = presenter.backPressed()

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> backPressed()
        else -> super.onOptionsItemSelected(item)
    }
}