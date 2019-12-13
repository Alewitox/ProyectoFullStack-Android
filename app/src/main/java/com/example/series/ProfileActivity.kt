package com.example.series

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_dialog.*
import kotlinx.android.synthetic.main.activity_dialog.view.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.toolbar
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setSupportActionBar(toolbar)

        editAliasBtn.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Editar alias")

            val mAlertDialog = mBuilder.show()
            mDialogView.dialogButtonAccept.setOnClickListener{
                mAlertDialog.dismiss()
                val name = mAlertDialog.dialogAlias.text.toString()
                aliasTextView.setText(name)
                profileTextView.setText(name)

            }
            mDialogView.dialogButtonCancel.setOnClickListener{
                mAlertDialog.dismiss()
            }
        }
    }




    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
