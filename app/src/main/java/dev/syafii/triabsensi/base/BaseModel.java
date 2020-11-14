/*
 * Created by Muhamad Syafii
 * Tuesday,20/10/2020
 * Copyright (c) 2020 by DeOne.
 * All Rights Reserved
 */

package dev.syafii.triabsensi.base;

public interface BaseModel {
    interface OnFinishedListener<T>{
        void onSuccess(T data);
        void onFailure(String message);
    }
}
