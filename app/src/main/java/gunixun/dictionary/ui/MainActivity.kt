package gunixun.dictionary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import gunixun.dictionary.R
import gunixun.dictionary.databinding.ActivityMainBinding
import gunixun.dictionary.domain.entities.DataModel
import gunixun.dictionary.ui.translation.TranslationFragment
import gunixun.dictionary.ui.details.DetailsFragment
import gunixun.dictionary.ui.history.HistoryFragment

class MainActivity :
    AppCompatActivity(),
    TranslationFragment.Controller
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        binding.bottomNavigationView.setOnItemSelectedListener {
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

    override fun openTranslationDetailsScreen(data: DataModel) {
        navigationTo(DetailsFragment.newInstance(data), true)
    }
}