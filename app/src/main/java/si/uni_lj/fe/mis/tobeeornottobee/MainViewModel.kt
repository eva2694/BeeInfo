package si.uni_lj.fe.mis.tobeeornottobee

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import si.uni_lj.fe.mis.tobeeornottobee.data.room.AppDatabase
import si.uni_lj.fe.mis.tobeeornottobee.model.hives.room.HiveDbEntity
import javax.inject.Inject


@HiltViewModel
@SuppressLint("CustomSplashScreen")
class MainViewModel @Inject
constructor(
    val roomDB: AppDatabase
) : ViewModel() {

    init {


    }
    val currentHive: MutableLiveData<HiveDbEntity> by lazy {
        MutableLiveData<HiveDbEntity>(
            HiveDbEntity(name = "hive 1",
            imageURL = "https://images.unsplash.com/photo-1504392022767-a8fc0771f239?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            latitude =1.35,
            longitude = 103.87,
            humidity = 50.9f,
            temperature = 36.6f,
            beeCount = 65000,
            isCollect = true,
            voc = 0
        ))
    }

    private val dao = roomDB.getHiveDao()

    val allHives = dao.getHives()


    fun addHive(hive: HiveDbEntity){
        viewModelScope.launch(Dispatchers.IO) {
            dao.addHive(hive)
        }
    }
    fun updateHive(hive: HiveDbEntity){
        viewModelScope.launch(Dispatchers.IO) {
            dao.updateHive(hive)
        }
    }
}