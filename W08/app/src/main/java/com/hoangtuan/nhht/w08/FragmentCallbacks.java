package com.hoangtuan.nhht.w08;

public interface FragmentCallbacks {

    public void onMsgFromMainToFragment(Student student, String msg);

    public void onCommandFromMainToFragment(String str);

}
