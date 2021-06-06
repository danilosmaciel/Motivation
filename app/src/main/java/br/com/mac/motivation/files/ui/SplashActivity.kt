package br.com.mac.motivation.files.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.mac.motivation.R
import br.com.mac.motivation.files.infra.MotivationConstants
import br.com.mac.motivation.files.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mbPreferences : SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(supportActionBar != null){
            supportActionBar!!.hide()
        }
        splashBtSalvar.setOnClickListener(this)
        mbPreferences = SecurityPreferences(this)
    }

    override fun onClick(view: View?) {
        if(view?.id == splashBtSalvar.id){
            handleSave()
        }
    }

    private fun handleSave() {
        val name = splash_edtNome.text.toString()
        if( name != ""){

            mbPreferences.storeString(MotivationConstants.KEY.NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            Toast.makeText(this, "Nome inválido!", Toast.LENGTH_SHORT ).show()
        }
    }

}