package com.example.encryptionDecryption;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.*;
import android.widget.*;
import android.util.*;
import android.content.Context;

import java.security.*;

import static com.example.encryptionDecryption.RSA.decryptRSA;
import static com.example.encryptionDecryption.RSA.encryptRSA;

public class MainActivity extends AppCompatActivity {
    String message;
    String key;
    private String NumbTest = "0123456789";
    private  PlayFair p;
    private   Button Switch ;
    private  TextView answer ;
    private  EditText emessage ;
    private  EditText ekey;
    private TextView emat;
    private TextView emessage2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
         Switch = findViewById(R.id.Textv);
         answer = findViewById(R.id.EncDec);
         emessage = findViewById(R.id.edit);
         ekey = findViewById(R.id.Key);
        emat=findViewById(R.id.EncDecmat);
        emessage2=findViewById(R.id.EncDecenc);
        emat.setVisibility(View.GONE);
        emessage2.setVisibility(View.GONE);
        Switch.setText("Advanced Encryption Standard");
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void Encrypt(View view) throws Exception {
        KeyPair keyPair = null;
        if (emessage.length() == 0) {
            Toast.makeText(this, "Enter a message to Encrypt", Toast.LENGTH_SHORT).show();
            return;
        }

        message = String.valueOf(emessage.getText());
        key = String.valueOf(ekey.getText());
        if (Switch.equals("Advanced Encryption Standard")) {
            {
                AdvancedEncryptionStandard aes = new AdvancedEncryptionStandard();
                String encData = aes.AESencrypt(key.getBytes("UTF-16LE"), message.getBytes("UTF-16LE"));

                answer.setText(encData);
            }

        }  if (Switch.getText().equals("Caesar Cipher")) {

            if (key.length() == 0) {
                Toast.makeText(this, "Enter a key to Encrypt", Toast.LENGTH_SHORT).show();
                return;
            }
            if (Integer.parseInt(key) > 26) {
                Toast.makeText(this, "The Key must be 26 or under", Toast.LENGTH_SHORT).show();
                return;
            }
            caesarcipher c = new caesarcipher();
            answer.setText(c.caesarcipherEnc(message, Integer.parseInt(key)));

        }  if (Switch.getText().equals("Vigenere Cipher")) {
            if (ekey.length() == 0) {
                Toast.makeText(this, "Enter a key to Encrypt", Toast.LENGTH_SHORT).show();
                return;
            }
            for (int i = 0; i < message.length(); i++) {
                for (int j = 0; j < NumbTest.length(); j++) {
                    if (message.charAt(i) == NumbTest.charAt(j)) {
                        Toast.makeText(this, "Number is not Allowed here", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            for (int i = 0; i < key.length(); i++) {
                for (int j = 0; j < NumbTest.length(); j++) {
                    if (key.charAt(i) == NumbTest.charAt(j)) {
                        Toast.makeText(this, "Number is not Allowed here", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            Vigenere v = new Vigenere();
            answer.setText(v.Vigenereencrypt(message, key));

        } else if (Switch.getText().equals("RSA")) {
            try {
                keyPair = buildKeyPair();
                PrivateKey privateKey = keyPair.getPrivate();
                pubKey = keyPair.getPublic();
                byte[] signed = encryptRSA(privateKey, message);
                String stringToStore = new String(Base64.encode(signed, 0));
                answer.setText(stringToStore);
            }
            catch (Exception e) {
                Toast.makeText(this, "Your message is to long", Toast.LENGTH_SHORT).show();
            }

        }
        else if (Switch.getText().equals("Play Fair")) {
            try
            {
                p=new PlayFair("");
                emessage2.setText(p.Encrypt(message,key));
                emat.setText(p.getT1());
            }
            catch (Exception e)
            {
                Toast.makeText(this, "Numbers are not allowed here", Toast.LENGTH_SHORT).show();
            }
        }
        else if(Switch.getText().toString()=="$$$"){

        }
    }
    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }
    private static PublicKey pubKey;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void Decrypt(View view) throws Exception
    {
        if (emessage.length() == 0) {
            Toast.makeText(this, "Enter a message to Decrypt", Toast.LENGTH_SHORT).show();
            return;
        }

        message = String.valueOf(emessage.getText());
        key = String.valueOf(ekey.getText());
        if (Switch.getText().equals("Advanced Encryption Standard")) {
            AdvancedEncryptionStandard aes = new AdvancedEncryptionStandard();
            try {
                String decData = aes.AESdecrypt(key, Base64.decode(message.getBytes("UTF-16LE"), Base64.DEFAULT));
                answer.setText(decData);
            } catch (Exception e) {
                Toast.makeText(this, "Your key is worng", Toast.LENGTH_SHORT).show();
            }
        }

        if (Switch.getText().equals("Caesar Cipher")) {

            if (ekey.length() == 0) {
                Toast.makeText(this, "Enter a key", Toast.LENGTH_SHORT).show();
                return;
            }
            if (Integer.parseInt(key) >= 26) {
                Toast.makeText(this, "The Key must be 26 or under", Toast.LENGTH_SHORT).show();
                return;
            }
            caesarcipher c = new caesarcipher();
            answer.setText(c.caesarcipherDec(message, Integer.parseInt(key)));

        }  if (Switch.getText().equals("Vigenere Cipher")) {
            if (ekey.length() == 0) {
                Toast.makeText(this, "Enter a key to Decrypt", Toast.LENGTH_SHORT).show();
                return;
            }

            for (int i = 0; i < message.length(); i++) {
                for (int j = 0; j < NumbTest.length(); j++) {
                    if (message.charAt(i) == NumbTest.charAt(j)) {
                        Toast.makeText(this, "Number is not Allowed here", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            for (int i = 0; i < key.length(); i++) {
                for (int j = 0; j < NumbTest.length(); j++) {
                    if (key.charAt(i) == NumbTest.charAt(j)) {
                        Toast.makeText(this, "Number is not Allowed here", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            Vigenere v = new Vigenere();
            answer.setText(v.Vigeneredecrypt(message, key));

        }
        if (Switch.getText().equals("RSA")) {
try {
    byte[] restoredBytes = Base64.decode(message.getBytes(), 0);
    byte[] verified = decryptRSA(pubKey, restoredBytes);
    answer.setText(new String(verified));
    }
         catch (Exception e)
         {
            Toast.makeText(this, "Your key is worng", Toast.LENGTH_SHORT).show();

        }
    }
         if (Switch.getText().equals("Play Fair")) {
try {
    emessage2.setText(p.Decrypt(message,key));
    emat.setText(p.getT1());
}
 catch (Exception e)
 {

                 Toast.makeText(this, "Numbers are not allowed here", Toast.LENGTH_SHORT).show();
 }
        }

        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void switchAlgho(View view)
    {
        RESET(view);

        if (Switch.getText().equals("Advanced Encryption Standard")) {
            ekey.setInputType(InputType.TYPE_CLASS_NUMBER);
            Switch.setText("Caesar Cipher");
        } else if (Switch.getText().equals("Caesar Cipher")) {
            ekey.setInputType(InputType.TYPE_CLASS_TEXT);
            Switch.setText("Vigenere Cipher");
        } else if (Switch.getText().equals( "Vigenere Cipher")) {
            Switch.setText("RSA");
            ekey.setVisibility(View.INVISIBLE);
        } else if (Switch.getText().equals("RSA")) {
            ekey.setVisibility(View.VISIBLE);
            answer.setVisibility(View.GONE);
            emat.setVisibility(View.VISIBLE);
            emessage2.setVisibility(View.VISIBLE);
            Switch.setText("Play Fair");
        } else if (Switch.getText().equals("Play Fair")) {
            answer.setVisibility(View.VISIBLE);
            emat.setVisibility(View.GONE);
            emessage2.setVisibility(View.GONE);
            Switch.setText("Advanced Encryption Standard");
        } else if (Switch.getText().toString() == "$$$") {
            Switch.setText("Advanced Encryption Standard");
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void RESET(View view)
    {
        emessage.setText("");
        ekey.setText("");
        answer.setText("");
        emessage2.setText("");
        emat.setText("");
        Toast.makeText(this, "All data has been deleted", Toast.LENGTH_SHORT).show();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void copyToClipboard(View view)
    {
        if (emessage2.length() == 0) {
            String copyText = String.valueOf(answer.getText());
            if (answer.length() == 0) {
                Toast.makeText(this, "There is no message to copy", Toast.LENGTH_SHORT).show();
                return;
            }

            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(copyText);
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData
                        .newPlainText("Your message :", copyText);
                clipboard.setPrimaryClip(clip);
            }
            Toast.makeText(this,
                    "Your message has be copied", Toast.LENGTH_SHORT).show();


        }
        else
        {
            if (emessage2.length() == 0) {
                Toast.makeText(this, "There is no message to copy", Toast.LENGTH_SHORT).show();
                return;
            }

            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(emessage2.getText().toString());
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData
                        .newPlainText("Your message :", emessage2.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
            Toast.makeText(this, "Your message has be copied", Toast.LENGTH_SHORT).show();
        }
    }

}

















