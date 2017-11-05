package pl.edu.pwr.fows.fows2017;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.entity.Lecture;
import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.entity.OrganiserTeam;
import pl.edu.pwr.fows.fows2017.entity.Organizer;
import pl.edu.pwr.fows.fows2017.entity.Question;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.entity.User;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;
import pl.edu.pwr.fows.fows2017.gateway.FirebaseTokenGateway;
import pl.edu.pwr.fows.fows2017.gateway.LectureGateway;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;
import pl.edu.pwr.fows.fows2017.gateway.OfferUrlGateway;
import pl.edu.pwr.fows.fows2017.gateway.OrganiserGateway;
import pl.edu.pwr.fows.fows2017.gateway.OrganizerGateway;
import pl.edu.pwr.fows.fows2017.gateway.QuestionGateway;
import pl.edu.pwr.fows.fows2017.gateway.QuestionnaireVersionGateway;
import pl.edu.pwr.fows.fows2017.gateway.SponsorGateway;
import pl.edu.pwr.fows.fows2017.gateway.UserGateway;
import pl.edu.pwr.fows.fows2017.usecase.AddUserAndLoginUseCase;
import pl.edu.pwr.fows.fows2017.usecase.CheckQuestionnaireVersionUseCase;
import pl.edu.pwr.fows.fows2017.usecase.FacebookPostsUseCase;
import pl.edu.pwr.fows.fows2017.usecase.GetUserUseCase;
import pl.edu.pwr.fows.fows2017.usecase.LecturesUseCase;
import pl.edu.pwr.fows.fows2017.usecase.MenuUseCase;
import pl.edu.pwr.fows.fows2017.usecase.MenuWithTagUseCase;
import pl.edu.pwr.fows.fows2017.usecase.OfferUrlUseCase;
import pl.edu.pwr.fows.fows2017.usecase.OrganisersUseCase;
import pl.edu.pwr.fows.fows2017.usecase.OrganizersUseCase;
import pl.edu.pwr.fows.fows2017.usecase.QuestionnaireUseCase;
import pl.edu.pwr.fows.fows2017.usecase.SendFirebaseTokenUseCase;
import pl.edu.pwr.fows.fows2017.usecase.SendQuestionnaireUseCase;
import pl.edu.pwr.fows.fows2017.usecase.SponsorUseCase;
import pl.edu.pwr.fows.fows2017.usecase.base.UseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

public class UseCaseFactory {

    private final MenuGateway menuGateway;
    private final FowsRxTransformerProvider rxTransformer;
    private final FacebookPostGateway facebookPostGateway;
    private final FacebookPostGateway facebookPostGatewaySharedPref;
    private final SponsorGateway sponsorGateway;
    private final LectureGateway lectureGateway;
    private final LectureGateway lectureGatewaySharedPref;
    private final OrganizerGateway organizerGateway;
    private final OrganiserGateway organiserGateway;
    private final OfferUrlGateway offerUrlGateway;
    private final QuestionGateway questionGateway;
    private final QuestionnaireVersionGateway questionnaireVersionGateway;
    private final QuestionnaireVersionGateway questionnaireVersionGatewaySharedPref;
    private final FirebaseTokenGateway firebaseTokenGateway;
    private final UserGateway userGateway;

    @Inject
    public UseCaseFactory(FowsRxTransformerProvider rxTransformer, MenuGateway menuGateway,
                          @Named("NetworkGateway") FacebookPostGateway facebookPostGateway,
                          @Named("LocalGateway") FacebookPostGateway facebookPostGatewaySharedPref,
                          @Named("NetworkGateway") SponsorGateway sponsorGateway,
                          @Named("NetworkGateway") LectureGateway lectureGateway,
                          @Named("LocalGateway") LectureGateway lectureGatewaySharedPref,
                          OrganizerGateway organizerGateway,
                          OrganiserGateway organiserGateway,
                          OfferUrlGateway offerUrlGateway,
                          QuestionGateway questionGateway,
                          @Named("NetworkGateway") QuestionnaireVersionGateway questionnaireVersionGateway,
                          @Named("LocalGateway") QuestionnaireVersionGateway questionnaireVersionGatewaySharedPref,
                          FirebaseTokenGateway firebaseTokenGateway,
                          UserGateway userGateway){
        this.menuGateway = menuGateway;
        this.rxTransformer = rxTransformer;
        this.facebookPostGateway = facebookPostGateway;
        this.facebookPostGatewaySharedPref = facebookPostGatewaySharedPref;
        this.sponsorGateway = sponsorGateway;
        this.lectureGateway = lectureGateway;
        this.lectureGatewaySharedPref = lectureGatewaySharedPref;
        this.organizerGateway = organizerGateway;
        this.organiserGateway = organiserGateway;
        this.offerUrlGateway = offerUrlGateway;
        this.questionGateway = questionGateway;
        this.questionnaireVersionGateway = questionnaireVersionGateway;
        this.questionnaireVersionGatewaySharedPref = questionnaireVersionGatewaySharedPref;
        this.firebaseTokenGateway = firebaseTokenGateway;
        this.userGateway = userGateway;
    }

    public UseCase<Observable<List<Menu>>> getMenuUseCase(){
        return new MenuUseCase(rxTransformer, menuGateway);
    }

    public UseCase<Observable<Menu>> isMenuWithTag(String tag){
        return new MenuWithTagUseCase(rxTransformer, menuGateway, tag);
    }

    public UseCase<Observable<List<FacebookPost>>> getFacebookPosts(){
        return new FacebookPostsUseCase(rxTransformer, facebookPostGateway);
    }

    public UseCase<Observable<List<FacebookPost>>> getFacebookPostsSharedPref(){
        return new FacebookPostsUseCase(rxTransformer, facebookPostGatewaySharedPref);
    }

    public UseCase<Observable<List<List<Sponsor>>>> getSponsors(){
        return new SponsorUseCase(rxTransformer, sponsorGateway);
    }

    public  UseCase<Observable<List<Lecture>>> getLectures(){
        return new LecturesUseCase(rxTransformer, lectureGateway);
    }

    public UseCase<Observable<List<Lecture>>> getLecturesSharedPref(){
        return new LecturesUseCase(rxTransformer, lectureGatewaySharedPref);
    }

    public UseCase<Single<List<Organizer>>> getOrganizers(){
        return new OrganizersUseCase(rxTransformer, organizerGateway);
    }
    public UseCase<Observable<List<OrganiserTeam>>> getOrganisers(){
        return new OrganisersUseCase(rxTransformer, organiserGateway);
    }

    public UseCase<Single<String>> getOfferUrl(Locale locale){
        return new OfferUrlUseCase(rxTransformer, offerUrlGateway, locale);
    }

    public UseCase<Observable<List<Question>>> getQuestionnaire(){
        return new QuestionnaireUseCase(rxTransformer, questionGateway);
    }

    public UseCase<Observable<Boolean>> isQuestionnaireDone(){
        return new CheckQuestionnaireVersionUseCase(rxTransformer, questionnaireVersionGateway,
                questionnaireVersionGatewaySharedPref);
    }

    public UseCase<Observable<Integer>> sendQuestionnaire(List<Question> questionnaire){
        return new SendQuestionnaireUseCase(rxTransformer, questionGateway, questionnaire);
    }

    public UseCase<Observable<Boolean>> sendFirebaseToken(String token, String language){
        return new SendFirebaseTokenUseCase(rxTransformer, firebaseTokenGateway, token, language);
    }

    public UseCase<Observable<Boolean>> addUserAndLogin(String email, String password, String displayName){
        return new AddUserAndLoginUseCase(rxTransformer,userGateway,email, password, displayName);
    }

    public UseCase<Observable<User>> getUser(){
        return new GetUserUseCase(rxTransformer, userGateway);
    }
}
