package in.omerjerk.processingdemo.sketch;

import android.app.Activity;

import in.omerjerk.processingdemo.MainActivity;
import processing.core.PApplet;

/**
 * Created by root on 23/6/15.
 */
public class Directional extends PApplet {

    MainActivity mainActivity;

    @Override
    public void settings() {
        size(displayWidth, displayHeight, P3D);
        fullScreen();
        smooth();
    }

    @Override
    public void setup() {
//        fullScreen(P3D);
        // MainActivity shares data between sketches and fragments
        mainActivity = (MainActivity)surface.getActivity();
        noStroke();
        fill(204);
    }

    @Override
    public void draw() {
        noStroke();
        background(0);
        float dirY = (mouseY / PApplet.parseFloat(height) - 0.5f) * 2;
        float dirX = (mouseX / PApplet.parseFloat(width) - 0.5f) * 2;
        directionalLight(204, 204, 204, -dirX, -dirY, -1);
        translate(width/2 - 100, height/2, 0);
        if (mainActivity.isToggleSwitch())
            box(80);
        else
            sphere(80);
        translate(200, 0, 0);
        if (mainActivity.isToggleSwitch())
            box(80);
        else
            sphere(80);
    }

}
