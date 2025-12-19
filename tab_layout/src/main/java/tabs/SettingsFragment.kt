package tabs

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ext.tab_layout.R

class SettingsFragment : Fragment(R.layout.fragment_simple_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.txtTitle).text = "Settings Page"
        view.findViewById<TextView>(R.id.txtDesc).text =
            "This is the Settings tab screen"
    }
}