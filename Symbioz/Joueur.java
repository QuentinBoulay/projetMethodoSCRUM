public class Joueur
{
	private String nomJoueur;
	private int idJoueur;
	
	private int nbPointsActionJoueurs = 10;
	private int cagnotte = 0;
	
	/**
	 *	Constructeur Joueur(int idJoueur).
	 *	
	 *	@param idJoueur :
	 *			L'id du joueur.
	 */
	public Joueur(int idJoueur)
	{
		this.idJoueur = idJoueur;
	}

	/**
	 *	Constructeur Joueur(String nomJoueur, int idJoueur).
	 *	
	 *	@param nomJoueur :
	 *			Le nom du joueur.
	 *	@param idJoueur :
	 *			L'id du joueur.
	 */
	public Joueur(String nomJoueur, int idJoueur)
	{
		this.nomJoueur = nomJoueur;
		this.idJoueur = idJoueur;
		
		System.out.println("** " + this.nomJoueur + "#" + this.idJoueur + " ok !");
	}
	
	/**
	 *	Cette méthode donne le pseudo du joueur. 
	 *	
	 *	@return 
	 *			le nom du joueur.
	 */
	public String getNomJoueur()
	{
		return this.nomJoueur;
	}
	
	/**
	 *	Cette méthode donne le pseudo du joueur. 
	 *	
	 *	@return 
	 *			le nom du joueur.
	 */
	public int getIdJoueur()
	{
		return this.idJoueur;
	}
	
	/**
	 *	Cette méthode permet de savoir combien de points d'action il reste au joueur. 
	 *	
	 *	@return
	 *			Les points d'action restants qu'ils restent pour un joueur.
	 */
	public int getPointsActionRestants()
	{
		return this.nbPointsActionJoueurs;
	}

	/**
	 *	Cette méthode donne met a jour la cagnotte. 
	 *	
	 *  @param ptsRestantsTour
	 * 			La cagnotte restant au joueur.
	 */
	public void setCagnotte(int ptsRestantsTour)
	{
		ptsRestantsTour /= 2;

		this.cagnotte = ptsRestantsTour;
	}

	/**
	 *	Cette méthode met la cagnotte dans les points du joueurs. 
	 *	
	 *
	 */
	public void ajoutCagnotte()
	{
		this.nbPointsActionJoueurs = cagnotte + 10;
		this.cagnotte = 0;
	}
	
	/**
	 *	Cette méthode permet d'ajouter un Zerb. 
	 * 
	 * 	@param nbrPions
	 * 			Le nombre de Zerb à ajouter.
	 */
	public void addZerb(int nbrPions)
	{
		System.out.println("** " + nbrPions + " 'Zerb' ajouté(s) !");
		this.nbPointsActionJoueurs -= Zerb.getValueOfZerb()*nbrPions; 
	}
	
	/**
	 *	Cette méthode permet d'ajouter un Crapit. 
	 * 
	 * 	@param nbrPions
	 * 			Le nombre de Crapit à ajouter.
	 */
	public void addCrapit(int nbrPions)
	{
		System.out.println("** " + nbrPions + " 'Crapit' ajouté(s) !");
		this.nbPointsActionJoueurs -= Crapit.getValueOfCrapit()*nbrPions; 
	}
	
	/**
	 *	Cette méthode permet d'ajouter un Krogul. 
	 * 
	 * 	@param nbrPions
	 * 			Le nombre de Krogul à ajouter.
	 */
	public void addKrogul(int nbrPions)
	{
		System.out.println("** " + nbrPions + " 'Krogul' ajouté(s) !");
		this.nbPointsActionJoueurs -= Krogul.getValueOfKrogul()*nbrPions; 
	}

}