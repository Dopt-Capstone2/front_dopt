package com.example.dopt_app.popup
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.R
import android.content.Intent
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import com.example.dopt_app.MainActivity
import kotlinx.android.synthetic.main.activity_popup_profile_done.*


class PopupProfileDoneActivity : AppCompatActivity()  {

    private lateinit var mDetector: GestureDetectorCompat


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_profile_done)

        profile_finish_btn.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

}