/*
 * Created by Jithin kozhiyodan on 29/11/20 11:56 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 29/11/20 11:56 PM
 *
 */

package com.hav.havlife.view.fragment

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.hav.havlife.R
import com.hav.havlife.data.data_util.CommonUtil.loadDateOfBirth
import com.hav.havlife.model.request_data.user.ProfileRequest
import com.hav.havlife.view.activity.ProfileCompletionContainer
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profile_completion_step_one.*
import kotlinx.android.synthetic.main.fragment_profile_completion_step_one.view.*
import java.io.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileCompletionStepOne.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileCompletionStepOne : Fragment() {

    private val PERMISSION_REQUEST_CODE = 103
    private val STORAGE_PERMISSION_REQUEST_CODE = 104
    private val CAMERA_REQUEST_CODE = 0
    private val STORAGE_REQUEST_CODE = 1
    var uuid = ""
    private var isEdited = false
    private var isEdit = false
    private var dataPicProfile: String? = ""
    var imgProfilePic: CircleImageView? = null
    private lateinit var edDob: EditText
    lateinit var txtProfilePic: TextView
    lateinit var imgAddPic: ImageView

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var obj: ProfileRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            obj = it.getSerializable("obj") as ProfileRequest
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile_completion_step_one, container, false)

        Log.i("email", obj.email)
        initUI(view)
        view.btnStepOne.setOnClickListener {
            obj.name = view.edFirstName.text.toString()
            obj.lastName = view.edLastName.text.toString()
            obj.birthDate = view.edDob.text.toString()
            obj.location = view.edLocation.text.toString()


            (activity as ProfileCompletionContainer).changeFragment(
                ProfileCompletionStepTwo(),
                "ProfileCompletionStepTwo"
            )
        }

        view.txtProfilePic.setOnClickListener {
            requestPermission()
        }
        view.imgAddPic.setOnClickListener {
            requestPermission()
        }
        edDob.setOnClickListener {
            getCalendar()
        }
        return view
    }

    private fun initUI(view: View) {
        imgProfilePic = view.findViewById(R.id.imgProfilePic)
        edDob = view.findViewById(R.id.edDob)
        txtProfilePic = view.findViewById(R.id.txtProfilePic)
        imgAddPic = view.findViewById(R.id.imgAddPic)
    }

    override fun onResume() {
        super.onResume()
        if (isEdit) {
            txtProfilePic.visibility = View.VISIBLE
            imgAddPic.visibility = View.GONE
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> requestPermission()
            STORAGE_PERMISSION_REQUEST_CODE -> requestPermission()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == STORAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null && data.data != null) run {
            var bitmap: Bitmap? = null
            if (data != null) {
                try {
                    bitmap =
                        MediaStore.Images.Media.getBitmap(
                            requireActivity()!!.contentResolver,
                            data.data
                        )
                    //bitmap = MediaStore.Images.Media.getBitmap(this,contentResolver,data.data)// as Nothing?
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            imgProfilePic!!.setImageBitmap(bitmap)
            isEdit = true
            isEdited = true
            val bytes = ByteArrayOutputStream()
            bitmap!!.compress(Bitmap.CompressFormat.JPEG, 90, bytes)

            val rootDir = requireActivity()!!.filesDir;
            val usrDir = "userPic"
            Log.i("rootDir", "rootDir${rootDir.toString()}")

            val desFolder = File("$rootDir/$usrDir")
            if (!desFolder.exists()) {
                desFolder.mkdir()
            }
            //Log.i("rootDir","rootDir${rootDir.toString()}")
            uuid = UUID.randomUUID().toString() + ".jpg"
            val destination = File("$desFolder/", uuid)


//            uuid=UUID.randomUUID().toString()+".jpg"
//            val destination = File(rootDir.toString()+"/userPic/",uuid)

            dataPicProfile = uuid//bytes.toByteArray()
            //txtUploadDoc.text = "Document.jpg"//dataPicProfile

            val fo: FileOutputStream
            try {
                destination.createNewFile();
                fo = FileOutputStream(destination)
                fo.write(bytes.toByteArray());
                fo.close();

                val bm = BitmapFactory.decodeFile(destination.absolutePath)
                val baos = ByteArrayOutputStream()
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)

//                val ba: ByteArray = baos.toByteArray()
//                val encodedString = Base64.encodeToString(ba, Base64.DEFAULT)
//                imgProfilePicB64 = encodedString


            } catch (e: FileNotFoundException) {
                e.printStackTrace();
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        else if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val thumbnail: Bitmap = data!!.extras!!.get("data") as Bitmap
            val bytes = ByteArrayOutputStream()
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
            val rootDir = requireActivity()!!.filesDir;
            Log.i("rootDir", "rootDir${rootDir.toString()}")
            uuid = UUID.randomUUID().toString() + ".jpg"
            //val destination = File(rootDir.toString()+"/userPic/",uuid)

            val usrDir = "userPic"
            Log.i("rootDir", "rootDir${rootDir.toString()}")

            val desFolder = File(rootDir.toString() + "/" + usrDir)
            if (!desFolder.exists()) {
                desFolder.mkdir()
            }
            //Log.i("rootDir","rootDir${rootDir.toString()}")
            //val uuid=UUID.randomUUID().toString()+".jpg"
            val destination = File(desFolder.toString() + "/", uuid)




            dataPicProfile = uuid//bytes.toByteArray()

            val fo: FileOutputStream
            try {
                destination.createNewFile();
                fo = FileOutputStream(destination)
                fo.write(bytes.toByteArray());
                fo.close();

                val bm = BitmapFactory.decodeFile(destination.absolutePath)
                val baos = ByteArrayOutputStream()
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)


            } catch (e: FileNotFoundException) {
                e.printStackTrace();
            } catch (e: IOException) {
                e.printStackTrace()
            }
            //imgProfilePic!!.setImageBitmap(thumbnail)
            imgProfilePic!!.setImageBitmap(thumbnail)
            //txtUploadDoc.text = dataPicProfile
            isEdit = true
            isEdited = true

        }
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                requireActivity()!!,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity()!!,
                    android.Manifest.permission.CAMERA
                )
            ) {
                //showUpdatePopUp()
                //ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), PERMISSION_REQUEST_CODE)
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity()!!,
                    arrayOf(
                        android.Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    PERMISSION_REQUEST_CODE
                )
            }
        } else if (ContextCompat.checkSelfPermission(
                requireActivity()!!,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity()!!,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                //showUpdatePopUp()
                //ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_PERMISSION_REQUEST_CODE)
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    STORAGE_PERMISSION_REQUEST_CODE
                )
            }
        } else {
            //bottomSheetLayout(0)
            //mBottomSheetDialog!!.show()
            selectImage()
        }
    }

    private fun selectImage() {
        val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder = AlertDialog.Builder(requireActivity()!!)
        builder.setTitle("Add Photo!")
        builder.setItems(options, DialogInterface.OnClickListener { dialog, item ->
            if (options[item] == "Take Photo") {

                val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePicture, CAMERA_REQUEST_CODE)

//                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                val f = File(Environment.getExternalStorageDirectory(), "temp.jpg")
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f))
//                startActivityForResult(intent, CAMERA_REQUEST_CODE)
            } else if (options[item] == "Choose from Gallery") {

                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT//
                startActivityForResult(
                    Intent.createChooser(intent, "Select File"),
                    STORAGE_REQUEST_CODE
                )

//                val intent =
//                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//                startActivityForResult(intent, STORAGE_REQUEST_CODE)
            } else if (options[item] == "Cancel") {
                dialog.dismiss()
            }
        })
        builder.show()
    }

    private fun getCalendar() {

        val c = Calendar.getInstance()
        c.add(Calendar.YEAR, -18)
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        //txtBob.text = loadDateOfBirth("$day-${month + 1}-$year")

        //memberRequest.familyMemberDob = convertDateToSql(btnDob.text.toString())
        edDob!!.setOnClickListener {
            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, month, day ->
                    // Display Selected date in TextView
                    edDob!!.setText(loadDateOfBirth("$day-${month + 1}-$year"))

                },
                year,
                month,
                day
            )
            dpd.show()
            dpd.datePicker.maxDate = c.timeInMillis

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileCompletionStepOne.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileCompletionStepOne().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}