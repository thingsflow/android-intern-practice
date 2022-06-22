package com.thingsflow.internapplication.ui.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.thingsflow.internapplication.MainActivity
import com.thingsflow.internapplication.R
import com.thingsflow.internapplication.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var glide: RequestManager

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        binding.titleTxtView.setOnClickListener(View.OnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireActivity())
            dialogBuilder.setTitle("입력")
            dialogBuilder.setMessage("Oranizaion/Repository")
            val popupView = layoutInflater.inflate(R.layout.input_popup, null)
            dialogBuilder.setView(popupView)
                .setPositiveButton("Search", DialogInterface.OnClickListener { dialog, id ->
                    val textView1: TextView = popupView.findViewById(R.id.input_org)
                    val textView2: TextView = popupView.findViewById(R.id.input_repo)
                    Log.d("LOG", "${textView1.text.toString()}, ${textView2.text.toString()}")
                    viewModel.updateList(textView1.text.toString(), textView2.text.toString())
                    dialog.cancel()
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })
            dialogBuilder.create()
            dialogBuilder.show()
        })
        glide = Glide.with(this)

        val adapter = RecyclerAdapter(object : RecyclerAdapter.HolderEvent {
            override fun onClickIssue(issue: Issue.GithubIssue) {
                val bundle = Bundle()
                bundle.putString("number", issue.number)
                bundle.putString("content", issue.body)
                val subFrag = DetailIssueFragment.newInstance()
                subFrag.arguments = bundle

                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, subFrag)
                    .addToBackStack(null).commit()
            }
        })

        binding.recyclerView.adapter = adapter

        viewModel.issueList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.listTitle.observe(viewLifecycleOwner, Observer {
            binding.titleTxtView.text = viewModel.listTitle.value.toString()
        })

        viewModel.searchSuccess.observe(viewLifecycleOwner, Observer {
            if (viewModel.searchSuccess.value == false) {
                val errDialogBuilder = AlertDialog.Builder(requireActivity())
                errDialogBuilder.setTitle("ERROR")
                errDialogBuilder.setMessage("A Non-existence Repository")
                errDialogBuilder.setPositiveButton(
                    "OK",
                    DialogInterface.OnClickListener { popdialog, i -> popdialog.cancel() })
                errDialogBuilder.create()
                errDialogBuilder.show()
                viewModel.changeBoolean(true)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        val activity = activity
        if (activity != null) {
            (activity as MainActivity).setActionBarTitle("InterApplication")
        }
    }

}