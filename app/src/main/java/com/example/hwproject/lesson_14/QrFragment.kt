package com.example.hwproject.lesson_14

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hwproject.databinding.FragmentQrBinding
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors

class QrFragment : Fragment() {

    private lateinit var binding: FragmentQrBinding
    private var cameraProvider: ProcessCameraProvider? = null
    private val viewModel: QrViewModel by lazy {
        ViewModelProvider(this).get(QrViewModel::class.java)
    }

    companion object {
        fun newInstance() = QrFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentQrBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        viewModel.context = requireContext()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCamera()
    }

    private fun setupCamera() {

        val lensFacing = CameraSelector.LENS_FACING_BACK
        val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
        viewModel.processCameraProvider.observe(viewLifecycleOwner, { provider ->
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
            .setTargetRotation(binding.preview.display.rotation)
            .build()
        previewUseCase.setSurfaceProvider(binding.preview.surfaceProvider)

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
            .setTargetRotation(binding.preview.display.rotation)
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