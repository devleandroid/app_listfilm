package android.br.com.lebronx.kodeapp.Activity

import android.animation.Animator
import android.animation.ValueAnimator
import android.br.com.lebronx.kodeapp.R
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.BounceInterpolator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val ANIMATION_DURATION:Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startAnimation()
    }

    private fun startAnimation() {
        val valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            textTitle.scaleX = value
            textTitle.scaleY = value
        }
        valueAnimator.interpolator = BounceInterpolator()
        valueAnimator.duration = ANIMATION_DURATION

        // Set animator listener.
        val intent = Intent(this, ListFilm::class.java)
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                // Navigate to main activity on navigation end.
                startActivity(intent)
                finish()
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationStart(p0: Animator?) {}

        })

        // Start animation.
        valueAnimator.start()
    }
}
