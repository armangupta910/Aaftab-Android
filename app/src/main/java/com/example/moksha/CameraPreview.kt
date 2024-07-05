package com.example.moksha

import android.content.Context
import android.graphics.Matrix
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.util.AttributeSet
import android.util.Log
import android.view.TextureView
import java.io.IOException

class CameraPreview(context: Context, private val mCamera: Camera) : TextureView(context), TextureView.SurfaceTextureListener {
    init {
        surfaceTextureListener = this
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
        try {
            mCamera.setPreviewTexture(surface)
            adjustAspectRatio(width, height)
            mCamera.startPreview()
        } catch (e: IOException) {
            Log.d(TAG, "Error setting camera preview: " + e.message)
        }
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
        adjustAspectRatio(width, height)
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {}

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
        return true
    }

    private fun adjustAspectRatio(viewWidth: Int, viewHeight: Int) {
        val size = mCamera.parameters.previewSize
        val previewWidth = size.width
        val previewHeight = size.height

        val aspectRatio = previewWidth.toFloat() / previewHeight

        val scaledWidth: Int
        val scaledHeight: Int

        if (viewWidth > viewHeight * aspectRatio) {
            scaledWidth = viewWidth
            scaledHeight = (viewWidth / aspectRatio).toInt()
        } else {
            scaledWidth = (viewHeight * aspectRatio).toInt()
            scaledHeight = viewHeight
        }

        val txform = Matrix()
        val xOffset = (viewWidth - scaledWidth) / 2
        val yOffset = (viewHeight - scaledHeight) / 2
        txform.setScale(scaledWidth.toFloat() / viewWidth, scaledHeight.toFloat() / viewHeight)
        txform.postTranslate(xOffset.toFloat(), yOffset.toFloat())
        setTransform(txform)
    }

    companion object {
        private const val TAG = "CameraPreview"
    }
}
