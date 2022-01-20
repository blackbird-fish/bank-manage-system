package task10.LanguageSource;

import java.util.Locale;
import java.util.ResourceBundle;
public class Language {
    private Locale locale;
    ResourceBundle rb;
    public Language(int choice) {
        switch (choice) {
            case 0:
                this.locale = new Locale("zh_CN");
                this.rb = ResourceBundle.getBundle("Message",locale);
                break;
            case 1:
                this.locale = new Locale("en_US");
                this.rb = ResourceBundle.getBundle("Message",locale);
                break;
        }
    }
    public String getString(String key) {
        return rb.getString(key);
    }
    
}
