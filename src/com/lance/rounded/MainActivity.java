package com.lance.rounded;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import com.lance.draw.RoundedBitmapDrawable;

/**
 * 测试绘图对象
 * @author ganchengkai
 *
 */
public class MainActivity extends ActionBarActivity {

	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView=(ImageView)findViewById(R.id.image_view);
		
		final Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.image_food);
		int radius = getResources().getDimensionPixelSize(R.dimen.radius);
		final Drawable drawable = new RoundedBitmapDrawable(bitmap, radius);
		
		imageView.setImageDrawable(drawable);
	}

	
}
