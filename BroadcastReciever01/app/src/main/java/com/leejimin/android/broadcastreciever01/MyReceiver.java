package com.leejimin.android.broadcastreciever01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    //아이디로 식별
    static final String BROADCAST_ACTION = "com.leejimin.android..MESSAGE"; //액션코드를 바꿔서 다른메세지 잡을수ㅇ


    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(BROADCAST_ACTION)){   // 모든 메세지를 다 캐치하면 안되니까 구분해준다
            //activity 화면을 띄워준다
            Intent i = new Intent(context, MapsActivity.class);
            i.addFlags( i.FLAG_ACTIVITY_NEW_TASK  //액티비티가 실행될때 액티비티의 상태를 세팅.
                    |   i.FLAG_ACTIVITY_CLEAR_TOP);  //(플래그값을 줘서구분)  -인자값이 하나지만 두개를 넣기위해'|'

            context.startActivity(i);  //컨텍스트를 통해 주고받을수있다.
                                        //브로드캐스트리시버는 컨텍스트 기본으로 안가져서, 만들어서 보내줌
                                        //(액티비티는 컨텍스트 가져서 그냥 보냄)

        }

    }
}
