package gunixun.dictionary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import gunixun.dictionary.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun navigationTo(fragment: Fragment, withTransaction: Boolean) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.push_up_out,
                R.anim.push_up_in,
                R.anim.slide_out
            )
            .replace(R.id.container, fragment)

        if (withTransaction) {
            transaction.addToBackStack("Transaction")
        }

        transaction.commit()
    }
}