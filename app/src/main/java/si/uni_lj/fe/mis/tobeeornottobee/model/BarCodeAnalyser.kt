package si.uni_lj.fe.mis.tobeeornottobee.model

import android.graphics.ImageFormat
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class BarCodeAnalyser(
    val callback: (List<Barcode>
    ) -> Unit
) : ImageAnalysis.Analyzer {
    @OptIn(ExperimentalGetImage::class) override fun analyze(imageProxy: ImageProxy) {
        val options = BarcodeScannerOptions.Builder()
            .build()

        val scanner = BarcodeScanning.getClient(options)
        val image = imageProxy
        image.image?.let {
            if (image.format == ImageFormat.YUV_420_888){

            val imageToProcess = (InputImage.fromBitmap(image.toBitmap(), image.imageInfo.rotationDegrees))

            scanner.process(imageToProcess)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.size > 0) {
                        callback(barcodes)


                    }
                }
                .addOnFailureListener {
                    image.image?.close()
                    image.close()
                    // Task failed with an exception
                    // ...
                }
        }
        imageProxy.close()
    }
    }
}