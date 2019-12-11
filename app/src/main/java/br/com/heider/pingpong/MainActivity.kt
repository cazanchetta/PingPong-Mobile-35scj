package br.com.heider.pingpong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this)
            .get(MainViewModel::class.java)

        setUpView()

        mainViewModel.homeScore.observe(this, Observer {goalHome ->
            tvHomeScore.text = goalHome.toString()
        })

        mainViewModel.awayScore.observe(this, Observer {goalAway ->
            tvAwayScore.text = goalAway.toString()
        })

    }

    private fun setUpView() {
        tvHomeName.text = intent.getStringExtra("HOME_NAME")
        tvAwayName.text = intent.getStringExtra("AWAY_NAME")
        setUpListener()
    }

    private fun setUpListener() {
        btHomeScore.setOnClickListener{
            mainViewModel.goalHome()
            tvHomeScore.text = mainViewModel.homeScore.toString()
        }

        btAwayScore.setOnClickListener{
            mainViewModel.goalAway()
            tvAwayScore.text = mainViewModel.awayScore.toString()
        }
    }

//    private fun resetGame(){
//        mainViewModel.homeScore = 0
//        mainViewModel.awayScore = 0
//
//        tvHomeScore.text = mainViewModel.homeScore.toString()
//        tvAwayScore.text = mainViewModel.awayScore.toString()
//    }

}
