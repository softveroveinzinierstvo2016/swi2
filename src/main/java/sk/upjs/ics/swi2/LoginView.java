package sk.upjs.ics.swi2;

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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.vaadin.event.ShortcutAction.KeyCode;
import core.db.entity.User;
import core.db.impl.UserDaoImpl;
import core.db.ints.UserDao;


@SuppressWarnings("serial")
public class LoginView extends VerticalLayout implements View {

    static Logger logger = Logger.getLogger("Login");
    private TextField userNameTextFiled;
    private PasswordField passwordTextFiled;
    private UserDao userDao = new UserDaoImpl();
    private User user;
    
    public LoginView() {
        setSizeFull();
        setSpacing(true);

        userNameTextFiled = new TextField("Username");
        passwordTextFiled = new PasswordField("Password");

        addComponent(userNameTextFiled);
        addComponent(passwordTextFiled);
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponent(loginButton());
        buttons.addComponent(registerButton());
        buttons.setSpacing(true);
        addComponent(buttons);

        setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);
        setComponentAlignment(userNameTextFiled, Alignment.MIDDLE_CENTER);
        setComponentAlignment(passwordTextFiled, Alignment.MIDDLE_CENTER);

    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

    private Button registerButton() {
        Button button = new Button("Register", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {

                getUI().getNavigator().navigateTo(mainUI.REGVIEW);
            }
        });
        return button;
    }

    private Button loginButton() {
        Button button = new Button("Log In", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                StringBuilder warning = new StringBuilder();
                List<Boolean> checkAllFields = new ArrayList<>();
                Boolean checking = true;
                if (userNameTextFiled.isEmpty()) {
                    warning.append("User name text field is empty\n");
                    checkAllFields.add(false);
                } else if (userNameTextFiled.getValue().length() > 24) {
                    warning.append("Too long user name\n");
                    checkAllFields.add(false);
                }

                if (passwordTextFiled.isEmpty()) {
                    warning.append("Password name text field is empty\n");
                    checkAllFields.add(false);
                } else if (passwordTextFiled.getValue().length() > 24) {
                    warning.append("Too long password");
                    checkAllFields.add(false);
                }
                for (Boolean check : checkAllFields) {
                    if (!check) {
                        checking = false;
                    }
                }
                if (checking) {
                    user = userDao.getVerifedUser(userNameTextFiled.getValue(), passwordTextFiled.getValue());
                    if (user == null) {
                        Notification notification = new Notification("Incorrect password");
                        notification.show(Page.getCurrent());
                        notification.setDelayMsec(5000);
                        passwordTextFiled.clear();
                    } else if (user.getRole().equals("bank")) {
                        getUI().getSession().setAttribute("loggedUser", user);
                        logger.info("Bank logged");
                        getUI().getNavigator().navigateTo(mainUI.MAINBANKVIEW);
                    } else if (user.getRole().equals("user")) {
                        getUI().getSession().setAttribute("loggedUser", user);
                        logger.info("User logged");
                        getUI().getNavigator().navigateTo(mainUI.MAINUSERVIEW);
                    } 
                } else {
                    Notification notification = new Notification(warning.toString());
                    notification.show(Page.getCurrent());
                    notification.setDelayMsec(10000);
                }
                userNameTextFiled.clear();
                passwordTextFiled.clear();
            }
        });
        button.setClickShortcut(KeyCode.ENTER);
        return button;
    }

}
