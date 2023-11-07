package com.hoangtuan.nhht.w08;

public interface MainCallbacks {

    public void onMsgFromFragToMain(Student student, String msg);
    public void onCommandFromFragToMain(String str);

}
