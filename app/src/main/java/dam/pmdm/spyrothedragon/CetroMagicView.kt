package dam.pmdm.spyrothedragon

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CetroMagicView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int=0
) : View(context, attrs, defStyleAttr){

    private val paint = Paint().apply {
        isAntiAlias=true
        style= Paint.Style.FILL
    }

    private var radius= 0f
    private var alphaVal=0
    private var currentColor =Color.MAGENTA

    private val magicColors= intArrayOf(Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.RED)

    fun iniciarAnimacion(){
        val animator = ValueAnimator.ofFloat(0f, 150f)
        animator.duration=2000
        animator.addUpdateListener { animation->
            val value = animation.animatedValue as Float
            radius= value

            alphaVal=(255*(1-value /150f)).toInt()

            val colorIndex=(value.toInt()/40)%magicColors.size
            currentColor= magicColors[colorIndex]

            invalidate()
        }
        animator.start()

        animator.addListener(object : android.animation.AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: android.animation.Animator){
                visibility= View.INVISIBLE
                radius=0f
            }
        })
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        if(radius>0){
            paint.color= currentColor
            paint.alpha=alphaVal

            canvas.drawCircle(width/2f, height/2f, radius, paint)
            paint.alpha= alphaVal/2
            canvas.drawCircle(width/2f, height/2f, radius*1.2f, paint)
        }
    }
}