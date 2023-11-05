package com.hoangtuan.nhht.w05;

public interface FragmentCallbacks {

    public void onMsgFromMainToFragment(Student student, String msg);

    public void onCommandFromMainToFragment(String str);
}
