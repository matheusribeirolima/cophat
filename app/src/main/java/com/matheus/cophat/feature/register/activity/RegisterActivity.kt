package com.matheus.cophat.feature.register.activity

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import com.matheus.cophat.R
import com.matheus.cophat.databinding.ActivityRegisterBinding
import com.matheus.cophat.feature.register.viewmodel.RegisterViewModel
import com.matheus.cophat.ui.BaseActivity
import com.matheus.cophat.ui.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {

    private val viewModel: RegisterViewModel by viewModel()

    private val args: RegisterActivityArgs by navArgs()

    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun initBinding() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fNavHostRegister) as NavHostFragment
        val graph =
            navHostFragment.navController.navInflater.inflate(R.navigation.nav_register)

        if (args.step == 1) {
            graph.startDestination = R.id.registerParentsFragment
        } else if (args.step == 2) {
            graph.startDestination = R.id.registerPatientFragment
        }
        navHostFragment.navController.graph = graph
    }
}