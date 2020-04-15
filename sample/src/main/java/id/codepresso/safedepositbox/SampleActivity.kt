package id.codepresso.safedepositbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class SampleActivity : AppCompatActivity() {

    private val safeDepositBox by lazy {
        SafeDepositBox(this, "SafeDepositBoxSample")
    }

    private val stringLogger by lazy {
        StringBuilder().append("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        safeDepositBox.storeListString("listPeople", listOf("Sule", "Andre", "Aziz"))
        stringLogger.append("Store List People")
        val user = safeDepositBox.getListString("listPeople")
        stringLogger.append("\n").append("Get List People").append("\n").append(user.toString())

        updateLogView()
    }

    private fun updateLogView() {
        tvLogger.text = stringLogger
    }
}