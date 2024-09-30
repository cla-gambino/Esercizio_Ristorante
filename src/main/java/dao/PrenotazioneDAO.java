package dao;

import model.Prenotazione;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

public class PrenotazioneDAO {

    public void savePrenotazione(Prenotazione prenotazione) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(prenotazione); // Usa persist invece di save
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
