package pl.edu.pwr.fows.fows2017.contest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pl.edu.pwr.fows.fows2017.declarationInterface.AuthInterface;
import pl.edu.pwr.fows.fows2017.declarationInterface.DatabaseInterface;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.entity.ContestQuestion;
import pl.edu.pwr.fows.fows2017.gateway.ContestQuestionGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.11.2017.
 */

public class ContestClient implements ContestQuestionGateway {

    private String urlQuestion = "https://fows-2017.firebaseio.com/contest/question.json";
    private String urlVersion = "https://fows-2017.firebaseio.com/contest/version.json";
    private ContestProvider provider;

    public ContestClient(SharedPreferencesDataInterface sharedPreferences, DatabaseInterface databaseInterface, AuthInterface authInterface) {
        provider = new ContestProvider(urlQuestion, urlVersion, sharedPreferences, databaseInterface, authInterface);
    }

    @Override
    public List<ContestQuestion> getQuestions() {
        try {
            return provider.getQuestion();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean sendAnswers(HashMap<String, String> answers) {
        return provider.sendAnswers(answers);
    }

    @Override
    public boolean isComplete() {
        return provider.checkVersion();
    }
}
