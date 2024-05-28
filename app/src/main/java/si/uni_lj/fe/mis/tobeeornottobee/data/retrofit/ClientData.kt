package si.uni_lj.fe.mis.tobeeornottobee.data.retrofit

data class ClientData(
    val client: Client
)

data class Client(
    val Longitude: Double,
    val Latitude: Double,
    val BatteryLevel: Int,
    val Movement: Int,
    val Humidity: Double,
    val Temperature: Double,
    val LightLevel: Int,
    val BeeCount: Int
)