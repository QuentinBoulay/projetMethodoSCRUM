import java.util.Scanner;
import java.util.ArrayList;

public class Jeu
{
	private String nomJoueur;
	private int ptsRestants;
	private int caseChoisie;
	private int nbrTour = 0;
	private int nbrJoueurs = 0;
	private Joueur[] tabJoueur;
	private Case[] plateau;
	private VerificationVictoire victory = new VerificationVictoire();
	private boolean vainqueur[] = new boolean[4];
	private int vainqueurs[] = new int[4];
	private int symbioz[] = new int[4];
	
	/**
	 * <b>Jeu</b>
	 * 
	 */
	public Jeu(int nbrJoueurs, Joueur[] tabJoueur, Case[] plateau)
	{
		for(int i = 0; i < 4; i++)
		{
			symbioz[i] = 0;
		}

		this.nbrJoueurs = nbrJoueurs;
		this.tabJoueur = tabJoueur;
		this.plateau = plateau;
		System.out.println("============================================================");
		System.out.println("===================== LANCEMENT DU JEU =====================");
		System.out.println("============================================================");
		System.out.println("                             --                             ");
		System.out.println("");

		while(this.nbrTour < 12 /* || ou qu'un joueur a gagne */)
		{
			System.out.println("========================== PHASE1 ==========================");
			System.out.println("");

			for(int i = 0; i < nbrJoueurs; i++)
			{

				this.nomJoueur = tabJoueur[i].getNomJoueur();
				this.ptsRestants = tabJoueur[i].getPointsActionRestants();
				System.out.println("");
				System.out.println("** [RAPPEL], tu as  " + ptsRestants + "PA (points d'actions) ?chaque PHASE1.");
				System.out.println("** Ces points d'actions sont des points de placement.");
				System.out.println("");
				System.out.println("** Pour placer un Zerb il faut d?enser 1PA.");
				System.out.println("** Pour placer un Crapit il faut d?enser 3PA.");
				System.out.println("** Pour placer un Krogul il faut d?enser 5PA.");
				System.out.println("");

				int choix = 0;
				
				while(this.ptsRestants>0 && choix != 4)
				{
					System.out.println("                             --                             ");
					System.out.println("");
					System.out.println("** [JOUEUR]" + this.nomJoueur + ", il te reste " + this.ptsRestants + " points d'actions !");
					System.out.println("                             --                             ");
					System.out.println("");
					
					System.out.println("** [JOUEUR]" + this.nomJoueur + ", quelle unite voulez-vous ajouter ?");
					System.out.println("");
					if(ptsRestants>=1)
					{
						System.out.println("** Rentrez '1' pour placer un Zerb.");
					}
					if(ptsRestants>=3)
					{
						System.out.println("** Rentrez '2' pour placer un Crapit.");
					}
					if(ptsRestants>=5)
					{
						System.out.println("** Rentrez '3' pour placer un Krogul.");
					}

					System.out.println("** Rentrez '4' pour finir votre tour.");
					
					Scanner sc = new Scanner(System.in);
					System.out.print("** Choix : ");
					try
					{
						choix = sc.nextInt();
					}
					catch(Exception e)
					{
						System.err.println("** [ERREUR] Veuillez entrer un choix valide !");
						System.exit(0); 
					}

					if(sc.nextLine() == null)
					{
						sc.close();
					}

					if(choix == 1)
					{
						boolean placement_zerb_possible = false;

						while (placement_zerb_possible == false)
						{

							sc = new Scanner(System.in);

							System.out.print("** Combien de Zerb voulez-vous ? ");
							int nombreChoix = sc.nextInt();

							InfoPlateau();

							new DessinPlateau();
							System.out.println();

							System.out.print("** Case : ");
							try
							{	
								caseChoisie = sc.nextInt();
							}
							catch(Exception e)
							{
								System.err.println("** [ERREUR] Veuillez entrer un numero de case valide !");
								System.exit(0); 
							}
							
							while(caseChoisie < 1 || caseChoisie > 24 && plateau[caseChoisie].getSymbioz()==false )
							{
								System.out.println("Veuillez saisir un nom de case valide.");
								caseChoisie = sc.nextInt();
							}

							if(plateau[caseChoisie - 1].setZerbs(nombreChoix, tabJoueur[i].getIdJoueur()) == true)
							{
								ptsRestants-=1*nombreChoix;
								placement_zerb_possible = true;
								System.out.println("** [JOUEUR] " + this.nomJoueur + " place " + nombreChoix + " Zerb || sur CASE = " + caseChoisie);
									tabJoueur[i].addZerb(nombreChoix);
							}

						}

						if(sc.nextLine() == null)
						{
								sc.close();
						}
					} 
					else if(choix == 2)
					{
						sc = new Scanner(System.in);

						System.out.print("** Combien de Crapit voulez-vous ? ");
						int nombreChoix = sc.nextInt();

						InfoPlateau();

						new DessinPlateau();
						System.out.println();

						System.out.print("** Case : ");
						try
						{
							caseChoisie = sc.nextInt();
						}
						catch(Exception e)
						{
							System.err.println("** [ERREUR] Veuillez entrer un nombre d'unite valide !");
							System.exit(0); 
						}	
						while(caseChoisie < 1 || caseChoisie > 24)
						{
							System.out.println("Veuillez saisir un nom de case valide.");
							caseChoisie = sc.nextInt();
						}

						plateau[caseChoisie - 1].setCrapits(nombreChoix, tabJoueur[i].getIdJoueur());
						ptsRestants-=3*nombreChoix;
						System.out.println("** [JOUEUR]" + this.nomJoueur + " place" + nombreChoix + " Crapit || CASE = " + caseChoisie);
						tabJoueur[i].addCrapit(nombreChoix);
						System.out.println(plateau[caseChoisie - 1].getCrapits(tabJoueur[i].getIdJoueur()));


						if(sc.nextLine() == null)
						{
							sc.close();
						}
					} 
					else if(choix == 3)
					{
						sc = new Scanner(System.in);

						System.out.print("** Combien de Krogul voulez-vous ? ");
						int nombreChoix = sc.nextInt();

						InfoPlateau();

						new DessinPlateau();
						System.out.println();

						System.out.print("** Case : ");

						try
						{
							caseChoisie = sc.nextInt();
						}
						catch(Exception e)
						{
							System.err.println("** [ERREUR] Veuillez entrer un nombre d'unite valide !");
							System.exit(0); 
						}

						while(caseChoisie < 1 || caseChoisie > 24)
						{
							System.out.println("Veuillez saisir un nom de case valide.");
							caseChoisie = sc.nextInt();
						}

						plateau[caseChoisie - 1].setKroguls(nombreChoix, tabJoueur[i].getIdJoueur());
						ptsRestants-=5*nombreChoix;
						System.out.println("** [JOUEUR]" + this.nomJoueur + " place" + nombreChoix + " Krogul || CASE = " + caseChoisie);
						tabJoueur[i].addKrogul(nombreChoix);
						System.out.println(plateau[caseChoisie - 1].getKroguls(tabJoueur[i].getIdJoueur()));

						if(sc.nextLine() == null)
						{
							sc.close();
						}

					}

					else if(choix == 4)
					{
						System.out.println("** [JOUEUR]" + this.nomJoueur + " a mis fin a sa phase1 !");

						tabJoueur[i].setCagnotte(ptsRestants);
					}

					System.out.println("");
					this.ptsRestants = tabJoueur[i].getPointsActionRestants();
				}

				if(this.ptsRestants <= 0  || choix == 4)
				{
					System.out.println("");
					System.out.println("============================================================");
					System.out.println("** " + this.nomJoueur + " a termine la PHASE1 !");
					System.out.println("============================================================");
					System.out.println("                             --                             ");
					System.out.println("");
				}
			}

			System.out.println("============================================================");
			System.out.println("======================= PHASE1 BILAN =======================");
			System.out.println("============================================================");
			System.out.println("");
			
			for(int i = 0; i < nbrJoueurs; i++)
			{
				this.nomJoueur = tabJoueur[i].getNomJoueur();
				this.ptsRestants = tabJoueur[i].getPointsActionRestants();
				System.out.println("** " + nomJoueur + ", il te restait " + ptsRestants + " ! Tu as donc " + ptsRestants / 2 + " dans ta cagnotte!");
				System.out.println("                             --                             ");
				System.out.println("");
				tabJoueur[i].ajoutCagnotte();
			}
			
			System.out.println("========================== PHASE2 ==========================");
			System.out.println("");
			
			int nbzerb, choix, compteur;

			for(int i = 0; i < nbrJoueurs; i++)
			{
				this.nomJoueur = tabJoueur[i].getNomJoueur();

				for (int j = 0; j<4; j++) /* zone ou on a besoin de 3 zerbs pour reproduction */ 
				{
					int reproductionZ = 0;

					if (plateau[j].getSymbioz()==false)
					{
						nbzerb = plateau[j].getZerbs(i);
						if (plateau[j].getZerbs(i)>=3)
						{
							reproductionZ = 1;
							if (plateau[j].getZerbs(i)>=6)
							{
								reproductionZ = 2;
								if (plateau[j].getZerbs(i)>=9)
								{
									reproductionZ = 3;
									if (plateau[j].getZerbs(i)==12)
									{
										reproductionZ = 4;
									}
								}
							}

							new DessinPlateau();
							System.out.println();
							System.out.println(nomJoueur+", il te reste "+reproductionZ+" de Zerbs à placer !");
							System.out.println("                             --                             ");

							ArrayList<Case> casesAdjacentes = plateau[j].getCasesAdjacentes();
							int nombre_cases_disponibles = casesAdjacentes.size()+1;
							int cases_disponibles_id[] = new int[nombre_cases_disponibles];

							for (int k = 0 ; k < reproductionZ ;)
							{	
								System.out.println("Choisissez entre les cases ci-dessous :");
								compteur=0;
								for (Case c : casesAdjacentes)
								{
									cases_disponibles_id[compteur]= c.getId();
									System.out.println(c.getId() + 1);
									compteur++;
								}
								cases_disponibles_id[compteur]=plateau[j].getId();
								System.out.println(plateau[j].getId() + 1);

								System.out.println(" Où voulez-vous placer votre " + (k+1) + "e zerb ?");
								Scanner sc = new Scanner(System.in);
								System.out.print("** Choix : ");
								try
								{
									boolean case_valide = false;
									while (case_valide == false)
									{
										choix = sc.nextInt()-1;
										for(int a = 0; a < nombre_cases_disponibles; a++)
										{
											if(choix == cases_disponibles_id[a])
											{
												case_valide = true;
												if (plateau[choix].setZerbs(1,i))
												{											
													k++;
												}
											}
										}
									}
								}
								catch(Exception e)
								{
									System.err.println("** [ERREUR] Veuillez entrer une case valide !");
									System.exit(0); 
								}

							}
						}
					}
					
				}

				for (int j = 4; j<24; j++) /* zone ou on a besoin de 4 zerbs pour reproduction */ 
				{
					int reproductionZ = 0;

					if (plateau[j].getSymbioz()==false)
					{
						nbzerb = plateau[j].getZerbs(i);
						if (plateau[j].getZerbs(i)>=4)
						{
							reproductionZ = 1;
							if (plateau[j].getZerbs(i)>=8)
							{
								reproductionZ = 2;
								if (plateau[j].getZerbs(i)==12)
								{
									reproductionZ = 3;
								}
							}

							new DessinPlateau();
							System.out.println();
							System.out.println(nomJoueur+", il te reste "+reproductionZ+" de Zerbs à placer !");
							System.out.println("                             --                             ");

							ArrayList<Case> casesAdjacentes = plateau[j].getCasesAdjacentes();
							int nombre_cases_disponibles = casesAdjacentes.size()+1;
							int cases_disponibles_id[] = new int[nombre_cases_disponibles];

							for (int k = 0 ; k < reproductionZ ; k++ )
							{	
								System.out.println("Choisissez entre les cases ci-dessous :");
								compteur=0;
								for (Case c : casesAdjacentes)
								{
									cases_disponibles_id[compteur]= c.getId();
									System.out.println(c.getId() + 1);
									compteur++;
								}
								cases_disponibles_id[compteur]=plateau[j].getId();
								System.out.println(plateau[j].getId() + 1);

								System.out.println(" Où voulez-vous placer votre " + (k+1) + "e zerb ?");
								Scanner sc = new Scanner(System.in);
								System.out.print("** Choix : ");
								try
								{
									boolean case_valide = false;
									while (case_valide == false)
									{
										choix = sc.nextInt()-1;
										for(int a = 0; a < nombre_cases_disponibles; a++)
										{
											if(choix == cases_disponibles_id[a])
											{
												case_valide = true;
												plateau[choix].setZerbs(1,i);
											}
										}
									}
								}
								catch(Exception e)
								{
									System.err.println("** [ERREUR] Veuillez entrer une case valide !");
									System.exit(0); 
								}

							}
						}
					}
				}
			}

			System.out.println("========================== PHASE3 ==========================");
			System.out.println("");

			int[] nbCrapit = new int[nbrJoueurs];


			for(int i = 0; i < nbrJoueurs; i++)
			{
				this.nomJoueur = tabJoueur[i].getNomJoueur();

				for (int j = 0; j<24; j++)
				{
					int reproductionK = 0;

					if (plateau[j].getSymbioz()==false)
					{
						if (plateau[j].getKroguls(i) != 0)
						{
							for(int k = 0; k < nbrJoueurs; k++)
							{
								nbCrapit[k] = plateau[j].getCrapits(k);

								System.out.println(nbCrapit[k]);




							}


									/*

								



								new DessinPlateau();
								System.out.println();
								System.out.println(nomJoueur+", il te reste "+reproductionK+" Krogul à placer !");
								System.out.println("                             --                             ");

								ArrayList<Case> casesAdjacentes = plateau[j].getCasesAdjacentes();
								int nombre_cases_disponibles = casesAdjacentes.size();
								int cases_disponibles_id[] = new int[nombre_cases_disponibles];

								for (int k = 0 ; k < reproductionK ; k++ )
								{	
									System.out.println("Choisissez entre les cases ci-dessous :");
									compteur = 0;
									for (Case c : casesAdjacentes)
									{
										cases_disponibles_id[compteur]= c.getId();
										System.out.println(c.getId() + 1);
										compteur++;
									}

									System.out.println(" Où voulez-vous placer votre " + (k+1) + "e zerb ?");
									Scanner sc = new Scanner(System.in);
									System.out.print("** Choix : ");
									try
									{
										boolean case_valide = false;
										while (case_valide == false)
										{
											choix = sc.nextInt()-1;
											for(int a = 0; a < nombre_cases_disponibles; a++)
											{
												if(choix == cases_disponibles_id[a])
												{
													case_valide = true;
												}
											}
										}
									}
									catch(Exception e)
									{
										System.err.println("** [ERREUR] Veuillez entrer une case valide !");
										System.exit(0); 
									}

								}*/
						}
					}
				}
			}


			
			System.out.println("========================== PHASE4 ==========================");
			System.out.println("");
			
			/* Vérification symbioz dans les cases */
			for(int i = 0; i < nbrJoueurs; i++)
			{
				for (int j = 0; j<24; j++)
				{
					if (plateau[j].getSymbioz()==false)
					{
						nbzerb = plateau[j].getZerbs(i);
						if (nbzerb==12)
						{
							plateau[j].setSymbioz();
							symbioz[i] += 1;
						}
					}
				}
			}

			for(int i = 0; i < 24; i++)
			{
				System.out.println(plateau[i].getId() + "" + plateau[i].getSymbioz());
			}
			for(int i = 0; i < 4; i++)
			{
				System.out.println("Joueur :" + (i+1) + " " + symbioz[i]);
			}

			vainqueur = victory.getVerificationVictoire(symbioz);
			int vict = 0;

			for (int i = 0 ; i < 4 ; i++)
			{
				if (vainqueur[i] == true && vict == 0)
				{
					System.out.println("========================== Fin Du Jeu ==========================");
					System.out.println("");

					System.out.print("Le joueur " + (i+1));
					vict = 1;
				}
				else if (vainqueur[i] == true && vict == 1)
				{
					System.out.print(" et " + (i+1));
				}
			}

			if (vict == 1)
			{
				break;
			}

			this.nbrTour++;
		}

		if (nbrTour == 12)
		{
			vainqueurs = victory.verificationVictoireTour(symbioz,nbrTour);
			int vict = 0;
			for (int i = 0 ; i < 4 ; i++)
			{
				if (vainqueurs[i] != -1 && vict == 0)
				{
					System.out.print("Le joueur " + (vainqueurs[i]+1));
					vict = 1;
				}
				else if (vainqueurs[i] != -1 && vict == 1)
				{
					System.out.print(" et " + (vainqueurs[i]+1));
				}
			}
		}
		
		System.out.println(" gagne!!");
		System.out.println("Merci d'avoir joué!!");
	}

	public void InfoPlateau()
	{
		System.out.println("Info :");

		for (int count = 0; count < this.nbrJoueurs; count++) {
			System.out.println("Information concernant le joueur :" + this.tabJoueur[count].getNomJoueur());
			for (int j = 0; j<24; j++) {
				if(this.plateau[j].getZerbs(count) != 0)
				{
					System.out.println("Nombre Zerbs = " + this.plateau[j].getZerbs(count) + " sur la case : " + (j+1));
				}
			}

			for (int j = 0; j<24; j++) {
				if(this.plateau[j].getCrapits(count) != 0)
				{
					System.out.println("Nombre Crapits = " + this.plateau[j].getCrapits(count) + " sur la case : " + (j+1));
				}

			}
			for (int j = 0; j<24; j++) {
				if(this.plateau[j].getKroguls(count) != 0)
				{
					System.out.println("Nombre Kroguls = " + this.plateau[j].getKroguls(count) + " sur la case : " + (j+1));
				}
			}
		}
	}
}
