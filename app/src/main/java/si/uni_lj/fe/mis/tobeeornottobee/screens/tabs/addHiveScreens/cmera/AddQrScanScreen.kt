package si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.addHiveScreens.cmera

import android.content.Context
import android.hardware.camera2.CameraManager
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import si.uni_lj.fe.mis.tobeeornottobee.model.BarCodeAnalyser
import java.util.concurrent.Executors

@Composable
fun AddQrScanScreen(inTabNavigation: NavHostController, paddingValues: PaddingValues) {
    val cameraSelector =  CameraSelector.DEFAULT_BACK_CAMERA

    AndroidView(
        modifier = Modifier
            // .size(width = 250.dp, height = 250.dp)
            .fillMaxWidth(),
        factory = { context ->
            val cameraManager =
                context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

            //cameraManager.setTorchMode(cameraManager.cameraIdList[0], true)
            val cameraExecutor = Executors.newSingleThreadExecutor()
            val previewView = PreviewView(context).also {
                it.scaleType = PreviewView.ScaleType.FILL_CENTER
            }
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder()

                    .build()
                    .also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }

                val imageCapture = ImageCapture.Builder().build()


                val imageAnalyzer = ImageAnalysis.Builder()
                    .build()
                    .also {
                        it.setAnalyzer(cameraExecutor, BarCodeAnalyser { barcodes ->

                            Toast.makeText(
                                context,
                                "${barcodes[0].displayValue}|| ${barcodes.size}",
                                Toast.LENGTH_SHORT
                            ).show()
                            barcodes.forEach { barcode ->
                            }

                            cameraProvider.unbindAll()

                            CoroutineScope(Dispatchers.Default).launch {
                                delay(2000)

                                withContext(Dispatchers.Main) {
                                    try {
                                        // Unbind use cases before rebinding


                                        // Bind use cases to camera
                                        cameraProvider.bindToLifecycle(
                                            context as ComponentActivity,
                                            cameraSelector,
                                            preview,
                                            imageCapture,
                                            it
                                        )// context

                                    } catch (exc: Exception) {
                                        Log.e("DEBUG", "Use case binding failed", exc)
                                    }
                                }

                            }


                        })
                    }



                try {
                    // Unbind use cases before rebinding
                    cameraProvider.unbindAll()

                    // Bind use cases to camera
                    cameraProvider.bindToLifecycle(
                        context as ComponentActivity,
                        cameraSelector,
                        preview,
                        imageCapture,
                        imageAnalyzer
                    )// context

                } catch (exc: Exception) {
                    Log.e("DEBUG", "Use case binding failed", exc)
                }
            }, ContextCompat.getMainExecutor(context))
            previewView
        },

        )
}