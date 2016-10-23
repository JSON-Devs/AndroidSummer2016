package jasongagnon.androidfinal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;
import android.widget.Button;

import java.util.Random;

/**
 * Created by jasongagnon on 9/13/16.
 */
public class LevelView extends View {

    private Context context;
    float x = -1;
    float y = -1;
    private DifferentLevels levels = new DifferentLevels();
    private int[][] level;
    private Handler handler;
    private final float radius = 700;
    //int manI = 0, manJ = 0;

    //paint
    private Paint black = new Paint();
    private Paint red = new Paint();
    private Paint green = new Paint();
    private Paint blue = new Paint();

    Button left, right, up, down;

    Level lvlClass = Maze.lvlClass;

    public LevelView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        handler = new Handler();

        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.FILL);

        red.setColor(Color.RED);
        red.setStyle(Paint.Style.FILL);

        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.FILL);

        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.FILL);

        level = levels.getTestLevel();

        lvlClass.setLevel(level);

        for(int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                switch (level[i][j]){
                    case 2:
                        lvlClass.setManI(i);
                        lvlClass.setManJ(j);
                        break;
                    default:
                }
            }
        }
        this.invalidate();
    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    protected void onDraw(Canvas canvas) {
        float xCor = 0, yCor = 0;
        level = lvlClass.getLevel();
        float width = this.getWidth()/level.length;
        float height = this.getHeight()/(level[0].length);
        float size = height;
        if(width < height){
            size = width;
        }
        for(int i = 0; i < level.length; i++){
            yCor = 0;
            for(int j = 0; j < level[i].length; j++){
                switch (level[i][j]){
                    case 1:
                        canvas.drawRect(size + xCor,size + yCor,xCor,yCor, black);
                        //canvas.drawRect(100 + xCor,100 + yCor,xCor,yCor, black);
                        break;
                    case 2:
                        canvas.drawRect(size + xCor,size + yCor,xCor,yCor, blue);
                        //canvas.drawRect(100 + xCor,100 + yCor,xCor,yCor, blue);
                        break;
                    case 3:
                        canvas.drawRect(size + xCor,size + yCor,xCor,yCor, green);
                        //canvas.drawRect(100 + xCor,100 + yCor,xCor,yCor, green);
                        break;
                    default:
                        canvas.drawRect(size + xCor,size + yCor,xCor,yCor, red);
                        //canvas.drawRect(100 + xCor,100 + yCor,xCor,yCor, red);
                }
                yCor += size;
                //yCor += 100;
            }
            xCor += size;
            //xCor += 100;
        }
        handler.postDelayed(runnable, 30);
    }
}
