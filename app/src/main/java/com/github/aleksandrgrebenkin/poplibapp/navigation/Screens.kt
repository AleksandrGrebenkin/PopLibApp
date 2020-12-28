package com.github.aleksandrgrebenkin.poplibapp.navigation

import androidx.fragment.app.Fragment
import com.github.aleksandrgrebenkin.poplibapp.mvp.model.entity.Team
import com.github.aleksandrgrebenkin.poplibapp.ui.fragment.TeamPlayersFragment
import com.github.aleksandrgrebenkin.poplibapp.ui.fragment.TeamsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class TeamsScreen() : SupportAppScreen() {
        override fun getFragment() = TeamsFragment.newInstance()
    }

    class TeamPlayersScreen(val team: Team) : SupportAppScreen() {
        override fun getFragment() = TeamPlayersFragment.newInstance(team)
    }
}