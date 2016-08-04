

public class CircleImageView extends ImageView {

	private final String TAG = CircleImageView.class.getSimpleName();
	private Bitmap mBitmap;

	public CircleImageView(Context context) {
		super(context);
		super.setScaleType(ScaleType.MATRIX);
	}

	@Override
	public void setImageBitmap(Bitmap bm) {
		if (bm != null) {
			mBitmap = bm;
			int imgViewMargin = (int)JarUtils.getResources().getDimension(R.dimen.rym_circleplugin_margin);
			View pluginItem = (View) getParent();
			ViewGroup.LayoutParams layoutParams = pluginItem.getLayoutParams();
			int pluginWidth = layoutParams.width;
			int pluginHeight = layoutParams.height;
			bm = ImageCastUtil.zoomImg(bm, pluginHeight);
			int bmWidth = bm.getWidth();
			int bmHeight = bm.getHeight();
			int x = pluginWidth < bmWidth ? pluginWidth : bmWidth;
			int y = pluginHeight < bmHeight ? pluginHeight : bmHeight;
			x = x - 2 * imgViewMargin;
			y = y - 2 * imgViewMargin;
			pluginWidth = pluginWidth - 2 * imgViewMargin;
			pluginHeight = pluginHeight - 2 * imgViewMargin;
			Object tag = pluginItem.getTag();
			PluginInfo info = null;
	        if (tag instanceof PluginInfo) {
	            info = (PluginInfo)tag;
	        }
	        else {
	            return;
	        }
	        int color = Tools.parseRGBColor(info.iconColor);
			bm = ImageCastUtil.createFramedPhoto(x, y,pluginWidth,pluginHeight, bm, x / 2.0f,color);
		}
		super.setImageBitmap(bm);
	}

	@Override
	public Drawable getDrawable() {
		if (null == mBitmap) {
			return super.getDrawable();
		} else {
			BitmapDrawable bitmapDrawable = new BitmapDrawable(mBitmap);
			return bitmapDrawable;
		}

	}

}
