package sk.upjs.ics.swi2;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import ui.view.bank.mainBankView;
import ui.view.user.mainUserView;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class mainUI extends UI {
private Navigator navigator;
    
    public static final String LOGINVIEW = "LoginView";
    public static final String MAINBANKVIEW = "mainBankView";
    public static final String MAINUSERVIEW = "mainUserView";
    public static final String REGVIEW = "RegistrationView";
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        Navigator.ComponentContainerViewDisplay viewDisplay = new Navigator.ComponentContainerViewDisplay(layout);
		navigator = new Navigator(UI.getCurrent(), viewDisplay);
                navigator.addView("", new LoginView());
                navigator.addView(LOGINVIEW, new LoginView());
		navigator.addView(MAINBANKVIEW, new mainBankView());
                navigator.addView(MAINUSERVIEW, new mainUserView());
                navigator.addView(REGVIEW, new RegisterView());
        
       
                layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);        
        
    }

    @WebServlet(urlPatterns = "/*", name = "mainUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = mainUI.class, productionMode = false)
    public static class mainUIServlet extends VaadinServlet {
    }
}
