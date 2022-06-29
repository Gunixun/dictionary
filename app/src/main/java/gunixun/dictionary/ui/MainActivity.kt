package gunixun.dictionary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import gunixun.dictionary.R
import gunixun.translation.TranslationFragment
import gunixun.details.DetailsFragment
import gunixun.history.HistoryFragment
import gunixun.model.DataModel
import gunixun.model.History
import gunixun.utils.viewById

class MainActivity :
    AppCompatActivity(),
    TranslationFragment.Controller,
    HistoryFragment.Controller
{

    private val bottomNavigationView by viewById<BottomNavigationView>(R.id.bottom_navigation_view)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            setHomePage()
        }
        initBottomNavigationView()
    }

    private fun navigationTo(fragment: Fragment, withTransaction: Boolean = false) {
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

    private fun setHomePage() {
        navigationTo(TranslationFragment.newInstance())
    }

    private fun initBottomNavigationView() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_home -> {
                    setHomePage()
                    true
                }
                R.id.bottom_view_history -> {
                    navigationTo(HistoryFragment.newInstance())
                    true
                }
                else -> true
            }
        }
    }

    override fun openDetailsScreen(data: DataModel) {
        navigationTo(DetailsFragment.newInstance(data.text), true)
    }

    override fun openDetailsScreen(history: History) {
        navigationTo(DetailsFragment.newInstance(history.word), true)
    }
}