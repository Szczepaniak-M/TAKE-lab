package ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@JMSDestinationDefinition(name = "java:app/jms/NewsQueue", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "NewsQueue")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/jms/NewsQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class NewsMDB implements MessageListener {

    @PersistenceContext
    private EntityManager em;

    public NewsMDB() {
    }

//    @Override
//    public void onMessage(Message message) {
//        ObjectMessage msg = null;
//        try {
//            if (message instanceof ObjectMessage) {
//                msg = (ObjectMessage) message;
//                NewsItem e = (NewsItem) msg.getObject();
//                em.persist(e);
//            }
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                String content = msg.getText();
                String[] headingAndBody = content.split("\\|");
                NewsItem newsItem = new NewsItem();
                newsItem.setHeading(headingAndBody[0]);
                newsItem.setBody(headingAndBody[1]);
                em.persist(newsItem);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
