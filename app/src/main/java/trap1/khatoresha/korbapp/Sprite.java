package trap1.khatoresha.korbapp;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Sprite extends RectF {
    private static final int BMP_COLUMNS=4;
    private static final int BMP_ROWS=4;
    private static final int DOWN=0, LEFT=1, RIGHT=2, UP=3;
    private int dX, dY, color;
    private Bitmap bitmap;
    private int currentFrame=0, iconWidth, iconHeight;
    public Sprite(){
        this(1,2, Color.RED);
    }
    public Sprite(int dX, int dY, int color) {
        this(1, 1, 11, 11, dX, dY, color);
    }

    public Sprite(float left, float top, float right, float bottom) {
        this(left, top, right, bottom,1,2,Color.RED);
    }
    public Sprite(float left, float top, float right, float bottom, int dX, int dY, int color) {
        super(left, top, right, bottom);
        this.dX = dX;
        this.dY = dY;
        this.color = color;
    }
    public void update(Canvas canvas){
        /*if(left+dX<0)//if next step hits boundary
            offsetTo(bottom,canvas.getWidth()); ///wrap oops
        if(right+dX>canvas.getWidth())
            offsetTo(bottom,-width()); //bounce off left and right boundaries
        if(top+dY>canvas.getHeight())//if next step puts off bottom of screen
            offsetTo(left,-height());//teleport to top of screen
        if(bottom+dY<0)
            offsetTo(left,canvas.getHeight()); */
        if(left+dX<0||right+dX>canvas.getWidth())//if next step hits boundary
            dX*=-1; //bounce off left and right boundaries
        if(top+dY>canvas.getHeight())//if next step puts off bottom of screen
            offsetTo(left,-height());//teleport to top of screen
        if(bottom+dY<0)
            offsetTo(left,canvas.getHeight());
        offset(dX,dY);//moves dX to the right and dY downwards
    }
    public void draw(Canvas canvas) {
        //canvas.drawRect(this, new Paint());
       // canvas.drawCircle(centerX(), centerY(), width() / 2, new Paint());
        /*if (bitmap == null) {
            Paint paint = new Paint();
            paint.setColor(color);
            canvas.drawCircle(centerX(), centerY(), width() / 2, paint);
        } else {
            iconWidth = bitmap.getWidth() / BMP_COLUMNS;//calculate width of 1 image
            iconHeight = bitmap.getHeight() / BMP_ROWS; //calculate height of 1 image
            int srcX = currentFrame * iconWidth;       //set x of source rectangle inside of bitmap based on current frame
            int srcY = getAnimationRow() * iconHeight; //set y to row of bitmap based on direction
            Rect src = new Rect(srcX, srcY, srcX + iconWidth, srcY + iconHeight);  //defines the rectangle inside of heroBmp to displayed
            canvas.drawBitmap(bitmap, src, this, null); //draw an image
        }*/
        canvas.drawBitmap(bitmap, null, this, null); //draw an image
      // System.out.println(this);
    }
    private int getAnimationRow () {
        if (Math.abs(dX) > Math.abs(dY)) {         //if magnitude of x is bigger than magnitude y
            if (Math.abs(dX) == dX) return RIGHT;  //if x is positive return row 2 for right
            else return LEFT;                          //if x is negative return row 1 for left
        } else if (Math.abs(dY) == dY) return DOWN;      //if y is positive return row 0 for up
        else return UP;
    }
    public int getdX(){
        return dX;
    }
    public void setdX(int dX){
        this.dX= dX;
    }
    public int getdY(){
        return dY;
    }
    public void setdY(int dY){
        this.dY= dY;
    }
    public int getColor(){
        return color;
    }
    public void setColor(int color){
        this.color = color;
    }
    public Bitmap getBitmap(){
        return bitmap;
    }
    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }
    public void grow(int i){
        right+=i;
        bottom+=i;
    }
}