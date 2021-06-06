package br.com.mac.motivation.files.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.mac.motivation.R
import br.com.mac.motivation.files.infra.MotivationConstants
import br.com.mac.motivation.files.infra.SecurityPreferences
import br.com.mac.motivation.files.repository.MockPhrases
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mSecurityPreferences: SecurityPreferences
    lateinit var mListFilters : List<Int>
    lateinit var mMockPhrase: MockPhrases
    var mColorWhite : Int = 0
    var mColorAccent : Int = 0
    var mPhaseFilter : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)
        mListFilters = listOf(mainImvAll.id, mainImvHappy.id, mainImvMorning.id)
        mColorWhite = resources.getColor(R.color.white)
        mColorAccent = resources.getColor(R.color.colorAccent)

        configListenters()
        mMockPhrase = MockPhrases()

        loadWelcomeUser()
        loadInitialPhrase()
    }

    private fun loadWelcomeUser() {
        mainTtvTexto.text = "Bem vindo ${mSecurityPreferences.getString(MotivationConstants.KEY.NAME)}!"
    }

    private fun loadInitialPhrase() {
        handleFilter(mainImvAll.id)
    }

    private fun configListenters() {
        mainImvAll.setOnClickListener(this)
        mainImvHappy.setOnClickListener(this)
        mainImvMorning.setOnClickListener(this)
        mainBtnNewPhrase.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val viewId = view!!.id

        when (viewId) {
            mainBtnNewPhrase.id -> handleNewPhrase()
            in mListFilters -> handleFilter(viewId)
        }
    }

    private fun handleFilter(viewId: Int) {


        mainImvAll.setColorFilter(mColorWhite)
        mainImvHappy.setColorFilter(mColorWhite)
        mainImvMorning.setColorFilter(mColorWhite)

        when(viewId){
           mainImvAll.id -> {
               mainImvAll.setColorFilter(mColorAccent)
               mPhaseFilter = MotivationConstants.PHRASEFILTER.ALL
           }
           mainImvHappy.id -> {
               mainImvHappy.setColorFilter(mColorAccent)
               mPhaseFilter = MotivationConstants.PHRASEFILTER.HAPPY
           }
           mainImvMorning.id -> {
               mainImvMorning.setColorFilter(mColorAccent)
               mPhaseFilter = MotivationConstants.PHRASEFILTER.MORNING
           }
       }
        handleNewPhrase()
    }

    private fun handleNewPhrase() {
        mainTtvPhrases.text = mMockPhrase.getPhrase(mPhaseFilter)
    }


}