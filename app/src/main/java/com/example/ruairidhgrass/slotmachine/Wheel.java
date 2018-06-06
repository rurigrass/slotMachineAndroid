package com.example.ruairidhgrass.slotmachine;

public class Wheel extends Thread {

    interface WheelListener {
        void newImage(int img);
    }

    private static int[] imgs = {R.drawable.slot_machine_apple, R.drawable.slot_machine_bar, R.drawable.slot_machine_cherry, R.drawable.slot_machine_lemon};
    public int currentIndex;
    private WheelListener wheelListener;
    private long frameDuration;
    private long startIn;
    private boolean hasStarted;

    public Wheel(WheelListener wheelListener, long frameDuration, long startIn) {
        this.wheelListener = wheelListener;
        this.frameDuration = frameDuration;
        this.startIn = startIn;
        currentIndex = 0;
        hasStarted = true;
    }

    public void nextImg() {
        currentIndex++;

        if (currentIndex == imgs.length) {
            currentIndex = 0;
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(startIn);
        } catch (InterruptedException e) {
        }

        while (hasStarted) {
            try {
                Thread.sleep(frameDuration);
            } catch (InterruptedException e) {
            }

            nextImg();

            if (wheelListener != null) {
                wheelListener.newImage(imgs[currentIndex]);
            }
        }
    }

    public void stopWheel() {
        hasStarted = false;

    }
}
