package com.thingsflow.app.architecture.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000:\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0002\u001a%\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u0007*\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000b\u001a%\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u0007*\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\r\u001a%\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u0007*\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000f\u001a\n\u0010\u0010\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0011\u001a\u00020\u0001*\u00020\u0002\u001a\u001e\u0010\u0012\u001a\u00020\u0013*\u00020\b2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u0007\u001a\u0012\u0010\u0015\u001a\u00020\u0013*\u00020\b2\u0006\u0010\u0016\u001a\u00020\n\u00a8\u0006\u0017"}, d2 = {"displayWidth", "", "Landroid/content/Context;", "dpToPx", "", "context", "fullScreen", "Lkotlin/Pair;", "Landroid/app/Activity;", "statusBarIconWhite", "", "(Landroid/app/Activity;Ljava/lang/Boolean;)Lkotlin/Pair;", "Landroid/view/Window;", "(Landroid/view/Window;Ljava/lang/Boolean;)Lkotlin/Pair;", "Landroidx/appcompat/app/AppCompatDialog;", "(Landroidx/appcompat/app/AppCompatDialog;Ljava/lang/Boolean;)Lkotlin/Pair;", "getNavigationBarHeight", "getStatusBarHeight", "restoreScreen", "", "originalFlags", "setStatusBarTheme", "whiteStatusIcon", "android-architecture-kit_debug"})
public final class ExtensionsKt {
    
    public static final int displayWidth(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$displayWidth) {
        return 0;
    }
    
    public static final int dpToPx(float $this$dpToPx, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final kotlin.Pair<java.lang.Integer, java.lang.Integer> fullScreen(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$fullScreen, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean statusBarIconWhite) {
        return null;
    }
    
    public static final void restoreScreen(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$restoreScreen, @org.jetbrains.annotations.NotNull()
    kotlin.Pair<java.lang.Integer, java.lang.Integer> originalFlags) {
    }
    
    public static final void setStatusBarTheme(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$setStatusBarTheme, boolean whiteStatusIcon) {
    }
    
    public static final int getStatusBarHeight(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$getStatusBarHeight) {
        return 0;
    }
    
    public static final int getNavigationBarHeight(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$getNavigationBarHeight) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final kotlin.Pair<java.lang.Integer, java.lang.Integer> fullScreen(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatDialog $this$fullScreen, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean statusBarIconWhite) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final kotlin.Pair<java.lang.Integer, java.lang.Integer> fullScreen(@org.jetbrains.annotations.NotNull()
    android.view.Window $this$fullScreen, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean statusBarIconWhite) {
        return null;
    }
}