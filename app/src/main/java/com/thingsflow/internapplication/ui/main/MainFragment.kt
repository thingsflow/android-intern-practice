package com.thingsflow.internapplication.ui.main

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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

        binding.issueRecyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = issueAdapter
        }
        observe()

        binding.title.setOnClickListener(View.OnClickListener {
            // 다른 Repository를 입력받을 수 있는 팝업 입력창 띄움
            val dialog = InputDialog.newInstance()
            dialog.show(parentFragmentManager, "InputDialog")
        })
    }

    private fun observe() = with(viewModel) {
        orgName.observe(viewLifecycleOwner, Observer {
            binding.orgName.text = it
        })

        repoName.observe(viewLifecycleOwner, Observer {
            binding.repoName.text = it
        })

        issues.observe(viewLifecycleOwner, Observer {
            issueAdapter.submitList(it)
        })

        loadingError.observe(viewLifecycleOwner, Observer {
            if (it) {
                showAlertDialog("입력 오류", "입력한 Repository를 조회할 수 없습니다.")
            }
        })

        eventStartDetailActivity.observeEvent(viewLifecycleOwner, Observer {
            navigateToDetailFragment()
        })
    }

    private fun navigateToDetailFragment() {
        val navDirection: NavDirections = MainFragmentDirections.actionMainFragmentToDetailFragment()
        val navController = findNavController()

        navController.navigate(navDirection)
    }

    private fun showAlertDialog(title: String, message: String) {
        val alertDialog = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()

        alertDialog.show()
    }
}