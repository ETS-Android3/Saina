package com.dennis.saina

import android.content.Intent
import android.graphics.*
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import com.dennis.saina.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import android.graphics.Bitmap


class DetectActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var selectBtn: Button
    lateinit var predictBtn: Button
    lateinit var bitmap: Bitmap
    lateinit var textView: TextView
    lateinit var returnBtn: Button
    lateinit var convertBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detect)


        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)
        selectBtn = findViewById(R.id.selectBtn)
        predictBtn = findViewById(R.id.predictBtn)
        returnBtn = findViewById(R.id.returnBtn)
        convertBtn = findViewById(R.id.convertBtn)

        val fileName = "labels.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use { it.readText() }
        val townList = inputString.split("\n")

        convertBtn.setOnClickListener {
            bitmap = convertToBlackWhite(bitmap)
            imageView.setImageBitmap(bitmap)
        }

        selectBtn.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            startActivityForResult(intent, 100)
        }

        returnBtn.setOnClickListener {
            super.onBackPressed()
        }

        predictBtn.setOnClickListener {
            lateinit var resized: Bitmap

            resized = convertToBlackWhite(resized)
            resized = resized.copy(Bitmap.Config.ARGB_8888, true)
            resized = Bitmap.createScaledBitmap(bitmap, 224, 224, false)


            val model = Model.newInstance(this)

// Creates inputs for reference.
            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)

            //create byteBuffer from resized bitmap

            val tbuffer = TensorImage.fromBitmap(resized)
            val byteBuffer = tbuffer.buffer

            inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer


            val max = getMax(outputFeature0.floatArray)

            textView.setText(townList[max])

// Releases model resources if no longer used.
            model.close()
        }


    }

    fun getMax(arr: FloatArray): Int {
        var index = 0
        var min = 0.0f

        for (i in 0..5) {
            if (arr[i] > min) {
                index = i
                min = arr[i]
            }
        }
        return index
    }


    fun convertToBlackWhite(bmp: Bitmap): Bitmap {
        val width = bmp.width
        val height = bmp.height
        val pixels = IntArray(width * height)
        bmp.getPixels(pixels, 0, width, 0, 0, width, height)
        val alpha = 0xFF shl 24 // ?bitmap?24?
        for (i in 0 until height) {
            for (j in 0 until width) {
                var grey = pixels[width * i + j]
                val red = grey and 0x00FF0000 shr 16
                val green = grey and 0x0000FF00 shr 8
                val blue = grey and 0x000000FF
                grey = (red * 0.3 + green * 0.59 + blue * 0.11).toInt()
                grey = alpha or (grey shl 16) or (grey shl 8) or grey
                pixels[width * i + j] = grey
            }
        }
        val newBmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        newBmp.setPixels(pixels, 0, width, 0, 0, width, height)
        return newBmp
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        imageView.setImageURI(data?.data)

        val uri: Uri? = data?.data

        bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)

    }

}


