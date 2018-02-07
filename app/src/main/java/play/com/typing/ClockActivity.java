package play.com.typing;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by YSPL on 7/5/2017.
 */

public class ClockActivity extends Activity {

    private OurView v;
    private float x = 0;
    private float y = 0;
    private double angleHand = 0;
    private double angleMinute = 0;
    private double angleSecond = 0;
    private int hourTime = 0;
    private int minTime = 0;
    private int secTime = 0;


    public void onCreate(Bundle save) {
        super.onCreate(save);
        v = new OurView(this);
        setContentView(v);
    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        v.pause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        v.resume();
    }

    private class OurView extends SurfaceView implements Runnable {
        Thread t = null;
        SurfaceHolder holder;
        boolean isItOK = false;

        public OurView(Context context) {
            super(context);

            // TODO Auto-generated constructor stub
            holder = getHolder();

        }

        public void run() {
            // TODO Auto-generated method stub
            while (isItOK == true) {
                if (x == 0) {
                    x = getWidth() / 2;
                    y = getHeight() / 2;
                }
                if (!holder.getSurface().isValid()) {
                    continue;
                }
                Canvas c = holder.lockCanvas();
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                c.drawCircle(x, y, 100, paint);
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(5);


                Angle angle = calculateAngle(hourTime, minTime, secTime);
                angleHand = angle.getHourAngle() * Math.PI / 180;
                angleMinute = angle.getMinuteAngle() * Math.PI / 180;
                angleSecond = angle.getSecAngle() * Math.PI / 180;
                c.drawLine(x, y, (float) (x + 100 * (Math.sin(angleHand))), (float) (y + 100 * (Math.cos(angleHand))), paint);
                c.drawLine(x, y, (float) (x + 100 * (Math.sin(angleMinute))), (float) (y + 100 * (Math.cos(angleMinute))), paint);
                c.drawLine(x, y, (float) (x + 100 * (Math.sin(angleSecond))), (float) (y + 100 * (Math.cos(angleSecond))), paint);

                holder.unlockCanvasAndPost(c);
                try {
                    Thread.sleep(1000);
                    returnHourAndMin();
                } catch (Exception e) {

                }
            }
        }

        private Angle calculateAngle(int hour, int min, int sec) {
            double hourangle = (30 * (hour + (min / 60)));
            double minangle = (6 * min);
            double secangle = (6 * sec);
            hourangle = hourangle <= 180 ? (180 - hourangle) : ((360 - hourangle) + 180);
            minangle = minangle <= 180 ? (180 - minangle) : ((360 - minangle) + 180);
            secangle = secangle <= 180 ? (180 - secangle) : ((360 - secangle) + 180);

            return new Angle(hourangle, minangle, secangle);
        }


        protected void onDraw(Canvas canvas) {
            // sprite.onDraw(canvas);
        }

        public void pause() {
            isItOK = false;
            while (true) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                break;

            }// end while
        }

        public void resume() {
            isItOK = true;
            t = new Thread(this);
            t.start();
        }

        private void returnHourAndMin() {
            secTime = secTime < 60 ? (secTime + 1) : 0;
            minTime = secTime < 60 ? minTime : minTime < 60 ? (minTime + 1) : 0;
            hourTime = minTime < 60 ? hourTime : hourTime < 24 ? (hourTime + 1) : 0;
        }
    }
}
