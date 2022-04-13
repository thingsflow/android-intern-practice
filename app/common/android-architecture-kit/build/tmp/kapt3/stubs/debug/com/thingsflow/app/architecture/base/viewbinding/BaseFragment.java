package com.thingsflow.app.architecture.base.viewbinding;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005B\u0005\u00a2\u0006\u0002\u0010\u0006J\u001f\u0010\u001f\u001a\u00028\u00012\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H$\u00a2\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\u000bH$J\b\u0010&\u001a\u00020\u000bH$J\u0010\u0010\'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020)H\u0016J&\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u0010.\u001a\u00020\u000bH\u0016J\b\u0010/\u001a\u00020\u000bH\u0016J\u001a\u00100\u001a\u00020\u000b2\u0006\u00101\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u00102\u001a\u00020\u000bH$R\u0012\u0010\u0007\u001a\u0004\u0018\u00018\u0001X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\bR\u001a\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u00a4\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00028\u00018DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168DX\u0084\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u0019X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0012\u0010\u001c\u001a\u00028\u0000X\u00a4\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\u00a8\u00063"}, d2 = {"Lcom/thingsflow/app/architecture/base/viewbinding/BaseFragment;", "VM", "Lcom/thingsflow/app/architecture/base/BaseViewModel;", "B", "Landroidx/viewbinding/ViewBinding;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Landroidx/viewbinding/ViewBinding;", "backFunc", "Lkotlin/Function0;", "", "getBackFunc", "()Lkotlin/jvm/functions/Function0;", "binding", "getBinding", "()Landroidx/viewbinding/ViewBinding;", "cleaner", "Lcom/thingsflow/app/architecture/base/Cleaner;", "getCleaner", "()Lcom/thingsflow/app/architecture/base/Cleaner;", "isInitializedBinding", "", "()Z", "onBackPressed", "Landroidx/activity/OnBackPressedCallback;", "getOnBackPressed", "()Landroidx/activity/OnBackPressedCallback;", "viewModel", "getViewModel", "()Lcom/thingsflow/app/architecture/base/BaseViewModel;", "createBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;)Landroidx/viewbinding/ViewBinding;", "loadData", "observeUi", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onDetach", "onViewCreated", "view", "setupUi", "android-architecture-kit_debug"})
public abstract class BaseFragment<VM extends com.thingsflow.app.architecture.base.BaseViewModel, B extends androidx.viewbinding.ViewBinding> extends androidx.fragment.app.Fragment {
    private B _binding;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.OnBackPressedCallback onBackPressed = null;
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
    protected abstract kotlin.jvm.functions.Function0<kotlin.Unit> getBackFunc();
    
    @org.jetbrains.annotations.NotNull()
    public androidx.activity.OnBackPressedCallback getOnBackPressed() {
        return null;
    }
    
    protected abstract void loadData();
    
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
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @java.lang.Override()
    public void onDetach() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public BaseFragment() {
        super();
    }
}