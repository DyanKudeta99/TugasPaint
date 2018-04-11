package com.ar_dy.tugaspaint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View {

    public int widht;
    public int heiht;
    private Bitmap aBitmap;
    private Canvas aCanvas;
    private Path aPath;
    private Paint aPaint;
    private float aX, aY;
    private static final float TOLERANCE =5 ;
    Context context;



    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        aPath = new Path();

        aPaint = new Paint();
        aPaint.setAntiAlias(true);
        aPaint.setColor(Color.BLACK);
        aPaint.setStyle(Paint.Style.STROKE);
        aPaint.setStrokeJoin(Paint.Join.ROUND);
        aPaint.setStrokeWidth(4f);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);

        aBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        aCanvas = new Canvas();
    }
    private void onStartTouch(float x,float y){
        aPath.moveTo(x, y);
        aX = x;
        aY = y;
    }
    private void moveTouch(float x, float y){
        float dx = Math.abs(x - aX);
        float dy = Math.abs(y - aY);
        if(dx >=TOLERANCE ||dy > TOLERANCE){
            aPath.quadTo(aX, aY, (x + aX)/2, (y + aY)/2);
            aX=x;
            aY=y;
        }
    }
    public void clearCanvas(){
        aPath.reset();
        //aCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }
    private void upTouch(){
        aPath.lineTo(aX,aY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(aPath, aPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x= event.getX();
        float y= event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                aPath.moveTo(x,y);
                //onStartTouch(x, y);
                //invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                aPath.lineTo(x,y);
                //moveTouch(x,y);
                //invalidate();
                break;
            case MotionEvent.ACTION_UP:
                //upTouch();
                // invalidate();
                break;

        }
        invalidate();
        return true;
    }
}

