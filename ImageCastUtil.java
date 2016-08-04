
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImageCastUtil {
	/**
	 * Drawable类型转换成Bitmap类型
	 *
	 * @param drawable
	 * @return
	 */
	private static final float EPSILON = 0.00001f;

	public static Bitmap converDrawable2BitmapByCanvas(Drawable drawable) {
		Bitmap bitmap = null;
		try {
			bitmap = Bitmap
					.createBitmap(
							drawable.getIntrinsicWidth(),
							drawable.getIntrinsicHeight(),
							drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
									: Bitmap.Config.RGB_565);

			Canvas canvas = new Canvas(bitmap);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			drawable.draw(canvas);
		} catch (OutOfMemoryError error) {
			PAOutOfMemoryHandler.handle();
		}
		return bitmap;
	}

	/**
	 * 根据资源Id得到Drawable图形对象
	 *
	 * @param res
	 *            应用程序的资源
	 * @param id
	 *            图片的资源id
	 * @return Drawable类型图
	 */
	public static Drawable resId2drawable(Resources res, int id) {
		Drawable drawable = null;
		try {
			Bitmap bitmap = BitmapFactory.decodeResource(res, id);
			drawable = new BitmapDrawable(res, bitmap);
		} catch (OutOfMemoryError error) {
			PAOutOfMemoryHandler.handle();
		}
		return drawable;
	}

	/**
	 * 根据资源Id得到Bitmap图形对象
	 *
	 * @param res
	 *            应用程序的资源
	 * @param id
	 *            图片的资源id
	 * @return Bitmap类型图
	 */
	public static Bitmap resId2bitmap(Resources res, int id) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeResource(res, id);
		} catch (OutOfMemoryError error) {
			PAOutOfMemoryHandler.handle();
		}
		return bitmap;
	}

	/**
	 * 按宽高比例缩放
	 *
	 * @param bm
	 *            原图
	 * @param radioWidth
	 *            宽度缩放比例
	 * @param radioHeight
	 *            高度缩放比例
	 * @return 缩放后图形
	 */
	public static Bitmap zoomImg(Bitmap bm, float radioWidth, float radioHeight) {
		if (compareFloatToZero(radioWidth) || compareFloatToZero(radioHeight)) {
			return bm; // 返回原图
		}
		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();
		// 缩放图片动作
		matrix.postScale(radioWidth, radioHeight);
		Bitmap bitmap = null;
		try {
			bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
					bm.getHeight(), matrix, true);
		} catch (OutOfMemoryError error) {
			PAOutOfMemoryHandler.handle();
		}
		return bitmap;
	}

	/**
	 * 比较一个float值是否等于0，因为float值是不精确的，所以与0比较时最好调用此方法
	 *
	 * @param number
	 * @return 若等于0，则返回true
	 */
	public static boolean compareFloatToZero(float number) {
		if (Math.abs(number) <= EPSILON) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 按高度等比缩放ImageView的图片
	 *
	 * @param res
	 *            应用程序的资源
	 * @param id
	 *            图片的资源id
	 * @param imgviewHeight
	 *            Imageview的高(px)
	 * @return
	 */
	public static Bitmap resId2zoomImg(Resources res, int id,
			float imgviewHeight) {
		Bitmap bm = resId2bitmap(res, id);
		if (compareFloatToZero(imgviewHeight)) {
			return bm; // 返回原图
		}
		return zoomImg(bm, imgviewHeight / bm.getHeight(),
				imgviewHeight / bm.getHeight());
	}

	/**
	 * 按高度等比缩放ImageView的图片
	 *
	 * @param bm
	 * @param imgviewHeight
	 *            Imageview的高(px)
	 * @return
	 */
	public static Bitmap zoomImg(Bitmap bm, float imgviewHeight) {
		if (compareFloatToZero(imgviewHeight)) {
			return bm; // 返回原图
		}
		return zoomImg(bm, imgviewHeight / bm.getHeight(),
				imgviewHeight / bm.getHeight());
	}

	/**
	 * @description 根据资源名称获得资源id
	 * @date 2014-12-16
	 * @param context
	 * @param resName
	 * @param name
	 * @return
	 */
	public static int getResourceIdbyName(Context context, String resName,
			String name) {

		int resID = -1;
		// 得到application对象
		ApplicationInfo appInfo = context.getApplicationInfo();
		// 得到该图片的id(name 是该图片的名字，drawable 是该图片存放的目录，appInfo.packageName是应用程序的包)
		resID = context.getResources().getIdentifier(
				"rym_" + name.toLowerCase(), resName, appInfo.packageName);
		return resID;
	}

	/**
	 *
	 * @param x
	 *            图像的宽度
	 * @param y
	 *            图像的高度
	 * @param canvasX
	 *            画布的宽度
	 * @param canvasY
	 *            画布的高度
	 * @param image
	 *            源图片
	 * @param outerRadiusRat
	 *            圆角的大小
	 * @param color
	 * @return 圆角图片
	 */
	public static Bitmap createFramedPhoto(int x, int y, int canvasX,
			int canvasY, Bitmap image, float outerRadiusRat, int color) {
		// 根据源文件新建一个darwable对象
		Drawable imageDrawable = new BitmapDrawable(image);

		// 新建一个新的输出图片
		Bitmap output = Bitmap.createBitmap(canvasX, canvasY,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		// 新建一个矩形
		RectF outerRect = new RectF(0, 0, canvasX, canvasY);

		// 产生一个红色的圆角矩形
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawRoundRect(outerRect, outerRadiusRat, outerRadiusRat, paint);

		// 将源图片绘制到这个圆角矩形上
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
		imageDrawable.setBounds(0, 0, x, y);
		canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);
		imageDrawable.draw(canvas);
		canvas.restore();

		return output;
	}

}
