package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.nmedia.R
import ru.netology.nmedia.auth.AppAuth
import ru.netology.nmedia.databinding.FragmentAuthUserBinding
import ru.netology.nmedia.util.AndroidUtils
import ru.netology.nmedia.viewmodel.AuthUserViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AuthUserFragment : Fragment() {

    @Inject
    lateinit var appAuth: AppAuth

    private val viewModel: AuthUserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAuthUserBinding.inflate(inflater, container, false)

        viewModel._loginModel.observe(viewLifecycleOwner) {
            if (it.error) {
                Snackbar.make(binding.root,
                    getString(R.string.wrong_login_or_password), Snackbar.LENGTH_LONG)
                    .show()
            }else if (it.authIsSuccess){
                findNavController().navigateUp()
            }
        }


        binding.submitButton.setOnClickListener {
            val login = binding.loginField.editText?.text.toString()
            val pass = binding.passwordField.editText?.text.toString()
            viewModel.getAuth(login, pass)
            AndroidUtils.hideKeyboard(requireView())
        }

        return binding.root
    }



}