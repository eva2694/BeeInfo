package si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.study.funFactScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import si.uni_lj.fe.mis.tobeeornottobee.R
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class FanFactsVM @Inject constructor(
    private val context: Context
): ViewModel() {

    fun readFactsFromXml(): List<Fact> {
        val factsList = mutableListOf<Fact>()
        var inputStream: InputStream? = null
        try {
            inputStream = context.resources.openRawResource(R.raw.bee_fun_fact_v4)
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = false
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            var currentFact: Fact? = null
            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        if (parser.name == "fact") {
                            currentFact = Fact()
                        } else if (parser.name == "title") {
                            currentFact?.title = parser.nextText()
                        } else if (parser.name == "description") {
                            currentFact?.description = parser.nextText()
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if (parser.name == "fact") {
                            currentFact?.let { factsList.add(it) }
                        }
                    }
                }
                eventType = parser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
        }
        return factsList
    }


}

data class Fact(
    var title: String = "",
    var description: String = ""
)