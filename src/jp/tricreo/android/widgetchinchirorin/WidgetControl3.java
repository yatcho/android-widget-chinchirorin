package jp.tricreo.android.widgetchinchirorin;
import java.util.*;

import jp.tricreo.android.widgetchinchirorin.R;
import android.app.*;
import android.appwidget.AppWidgetManager;
import android.content.*;
import android.os.IBinder;
import android.widget.RemoteViews;

//�z�[���E�B�W�F�b�g�𐧌䂷��T�[�r�X
public class WidgetControl3 extends Service {
    private static final String ACTION_BTNCLICK =
        "jp.tricreo.WidgetControl3.ACTION_BTNCLICK";

    //�T�[�r�X�J�n���ɌĂ΂��
    @Override
    public void onStart(Intent intent,int startId) {
        super.onStart(intent, startId);

        //�����[�g�r���[�̎擾
        AppWidgetManager manager=AppWidgetManager.getInstance(this);
        RemoteViews view=new RemoteViews(getPackageName(),R.layout.dicelayout);
        //�{�^���������ꂽ��
        if (ACTION_BTNCLICK.equals(intent.getAction())) {
        	btnClicked(view);
        }

        //button1,button2,button3�ƃ{�^���N���b�N�C�x���g�̊֘A�t��
        Intent intentbutton=new Intent(ACTION_BTNCLICK);
        PendingIntent pendingintentbutton=PendingIntent.getService(this,0,intentbutton,0);
        view.setOnClickPendingIntent(R.id.button,pendingintentbutton);

        //�z�[���E�B�W�F�b�g�̍X�V
        ComponentName widget=new ComponentName(this,widgetchinchirorin.class);
        manager.updateAppWidget(widget,view);

    }

    //�o�C���_�[��Ԃ�
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //�{�^���N���b�N���ɌĂ΂��
    public void btnClicked(RemoteViews view){
        String[] deme = new String[]{"�s���]��","�j�]��","�T���]��","�����]��","�S�]��","���N�]��",
        		"�s��","�j�]�E","�T���^","���c��","�S�P","���b�|�E","�V�S��","�q�t�~","���i�V"};
    	int[] ids={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6,
    			R.drawable.dice7,R.drawable.dice8,R.drawable.dice9,R.drawable.dice10,R.drawable.dice11,R.drawable.dice12};
    	int idx1=rand(6);
    	int idx2=rand(6);
    	int idx3=rand(6);

    	//���̔���
    	if (idx1 == idx2 && idx1 == idx3){view.setTextViewText(R.id.rule1, deme[idx1]);idx1+=6;idx2+=6;idx3+=6;}
    	else if (idx1 == idx2){view.setTextViewText(R.id.rule1, deme[idx3+6]);idx3+=6;}
    	else if (idx1 == idx3){view.setTextViewText(R.id.rule1, deme[idx2+6]);idx2+=6;}
    	else if (idx2 == idx3){view.setTextViewText(R.id.rule1, deme[idx1+6]);idx1+=6;}
    	else if (idx1+idx2+idx3 == 12){view.setTextViewText(R.id.rule1, deme[12]);idx1+=6;idx2+=6;idx3+=6;}
    	else if (idx1+idx2+idx3 == 3){view.setTextViewText(R.id.rule1, deme[13]);idx1+=6;idx2+=6;idx3+=6;}
    	else {view.setTextViewText(R.id.rule1, deme[14]);}

    	//�T�C�R���C���[�W�̃Z�b�g
    	view.setImageViewResource(R.id.image1,ids[idx1]);
    	view.setImageViewResource(R.id.image2,ids[idx2]);
    	view.setImageViewResource(R.id.image3,ids[idx3]);
    }

    //�����̎擾
    private static Random rand=new Random();
    public static int rand(int num) {
        return (rand.nextInt()>>>1)%num;
    }
}