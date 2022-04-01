package com.thingsflow.internapplication.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.thingsflow.internapplication.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var issueAdapter: IssueAdapter
    private val viewModel: MainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.changeTitle("google", "dagger")
        binding.issueRecyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = issueAdapter
        }
        observe()

        binding.title.setOnClickListener(View.OnClickListener {
            // 다른 Repository를 입력받을 수 있는 팝업 입력창 띄움
            val dialog = InputDialog.newInstance()
//            dialog.
            dialog.show(parentFragmentManager, "InputDialog")
        })
    }

    private fun observe() = with(viewModel) {
        orgName.observe(viewLifecycleOwner, Observer {
            binding.orgName.setText(it)
        })

        repoName.observe(viewLifecycleOwner, Observer {
            binding.repoName.setText(it)
        })

        issues.observe(viewLifecycleOwner, Observer {
            issueAdapter.submitList(it)
        })
    }

}