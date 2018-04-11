package com.ar_dy.tugaspaint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CanvasView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CanvasView canvasView = (CanvasView) findViewById(R.id.canvas);
    }
    public void clearCanvas(View v) {
        canvasView.clearCanvas();
    }
}