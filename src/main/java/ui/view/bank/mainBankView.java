package ui.view.bank;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import core.db.entity.User;
import core.db.impl.UserDaoImpl;
import core.db.ints.UserDao;
import sk.upjs.ics.swi2.mainUI;
import com.vaadin.server.Page;
import com.vaadin.ui.MenuBar;


public class mainBankView extends VerticalLayout implements View {

    private User loggedUser;
    private MenuBar barmenu;
    private UserDao userDao = new UserDaoImpl();

    public mainBankView() {
        setSizeFull();

        barmenu = new MenuBar();
        barmenu.setWidth("100%");
        barmenu.setSizeFull();
        barmenu.setStyleName("menuRight2");

        addComponent(barmenu);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        loggedUser = (User) getUI().getSession().getAttribute("loggedUser");
        if (loggedUser != null && loggedUser.getRole().equals("bank")) {
            barmenu.removeItems();
            MenuBar.MenuItem userMenu = barmenu.addItem(loggedUser.getUserName(), null, null);
            userMenu.setStyleName("menuRight");
            MenuBar.MenuItem logout = userMenu.addItem("Logout", null, mycommand);

        } else {
            Notification notification = new Notification("You are not logged");
            notification.show(Page.getCurrent());
            notification.setDelayMsec(3000);
            getUI().getNavigator().navigateTo(mainUI.LOGINVIEW);
        }

    }
    
    MenuBar.Command mycommand = new MenuBar.Command() {
        public void menuSelected(MenuBar.MenuItem selectedItem) {
            getUI().getSession().close();
            Notification notification = new Notification("Bye");
            notification.show(Page.getCurrent());
            notification.setDelayMsec(3000);
            getUI().getNavigator().navigateTo(mainUI.LOGINVIEW);
        }
    };
}
