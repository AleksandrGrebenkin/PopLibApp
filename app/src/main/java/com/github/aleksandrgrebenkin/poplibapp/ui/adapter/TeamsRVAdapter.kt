package com.github.aleksandrgrebenkin.poplibapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.github.aleksandrgrebenkin.poplibapp.databinding.ItemTeamBinding
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.image.IImageLoader
import com.github.aleksandrgrebenkin.poplibapp.mvp.presenter.list.ITeamsListPresenter
import com.github.aleksandrgrebenkin.poplibapp.mvp.view.list.TeamItemView
import kotlinx.android.extensions.LayoutContainer
import javax.inject.Inject

class TeamsRVAdapter(
    val presenter: ITeamsListPresenter
) : RecyclerView.Adapter<TeamsRVAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemTeamBinding = ItemTeamBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemTeamBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root), LayoutContainer, TeamItemView {

        override val containerView = binding.root
        override var pos = -1

        override fun setName(text: String) {
            binding.tvName.text = text
        }

        override fun setTag(text: String) {
            binding.tvTag.text = text
        }

        override fun setRating(text: String) {
            binding.tvRating.text = text
        }

        override fun setWins(text: String) {
            binding.tvWins.text = text
        }

        override fun setLosses(text: String) {
            binding.tvLosses.text = text
        }

        override fun loadImage(url: String) {
            imageLoader.loadInto(url, binding.ivImage)
        }
    }
}