package View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.desafiomobile.R;

public class SplashView extends AppCompatActivity {

    private static final int SPLASH_DELAY_MS = 1000; // Tempo de exibição da splash screen (1 segundo)

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Defina o layout da splash screen (opcional)
        setContentView(R.layout.activity_splash_view);

        // Simula a obtenção dos dados da API ou outras inicializações. Neste ponto, você pode realizar
        // chamadas à API ou executar outras tarefas de inicialização necessárias.

        // Após o tempo de exibição da splash screen, navegue para a próxima Activity.
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashView.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finalize a SplashView para que o usuário não possa voltar para ela pressionando o botão "Back".
        }, SPLASH_DELAY_MS);
    }
}
