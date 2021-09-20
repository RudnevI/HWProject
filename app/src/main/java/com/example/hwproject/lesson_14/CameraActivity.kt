package com.example.hwproject.lesson_14

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.hwproject.R
import com.example.hwproject.databinding.ActivityCameraBinding
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    private val viewModel: QrViewModel by lazy {
        ViewModelProvider(this).get(QrViewModel::class.java)
    }
    private var cameraProvider: ProcessCameraProvider? = null
    private val isCameraPermissionGranted: Boolean
        get() = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewModel.context = this
        setupCamera()
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkCamera()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            if (!isCameraPermissionGranted) {
                Toast.makeText(
                    this,
                    "Camera Access Is Needed to Continue",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }

    private fun checkCamera() {
        if (isCameraPermissionGranted) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                PERMISSION_REQUEST
            )
        }
    }

    companion object {
        private const val PERMISSION_REQUEST = 1
    }

    private fun startCamera() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<QrFragment>(R.id.container)
        }
    }

    private fun setupCamera() {

        val lensFacing = CameraSelector.LENS_FACING_BACK
        val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
        viewModel.processCameraProvider.observe(this, { provider ->
            cameraProvider = provider

        })
        bindPreview()
        scanForResult(cameraSelector)
    }

    private fun scanForResult(cameraSelector: CameraSelector) {
        if (cameraProvider == null) {
            return
        }
        val previewUseCase = Preview.Builder()
            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
            .setTargetRotation(binding.container.display.rotation)
            .build()
        previewUseCase.setSurfaceProvider(binding.container.surfaceProvider)

        try {
            cameraProvider?.bindToLifecycle(this, cameraSelector, previewUseCase)
        } catch (e: Exception) {
            Log.e("CameraActivity", e.localizedMessage!!)
        }
    }

    private fun bindPreview() {
        val scanOptions = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
        val scanner = BarcodeScanning.getClient(scanOptions)
        val analysisUseCase = ImageAnalysis.Builder()
            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
            .setTargetRotation(binding.container.display.rotation)
            .build()
        val cameraExecutor = Executors.newSingleThreadExecutor()
        analysisUseCase.setAnalyzer(cameraExecutor, { imageProxy ->
            processImageProxy(scanner, imageProxy)
        })
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun processImageProxy(scanner: BarcodeScanner, imageProxy: ImageProxy) {
        val inputImage = InputImage.fromMediaImage(
            imageProxy.image!!,
            imageProxy.imageInfo.rotationDegrees
        )
        scanner.process(inputImage).addOnSuccessListener { barcodes ->
            barcodes.forEach {
                println("#A $it")
            }
        }.addOnFailureListener {
            Log.e("CameraActivity", it.localizedMessage!!)
        }.addOnCompleteListener {
            imageProxy.close()
        }
    }


}