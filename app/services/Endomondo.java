package services;

import scala.NotImplementedError;

import static play.libs.F.Promise;

/**
 * Bibliothèque utilitaire pour effectuer des requêtes HTTP sur votre Web service Endomondo.
 *
 * Votre code ne devrait pas être bloquant si vous souhaitez que votre application supporte une importante
 * montée en charge.
 */
public class Endomondo {

    /**
     * (Exemple de méthode à implémenter)
     *
     * Appelle le Web service Endomondo pour savoir si un couple (nom, mot de passe) identifie effectivement un utilisateur existant.
     *
     * @param name Nom de l’utilisateur
     * @param password Mot de passe
     * @return Une promesse de booléen qui vaudra `true` ssi l’utilisateur a pu être identifié
     */
    public Promise<Boolean> authenticate(String name, String password) {
        throw new NotImplementedError("TODO");
    }

}
