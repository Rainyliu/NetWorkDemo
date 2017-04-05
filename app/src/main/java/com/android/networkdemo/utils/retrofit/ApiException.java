package com.android.networkdemo.utils.retrofit;

public class ApiException extends RuntimeException {

    public static final int FAILED = 404;

    public ApiException(int code) {
        this(getApiExceptionMessage(code));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code){
        String message;
        switch (code) {
            case FAILED:
                message = "请求服务器失败";
                break;
            default:
                message = "未知错误";
                break;
        }
        return message;
    }
}

