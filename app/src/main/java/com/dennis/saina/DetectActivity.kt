package com.dennis.saina

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.dennis.saina.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class DetectActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var selectBtn: Button
    lateinit var predictBtn: Button
    lateinit var bitmap: Bitmap
    lateinit var textView: TextView
    lateinit var returnBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detect)


        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)
        selectBtn = findViewById(R.id.selectBtn)
        predictBtn = findViewById(R.id.predictBtn)
        returnBtn = findViewById(R.id.returnBtn)

        val fileName = "labels.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use { it.readText() }
        val townList = inputString.split("\n")

        selectBtn.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"

            startActivityForResult(intent, 100)
        }

        returnBtn.setOnClickListener {
            super.onBackPressed()
        }

        predictBtn.setOnClickListener {
            var resized: Bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, false)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        imageView.setImageURI(data?.data)

        val uri: Uri? = data?.data

        bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)

    }

}