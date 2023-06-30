import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VerificationVictoire
{
	public VerificationVictoire()
	{}

	public boolean[] getVerificationVictoire(int[] symbioz)
	{
		int n = 0;
		boolean[] victoire = new boolean[4];
		while (n < 4)
		{
			if (symbioz[n] == 3)
			{
				victoire[n] = true;
			}
			else
			{
				victoire[n] = false;
			}

			n++;
		}

		return victoire;
	}

	public int[] verificationVictoireTour(int[] symbioz, int tour)
	{
		int n = 0;
		int plusHautJoueur = 0;
		int plusHauteSymbioz = 0;
		int[] victoire = new int[4];
		int m = 0;

		while (m < 4)
		{
			victoire[m] = -1;
			m++;
		}
		m = 0;

		while (n < 4)
		{
			if (tour == 12)
			{
				if (symbioz[n] > plusHauteSymbioz)
				{
					plusHautJoueur = n;
					plusHauteSymbioz = symbioz[n];
					m = 0;
					while (m < 4)
					{
						victoire[m] = -1;
						m++;
					}
					m = 0;
					victoire[m] = n;
					m++;
				}
				else if (symbioz[n] == plusHauteSymbioz)
				{
					victoire[m] = n;
					m++;
				}
			}

			n++;
		}

		return victoire;
	}

	/*public static void main(String[] args)
	{
		int[] symbioz = new int[4];
		symbioz[0] = 1;
		symbioz[1] = 2;
		symbioz[2] = 2;
		symbioz[3] = 0;

		verificationVictoire ver = new verificationVictoire();

		boolean[] victoire = new boolean[4];
		victoire = ver.getVerificationVictoire(symbioz);
		System.out.println(victoire[0]);
		System.out.println(victoire[1]);
		System.out.println(victoire[2]);
		System.out.println(victoire[3]);

		int[] vict = new int[4];
		vict = ver.verificationVictoireTour(symbioz, 12);

		System.out.println(vict[0]);
		System.out.println(vict[1]);
		System.out.println(vict[2]);
		System.out.println(vict[3]);
	}*/
}