package id.codepresso.safedepositbox.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.codepresso.safedepositbox.SafeDepositBox
import kotlinx.android.synthetic.main.activity_main.*

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

        val tatang = User("Tatang", "Sutarna")
        val bejo = User("Bejo", "Surojo")

        safeDepositBox.apply {
            storeObject("tatang", tatang)
            storeObject("bejo", bejo)
        }

        stringLogger.append("Storing Object Value\n\n")

        val tatangFromPref = safeDepositBox.getObject<User>("tatang")
        val bejoFromPref = safeDepositBox.getObject<User>("bejo")

        stringLogger.append("Getting Object Value\n\n")
        stringLogger.append(tatangFromPref.toString())
        stringLogger.append("\n")
        stringLogger.append(bejoFromPref.toString())

        updateLogView()
    }

    private fun updateLogView() {
        tvLogger.text = stringLogger
    }
}