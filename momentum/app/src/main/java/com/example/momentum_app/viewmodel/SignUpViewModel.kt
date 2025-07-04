import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momentum_app.repository.UserRepository
import kotlinx.coroutines.launch


class SignUpViewModel : ViewModel() {
    private val repository = UserRepository()

    fun registerUser(username: String, email: String, password: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {

                val response = repository.signUp(username, email, password)


                if (response.isSuccessful) {
                    onResult(true, "User registered successfully")
                } else {
                    onResult(false, "Failed to register user")
                }

            } catch (e: Exception) {
                onResult(false, "Error: ${e.message}")
            }
        }
    }

    fun loginUser(context: Context,username: String, password: String, onResult: (Boolean, String) -> Unit){
        viewModelScope.launch {
            val username = getUsername(context)
            val login = repository.signIn(username, password)
            onResult(true, "User logged in successfully")
            saveUsername(context, username)

//            if (login.isSuccessful)
//            {
//                onResult(true, "User logged in successfully")
//            } else
//            {
//                onResult(false, "Failed to login user")
//            }
        }
    }
}
fun saveUsername(context: Context, username: String) {
    val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putString("username", username)
        apply()
    }
}

private fun getUsername(context: Context): String {
    val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    return sharedPref.getString("username", "User") ?: "User"
}