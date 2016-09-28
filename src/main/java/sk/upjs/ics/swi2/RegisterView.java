package sk.upjs.ics.swi2;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import core.db.entity.User;
import core.db.impl.UserDaoImpl;
import core.db.ints.UserDao;
import core.utils.HashingUtils;
import core.utils.EmailUtils;
import core.utils.verify.RegisterVerifycation;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class RegisterView extends VerticalLayout implements View {

    private static Logger logger = Logger.getLogger("Register");
    private TextField nameTextField;
    private TextField userNameTextField;
    private TextField surnameTextField;
    private TextField emailTextField;
    private PasswordField passwordTextField;
    private PasswordField password2TextField;
    private UserDao userDao = new UserDaoImpl();
    private HashingUtils hashingUtils = new HashingUtils();
    private EmailUtils emailUtils = new EmailUtils();
    private RegisterVerifycation verify = new RegisterVerifycation();

    public RegisterView() {

        nameTextField = new TextField("Name");
        userNameTextField = new TextField("User name");
        surnameTextField = new TextField("Surname");
        emailTextField = new TextField("Email");
        passwordTextField = new PasswordField("Password");
        password2TextField = new PasswordField("Verify password");
        addComponent(nameTextField);
        addComponent(userNameTextField);
        addComponent(surnameTextField);
        addComponent(emailTextField);
        addComponent(passwordTextField);
        addComponent(password2TextField);
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponent(registerButton());
        buttons.setSpacing(true);
        addComponent(buttons);

        setComponentAlignment(nameTextField, Alignment.MIDDLE_CENTER);
        setComponentAlignment(userNameTextField, Alignment.MIDDLE_CENTER);
        setComponentAlignment(surnameTextField, Alignment.MIDDLE_CENTER);
        setComponentAlignment(emailTextField, Alignment.MIDDLE_CENTER);
        setComponentAlignment(passwordTextField, Alignment.MIDDLE_CENTER);
        setComponentAlignment(password2TextField, Alignment.MIDDLE_CENTER);
        setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

    private Button registerButton() {

        Button button = new Button("Register", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                User user = new User();
                String check = verify.verifyAll(nameTextField.getValue(),
                        userNameTextField.getValue(),
                        surnameTextField.getValue(),
                        emailTextField.getValue(),
                        passwordTextField.getValue(),
                        password2TextField.getValue());
                if (check.isEmpty()) {
                    user.setName(nameTextField.getValue());
                    user.setUserName(userNameTextField.getValue());
                    user.setSurname(surnameTextField.getValue());
                    user.setEmail(emailTextField.getValue());
                    try {
                        user.setPassword(hashingUtils.hash(passwordTextField.getValue()));
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(RegisterView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    user.setRole("user");
                    userDao.addUser(user);
                    nameTextField.clear();
                    surnameTextField.clear();
                    emailTextField.clear();
                    passwordTextField.clear();
                    password2TextField.clear();
                    getUI().getNavigator().navigateTo(mainUI.LOGINVIEW);

                } else {
                    Notification notification = new Notification(check);
                    notification.setDelayMsec(10000);
                    notification.show(Page.getCurrent());
                }

            }
        });
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        return button;
    }
}
