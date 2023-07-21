package Model;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.desafiomobile.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import Model.User;

public class UserFragment extends Fragment {

    private static final String TAG = "UserFragment";
    private ImageView userImageView;
    private View rootView;

    public UserFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user, container, false);
        Button btnLoadData = rootView.findViewById(R.id.getDataButton);
        userImageView = rootView.findViewById(R.id.userImageView);

        btnLoadData.setOnClickListener(v -> fetchDataFromAPI());

        return rootView;
    }

    private void fetchDataFromAPI() {
        new Thread(() -> {
            try {
                URL url = new URL("https://randomuser.me/api/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                    bufferedReader.close();
                    inputStream.close();

                    // Parse the JSON response
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONObject resultsObject = jsonObject.getJSONArray("results").getJSONObject(0);
                    User user = User.fromJsonObject(resultsObject);

                    // Update UI with user data
                    rootView.post(() -> {
                        updateUI(user);
                    });
                } else {
                    Log.e(TAG, "fetchDataFromAPI: HTTP error code: " + responseCode);
                }
            } catch (IOException | JSONException e) {
                Log.e(TAG, "fetchDataFromAPI: Error fetching data from API", e);
                rootView.post(() -> {
                    Toast.makeText(getActivity(), "Erro ao obter dados do usuário", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

    private void updateUI(User user) {

        // Formata a data de nascimento no formato desejado (dia/mês/ano)
        String formattedBirthday = formatDateOfBirth(user.getDateOfBirth().getDate());

        // Formata o telefone no formato desejado (xxx) xxx-xxxx
        String formattedPhone = formatPhoneNumber(user.getPhone());

        // Atualiza os TextViews com os dados do usuário
        TextView nameTextView = rootView.findViewById(R.id.nameTextView);
        nameTextView.setText(user.getName().getFullName());

        TextView emailTextView = rootView.findViewById(R.id.emailTextView);
        emailTextView.setText(user.getEmail());

        TextView locationTextView = rootView.findViewById(R.id.adressTextView);
        locationTextView.setText(user.getLocation().getFullAddress());

        TextView birthdayTextView = rootView.findViewById(R.id.birthdayTextView);
        birthdayTextView.setText(formattedBirthday);

        TextView phoneTextView = rootView.findViewById(R.id.phoneTextView);
        phoneTextView.setText(formattedPhone);

        TextView passwordTextView = rootView.findViewById(R.id.passwordTextView);
        passwordTextView.setText(user.getLogin().getPassword());

        // Carrega a imagem do usuário usando Glide
        String imageUrl = user.getPicture().getLarge();
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop();

        Glide.with(this)
                .load(imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.perfil) // Imagem de placeholder enquanto carrega
                .into(userImageView);

    }

    private String formatDateOfBirth(String rawDate) {
        try {
            // Primeiro, formate a data para incluir o horário e a zona
            DateTimeFormatter formatterWithTimezone = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(rawDate, formatterWithTimezone);

            // Em seguida, converta para LocalDate para obter apenas a data
            LocalDate date = zonedDateTime.toLocalDate();

            // Formate a data no formato desejado (dia/mês/ano)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return formatter.format(date);
        } catch (Exception e) {
            Log.e(TAG, "formatDateOfBirth: Error parsing date", e);
            return rawDate; // Em caso de erro, retorna a data não formatada
        }
    }

    private String formatPhoneNumber(String rawPhone) {
        try {
            // Remova todos os caracteres não numéricos do telefone
            String digitsOnly = rawPhone.replaceAll("[^0-9]", "");

            // Verifique se o telefone tem o formato correto
            if (digitsOnly.length() == 10) {
                return "(" + digitsOnly.substring(0, 3) + ") " +
                        digitsOnly.substring(3, 6) + "-" + digitsOnly.substring(6);
            } else {
                // Se o telefone não tiver o formato esperado, retorne o telefone não formatado
                return rawPhone;
            }
        } catch (Exception e) {
            Log.e(TAG, "formatPhoneNumber: Error formatting phone number", e);
            return rawPhone; // Em caso de erro, retorne o telefone não formatado
        }
    }
}