package Main;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.Algorithms.R;

import Encryption.EncryptionManin;



public class MainActivity extends AppCompatActivity {
    EncryptionManin encryptionManin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void goToEncryption(View view) {
        encryptionManin = new EncryptionManin();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.container, encryptionManin);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void buttonClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.Swtich:
                    encryptionManin.switchAlgho(view);
                    break;
                case R.id.Encrypt_Buuton:
                    encryptionManin.Encrypt(view);
                    break;
                case R.id.Decrypt_Buuton:
                    encryptionManin.Decrypt(view);
                    break;
                case R.id.copy_button:
                    encryptionManin.copyToClipboard(view);
                    break;
                case R.id.reset_button:
                    encryptionManin.reset(view);
                    break;
            }
        }
        catch (Exception e){
            e.printStackTrace();
    }

    }
}
