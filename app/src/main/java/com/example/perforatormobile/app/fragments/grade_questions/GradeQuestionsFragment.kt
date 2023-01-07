package com.example.perforatormobile.app.fragments.grade_questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.perforatormobile.R
import com.example.perforatormobile.databinding.FragmentGradeQuestionsBinding
import com.example.perforatormobile.domain.entities.Grade

class GradeQuestionsFragment: Fragment(R.layout.fragment_grade_questions) {
    private val viewModel: GradeQuestionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentGradeQuestionsBinding.inflate(inflater, container, false)

        val gradedQuestionsListAdapter = GradedQuestionsListAdapter(::updateGradeInViewModel)
        binding.gradeQuestionsViewPager.adapter = gradedQuestionsListAdapter
        gradedQuestionsListAdapter.submitList(viewModel.grades)

        binding.saveGradesButton.setOnClickListener {

        }

        return binding.root
    }

    private fun updateGradeInViewModel(position: Int, grade: Grade)
    {
        viewModel.grades[position] = grade
    }

}