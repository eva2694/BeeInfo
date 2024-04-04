package si.uni_lj.fe.mis.tobeeornottobee

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import si.uni_lj.fe.mis.tobeeornottobee.data.room.AppDatabase
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject
constructor(
    val roomDB: AppDatabase
) : ViewModel() {


}