package com.thingsflow.internapplication.ui.main

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

interface OnClickIssueListener {
    fun onClick(issueIdx: Int)
}

@Module
@InstallIn(FragmentComponent::class)
abstract class OnClickIssueModule {
    @Binds
    abstract fun bindOnClickIssue(onClickIssueListenerImpl: MainFragment.OnClickIssueListenerImpl) : OnClickIssueListener
}