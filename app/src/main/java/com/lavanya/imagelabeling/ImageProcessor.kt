package com.lavanya.imagelabeling

import ai.fritz.vision.FritzVision
import ai.fritz.vision.FritzVisionImage
import ai.fritz.vision.ImageRotation
import ai.fritz.vision.PredictorStatusListener
import ai.fritz.vision.imagelabeling.FritzVisionLabelPredictor
import ai.fritz.vision.imagelabeling.ImageLabelManagedModelFast
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy


class ImageProcessor : ImageAnalysis.Analyzer {
    var predictor: FritzVisionLabelPredictor? = null
    val TAG = javaClass.simpleName
    override fun analyze(image: ImageProxy?, rotationDegrees: Int) {

        //Handle all the ML logic here
        val mediaImage = image?.getImage()

        var imageRotation = ImageRotation.getFromValue(rotationDegrees)

        val visionImage = FritzVisionImage.fromMediaImage(mediaImage, imageRotation)

        val managedModel = ImageLabelManagedModelFast()

        FritzVision.ImageLabeling.loadPredictor(
            managedModel,
            object : PredictorStatusListener<FritzVisionLabelPredictor> {
                override fun onPredictorReady(p0: FritzVisionLabelPredictor?) {
                    Log.d(TAG, "Image Labeling predictor is ready")
                    predictor = p0
                }
            })

        val labelResult = predictor?.predict(visionImage)

        Log.e("TAG 10", labelResult?.resultString)
    }
}