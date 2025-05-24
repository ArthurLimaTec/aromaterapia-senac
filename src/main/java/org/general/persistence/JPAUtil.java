//package org.general.persistence;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//
///**
// * Classe de utilidades do JPA.
// * @author Arthur Lima
// * @version 1.0
// */
//public class JPAUtil {
//
//    private static final String PERSISTENCE_UNIT = "projeto-integrador";
//
//    private static EntityManager em;
//    private static EntityManagerFactory fabrica;
//
//    /**
//     * Método para cria a entidade se estiver nula.
//     * @return EntityManager.
//     */
//    public static EntityManager getEntityManager() {
//        if (fabrica == null || !fabrica.isOpen())
//            fabrica = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
//
//        if (em == null || !em.isOpen()) //cria se em nulo ou se o entity manager foi fechado
//            em = fabrica.createEntityManager();
//
//        return em;
//    }
//
//    /**
//     * Método para fechar o EntityManager e o factory.
//     */
//    public static void closeEtityManager() {
//        if (em.isOpen() && em != null) {
//            em.close();
//            fabrica.close();
//        }
//    }
//}