package copper.entities;

import org.mindrot.jbcrypt.BCrypt;

public class SecurityUtils
{

    public static String hashText(String plainText)
    {
        return BCrypt.hashpw(plainText, BCrypt.gensalt());
    }

}
