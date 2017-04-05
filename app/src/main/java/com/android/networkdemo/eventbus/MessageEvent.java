package com.android.networkdemo.eventbus;

/**
 * Author: liuyuting
 * Description: MyApplication 1、定义事件
 * Since: 2017/3/22 10:28
 */

public class MessageEvent {
    public final String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

//    // This method will be called when a MessageEvent is posted
//    @Subscribe
//    public void onMessageEvent(MessageEvent event){
//        Toast.makeText(getActivity(), event.message, Toast.LENGTH_SHORT).show();
//    }
//
//    // This method will be called when a SomeOtherEvent is posted
//    @Subscribe
//    public void handleSomethingElse(SomeOtherEvent event){
//        doSomethingWith(event);
//    }
}
