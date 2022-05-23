package com.example.final2.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.final2.databinding.FragmentNotificationsBinding
import com.example.final2.ui.login.LoginActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val notificationsViewModel =
            ViewModelProvider(this)[NotificationsViewModel::class.java]

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val hello : TextView = binding.hello
        val switch : SwitchCompat = binding.themeSwitch
        val delete : Button = binding.deleteAccount
        val logout : Button = binding.logoutAccount
        val textView: TextView = binding.textNotifications
        switch.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        logout.setOnClickListener {
            Toast.makeText(activity, "Logged out", Toast.LENGTH_SHORT).show()

            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(activity, LoginActivity::class.java))
        }
        delete.setOnClickListener {

            val user = FirebaseAuth.getInstance().currentUser
            user?.delete()?.addOnCompleteListener(OnCompleteListener<Void>() {

                    if (user.delete().isSuccessful) {
                        Toast.makeText(activity, "Deleted account", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(activity, LoginActivity::class.java))
                    }

            })

                    }
                            notificationsViewModel.text.observe(viewLifecycleOwner) {
                        textView.text = it
                    }
                        return root
            }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}