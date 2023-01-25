package com.example.perforatormobile.app.fragments.grade_questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.perforatormobile.R
import com.example.perforatormobile.databinding.FragmentGradeQuestionsBinding
import com.example.perforatormobile.domain.entities.Grade
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GradeQuestionsFragment: Fragment(R.layout.fragment_grade_questions) {
    private val viewModel: GradeQuestionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGradeQuestionsBinding.inflate(inflater, container, false)

        val gradedQuestionsListAdapter = GradedQuestionsListAdapter(
            viewModel::updateComment,
            viewModel::updateGrade
        )
        binding.gradeQuestionsViewPager.adapter = gradedQuestionsListAdapter
        gradedQuestionsListAdapter.submitList(viewModel.grades)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isReviewSaved
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect {
                    if (it) {
                        findNavController().navigate(R.id.action_navigation_grade_questions_to_peers_review)
                    }
                }
        }

        binding.saveGradesButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.saveReview()
            }
        }

        return binding.root
    }
}