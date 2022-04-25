package be.intec.utility;

import be.intec.models.Order;
import be.intec.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Component
public class MailConstructor {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private Environment env;

    public MimeMessagePreparator constructOrderConfirmationEmail(User user, Order order, Locale locale) {

        Context context =  new Context();
        context.setVariable("order",order);
        context.setVariable("user",user);
        context.setVariable("cartItemList",order.getCartItemList());
        String text = templateEngine.process("orderConformationEmailTemplate",context);

        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setTo(user.getEmail());
                email.setSubject("order Conformation - " + order.getId());
                email.setText(text,true);
                email.setFrom(new InternetAddress("sameerun.shaikh@gmail.com"));

            }
        };
        return messagePreparator;
    }
}