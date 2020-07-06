package Hash;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.Algorithms.R;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


public class HashMain extends Fragment {
    private Button Switch;
    private Button Hash_Buuton;
    private TextView Answer;
    private EditText Textfield_Text;
    private EditText Textfield_salt;
    private EditText Textfield_round;
    private String message;
    private String salt;
    private int round;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hash_main, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Switch = view.findViewById(R.id.Swtich);
        Hash_Buuton = view.findViewById(R.id.hash_Buuton);
        Answer = view.findViewById(R.id.Answer);
        Textfield_Text = view.findViewById(R.id.TextArea);
        Textfield_salt = view.findViewById(R.id.salt);
        Textfield_round = view.findViewById(R.id.round);

        return view;
    }



    public void hash(View view) throws Exception {


        if (Textfield_Text.length() == 0) {
            Toast.makeText(view.getContext(), "Enter a message to Encrypt", Toast.LENGTH_SHORT).show();
            return;
        }
        message = String.valueOf(Textfield_Text.getText());
        salt = String.valueOf(Textfield_salt.getText());
        round = Integer.parseInt(String.valueOf(Textfield_round.getText()));
        String Algorithm = String.valueOf(Switch.getText());
        switch (Algorithm) {
            case "SHA-256": {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
                String encoded = Base64.encodeToString(hash, 0);
                Answer.setText(encoded);
            }


        }
    }







    public void switchAlgho(View view) {
        reset(null);
        String SwitchValue = Switch.getText().toString();
        switch (SwitchValue) {
            case "SHA-256":
                Switch.setText("Advanced Encryption Standard");
                break;

        }

    }


    public void reset(View view) {
        Textfield_Text.setText("");
        Textfield_salt.setText("");
        Textfield_round.setText("");
        Answer.setText("");
        if(view!=null)
        Toast.makeText(view.getContext(), "All data has been deleted", Toast.LENGTH_SHORT).show();
    }



    public void copyToClipboard(View view) {
            String copyText = String.valueOf(Answer.getText());
            if (Answer.length() == 0) {
                Toast.makeText(view.getContext(), "There is no message to copy", Toast.LENGTH_SHORT).show();
                return;
            }

            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager)
                        view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(copyText);
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                        view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData
                        .newPlainText("Your message :", copyText);
                clipboard.setPrimaryClip(clip);
            }
            Toast.makeText(view.getContext(),
                    "Your message has be copied", Toast.LENGTH_SHORT).show();
        }
    }






















