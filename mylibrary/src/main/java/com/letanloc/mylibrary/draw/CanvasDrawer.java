package com.letanloc.mylibrary.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.letanloc.mylibrary.BuildConfig;
import com.letanloc.mylibrary.DrawableViewConfig;
import com.letanloc.mylibrary.draw.log.CanvasLogger;
import com.letanloc.mylibrary.draw.log.DebugCanvasLogger;
import com.letanloc.mylibrary.draw.log.NullCanvasLogger;

public class CanvasDrawer {

    private boolean showCanvasBounds;
    private Paint paint;
    private float scaleFactor = 1.0f;
    private RectF viewRect = new RectF();
    private RectF canvasRect = new RectF();
    private CanvasLogger canvasLogger;

    public CanvasDrawer() {
        initPaint();
        initLogger();
    }

    public void onDraw(Canvas canvas) {
        canvasLogger.log(canvas, canvasRect, viewRect, scaleFactor);
        if (showCanvasBounds) {
            canvas.drawRect(canvasRect, paint);
        }
        canvas.translate(-viewRect.left, -viewRect.top);
        canvas.scale(scaleFactor, scaleFactor);
    }

    public void onScaleChange(float scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public void onViewPortChange(RectF viewRect) {
        this.viewRect = viewRect;
    }

    public void onCanvasChanged(RectF canvasRect) {
        this.canvasRect = canvasRect;
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG);

    /*
    * ANTI_ALIAS_FLAG =Vẽ cờ cho phép khử răng cưa khi vẽ.
    *
    *int	ANTI_ALIAS_FLAG	Vẽ cờ cho phép khử răng cưa khi vẽ.

    int	DEV_KERN_TEXT_FLAG	 Legacy sơn cờ, không còn sử dụng.

    int	DITHER_FLAG	Vẽ lá cờ đó cho phép phối màu khi blitting.

    int	EMBEDDED_BITMAP_TEXT_FLAG	Vẽ lá cờ đó cho phép việc sử dụng các phông chữ bitmap khi vẽ văn bản.

    int	FAKE_BOLD_TEXT_FLAG	Vẽ lá cờ đó áp dụng một hiệu ứng bolding tổng hợp để văn bản được vẽ.

    int	FILTER_BITMAP_FLAG	Vẽ cờ cho phép lấy mẫu Bilinear trên bitmap thu nhỏ lại.

    int	HINTING_OFF	Tùy chọn vô hiệu hóa Hinter Font chữ hinting.

    int	HINTING_ON	Tùy chọn Hinter Font chữ cho phép hinting.

    int	LINEAR_TEXT_FLAG	Vẽ cờ cho phép mở rộng quy mô tuyến tính thông suốt của văn bản.

    int	STRIKE_THRU_TEXT_FLAG	Vẽ lá cờ đó áp dụng một trang trí quá ấn văn bản được vẽ.
    int	SUBPIXEL_TEXT_FLAG	Vẽ cờ cho phép subpixel vị trí của văn bản.
    int	UNDERLINE_TEXT_FLAG	Vẽ lá cờ đó áp dụng một gạch dưới trang trí cho văn bản được vẽ.
    * */
        paint.setStrokeWidth(2.0f);
        paint.setStyle(Paint.Style.STROKE);
    }

    private void initLogger() {
        if (BuildConfig.DEBUG) {
            canvasLogger = new DebugCanvasLogger();
        } else {
            canvasLogger = new NullCanvasLogger();
        }
    }

    public void setConfig(DrawableViewConfig config) {
        this.showCanvasBounds = config.isShowCanvasBounds();
    }
}
/*
* */