package com.lance.draw;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;

/**
 * 给图片带上圆角
 * @author ganchengkai
 *
 */
public class RoundedBitmapDrawable extends Drawable {

	private Bitmap mBitmap;
	private final Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
	private int mRadius;//传入的圆角半径
	private RectF mRectF;//记录绘图的边界
	
	public RoundedBitmapDrawable(Bitmap bitmap, int radius) {
		this.mRadius = radius;
		this.setBitmap(bitmap);
	}

	public void setBitmap(Bitmap bitmap) {
		this.mBitmap = bitmap;
		//设置位图的着色器---使用什么样的方式拉伸图片
		final Shader shader = new BitmapShader(bitmap, TileMode.CLAMP,TileMode.CLAMP);
		mPaint.setShader(shader);
		this.invalidateSelf();
	}

	public void setmRadius(int mRadius) {
		this.mRadius = mRadius;
		this.invalidateSelf();
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRoundRect(mRectF, mRadius, mRadius, mPaint);//绘制圆角矩形
	}
	
	@Override
	public int getIntrinsicWidth() {//获取理想的宽度
		if(mBitmap==null){
			return 0;
		}
		return mBitmap.getWidth();
	}

	@Override
	public int getIntrinsicHeight() {//获取理想的高度
		if(mBitmap==null){
			return 0;
		}
		return mBitmap.getHeight();
	}

	@Override
	public void setAlpha(int alpha) {
		int oldAlpha=mPaint.getAlpha();
		if(alpha!=oldAlpha){
			mPaint.setAlpha(alpha);
			invalidateSelf();//重新绘制
		}
	}

	@Override//对颜色通道进行转换---滤镜效果
	public void setColorFilter(ColorFilter cf) {
		mPaint.setColorFilter(cf);
		this.invalidateSelf();
	}

	@Override
	public int getOpacity() {
		return PixelFormat.TRANSLUCENT;
	}

	@Override
	protected void onBoundsChange(Rect bounds) {
		this.mRectF=new RectF(bounds);
	}
	
}
