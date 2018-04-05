package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.network.exeption;

class InternalAppException extends Exception {

    public InternalAppException(String detailMessage) {
        super(detailMessage);
    }

    public InternalAppException(Throwable throwable) {
        super(throwable);
    }
}
