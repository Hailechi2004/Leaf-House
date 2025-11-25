package com.example.leafhouse

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.ByteBuffer
import java.nio.ByteOrder

data class Classification(
    val label: String,
    val confidence: Float
)

class ImageClassifier(private val context: Context) {
    
    private var interpreter: Interpreter? = null
    private var labels: List<String> = listOf()
    
    companion object {
        private const val MODEL_NAME = "model.tflite"
        private const val LABEL_FILE = "labels.txt"
        private const val INPUT_SIZE = 256
        private const val PIXEL_SIZE = 3 // RGB
        private const val IMAGE_MEAN = 0f
        private const val IMAGE_STD = 255f
    }
    
    init {
        setupInterpreter()
        loadLabels()
    }
    
    private fun setupInterpreter() {
        try {
            val options = Interpreter.Options().apply {
                setNumThreads(4)
            }
            val model = FileUtil.loadMappedFile(context, MODEL_NAME)
            interpreter = Interpreter(model, options)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    
    private fun loadLabels() {
        try {
            val reader = BufferedReader(InputStreamReader(context.assets.open(LABEL_FILE)))
            labels = reader.readLines()
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    
    fun classifyImage(bitmap: Bitmap): Classification? {
        if (interpreter == null || labels.isEmpty()) {
            return null
        }
        
        try {
            // Resize bitmap to model input size
            val resizedBitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, true)
            
            // Create TensorImage and process
            val tensorImage = TensorImage.fromBitmap(resizedBitmap)
            val imageProcessor = ImageProcessor.Builder()
                .add(ResizeOp(INPUT_SIZE, INPUT_SIZE, ResizeOp.ResizeMethod.BILINEAR))
                .add(NormalizeOp(IMAGE_MEAN, IMAGE_STD))
                .build()
            
            val processedImage = imageProcessor.process(tensorImage)
            
            // Create output array
            val output = Array(1) { FloatArray(labels.size) }
            
            // Run inference
            interpreter?.run(processedImage.buffer, output)
            
            // Find the class with highest confidence
            val confidences = output[0]
            val maxIndex = confidences.indices.maxByOrNull { confidences[it] } ?: 0
            val maxConfidence = confidences[maxIndex]
            
            return Classification(
                label = labels[maxIndex],
                confidence = maxConfidence
            )
            
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
    
    fun close() {
        interpreter?.close()
        interpreter = null
    }
}
