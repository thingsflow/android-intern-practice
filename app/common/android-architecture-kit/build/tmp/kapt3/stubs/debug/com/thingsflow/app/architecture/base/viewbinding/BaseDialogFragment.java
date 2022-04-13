package com.thingsflow.app.architecture.base.viewbinding;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005B\u0005\u00a2\u0006\u0002\u0010\u0006J\u001f\u0010\u001d\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H$\u00a2\u0006\u0002\u0010\"J\b\u0010#\u001a\u00020\u0015H$J\u0012\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J&\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010*\u001a\u00020\u0015H\u0016J\u0010\u0010\u0013\u001a\u00020\u00152\u0006\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020\u0015H\u0016J\u001a\u0010.\u001a\u00020\u00152\u0006\u0010/\u001a\u00020)2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J \u00100\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\b\u00102\u001a\u00020\u0015H$R\u0012\u0010\u0007\u001a\u0004\u0018\u00018\u0001X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\bR\u0014\u0010\t\u001a\u00028\u00018DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00028\u0000X\u00a4\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\u00a8\u00063"}, d2 = {"Lcom/thingsflow/app/architecture/base/viewbinding/BaseDialogFragment;", "VM", "Lcom/thingsflow/app/architecture/base/BaseViewModel;", "B", "Landroidx/viewbinding/ViewBinding;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "()V", "_binding", "Landroidx/viewbinding/ViewBinding;", "binding", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "cleaner", "Lcom/thingsflow/app/architecture/base/Cleaner;", "getCleaner", "()Lcom/thingsflow/app/architecture/base/Cleaner;", "isInitializedBinding", "", "()Z", "onDismiss", "Lkotlin/Function0;", "", "getOnDismiss", "()Lkotlin/jvm/functions/Function0;", "setOnDismiss", "(Lkotlin/jvm/functions/Function0;)V", "viewModel", "getViewModel", "()Lcom/thingsflow/app/architecture/base/BaseViewModel;", "createBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;)Landroidx/viewbinding/ViewBinding;", "observeUi", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "onDestroyView", "dialog", "Landroid/content/DialogInterface;", "onStart", "onViewCreated", "view", "setOnDismissListener", "listener", "setupUi", "android-architecture-kit_debug"})
public abstract class BaseDialogFragment<VM extends com.thingsflow.app.architecture.base.BaseViewModel, B extends androidx.viewbinding.ViewBinding> extends androidx.appcompat.app.AppCompatDialogFragment {
    private B _binding;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final B getBinding() {
        return null;
    }
    
    protected final boolean isInitializedBinding() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected abstract VM getViewModel();
    
    @org.jetbrains.annotations.NotNull()
    protected final com.thingsflow.app.architecture.base.Cleaner getCleaner() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kotlin.jvm.functions.Function0<kotlin.Unit> getOnDismiss() {
        return null;
    }
    
    public final void setOnDismiss(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> p0) {
    }
    
    protected abstract void setupUi();
    
    protected abstract void observeUi();
    
    @org.jetbrains.annotations.NotNull()
    protected abstract B createBinding(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container);
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onStart() {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onDismiss(@org.jetbrains.annotations.NotNull()
    android.content.DialogInterface dialog) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.thingsflow.app.architecture.base.viewbinding.BaseDialogFragment<VM, B> setOnDismissListener(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> listener) {
        return null;
    }
    
    public BaseDialogFragment() {
        super();
    }
}