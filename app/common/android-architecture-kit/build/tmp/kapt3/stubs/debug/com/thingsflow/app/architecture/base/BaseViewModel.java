package com.thingsflow.app.architecture.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0014H\u0016R \u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR \u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00060\u0005X\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00060\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010\u00a8\u0006\u0015"}, d2 = {"Lcom/thingsflow/app/architecture/base/BaseViewModel;", "Landroidx/lifecycle/ViewModel;", "Lcom/thingsflow/app/architecture/base/Cleaner;", "()V", "_loadingEvent", "Landroidx/lifecycle/MutableLiveData;", "Lcom/thingsflow/app/architecture/aac/Event;", "", "get_loadingEvent", "()Landroidx/lifecycle/MutableLiveData;", "_toastEvent", "Lcom/thingsflow/app/architecture/data/res/StringModel;", "get_toastEvent", "loadingEvent", "Landroidx/lifecycle/LiveData;", "getLoadingEvent", "()Landroidx/lifecycle/LiveData;", "toastEvent", "getToastEvent", "onDestroyView", "", "android-architecture-kit_debug"})
public class BaseViewModel extends androidx.lifecycle.ViewModel implements com.thingsflow.app.architecture.base.Cleaner {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.thingsflow.app.architecture.aac.Event<java.lang.Boolean>> _loadingEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.thingsflow.app.architecture.aac.Event<java.lang.Boolean>> loadingEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.thingsflow.app.architecture.aac.Event<com.thingsflow.app.architecture.data.res.StringModel>> _toastEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.thingsflow.app.architecture.aac.Event<com.thingsflow.app.architecture.data.res.StringModel>> toastEvent = null;
    
    @org.jetbrains.annotations.NotNull()
    protected final androidx.lifecycle.MutableLiveData<com.thingsflow.app.architecture.aac.Event<java.lang.Boolean>> get_loadingEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.thingsflow.app.architecture.aac.Event<java.lang.Boolean>> getLoadingEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final androidx.lifecycle.MutableLiveData<com.thingsflow.app.architecture.aac.Event<com.thingsflow.app.architecture.data.res.StringModel>> get_toastEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.thingsflow.app.architecture.aac.Event<com.thingsflow.app.architecture.data.res.StringModel>> getToastEvent() {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    public BaseViewModel() {
        super();
    }
}