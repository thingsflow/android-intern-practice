package com.thingsflow.app.architecture.aac;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a@\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00060\u00052\u0014\b\u0004\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\bH\u0086\b\u00f8\u0001\u0000\u001a@\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\t2\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00060\u00052\u0014\b\u0004\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\bH\u0086\b\u00f8\u0001\u0000\u001a:\u0010\n\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0014\b\u0004\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\bH\u0086\b\u00f8\u0001\u0000\u001a:\u0010\n\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\t2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0014\b\u0004\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\bH\u0086\b\u00f8\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006\u000b"}, d2 = {"event", "", "T", "Landroidx/appcompat/app/AppCompatActivity;", "liveData", "Landroidx/lifecycle/LiveData;", "Lcom/thingsflow/app/architecture/aac/Event;", "runnable", "Lkotlin/Function1;", "Landroidx/fragment/app/Fragment;", "observe", "android-architecture-kit_debug"})
public final class ExtensionsKt {
    
    public static final <T extends java.lang.Object>void observe(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$observe, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> liveData, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> runnable) {
    }
    
    public static final <T extends java.lang.Object>void event(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment $this$event, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.thingsflow.app.architecture.aac.Event<T>> liveData, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> runnable) {
    }
    
    public static final <T extends java.lang.Object>void observe(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity $this$observe, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> liveData, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> runnable) {
    }
    
    public static final <T extends java.lang.Object>void event(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity $this$event, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.thingsflow.app.architecture.aac.Event<T>> liveData, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> runnable) {
    }
}