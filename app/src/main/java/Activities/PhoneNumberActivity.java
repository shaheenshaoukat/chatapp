package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chatapp.databinding.ActivityPhoneNumberBinding;
import com.google.firebase.auth.FirebaseAuth;

public class PhoneNumberActivity extends AppCompatActivity {

    ActivityPhoneNumberBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPhoneNumberBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        //openkeypad
        binding.phoneBox.requestFocus();

        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null)
        {
            startActivity(new Intent(PhoneNumberActivity.this,MainActivity.class));
            finish();
        }




        getSupportActionBar().hide();
        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PhoneNumberActivity.this,OTPActivity.class);
                intent.putExtra("phonenumber",binding.phoneBox.getText().toString());
                startActivity(intent);

            }
        });
    }
}