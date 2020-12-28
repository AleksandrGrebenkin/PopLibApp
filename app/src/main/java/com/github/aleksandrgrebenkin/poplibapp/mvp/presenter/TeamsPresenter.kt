package com.github.aleksandrgrebenkin.poplibapp.mvp.presenter

import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.Team
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.repo.ITeamsRepo
import com.github.aleksandrgrebenkin.poplibapp.mvp.presenter.list.ITeamsListPresenter
import com.github.aleksandrgrebenkin.poplibapp.mvp.view.TeamsView
import com.github.aleksandrgrebenkin.poplibapp.mvp.view.list.TeamItemView
import com.github.aleksandrgrebenkin.poplibapp.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import javax.inject.Inject

class TeamsPresenter : MvpPresenter<TeamsView>() {

    @Inject
    lateinit var teamsRepo: ITeamsRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var uiScheduler: Scheduler

    class TeamsListPresenter : ITeamsListPresenter {
        val teams = mutableListOf<Team>()

        override var itemClickListener: ((TeamItemView) -> Unit)? = null

        override fun bindView(view: TeamItemView) {
            val team = teams[view.pos]
            view.setName(team.name)
            view.setTag(team.tag)
            view.setRating(team.rating.toString())
            view.setWins(team.wins.toString())
            view.setLosses(team.losses.toString())
            team.logoUrl?.let { view.loadImage(it) }
        }

        override fun getCount() = teams.size
    }

    val teamsListPresenter = TeamsListPresenter()
    var getTeamsDisposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        teamsListPresenter.itemClickListener = { itemView ->
            val team = teamsListPresenter.teams[itemView.pos]
            router.navigateTo(Screens.TeamPlayersScreen(team))
        }
    }

    fun loadData() {
        getTeamsDisposable = teamsRepo.getTeams()
            .observeOn(uiScheduler)
            .subscribe(
                { teams ->
                    teamsListPresenter.teams.clear()
                    teamsListPresenter.teams.addAll(teams)
                    viewState.updateList()
                },
                { error ->
                    viewState.showError(error.message ?: error.stackTraceToString())
                }
            )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        getTeamsDisposable?.dispose()
    }
}