package com.example.taskmanager.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.ui.data.local.Pref
import com.example.taskmanager.ui.util.loadImage

class ProfileFragment : Fragment() {

    private val GALLERY_REQUEST_CODE = 1

    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOpenGallery()
        setPref()
    }

    private fun setPref() {
        pref = Pref(requireContext())
        binding.profileImage.loadImage(pref.profImage())
        binding.editName.setText(pref.editName())
        binding.buttSave.setOnClickListener {
            pref.userName(binding.editName.text.toString())
        }
    }

    private fun setOpenGallery() {
        binding.profileImage.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageUri = data?.data
            pref.userImage(imageUri.toString())
            binding.profileImage.loadImage(imageUri.toString())
        }
    }
}