package com.github.aleksandrgrebenkin.poplibapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.aleksandrgrebenkin.poplibapp.databinding.ItemTeamPlayerBinding
import com.github.aleksandrgrebenkin.poplibapp.mvp.presenter.list.ITeamPlayersListPresenter
import com.github.aleksandrgrebenkin.poplibapp.mvp.view.list.TeamPlayerItemView
import kotlinx.android.extensions.LayoutContainer

class TeamPlayersRVAdapter(val presenter: ITeamPlayersListPresenter) :
    RecyclerView.Adapter<TeamPlayersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemTeamPlayerBinding = ItemTeamPlayerBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemTeamPlayerBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val binding: ItemTeamPlayerBinding) :
        RecyclerView.ViewHolder(binding.root), LayoutContainer, TeamPlayerItemView {

        override val containerView = binding.root
        override var pos = -1

        override fun setName(text: String) {
            binding.tvName.text = text
        }

        override fun setGamesPlayed(text: String) {
            binding.tvGamesPlayed.text = text
        }

        override fun setWins(text: String) {
            binding.tvWins.text = text
        }
    }
}