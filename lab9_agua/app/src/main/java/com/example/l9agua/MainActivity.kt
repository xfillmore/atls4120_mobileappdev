package com.example.l9agua

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var mWaterBottle = WaterBottle()
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            launchWebsite()
        }

        btn_addwater.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("currentQty", mWaterBottle.qty)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    private fun launchWebsite () {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(R.string.external_link.toString())

        // deprecated in API 30
//        if (intent.resolveActivity(packageManager) != null) {
//            startActivity(intent)
//        }
//        else {
//            Snackbar.make(root_layout, "Unable to load website", Snackbar.LENGTH_SHORT).show()
//        }
        startActivity(intent)
    }

    private fun updateDisplays () {
        text_qtyconsumed.text = mWaterBottle.qty.toString()
        txt_percentage.text = (mWaterBottle.qty/2500).toString() + "%"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_CODE) && (resultCode == Activity.RESULT_OK)) {
            if (data != null) {
                mWaterBottle.qty = data.getIntExtra("newQty", mWaterBottle.qty)
            }
        }
    }
}