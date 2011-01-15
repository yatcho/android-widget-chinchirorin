package jp.tricreo.android.widgetchinchirorin;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

//ホームウィジェット
public class widgetchinchirorin extends AppWidgetProvider {
    //更新時に呼ばれる
    @Override
    public void onUpdate(Context context,
        AppWidgetManager appWidgetManager,int[] appWidgetIds) {
        //ホームウィジェットを処理するサービスの実行
        Intent intent=new Intent(context,WidgetControl3.class);
        context.startService(intent);
    }
}