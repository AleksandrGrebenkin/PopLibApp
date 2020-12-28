package com.github.aleksandrgrebenkin.poplibapp.mvp.presenter

import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.Team
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.TeamPlayer
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.repo.IPlayerRepo
import com.github.aleksandrgrebenkin.poplibapp.mvp.presenter.list.ITeamPlayersListPresenter
import com.github.aleksandrgrebenkin.poplibapp.mvp.view.TeamPlayersView
import com.github.aleksandrgrebenkin.poplibapp.mvp.view.list.TeamPlayerItemView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class TeamPlayersPresenter(private val team: Team) : MvpPresenter<TeamPlayersView>() {

    @Inject
    lateinit var playerRepo: IPlayerRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var uiScheduler: Scheduler

    class TeamPlayersListPresenter : ITeamPlayersListPresenter {
        val teamPlayers = mutableListOf<TeamPlayer>()

        override var itemClickListener: ((TeamPlayerItemView) -> Unit)? = null

        override fun bindView(view: TeamPlayerItemView) {
            val teamPlayer = teamPlayers[view.pos]
            view.setName(teamPlayer.name?:"unknown")
            view.setGamesPlayed(teamPlayer.gamesPlayed.toString())
            view.setWins(teamPlayer.wins.toString())
        }

        override fun getCount() = teamPlayers.size
    }

    val teamPlayersListPresenter = TeamPlayersListPresenter()
    var getTeamPlayersDisposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        getTeamPlayersDisposable = playerRepo.getTeamPlayers(team)
            .observeOn(uiScheduler)
            .subscribe(
                { teamPlayers ->
                    teamPlayersListPresenter.teamPlayers.clear()
                    teamPlayersListPresenter.teamPlayers.addAll(teamPlayers)
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
        getTeamPlayersDisposable?.dispose()
    }

}