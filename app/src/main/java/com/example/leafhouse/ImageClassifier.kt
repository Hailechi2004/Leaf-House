package com.example.leafhouse

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
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
        private const val MODEL_NAME = "models.tflite"
        private const val LABEL_FILE = "labels.txt"
        private const val INPUT_SIZE = 300  // Model requires 300x300 input
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
            
            // Log model info
            Log.d("ImageClassifier", "Model loaded successfully")
            Log.d("ImageClassifier", "Input shape: ${interpreter?.getInputTensor(0)?.shape()?.contentToString()}")
            Log.d("ImageClassifier", "Output shape: ${interpreter?.getOutputTensor(0)?.shape()?.contentToString()}")
        } catch (e: Exception) {
            Log.e("ImageClassifier", "Error loading model", e)
            e.printStackTrace()
        }
    }
    
    private fun loadLabels() {
        try {
            val reader = BufferedReader(InputStreamReader(context.assets.open(LABEL_FILE)))
            labels = reader.readLines()
            reader.close()
            Log.d("ImageClassifier", "Loaded ${labels.size} labels: $labels")
        } catch (e: Exception) {
            Log.e("ImageClassifier", "Error loading labels", e)
            e.printStackTrace()
        }
    }
    
    fun classifyImage(bitmap: Bitmap): Classification? {
        Log.d("ImageClassifier", "Starting classification...")
        
        if (interpreter == null) {
            Log.e("ImageClassifier", "Interpreter is null")
            return null
        }
        
        if (labels.isEmpty()) {
            Log.e("ImageClassifier", "Labels list is empty")
            return null
        }
        
        try {
            // Convert HARDWARE bitmap to ARGB_8888 if needed
            val mutableBitmap = if (bitmap.config == Bitmap.Config.HARDWARE) {
                bitmap.copy(Bitmap.Config.ARGB_8888, false)
            } else {
                bitmap
            }
            
            // Resize bitmap to model input size
            val resizedBitmap = Bitmap.createScaledBitmap(mutableBitmap, INPUT_SIZE, INPUT_SIZE, true)
            Log.d("ImageClassifier", "Bitmap resized to ${INPUT_SIZE}x${INPUT_SIZE}")
            
            // Convert bitmap to ByteBuffer
            val inputBuffer = ByteBuffer.allocateDirect(4 * INPUT_SIZE * INPUT_SIZE * PIXEL_SIZE)
            inputBuffer.order(ByteOrder.nativeOrder())
            
            val intValues = IntArray(INPUT_SIZE * INPUT_SIZE)
            resizedBitmap.getPixels(intValues, 0, INPUT_SIZE, 0, 0, INPUT_SIZE, INPUT_SIZE)
            
            var pixel = 0
            for (i in 0 until INPUT_SIZE) {
                for (j in 0 until INPUT_SIZE) {
                    val value = intValues[pixel++]
                    // Keep values in [0, 255] range - model was trained without normalization
                    inputBuffer.putFloat(((value shr 16) and 0xFF).toFloat())
                    inputBuffer.putFloat(((value shr 8) and 0xFF).toFloat())
                    inputBuffer.putFloat((value and 0xFF).toFloat())
                }
            }
            
            Log.d("ImageClassifier", "Input buffer prepared")
            
            // Create output array
            val output = Array(1) { FloatArray(labels.size) }
            
            // Run inference
            interpreter?.run(inputBuffer, output)
            Log.d("ImageClassifier", "Inference completed")
            
            // Find the class with highest confidence
            val confidences = output[0]
            val maxIndex = confidences.indices.maxByOrNull { confidences[it] } ?: 0
            val maxConfidence = confidences[maxIndex]
            
            Log.d("ImageClassifier", "Results - Label: ${labels[maxIndex]}, Confidence: $maxConfidence")
            Log.d("ImageClassifier", "All confidences: ${confidences.joinToString()}")
            
            return Classification(
                label = labels[maxIndex],
                confidence = maxConfidence
            )
            
        } catch (e: Exception) {
            Log.e("ImageClassifier", "Error during classification", e)
            e.printStackTrace()
            return null
        }
    }
    
    fun close() {
        interpreter?.close()
        interpreter = null
    }
}
