package View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import Model.UserFragment;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        showUserFragment();


    }

    private void showUserFragment() {
        // Exibe o fragmento UserFragment
        UserFragment fragment = new UserFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit();
    }
}