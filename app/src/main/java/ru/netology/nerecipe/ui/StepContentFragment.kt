package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.netology.nerecipe.databinding.AddOrEditStepFragmentBinding
import ru.netology.nerecipe.util.focusAndShowKeyboard

class StepContentFragment : Fragment() {

    private val args by navArgs<StepContentFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = AddOrEditStepFragmentBinding.inflate(
        layoutInflater, container, false
    ).also { binding ->
        binding.edit.setText(args.initialStepText)
        binding.edit.focusAndShowKeyboard()
        binding.ok.setOnClickListener {
            onOkButtonClicked(binding)
        }
    }.root

    private fun onOkButtonClicked(binding: AddOrEditStepFragmentBinding) {
        val text = binding.edit.text

        if (!text.isNullOrBlank()) {
            val resultBundle = Bundle(1)
            resultBundle.putString(RESULT_KEY, text.toString())
            setFragmentResult(
                REQUEST_CURRENT_RECIPE_KEY,
                resultBundle
            )
        }
        findNavController().popBackStack()
    }

    companion object {
        const val REQUEST_CURRENT_RECIPE_KEY = "requestForCurrentRecipeFragmentKey"
        const val RESULT_KEY = "recipeNewContent"
    }
}