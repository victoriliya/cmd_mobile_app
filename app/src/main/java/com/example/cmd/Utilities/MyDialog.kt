import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColor
import com.example.cmd.R

object MyDialog {

    fun showLoadingDialog(context: Context):Dialog{

        val progressDialog = Dialog(context)

        progressDialog.let {
            it.show()
            it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setContentView(R.layout.confirm_success_dialogue)
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            return it
        }
    }
}