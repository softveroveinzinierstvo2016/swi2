package core.utils.verify;

import core.db.impl.UserDaoImpl;
import core.db.ints.UserDao;
import core.utils.EmailUtils;
import core.utils.HashingUtils;

public class RegisterVerifycation {

    private UserDao userDao = new UserDaoImpl();
    private HashingUtils hashingUtils = new HashingUtils();
    private EmailUtils emailUtils = new EmailUtils();
        
    public String verifyAll(String name, String userName, String surname, String email, String password, String password2) {
        StringBuilder warningBuilder = new StringBuilder();
        if (!name.isEmpty()) {
        } else {
            warningBuilder.append("Field with name is empty\n");

            if (name.length() > 24) {
                warningBuilder.append("Too long name(max 24)\n");
            }
            if (!userName.isEmpty()) {
                if (userName.length() > 24) {
                    warningBuilder.append("Too long user name(max 24)\n");

                } else if (!userDao.checkUserName(userName)) {
                    warningBuilder.append("Field with user name is not unique. Please select other user name\n");
                }

            } else {
                warningBuilder.append("Field with user name is empty\n");
            }

            if (!surname.isEmpty()) {

            } else {
                warningBuilder.append("Field with surname is empty\n");
            }
            if (surname.length() > 44) {
                warningBuilder.append("Too long surname(max 44)\n");
            }

            if (!email.isEmpty() && emailUtils.validateMail(email)) {

            } else {
                warningBuilder.append("Field with email is incorrect\n");

            }
            if (email.length() > 24) {
                warningBuilder.append("Too long email(max 24)\n");

            }

            if (!password.isEmpty() && password.equals(password2)) {

                if (password.length() > 24) {
                    warningBuilder.append("Too long password(max 24)\n");
                }

            } else {
                warningBuilder.append("Password is incorrect\n");
            }
          

        }
        return warningBuilder.toString();
    }
}
