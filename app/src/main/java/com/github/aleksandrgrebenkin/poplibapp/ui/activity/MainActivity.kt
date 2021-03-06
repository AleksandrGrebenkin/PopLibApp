package com.github.aleksandrgrebenkin.poplibapp.ui.activity

import android.os.Bundle
import android.view.MenuItem
import com.github.aleksandrgrebenkin.poplibapp.databinding.ActivityMainBinding
import com.github.aleksandrgrebenkin.poplibapp.mvp.presenter.MainPresenter
import com.github.aleksandrgrebenkin.poplibapp.mvp.view.MainView
import com.github.aleksandrgrebenkin.poplibapp.ui.App
import com.github.aleksandrgrebenkin.poplibapp.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator by lazy {
        SupportAppNavigator(
            this,
            supportFragmentManager,
            binding.fragmentContainer.id
        )
    }

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }
}