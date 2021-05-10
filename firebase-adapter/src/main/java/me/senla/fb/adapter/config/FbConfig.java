package me.senla.fb.adapter.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Log4j2
@Configuration
public class FbConfig {

    public static final String APP_NAME = "app_name";
    public static final String OPTIONS_JSON = "/options.json";

    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(OPTIONS_JSON).getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        return getFirebaseMessaging(firebaseOptions);
    }

    private FirebaseMessaging getFirebaseMessaging(final FirebaseOptions firebaseOptions) {
        try {
            FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, APP_NAME);
            return FirebaseMessaging.getInstance(app);
        } catch (Exception e){
            log.error(String.format("You can't connect to FCM because the file [%s] not correct, pls go to https://firebase.google.com/ and create your own", OPTIONS_JSON));
            throw new Error("The application can't be started without a secret data for FCM");
        }
    }
}
