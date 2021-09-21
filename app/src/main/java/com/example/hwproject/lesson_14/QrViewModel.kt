package com.example.hwproject.lesson_14

import android.content.Context
import android.util.Log
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception
import java.util.concurrent.ExecutionException

class QrViewModel : ViewModel() {

    lateinit var context: Context

    private var cameraProviderLiveData: MutableLiveData<ProcessCameraProvider>? = null

    val processCameraProvider: LiveData<ProcessCameraProvider>
        get() {
            if (cameraProviderLiveData == null) {
                cameraProviderLiveData = MutableLiveData()
                val cameraProviderPromise = ProcessCameraProvider.getInstance(
                    context
                )
                cameraProviderPromise.addListener(
                    {
                        try {
                            cameraProviderLiveData!!.value = cameraProviderPromise.get()
                        } catch (e: ExecutionException) {
                            Log.e("CameraException", "Problem with Camera Provider", e)
                        } catch (e: Exception) {
                            Log.e("CameraException", "Problem with Camera", e)
                        }
                    },
                    ContextCompat.getMainExecutor(context)
                )


            }
            return cameraProviderLiveData!!
        }
}
