package form;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.User;
import dao.DAOException;
import dao.DAOUser;

public class InscriptionForm {

    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String CHAMP_CONF = "confirmation";
    private static final String CHAMP_SENDEMAIL = "emailSend";
    private static final String CHAMP_NOM = "nom";
    private static final String ALGO_CHIFFREMENT = "SHA-256";
    private String resultat;
    private Map<String, String> erreurs;

    public InscriptionForm()
    {
        erreurs = new HashMap<String, String>();
    }

    public String getResultat()
    {
        return resultat;
    }

    public Map<String, String> getErreurs()
    {
        return erreurs;
    }

    public User inscrireUtilisateur(HttpServletRequest request) throws IOException
    {
        String email = getValeurChamp(request, "email");
        String motDePasse = getValeurChamp(request, "mdp");
        String confirmation = getValeurChamp(request, "mdp");
        String nom = getValeurChamp(request, "pseudo");
        User utilisateur = new User();
        try
        {
            traiterNom(nom, utilisateur);
            traiterEmail(email, utilisateur);
            traiterMotsDePasse(motDePasse, confirmation, utilisateur);

                if (erreurs.isEmpty())
                {
                    System.out.println("on genere la clef...");
                    String key = generateKey();
                    utilisateur.setKey(key);
                    //traiterMail(email, key, nom);
                    if (erreurs.isEmpty())
                    {
                        System.out.println("on creer l'utilisateur...");
                        DAOUser.createUser(utilisateur);
                        if (erreurs.isEmpty())
                        {
                            resultat = "Succès de l'inscription";
                        }
                        else
                        {
                            resultat = "Echec de l'inscription1";
                        }
                    }
                    else
                    {
                        resultat = "Echec de l'inscription2";
                    }
                }
                else
                {
                	System.out.println("Echec 3");
                    resultat = "Echec de l'inscription3";
                }
        }
        catch (DAOException e)
        {
            resultat = "Echec de l'inscription : une erreur imprévue est survenue, merci de réessa" + "yer dans quelques instants.";
            e.printStackTrace();
        }
        return utilisateur;
    }



    private String generateKey()
    {
        String key = "";
        for (int i = 0; i < 20; i++)
        {
            key = (new StringBuilder(String.valueOf(key))).append(genereChar()).toString();
        }

        return key;
    }

    private char genereChar()
    {
        Random r = new Random();
        char c;
        do
        {
            int nmb = r.nextInt(75) + 48;
            c = (char) nmb;
        }
        while (c == '^' || c == '<' || c == '>' || c == '?' || c == '`' || c == '\'' || c == '\"' || c == '[' || c == ']' || c == '\\' || c == '@' || c == ':' || c == ';' || c == '=' || c =='_' || c =='-');
        return c;
    }

    private void traiterMail(String email, String key, String pseudo)
    {
        try
        {
            envoyerEmail(email, key, pseudo);
        }
        catch (FormValidationException e)
        {
            setErreur(CHAMP_SENDEMAIL, e.getMessage());
            e.printStackTrace();
        }
    }

    private void traiterNom(String nom, User utilisateur)
    {
        try
        {
            validationNom(nom);
        }
        catch (FormValidationException e)
        {
            setErreur(CHAMP_NOM, e.getMessage());
        }
        utilisateur.setName(nom);
    }

    private void traiterEmail(String email, User utilisateur)
    {
        try
        {
            validationEmail(email);
        }
        catch (FormValidationException e)
        {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        utilisateur.setEmail(email);
    }

    private void traiterMotsDePasse(String motDePasse, String confirmation, User utilisateur)
    {
        try
        {
            validationMotsDePasse(motDePasse, confirmation);
        }
        catch (FormValidationException e)
        {
            setErreur(CHAMP_PASS, e.getMessage());
            setErreur(CHAMP_CONF, null);
        }
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
        passwordEncryptor.setPlainDigest(false);
        String motDePasseChiffre = passwordEncryptor.encryptPassword(motDePasse);
        utilisateur.setPwd(motDePasseChiffre);
    }

    private void validationEmail(String email) throws FormValidationException
    {
        if (email != null)
        {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)"))
            {
                throw new FormValidationException("Merci de saisir une adresse mail valide.");
            }
            if (DAOUser.getUserByEmail(email) != null)
            {
                throw new FormValidationException("Cette adresse email est déjà utilisée, merci d'en choisir une autre.");
            }
            else
            {
                return;
            }
        }
        else
        {
            throw new FormValidationException("Merci de saisir une adresse mail.");
        }
    }

    private void validationMotsDePasse(String motDePasse, String confirmation) throws FormValidationException
    {
        if (motDePasse != null && confirmation != null)
        {
            if (motDePasse.contains("'") || motDePasse.contains("\"") || motDePasse.contains(">") || motDePasse.contains("<") || motDePasse.contains(" "))
            {
                throw new FormValidationException("Le mot de passe ne doit pas contenir des caractères spéciaux");
            }
            if (confirmation.contains("'") || confirmation.contains("\"") || confirmation.contains(">") || confirmation.contains("<")
                    || confirmation.contains(" "))
            {
                throw new FormValidationException("La confirmation ne doit pas contenir des caractères spéciaux");
            }
            if (!motDePasse.equals(confirmation))
            {
                throw new FormValidationException("Les mots de passe entrés sont différents, merci de les saisir \340 nouveau" + ".");
            }
            if (motDePasse.length() < 3)
            {
                throw new FormValidationException("Les mots de passe doivent contenir au moins 3 caractères.");
            }
            else
            {
                return;
            }
        }
        else
        {
            throw new FormValidationException("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    private void validationNom(String nom) throws FormValidationException
    {
        if (nom == null || nom.length() < 3)
        {
            throw new FormValidationException("Le nom d'utilisateur doit contenir au moins 3 caractères.");
        }
        if (nom.contains("'") || nom.contains("\"") || nom.contains(">") || nom.contains("<") || nom.contains(" ") || nom.contains("&") || nom.contains("=")
                || nom.contains("/") || nom.contains("é") || nom.contains("è") || nom.equals(""))
        {
            throw new FormValidationException("Le nom d'utilisateur ne doit pas contenir des caractères spéciaux");
        }
        if (DAOUser.getUserByUsername(nom) != null)
        {
            throw new FormValidationException("Nom de compte déjà utilisé");
        }
        else
        {
            return;
        }
    }

    private void setErreur(String champ, String message)
    {
    	System.out.println(message);
        erreurs.put(champ, message);
    }

    private static String getValeurChamp(HttpServletRequest request, String nomChamp)
    {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0)
        {
            return null;
        }
        else
        {
            return valeur.trim();
        }
    }

    public static boolean envoyerEmail(String dest, String key, String pseudo) throws FormValidationException
    {
        boolean ok = false;
        try
        {
            Properties p = new Properties();
            FileInputStream fis = new FileInputStream(new File(InscriptionForm.class.getResource("MailConfig.properties").getPath()));
            p.load(fis);
            Email email = new SimpleEmail();
            String hostName = p.getProperty("hostname");
            String from = p.getProperty("from");
            String mdp = p.getProperty("motDePasse");
            email.setHostName(hostName);
            email.setAuthenticator(new DefaultAuthenticator(from, mdp));
            email.setFrom(from);
            email.setSubject("Validation d'inscription Alykraft");
            email.setMsg((new StringBuilder("Bienvenue sur notre Projet !\n Afin de valider votre inscription, merci de cliquer "
                    + "sur le lien ci-dessous.\nhttp://www.minestate.fr/valide?name=")).append(pseudo).append("&key=").append(key)
                    .append("\nA bient\364t sur minestate !").toString());
            email.addTo(dest);
            email.send();
            ok = true;
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            throw new FormValidationException("Erreur lors de l'envoie de l'email");
        }
        return ok;
    }

	public User inscrireUtilisateurAndroid(String nom, String email,
			String motDePasse, String confirmation) {
		User utilisateur = new User();
        try
        {
            traiterNom(nom, utilisateur);
            traiterEmail(email, utilisateur);
            traiterMotsDePasse(motDePasse, confirmation, utilisateur);

                if (erreurs.isEmpty())
                {
                    System.out.println("on genere la clef...");
                    String key = generateKey();
                    utilisateur.setKey(key);
                    //traiterMail(email, key, nom);
                    if (erreurs.isEmpty())
                    {
                        System.out.println("on creer l'utilisateur...");
                        DAOUser.createUser(utilisateur);
                        if (erreurs.isEmpty())
                        {
                            resultat = "Succès de l'inscription";
                        }
                        else
                        {
                            resultat = "Echec de l'inscription1";
                        }
                    }
                    else
                    {
                        resultat = "Echec de l'inscription2";
                    }
                }
                else
                {
                	System.out.println("Echec 3");
                    resultat = "Echec de l'inscription3";
                }
        }
        catch (DAOException e)
        {
            resultat = "Echec de l'inscription : une erreur imprévue est survenue, merci de réessa" + "yer dans quelques instants.";
            e.printStackTrace();
        }
        return utilisateur;
	}
}
