import java.util.Scanner;

public class InitialisationJeu
{
	private int nbrJoueurs;
	public static final int NBR_JOUEURS_MAX = 4;
	public final static int NBR_CASES_PLATEAU = 24;
	private Joueur[] tabJoueur = new Joueur[NBR_JOUEURS_MAX];
	
	/**
	 * <b>InitialisationJeu</b>
	 * 
	 */
	public InitialisationJeu()
	{
		System.out.println("============================================================");
		System.out.println("========================= INIT JEU =========================");
		System.out.println("============================================================");
		System.out.println("                             --                             ");
		System.out.println("============================================================");
		System.out.println("===================== CREATION JOUEURS =====================");
		System.out.println("============================================================");
		System.out.println("                             --                             ");
		System.out.println("");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("** Combien de joueurs vont jouer ? [2-4] : ");
		try
		{
			this.nbrJoueurs = sc.nextInt();
		}
		catch(Exception e)
		{
			System.err.println("** [ERREUR] Veuillez entrer un nombre de joueurs valide !");
			System.exit(0); 
		}
		
		// puis on vide la ligne avant d'en lire une autre prochainement
		sc.nextLine();

			if(this.nbrJoueurs > 4)
			{
				System.err.println("Il ne peux pas y avoir plus de 4 joueurs !");
				System.exit(1);
			}
			
			System.out.println("                             --                             ");
			System.out.println("");

			for(int i = 0; i < this.nbrJoueurs; i++)
			{
				System.out.print("** Entrez le pseudo du joueur " + (i+1) + " : ");
				String nomJoueur = sc.nextLine();
				int idJoueur = i;
				this.tabJoueur[i] = new Joueur(nomJoueur, idJoueur);
				System.out.println("");
			}

			if(sc.nextLine() == null)
			{
				sc.close();
			}
			
			System.out.println("============================================================");
			System.out.println("=================== INIT PTS DES JOUEURS ===================");
			System.out.println("============================================================");
			System.out.println("                             --                             ");
			System.out.println("");
			
			for(int i = 0; i < this.nbrJoueurs; i++)
			{
				System.out.println("** " + this.tabJoueur[i].getNomJoueur() + " a " + this.tabJoueur[i].getPointsActionRestants() + " points d'actions.");
				System.out.println("");
			}

			Case[] plateau = new Case[NBR_CASES_PLATEAU];

			for (int i=0; i<24; i++)
			{
				plateau[i] = new Case(i, this.nbrJoueurs);
			}

			plateau[0].addCaseAdjacente(plateau[1], plateau[2], plateau[4], plateau[6]);
			plateau[1].addCaseAdjacente(plateau[0], plateau[3], plateau[5], plateau[7]);
			plateau[2].addCaseAdjacente(plateau[0], plateau[3], plateau[8], plateau[10]);
      		plateau[3].addCaseAdjacente(plateau[1], plateau[2], plateau[9], plateau[11]);
      
      		plateau[4].addCaseAdjacente(plateau[0], plateau[5], plateau[6], plateau[12], plateau[14]);
      		plateau[5].addCaseAdjacente(plateau[1], plateau[4], plateau[7], plateau[13], plateau[15]);
      		plateau[6].addCaseAdjacente(plateau[0], plateau[4], plateau[8], plateau[14], plateau[16]);
      		plateau[7].addCaseAdjacente(plateau[1], plateau[5], plateau[9], plateau[15], plateau[18]);
      		plateau[8].addCaseAdjacente(plateau[2], plateau[6], plateau[10], plateau[18], plateau[20]);
      		plateau[9].addCaseAdjacente(plateau[3], plateau[7], plateau[11], plateau[19], plateau[21]);
      		plateau[10].addCaseAdjacente(plateau[2], plateau[8], plateau[11], plateau[20], plateau[22]);
      		plateau[11].addCaseAdjacente(plateau[3], plateau[9], plateau[10], plateau[21], plateau[23]);

      		plateau[12].addCaseAdjacente(plateau[4], plateau[13], plateau[14]);
      		plateau[13].addCaseAdjacente(plateau[5], plateau[12], plateau[15]);
      		plateau[14].addCaseAdjacente(plateau[4], plateau[6], plateau[12], plateau[16]);
      		plateau[15].addCaseAdjacente(plateau[5], plateau[7], plateau[13], plateau[17]);
      		plateau[16].addCaseAdjacente(plateau[6], plateau[14], plateau[18]);
      		plateau[17].addCaseAdjacente(plateau[7], plateau[15], plateau[19]);
      		plateau[18].addCaseAdjacente(plateau[8], plateau[16], plateau[20]);
      		plateau[19].addCaseAdjacente(plateau[9], plateau[17], plateau[21]);
			plateau[20].addCaseAdjacente(plateau[8], plateau[10], plateau[18], plateau[22]);
			plateau[21].addCaseAdjacente(plateau[9], plateau[11], plateau[19], plateau[23]);
			plateau[22].addCaseAdjacente(plateau[10], plateau[20], plateau[23]);
      		plateau[23].addCaseAdjacente(plateau[11], plateau[21], plateau[22]);
      
			new Jeu(this.nbrJoueurs, this.tabJoueur, plateau);
	}
}