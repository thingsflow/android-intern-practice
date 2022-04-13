package com.thingsflow.app.architecture.base.databinding;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005B\u0005\u00a2\u0006\u0002\u0010\u0006J\r\u0010\u0016\u001a\u00028\u0001H$\u00a2\u0006\u0002\u0010\u000bJ\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0018H\u0014J\b\u0010\u001c\u001a\u00020\u0018H$R\u0012\u0010\u0007\u001a\u0004\u0018\u00018\u0001X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\bR\u0014\u0010\t\u001a\u00028\u00018DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0012\u0010\u0013\u001a\u00028\u0000X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\u001d"}, d2 = {"Lcom/thingsflow/app/architecture/base/databinding/BaseActivity;", "VM", "Lcom/thingsflow/app/architecture/base/BaseViewModel;", "B", "Landroidx/databinding/ViewDataBinding;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "_binding", "Landroidx/databinding/ViewDataBinding;", "binding", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "cleaner", "Lcom/thingsflow/app/architecture/base/Cleaner;", "getCleaner", "()Lcom/thingsflow/app/architecture/base/Cleaner;", "isInitializedBinding", "", "()Z", "viewModel", "getViewModel", "()Lcom/thingsflow/app/architecture/base/BaseViewModel;", "createBinding", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setupUi", "android-architecture-kit_debug"})
public abstract class BaseActivity<VM extends com.thingsflow.app.architecture.base.BaseViewModel, B extends androidx.databinding.ViewDataBinding> extends androidx.appcompat.app.AppCompatActivity {
    private B _binding;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final B getBinding() {
        return null;
    }
    
    protected final boolean isInitializedBinding() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract VM getViewModel();
    
    @org.jetbrains.annotations.NotNull()
    protected final com.thingsflow.app.architecture.base.Cleaner getCleaner() {
        return null;
    }
    
    protected abstract void setupUi();
    
    @org.jetbrains.annotations.NotNull()
    protected abstract B createBinding();
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public BaseActivity() {
        super();
    }
}